package com.wjm.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.wjm.bookstore.domain.TradeItem;

public interface TradeItemDAO {

	/**
	 * 批量保存 TradeItem 对象
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);

	/**
	 * 根据 tradeId 获取和其关联的 TradeItem 的集合
	 * @param tradeId
	 * @return
	 */
	public abstract List<TradeItem> getTradeItemsWithTradeId(long tradeId);

}


