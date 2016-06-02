package com.wjm.bookstore.dao.impl.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.bookstore.dao.TradeItemDAO;
import com.wjm.bookstore.domain.TradeItem;

@Repository
public class TradeItemDAOImple extends BaseDAO implements TradeItemDAO {

	@Override
	@Transactional
	public void batchSave(Collection<TradeItem> items) {
		List<TradeItem> itemsList = new ArrayList<TradeItem>(items);
		for(int i = 0; i < itemsList.size(); i++) {
			getEntityManager().persist(itemsList.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TradeItem> getTradeItemsWithTradeId(long tradeId) {
		String jpql = "SELECT item FROM TradeItem item WHERE item.trade.tradeId = ?";
		Query query = getEntityManager().createQuery(jpql);
		return query.setParameter(1, tradeId).getResultList();
	}

}
