package xyz.nahidalibrary.account.dto

import xyz.nahidalibrary.account.anno.NoArg

@NoArg
data class LoginDto(
  val username: String,
  val password: String
) {
  /**
   * (?=.*[0-9])：至少包含一个数字
   * (?=.*[a-z])：至少包含一个小写字母
   * (?=.*[A-Z])：至少包含一个大写字母
   * (?=.*[@#$%^&+=])：至少包含一个标点符号，可以根据需要修改该部分来包含其他标点符号
   * (?=\S+$)：不包含空格或空白字符
   * .{6,10}：密码长度为6到10位
   */
}