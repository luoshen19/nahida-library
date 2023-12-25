package xyz.drinkice.nahidalibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TemplateApplication

fun main(args: Array<String>) {
  runApplication<TemplateApplication>(*args)
}