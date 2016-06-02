package com.wjm.bookstore.test.cases;

import com.wjm.bookstore.domain.User;
import com.wjm.bookstore.test.service.UserServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dmall on 2016/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext-mybatis.xml")
public class UserTest {
    @Autowired
    UserServiceTest userServiceTest;

    @Test
    public void testGetUser() {
        User user = userServiceTest.getUserByUserName("lisi");
        System.out.println(user);
    }
}
