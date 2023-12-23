package xyz.drinkice.nahidalibrary.exception

import org.springframework.http.HttpStatus

open class BizException(
  open val errorType: xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum = xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum.SERVER_ERROR,
  override val message: String = "服务器错误",
  open val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
): RuntimeException() {
  constructor(
    bizError: xyz.drinkice.nahidalibrary.common.BizErrorResult = xyz.drinkice.nahidalibrary.common.BizErrorResult(),
    httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
  ): this(
    message = bizError.message,
    errorType = bizError.error,
    httpStatus = httpStatus
  )
}