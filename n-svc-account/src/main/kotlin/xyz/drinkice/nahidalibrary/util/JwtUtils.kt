package xyz.drinkice.nahidalibrary.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtUtils {
  
  // 过期时间5分钟
  private const val EXPIRE_TIME = 5 * 60 * 1000

  fun verify(token: String, secret: String): Boolean {
    return try {
      JWT.require(Algorithm.HMAC256(secret)).build().verify(token)
      true
    } catch (e: Exception) {
      false
    }
  }
  
  fun getId(token: String): Long = JWT.decode(token).getClaim(JwtClaimKeyEnum.ID.toString()).asLong()
  
  fun getUsername(token: String): String = JWT.decode(token).getClaim(JwtClaimKeyEnum.USERNAME.toString()).asString()
  
  /**
   * 生成签名
   * @param secret JWT加密密钥
   * @return 加密的token
   */
  fun sign(id: Long, username: String, secret: String): String =
    JWT.create()
      .withClaim(JwtClaimKeyEnum.ID.toString(), id)
      .withClaim(JwtClaimKeyEnum.USERNAME.toString(), username)
      .withExpiresAt(Date(System.currentTimeMillis() + EXPIRE_TIME))
      .sign(Algorithm.HMAC256(secret))
}

enum class JwtClaimKeyEnum {
  ID, USERNAME
}
