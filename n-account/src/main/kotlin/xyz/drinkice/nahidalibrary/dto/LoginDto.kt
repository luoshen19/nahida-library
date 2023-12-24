package xyz.drinkice.nahidalibrary.dto

import xyz.drinkice.nahidalibrary.common.anno.NoArg

@NoArg
data class LoginDto(
  val username: String,
  val password: String
)

@NoArg
data class LoginByCodeDto(
  val email: String,
  val code: String
)