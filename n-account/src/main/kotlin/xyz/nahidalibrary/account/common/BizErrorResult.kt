package xyz.nahidalibrary.account.common

data class BizErrorResult(
  val error: BizErrorEnum = BizErrorEnum.SERVER_ERROR,
  val message: String = "服务器错误"
)

enum class BizErrorEnum {
  SERVER_ERROR
}