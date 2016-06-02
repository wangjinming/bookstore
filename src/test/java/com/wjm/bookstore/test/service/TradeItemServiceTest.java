package com.wjm.bookstore.test.service;

import com.wjm.bookstore.dao.TradeItemDAO;
import com.wjm.bookstore.domain.TradeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by dmall on 2016/6/1.
 */
@Repository
public class TradeItemServiceTest {
    @Autowired
    private TradeItemDAO tradeItemDAO;

    public List<TradeItem> getTradeItemsWithTradeId(long tradeId) {
        return tradeItemDAO.getTradeItemsWithTradeId(tradeId);
    }

    public void batchSave(Collection<TradeItem> items) {
        tradeItemDAO.batchSave(items);
    }
}
