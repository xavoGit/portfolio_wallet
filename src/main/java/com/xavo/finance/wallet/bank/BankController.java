package com.xavo.finance.wallet.bank;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavo.finance.wallet.entities.Bank;

@Slf4j
@RestController
@RequestMapping("api/v1/banks")
public record BankController(BankService bankService) {

	@PostMapping
	public void registerCustomer(@RequestBody BankRegistrationRequest accountRegistrationRequest) {
		log.info("new bank registration {}", accountRegistrationRequest);
		bankService.registerAccount(accountRegistrationRequest);
	}

	@GetMapping
	public ResponseEntity<List<Bank>> getAllBanks() {
		List<Bank> banks = new ArrayList<Bank>();

		bankService.getAllBanks().forEach(banks::add);
		if (banks.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(banks, HttpStatus.OK);
	}
}
