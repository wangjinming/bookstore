package com.wjm.bookstore.test.service;

import com.wjm.bookstore.dao.BookDAO;
import com.wjm.bookstore.domain.Book;
import com.wjm.bookstore.domain.BookCriteria;
import com.wjm.bookstore.domain.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by dmall on 2016/5/31.
 */
@Service
public class BookServiceTest {
    @Autowired
    BookDAO bookDAO;

    public Book getBook(long id) {
        return bookDAO.getBook(id);
    }

    public List<Book> getPageList(BookCriteria cb) {
        return bookDAO.getPageList(cb);
    }

    public long getTotalBookNumber(BookCriteria cb) {
        return bookDAO.getTotalBookNumber(cb);
    }

    public int getStoreNumber(long id) {
        return bookDAO.getStoreNumber(id);
    }

    public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        bookDAO.batchUpdateStoreNumberAndSalesAmount(items);
    }

}
