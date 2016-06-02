package com.wjm.bookstore.dao;

import com.wjm.bookstore.domain.User;

public interface UserDAO {

	/**
	 * 根据用户名获取 User 对象
	 * @param username
	 * @return
	 */
	public User getUser(String username);

}

