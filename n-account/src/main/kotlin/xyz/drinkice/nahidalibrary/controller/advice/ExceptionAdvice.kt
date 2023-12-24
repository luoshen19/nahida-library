package xyz.drinkice.nahidalibrary.controller.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import xyz.drinkice.nahidalibrary.common.model.BizErrorResult
import xyz.drinkice.nahidalibrary.exception.BizException

@RestControllerAdvice
class ExceptionAdvice {
  
  private val logger = LoggerFactory.getLogger(ExceptionAdvice::class.java)
  
  @ExceptionHandler(BizException::class)
  fun handleBizException(e: BizException): ResponseEntity<BizErrorResult> {
    logger.error("ExceptionAdvice ${e.errorType}", e)
    return ResponseEntity(BizErrorResult(e.errorType, e.message), e.httpStatus)
  }
  
  @ExceptionHandler(Exception::class)
  fun handleException(e: Exception): ResponseEntity<BizErrorResult> {
    val bizErrorResult = BizErrorResult()
    logger.error("ExceptionAdvice ${bizErrorResult.error}", e)
    return ResponseEntity(bizErrorResult, HttpStatus.INTERNAL_SERVER_ERROR)
  }
}