package xyz.drinkice.nahidalibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AccountApplication

fun main(args: Array<String>) {
  runApplication<AccountApplication>(*args)
}