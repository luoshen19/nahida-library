package xyz.nahidalibrary.account.exception

import xyz.nahidalibrary.account.common.BizErrorEnum
import xyz.nahidalibrary.account.common.BizErrorResult

class BizException(bizError: BizErrorResult): RuntimeException() {
  constructor(error: BizErrorEnum, message: String): this(BizErrorResult(error, message))
}