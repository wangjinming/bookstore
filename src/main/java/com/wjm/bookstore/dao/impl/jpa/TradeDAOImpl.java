package com.wjm.bookstore.dao.impl.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.bookstore.dao.TradeDAO;
import com.wjm.bookstore.domain.Trade;

@Repository
public class TradeDAOImpl extends BaseDAO implements TradeDAO {

	@Override
	@Transactional
	public void insert(Trade trade) {
		getEntityManager().persist(trade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trade> getTradesWithUserId(Long userId) {
		String jpql = "SELECT t FROM Trade t WHERE t.tradeId = ?";
		Query query = getEntityManager().createQuery(jpql);
		List<Trade> trades = (List<Trade>) query.setParameter(1, userId).getResultList();

		return trades;
	}

}
