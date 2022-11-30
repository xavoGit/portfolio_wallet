package com.xavo.finance.wallet.bank;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavo.finance.wallet.entities.Bank;

public interface BankRepository  extends JpaRepository<Bank, Integer>{

}
