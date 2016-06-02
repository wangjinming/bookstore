package com.wjm.bookstore.test.cases;

/**
 * Created by dmall on 2016/5/31.
 */

import com.wjm.bookstore.service.AccountService;
import com.wjm.bookstore.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext-mybatis.xml")
public class AccountTest {
    @Autowired
    AccountService accountService;

    @Test
    public void testGet() {
        Account account = accountService.getAccount(1L);
        System.out.println(account);
    }

    @Test
    public void testUpdateBalance() {
        accountService.setAccount(2L, 400);
        System.out.println(accountService.getAccount(2L));
    }
}
