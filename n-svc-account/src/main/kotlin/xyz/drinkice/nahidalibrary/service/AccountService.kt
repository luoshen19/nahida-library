package xyz.drinkice.nahidalibrary.service

import xyz.drinkice.nahidalibrary.model.AccountModel
import xyz.drinkice.nahidalibrary.vo.LoginVo

interface AccountService {
  
  fun login(username: String, password: String, isEmail: Boolean = false): LoginVo
  
  fun getOrCreate(username: String, password: String): AccountModel
}