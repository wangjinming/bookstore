package com.wjm.bookstore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.bookstore.dao.AccountDAO;
import com.wjm.bookstore.dao.BookDAO;
import com.wjm.bookstore.dao.TradeDAO;
import com.wjm.bookstore.dao.TradeItemDAO;
import com.wjm.bookstore.dao.UserDAO;
import com.wjm.bookstore.domain.Book;
import com.wjm.bookstore.domain.BookCriteria;
import com.wjm.bookstore.domain.Page;
import com.wjm.bookstore.domain.ShoppingCart;
import com.wjm.bookstore.domain.ShoppingCartItem;
import com.wjm.bookstore.domain.Trade;
import com.wjm.bookstore.domain.TradeItem;

@Service
public class BookService {
	@Autowired
	private BookDAO bookDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TradeDAO tradeDAO;
	@Autowired
	private TradeItemDAO tradeItemDAO;
	
	@Transactional(readOnly=true)
	public Page<Book> getBookPage(BookCriteria bc) {
		Page<Book> page = new Page<Book>(bc.getPageNo());

		page.setTotalItemNumber(bookDAO.getTotalBookNumber(bc));
		//校验 pageNo 的合法性
		bc.setPageNo(page.getPageNo());
		page.setList(bookDAO.getPageList(bc));

		return page;
	}
	
	@Transactional(readOnly=true)
	public Book getBook(long id) {
		return bookDAO.getBook(id);
	}
	
	public boolean addToCart(long id, ShoppingCart sc) {
		Book book = bookDAO.getBook(id);
		
		if(book != null){
			sc.addBook(book);
			return true;
		}
		
		return false;
	}
	
	public void deleteFromCart(long id, ShoppingCart sc) {
		sc.removeItem(id);
	}

	public void clearCart(ShoppingCart sc) {
		sc.clear();
		
	}

	public void updateQuantity(ShoppingCart sc, long id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}

	@Transactional(propagation=Propagation.REQUIRED )
	public void cash(ShoppingCart shoppingCart, String username,
			String accountId) {
		//1. 更新 mybooks 数据表相关记录的 salesamount 和 storenumber
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		
		//2. 更新 account 数据表的 balance
		accountDAO.updateBalance(Long.parseLong(accountId), shoppingCart.getTotalMoney());
		
		//3. 向 trade 数据表插入一条记录
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUser(userDAO.getUser(username));
		tradeDAO.insert(trade);
		
		//4. 向 tradeitem 数据表插入 n 条记录
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci: shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			
			tradeItem.setBook(sci.getBook());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTrade(trade);
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		//5. 清空购物车
		shoppingCart.clear();
	}
}
