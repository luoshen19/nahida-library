package xyz.nahidalibrary.account.util

import org.apache.shiro.crypto.hash.Md5Hash

object CommonUtils {
  
  fun md5(text: String): String = Md5Hash(text).toHex()
  
  fun md5(text: CharArray): String = Md5Hash(String(text)).toHex()
}