package xyz.nahidalibrary.account.dto

import xyz.nahidalibrary.account.anno.NoArg

@NoArg
data class LoginDto(
  val username: String,
  val password: String
)