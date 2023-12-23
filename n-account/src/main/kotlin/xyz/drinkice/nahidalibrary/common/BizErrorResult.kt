package xyz.drinkice.nahidalibrary.common

data class BizErrorResult(
  val error: xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum = xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum.SERVER_ERROR,
  val message: String = "服务器错误"
)

enum class BizErrorTypeEnum {
  SERVER_ERROR,
  UNAUTHORIZED,
  UNREGISTERED,
  VERIFICATION
}