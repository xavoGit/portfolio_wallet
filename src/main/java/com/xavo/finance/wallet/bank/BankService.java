package com.xavo.finance.wallet.bank;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xavo.finance.wallet.entities.Bank;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankService {

	private BankRepository bankRepository;
	
	public void registerAccount(BankRegistrationRequest request) {
		Bank customer = Bank.builder()
				.name(request.name())
				.build();
		bankRepository.saveAndFlush(customer);
	}
	
	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

}
