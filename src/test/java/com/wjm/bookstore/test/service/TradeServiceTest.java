package com.wjm.bookstore.test.service;

import com.wjm.bookstore.dao.TradeDAO;
import com.wjm.bookstore.domain.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmall on 2016/6/1.
 */
@Service
public class TradeServiceTest {
    @Autowired
    TradeDAO tradeDAO;

    public  void insert(Trade trade) {
        tradeDAO.insert(trade);
    }

    public List<Trade> getTradesWithUserId(long userId) {
        List<Trade> tradesWithUserId = tradeDAO.getTradesWithUserId(userId);
        return tradesWithUserId;
    }
}
