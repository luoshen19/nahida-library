package xyz.drinkice.nahidalibrary.dto

@xyz.drinkice.nahidalibrary.anno.NoArg
data class LoginDto(
  val username: String,
  val password: String
)

@xyz.drinkice.nahidalibrary.anno.NoArg
data class LoginByCodeDto(
  val email: String,
  val code: String
)