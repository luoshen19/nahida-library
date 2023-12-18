package xyz.nahidalibrary.account.service

import xyz.nahidalibrary.account.model.AccountModel

interface AccountService {
  
  fun getOrCreate(username: String, password: String): AccountModel
}