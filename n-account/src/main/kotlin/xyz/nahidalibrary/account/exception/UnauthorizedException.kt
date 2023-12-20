package xyz.nahidalibrary.account.exception

import org.springframework.http.HttpStatus
import xyz.nahidalibrary.account.common.BizErrorTypeEnum

class UnauthorizedException(
  override val errorType: BizErrorTypeEnum = BizErrorTypeEnum.UNAUTHORIZED,
  override val message: String = "认证失败",
  override val httpStatus: HttpStatus = HttpStatus.UNAUTHORIZED
) : BizException()