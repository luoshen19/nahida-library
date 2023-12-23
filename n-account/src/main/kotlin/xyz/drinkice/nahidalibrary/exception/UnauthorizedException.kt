package xyz.drinkice.nahidalibrary.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(
  override val errorType: xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum = xyz.drinkice.nahidalibrary.common.BizErrorTypeEnum.UNAUTHORIZED,
  override val message: String = "认证失败",
  override val httpStatus: HttpStatus = HttpStatus.UNAUTHORIZED
) : BizException()