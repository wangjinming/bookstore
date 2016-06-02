package com.wjm.bookstore.test.service;

import com.wjm.bookstore.dao.UserDAO;
import com.wjm.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmall on 2016/6/1.
 */
@Service
public class UserServiceTest {
    @Autowired
    UserDAO userDAO;

    public User getUserByUserName(String username){
        return userDAO.getUser(username);
    }
}
