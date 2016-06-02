package com.wjm.bookstore.dao;

import java.util.List;

import com.wjm.bookstore.domain.Trade;

public interface TradeDAO {

	/**
	 * 向数据表中插入 Trade 对象
	 * @param trade
	 */
	public abstract void insert(Trade trade);

	/**
	 * 根据 userId 获取和其关联的 Trade 的集合
	 * @param userId
	 * @return
	 */
	public abstract List<Trade> getTradesWithUserId(Long userId);

}