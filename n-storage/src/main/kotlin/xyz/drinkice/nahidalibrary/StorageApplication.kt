package xyz.drinkice.nahidalibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class StorageApplication

fun main(args: Array<String>) {
  runApplication<StorageApplication>(*args)
}