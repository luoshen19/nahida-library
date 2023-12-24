package xyz.drinkice.nahidalibrary.exception

import org.springframework.http.HttpStatus
import xyz.drinkice.nahidalibrary.common.model.BizErrorTypeEnum

class VerificationException(
  override val errorType: BizErrorTypeEnum = BizErrorTypeEnum.VERIFICATION,
  override val message: String = "参数错误",
  override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
) : BizException()