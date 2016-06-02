package com.wjm.bookstore.test.cases;

import com.wjm.bookstore.domain.Trade;
import com.wjm.bookstore.domain.User;
import com.wjm.bookstore.test.service.TradeServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dmall on 2016/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext-mybatis.xml")
public class TradeTest {
    @Autowired
    TradeServiceTest tradeServiceTest;

    @Test
    public void testInsert() {
        Trade trade = new Trade();
        trade.setTradeTime(new Date());
        User user = new User();
        user.setUserId(1L);
        trade.setUser(user);

        tradeServiceTest.insert(trade);
    }

    @Test
    public void testGetTradesWithUserId() {
        System.out.println(tradeServiceTest.getTradesWithUserId(1L));
    }
}
