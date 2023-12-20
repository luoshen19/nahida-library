package xyz.nahidalibrary.account.service

import xyz.nahidalibrary.account.model.AccountModel
import xyz.nahidalibrary.account.vo.LoginVo

interface AccountService {
  
  fun login(username: String, password: String, isEmail: Boolean = false): LoginVo
  
  fun getOrCreate(username: String, password: String): AccountModel
}