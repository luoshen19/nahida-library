package xyz.nahidalibrary.account.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import java.io.UnsupportedEncodingException
import java.util.*

object JwtUtils {
  
  // 过期时间5分钟
  private const val EXPIRE_TIME = 5 * 60 * 1000

  fun verify(token: String, username: String, secret: String): Boolean {
    return try {
      val algorithm = Algorithm.HMAC256(secret)
      val verifier = JWT.require(algorithm)
        .withClaim("username", username)
        .build()
      val jwt = verifier.verify(token)
      true
    } catch (e: Exception) {
      false
    }
  }
  
  /**
   * 获得token中的信息无需secret解密也能获得
   * @return token中包含的用户名
   */
  fun getUsername(token: String): String? {
    return try {
      val jwt = JWT.decode(token);
      jwt.getClaim("username").asString();
    } catch (e: JWTDecodeException) {
      null;
    }
  }
  
  /**
   * 生成签名,5min后过期
   * @param username 用户名
   * @param secret 用户的密码
   * @return 加密的token
   */
  fun sign(username: String, secret: String): String? {
    return try {
      val date = Date(System.currentTimeMillis() + EXPIRE_TIME);
      val algorithm = Algorithm.HMAC256(secret);
      JWT.create()
        .withClaim("username", username)
        .withExpiresAt(date)
        .sign(algorithm)
    } catch (e: UnsupportedEncodingException) {
      null
    }
  }
}