package com.wjm.bookstore.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wjm.bookstore.domain.Account;
import com.wjm.bookstore.domain.Book;
import com.wjm.bookstore.domain.BookCriteria;
import com.wjm.bookstore.domain.Page;
import com.wjm.bookstore.domain.ShoppingCart;
import com.wjm.bookstore.domain.ShoppingCartItem;
import com.wjm.bookstore.domain.User;
import com.wjm.bookstore.service.AccountService;
import com.wjm.bookstore.service.BookService;
import com.wjm.bookstore.service.UserService;
import com.wjm.bookstore.web.BookStoreUtils;

@Controller
@RequestMapping("/book")
public class BookHandler {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/getBooks")
	public ModelAndView getBooks(@RequestParam(value="pageNo",defaultValue="1") Integer pageNo, 
						 @RequestParam(value="minPrice", defaultValue="0") Float minPrice,
						 @RequestParam(value="maxPrice", defaultValue="100000") Float maxPrice) throws ServletException, IOException {

		
		BookCriteria bc = new BookCriteria(minPrice, maxPrice, pageNo);
		Page<Book> bookPage = bookService.getBookPage(bc);
		
		ModelAndView mv = new ModelAndView("books");
		mv.addObject("bookPage", bookPage);
		return mv;
	}
	
	@RequestMapping("/getBook")
	public ModelAndView getBook(@RequestParam("bookId") Long id) throws ServletException, IOException {
		
		Book book = bookService.getBook(id);
		
		ModelAndView mv = new ModelAndView("book");
		mv.addObject("book", book);

		return mv;
	}
	
	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam("id") Long id, HttpServletRequest request) throws ServletException, IOException {
		ShoppingCart sc = BookStoreUtils.getShoppingCart(request);
		bookService.addToCart(id, sc);
		return "redirect:/book/getBooks";
	}
	
	@RequestMapping("/deleteFromCart")
	public String deleteFromCart(@RequestParam("id") long id, HttpServletRequest request) throws ServletException, IOException {
		
		ShoppingCart sc = BookStoreUtils.getShoppingCart(request);
		bookService.deleteFromCart(id, sc);
		
		return "cart";
	}
	
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request) {
		
		ShoppingCart sc = BookStoreUtils.getShoppingCart(request);
		bookService.clearCart(sc);

		return "cart";
	}
	
	@RequestMapping("/updateQuantity")
	@ResponseBody
	public Map<String, Object> updateQuantity(HttpServletRequest request,
			@RequestParam("id") long id, @RequestParam("quantity") int quantity) throws ServletException, IOException {
				
		ShoppingCart sc = BookStoreUtils.getShoppingCart(request);
		bookService.updateQuantity(sc, id, quantity);
			
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalMoney", sc.getTotalMoney());
		result.put("totalQuantity", sc.getBookNumber());
			
		return result;
		
	}
	
	@RequestMapping("/cash")
	public String cash(@RequestParam("username") String username, 
			@RequestParam("accountId") String accountId,
			HttpServletRequest request) throws ServletException, IOException {
		//1. 简单验证: 验证表单域的值是否符合基本的规范: 是否为空, 是否可以转为 int 类型, 是否是一个 email. 不需要进行查询
		//数据库或调用任何的业务方法.
		
		StringBuffer errors = validateFormField(username, accountId);
		
		//表单验证通过。 
		if(errors.toString().equals("")){
			errors = validateUser(username, accountId);
			
			//用户名和账号验证通过
			if(errors.toString().equals("")){
				errors = validateBookStoreNumber(request);
				
				//库存验证通过
				if(errors.toString().equals("")){
					errors = validateBalance(request, accountId);
				}
			}
		}
		
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);	
			return "cash";
		}
		
		//验证通过执行具体的逻辑操作
		bookService.cash(BookStoreUtils.getShoppingCart(request), username, accountId); 
		return "success";
	}
	
	// 验证表单域是否符合基本的规则: 是否为空.
	private StringBuffer validateFormField(String username, String accountId) {
		StringBuffer errors = new StringBuffer("");

		if (username == null || username.trim().equals("")) {
			errors.append("用户名不能为空<br>");
		}

		if (accountId == null || accountId.trim().equals("")) {
			errors.append("账号不能为空");
		}

		return errors;
	}
	
	// 验证用户名和账号是否匹配
	private StringBuffer validateUser(String username, String accountId) {
		boolean flag = false;
		User user = userService.getUserByUserName(username);
		if (user != null) {
			Account account = user.getAccount();
			if (account != null && accountId.trim().equals("" + account.getAccountId())) {
				flag = true;
			}
		}

		StringBuffer errors2 = new StringBuffer("");
		if (!flag) {
			errors2.append("用户名和账号不匹配");
		}

		return errors2;
	}
	
	// 验证库存是否充足
	private StringBuffer validateBookStoreNumber(HttpServletRequest request) {

		StringBuffer errors = new StringBuffer("");
		ShoppingCart cart = BookStoreUtils.getShoppingCart(request);

		for (ShoppingCartItem sci : cart.getItems()) {
			int quantity = sci.getQuantity();
			int storeNumber = bookService.getBook(sci.getBook().getId())
					.getStoreNumber();

			if (quantity > storeNumber) {
				errors.append(sci.getBook().getTitle() + "库存不足<br>");
			}
		}

		return errors;
	}
	
	//验证余额是否充足
	private StringBuffer validateBalance(HttpServletRequest request, String accountId){
		
		StringBuffer errors = new StringBuffer("");
		ShoppingCart cart = BookStoreUtils.getShoppingCart(request);
		
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		if(cart.getTotalMoney() > account.getBalance()){
			errors.append("余额不足!");
		}
		
		return errors;
	}

}
