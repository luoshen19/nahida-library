package xyz.nahidalibrary.account.exception

import org.springframework.http.HttpStatus
import xyz.nahidalibrary.account.common.BizErrorResult
import xyz.nahidalibrary.account.common.BizErrorTypeEnum

open class BizException(
  open val errorType: BizErrorTypeEnum = BizErrorTypeEnum.SERVER_ERROR,
  override val message: String = "服务器错误",
  open val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
): RuntimeException() {
  constructor(
    bizError: BizErrorResult = BizErrorResult(),
    httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
  ): this(
    message = bizError.message,
    errorType = bizError.error,
    httpStatus = httpStatus
  )
}