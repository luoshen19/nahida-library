package xyz.nahidalibrary.account.mapper

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import xyz.nahidalibrary.account.model.AccountModel


@SpringBootTest
class AccountMapperTest {
  
  @Autowired
  private lateinit var accountMapper: AccountMapper
  
  @Test
  fun testSelect() {
    val list: List<AccountModel> = accountMapper.selectList(null)
    list.forEach(System.out::println)
  }
}