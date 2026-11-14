package com.finance.service

import com.finance.model.Account

class AccountService {

    private val accounts = listOf(
            Account(1, "Checking", 1000.54),
            Account(2,"Saving", 350.0)
        )
    fun getAllAccounts(): List<Account>{
        return accounts
    }

    fun getAccountById(id: Int): Account?{
        return accounts.find { it.id == id }
    }
}