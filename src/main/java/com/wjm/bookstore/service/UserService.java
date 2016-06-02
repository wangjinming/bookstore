package com.wjm.bookstore.service;

import com.wjm.bookstore.dao.TradeDAO;
import com.wjm.bookstore.dao.TradeItemDAO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.bookstore.dao.UserDAO;
import com.wjm.bookstore.domain.Trade;
import com.wjm.bookstore.domain.TradeItem;
import com.wjm.bookstore.domain.User;

import java.util.HashSet;
import java.util.LinkedHashSet;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private TradeDAO tradeDAO;

	@Autowired
	private TradeItemDAO tradeItemDAO;

	@Transactional(readOnly=true)
	public User getUserByUserName(String username){
		return userDAO.getUser(username);
	}

	@Transactional(readOnly=true)
	public User getUserWithTrades(String username) {
		User user = getUserByUserName(username);
		user.setTrades(new LinkedHashSet<Trade>(tradeDAO.getTradesWithUserId(user.getUserId())));
		for(Trade trade :user.getTrades()) {
			trade.setItems(new HashSet<TradeItem>(tradeItemDAO.getTradeItemsWithTradeId(trade.getTradeId())));
			for(TradeItem item:trade.getItems()) {
				Hibernate.initialize(item.getBook());
			}
		}
		return user;
	}
}
