package xyz.nahidalibrary.account.config.nshiro

import org.apache.shiro.authc.AuthenticationToken

class JwtToken(private val token: String) : AuthenticationToken {
  
  override fun getPrincipal(): String = token
  
  override fun getCredentials(): String = token
}