package com.wjm.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.bookstore.dao.AccountDAO;
import com.wjm.bookstore.domain.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Transactional(readOnly=true)
	public Account getAccount(long accountId){
		return accountDAO.get(accountId);
	}

	@Transactional(readOnly=false)
	public void setAccount(long accountId, float balance) {
		accountDAO.updateBalance(accountId, balance);
	}
}
