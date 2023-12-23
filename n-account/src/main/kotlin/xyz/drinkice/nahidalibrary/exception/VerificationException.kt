package xyz.drinkice.nahidalibrary.exception

import org.springframework.http.HttpStatus

class VerificationException(
  override val errorType: xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum = xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum.VERIFICATION,
  override val message: String = "参数错误",
  override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
) : BizException()