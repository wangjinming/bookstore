package com.wjm.bookstore.test.cases;

import com.wjm.bookstore.domain.Book;
import com.wjm.bookstore.domain.BookCriteria;
import com.wjm.bookstore.domain.ShoppingCartItem;
import com.wjm.bookstore.test.service.BookServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by dmall on 2016/5/31.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext-mybatis.xml")
public class BookTest {

    @Autowired
    BookServiceTest bookServiceTest;

    @Test
    public void testGetBook() {
        Book book = bookServiceTest.getBook(1L);
        System.out.println(book);
    }

    @Test
    public void testGetPageList() {
        BookCriteria bc = new BookCriteria(2);
        //bc.setMinPrice(100);
        List<Book> books = bookServiceTest.getPageList(bc);
        System.out.println(books);
    }

    @Test
    public void testGetTotalBookNumber() {
        BookCriteria bc = new BookCriteria(1);
        long number = bookServiceTest.getTotalBookNumber(bc);
        System.out.println(number);
    }

    @Test
    public void testGetStoreNumber() {
        int storeNum = bookServiceTest.getStoreNumber(1);
        System.out.println("store number : " + storeNum);
    }

    @Test
    public void testBatchUpdateStoreNumberAndSalesAmount() {
        Book book1 = new Book();
        book1.setId(1L);
        ShoppingCartItem item1 = new ShoppingCartItem(book1);
        item1.setQuantity(2);

        Book book2 = new Book();
        book2.setId(2L);
        ShoppingCartItem item2 = new ShoppingCartItem(book2);

        Collection<ShoppingCartItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        bookServiceTest.batchUpdateStoreNumberAndSalesAmount(items);
    }

    @Test
    public void testBatchUpdateStoreNumberAndSalesAmount2() {
        Book book1 = new Book();
        book1.setId(1L);
        ShoppingCartItem item1 = new ShoppingCartItem(book1);
        item1.setQuantity(2);

        Book book2 = new Book();
        book2.setId(2L);
        ShoppingCartItem item2 = new ShoppingCartItem(book2);

        Map<Long, ShoppingCartItem> map = new HashMap<>();
        map.put(1L, item1);
        map.put(2L, item2);

        Collection<ShoppingCartItem> shoppingCartItems = new ArrayList<>(map.values());
        System.out.println(map.values().getClass().getName());
        bookServiceTest.batchUpdateStoreNumberAndSalesAmount(shoppingCartItems);
    }
}
