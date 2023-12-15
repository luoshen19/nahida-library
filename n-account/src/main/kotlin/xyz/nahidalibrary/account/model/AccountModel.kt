package xyz.nahidalibrary.account.model

import com.baomidou.mybatisplus.annotation.*

@TableName("n_account")
data class AccountModel(
  val username: String,
  @TableField("'password'")
  val password: String,
  val nickname: String,
  val email: String
): BaseModel()
