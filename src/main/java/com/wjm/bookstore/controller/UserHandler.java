package com.wjm.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wjm.bookstore.domain.User;
import com.wjm.bookstore.service.UserService;

@Controller
@RequestMapping("/user")
public class UserHandler {
	@Autowired
	private UserService userService;
	
	@RequestMapping("showUserTrade")
	public ModelAndView showUserTrade(@RequestParam("username") String username) {
		//	调用 UserService 的 getUser(username) 获取User 对象：要求 trades 是被装配好的，而且每一个 Trrade 对象的 items 也被装配好
		User user = userService.getUserWithTrades(username);
		
		//	把 User 对象放入到 request 中
		if(user == null){
			ModelAndView mv = new ModelAndView("error-1");
			
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("trades");
		mv.addObject("user", user);

		return mv;
	}
}
