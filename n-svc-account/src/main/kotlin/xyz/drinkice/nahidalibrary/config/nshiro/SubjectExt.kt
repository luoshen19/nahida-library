package xyz.drinkice.nahidalibrary.config.nshiro

import org.apache.shiro.subject.Subject

fun Subject.getId() = (this.principal as CurrentAccount).id

fun Subject.getUsername() = (this.principal as CurrentAccount).username

fun Subject.getSecret() = (this.principal as CurrentAccount).secret

const val PRINCIPAL_KEY: String = "CurrentAccount"

data class CurrentAccount(
  val id: Long,
  val username: String,
  val secret: String
)