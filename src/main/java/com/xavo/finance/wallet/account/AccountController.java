package com.xavo.finance.wallet.account;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavo.finance.wallet.entities.Account;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
public record AccountController(AccountService accountService) {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<Account> registerAccount(@RequestBody AccountRegistrationRequest accountRegistrationRequest) {
		ResponseEntity response;
		log.info("new account registration {}", accountRegistrationRequest);
		Account account = accountService.registerAccount(accountRegistrationRequest);
		if(account.getId()!=null) {
			response = new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		ResponseEntity response;

		accountService.getAllAccounts().forEach(accounts::add);
		if (accounts.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<>(accounts, HttpStatus.OK);
		}
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable("id") int id) {
		Optional<Account> account = accountService.findById(id);
		ResponseEntity response;

		if (account.isPresent()) {
			response = new ResponseEntity<>(account.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{id}/charges")
	public ResponseEntity<List<Object>> getAccountChargesById(@PathVariable("id") int id) {
		Optional<Account> account = accountService.findById(id);
		ResponseEntity response;

		if (account.isPresent()) {
			response = new ResponseEntity<>(accountService.getCharges(account.get()), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
