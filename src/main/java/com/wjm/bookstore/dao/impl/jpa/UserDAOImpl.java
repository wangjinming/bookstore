package com.wjm.bookstore.dao.impl.jpa;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.wjm.bookstore.dao.UserDAO;
import com.wjm.bookstore.domain.User;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public User getUser(String username) {
		
		String hql = "From User u where u.username = ?";
		
		EntityManager entityManager = getEntityManager();
		User user = (User) entityManager.createQuery(hql).setParameter(1, username).getSingleResult();
		
		return user;
	}

}
