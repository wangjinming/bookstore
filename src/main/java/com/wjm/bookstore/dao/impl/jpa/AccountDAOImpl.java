package com.wjm.bookstore.dao.impl.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.bookstore.dao.AccountDAO;
import com.wjm.bookstore.domain.Account;

@Repository
public class AccountDAOImpl extends BaseDAO implements AccountDAO {

    @Override
    public Account get(Long accountId) {
        String hql = "From Account a Where a.accountId = ?";
        Account acount = (Account) getEntityManager().createQuery(hql)
                .setParameter(1, accountId).getSingleResult();
        return acount;
    }

    @Override
    @Transactional
    public void updateBalance(Long accountId, float amount) {
        String hql = "Update Account a Set a.balance = :balance Where a.accountId = :accountId";
        getEntityManager().createQuery(hql).setParameter("balance", amount).setParameter("accountId", accountId)
                .executeUpdate();
    }

}
