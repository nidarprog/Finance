package com.finance.service

import com.finance.model.Account

class AccountService {
    fun getAllAccounts(): List<Account>{
        return listOf(
            Account(1, "Checking", 1000.54),
            Account(2,"Saving", 350.0)
        )
    }
}