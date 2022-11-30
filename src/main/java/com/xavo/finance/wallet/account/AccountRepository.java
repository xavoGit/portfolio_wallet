package com.xavo.finance.wallet.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavo.finance.wallet.entities.Account;

public interface AccountRepository  extends JpaRepository<Account, Integer>{

}
