package xyz.nahidalibrary.account.common

data class BizErrorResult(
  val error: BizErrorTypeEnum = BizErrorTypeEnum.SERVER_ERROR,
  val message: String = "服务器错误"
)

enum class BizErrorTypeEnum {
  SERVER_ERROR,
  UNAUTHORIZED,
  VERIFICATION
}