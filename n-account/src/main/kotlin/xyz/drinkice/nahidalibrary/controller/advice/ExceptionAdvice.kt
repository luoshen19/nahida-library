package xyz.drinkice.nahidalibrary.controller.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import xyz.drinkice.nahidalibrary.exception.BizException

@RestControllerAdvice
class ExceptionAdvice {
  
  private val logger = LoggerFactory.getLogger(ExceptionAdvice::class.java)
  
  @ExceptionHandler(BizException::class)
  fun handleBizException(e: BizException): ResponseEntity<xyz.drinkice.nahidalibrary.common.BizErrorResult> {
    logger.error("ExceptionAdvice ${e.errorType}", e)
    return ResponseEntity(xyz.drinkice.nahidalibrary.common.BizErrorResult(e.errorType, e.message), e.httpStatus)
  }
  
  @ExceptionHandler(Exception::class)
  fun handleException(e: Exception): ResponseEntity<xyz.drinkice.nahidalibrary.common.BizErrorResult> {
    val bizErrorResult = xyz.drinkice.nahidalibrary.common.BizErrorResult()
    logger.error("ExceptionAdvice ${bizErrorResult.error}", e)
    return ResponseEntity(bizErrorResult, HttpStatus.INTERNAL_SERVER_ERROR)
  }
}