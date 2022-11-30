package com.xavo.finance.wallet.account;

import com.xavo.finance.wallet.entities.AccountType;
import com.xavo.finance.wallet.entities.Bank;

public record AccountRegistrationRequest(

	String name,
	Bank bank,
	AccountType type,
	String balance_on,
	Float balance) {
}