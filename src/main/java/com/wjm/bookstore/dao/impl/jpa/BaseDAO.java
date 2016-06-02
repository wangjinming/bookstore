package com.wjm.bookstore.dao.impl.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class BaseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
