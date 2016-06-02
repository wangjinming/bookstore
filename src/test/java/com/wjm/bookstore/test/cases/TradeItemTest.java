package com.wjm.bookstore.test.cases;

import com.wjm.bookstore.domain.Book;
import com.wjm.bookstore.domain.Trade;
import com.wjm.bookstore.domain.TradeItem;
import com.wjm.bookstore.test.service.TradeItemServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dmall on 2016/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext-mybatis.xml")
public class TradeItemTest {
    @Autowired
    TradeItemServiceTest tradeItemServiceTest;

    @Test
    public void testGetTradeItemsWithTradeId() {
        System.out.println(tradeItemServiceTest.getTradeItemsWithTradeId(6));
    }

    @Test
    public void testBatchSave(){
        Trade trade = new Trade();
        trade.setTradeId(6l);

        Collection<TradeItem> items = new ArrayList<>();
        TradeItem item1 = new TradeItem();
        item1.setQuantity(2);
        Book book1 = new Book();
        book1.setId(1l);
        item1.setBook(book1);
        item1.setTrade(trade);

        TradeItem item2 = new TradeItem();
        item2.setQuantity(3);
        Book book2 = new Book();
        book2.setId(2l);
        item2.setBook(book2);
        item2.setTrade(trade);

        items.add(item1);
        items.add(item2);

        tradeItemServiceTest.batchSave(items);
    }

}
