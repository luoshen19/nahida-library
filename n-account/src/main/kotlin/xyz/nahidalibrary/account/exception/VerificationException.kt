package xyz.nahidalibrary.account.exception

import org.springframework.http.HttpStatus
import xyz.nahidalibrary.account.common.BizErrorTypeEnum

class VerificationException(
  override val errorType: BizErrorTypeEnum = BizErrorTypeEnum.VERIFICATION,
  override val message: String = "参数错误",
  override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
) : BizException()