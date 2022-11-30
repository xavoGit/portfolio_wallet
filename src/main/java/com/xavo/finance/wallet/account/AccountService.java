package com.xavo.finance.wallet.account;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xavo.finance.common.Utility;
import com.xavo.finance.wallet.entities.Account;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

	private AccountRepository accountRepository;
	private final RestTemplate restTemplate;
	
	public Account registerAccount(AccountRegistrationRequest request) {
		Account account = Account.builder()
				.name(request.name())
				.bank(request.bank())
				.type(request.type())
				.balance(request.balance())
				.balance_on(Utility.parseDate(request.balance_on()))
				.build();
		accountRepository.saveAndFlush(account);
		return account;
	}
	
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
	
	public Optional<Account> findById(Integer id) {
		return accountRepository.findById(id);
	}

	public Object getCharges(Account account) {
		Object obj = restTemplate.getForObject(
				"http://TRANSACTIONS/api/v1/transactions/charges?accountId={accountId}",
				Object.class,
				account.getId());
		return obj;
	}

}
