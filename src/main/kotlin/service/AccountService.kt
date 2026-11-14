package com.finance.service

import com.finance.model.Account
import com.finance.model.CreateAccountRequest

class AccountService {

    private val accounts = mutableListOf(
            Account(1, "Checking", 1000.54),
            Account(2,"Saving", 350.0)
        )
    private var nextId = 3

    fun getAllAccounts(): List<Account>{
        return accounts.toList()
    }

    fun getAccountById(id: Int): Account?{
        return accounts.find { it.id == id }
    }

    fun createAccount(request: CreateAccountRequest): Account {
        val account = Account(
            id = nextId++,
            name = request.name,
            balance = request.balance
        )
        accounts.add(account)

        return account
    }
}