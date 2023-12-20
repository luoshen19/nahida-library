package xyz.nahidalibrary.account.util

object RegexMatchUtils {
  
  /**
   * ^[A-Za-z0-9._%+-]+@ 匹配邮箱的用户名部分，可以包含字母、数字、点、下划线、百分号、加号、减号。
   * [A-Za-z0-9.-]+\\. 匹配邮箱的域名部分，可以包含字母、数字、点、减号。
   * [A-Za-z]{2,}$ 匹配邮箱的顶级域名部分，至少包含两个字母。
   */
  fun isEmail(text: String) =
    Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matches(text)
  
  /**
   * 英文 数字 _(下划线)
   */
  fun isUsername(text: String) =
    Regex("^[a-zA-Z][a-zA-Z0-9_]{6,11}\$").matches(text)
  
  /**
   * !@#%^&*()_+=,. 键盘上数字一排加上「,.」且去掉了「$-」这两个需要转义的字符
   */
  fun isPassword(text: String) =
    Regex("^[a-zA-Z0-9!@#%^&*()_+=,.]{6,12}\$").matches(text)
}