package com.wjm.bookstore.dao.impl.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wjm.bookstore.dao.BookDAO;
import com.wjm.bookstore.domain.Book;
import com.wjm.bookstore.domain.BookCriteria;
import com.wjm.bookstore.domain.Page;
import com.wjm.bookstore.domain.ShoppingCartItem;

@Repository
public class BookDAOImpl extends BaseDAO implements BookDAO {

	@Override
	public Book getBook(long id) {
		Book book = (Book) getEntityManager().find(Book.class, id);
		return book;
	}

	@Override
	public long getTotalBookNumber(BookCriteria cb) {
		String jpql = "SELECT count(b.id) FROM Book b WHERE b.price > ? AND b.price < ?";
		Query query = getEntityManager().createQuery(jpql);
		long totalBookNumber = (long) query.setParameter(1, cb.getMinPrice())
				.setParameter(2, cb.getMaxPrice()).getSingleResult();

		return totalBookNumber;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getPageList(BookCriteria cb) {
		String jpql = "SELECT b from Book b WHERE b.price > ? AND b.price < ?";
		Query query = getEntityManager().createQuery(jpql);
		
		List<Book> books = query.setFirstResult(cb.getOffset())
				.setMaxResults(cb.getPageSize())
				.setParameter(1, cb.getMinPrice())
		  		.setParameter(2, cb.getMaxPrice()).getResultList();
		return books;
	}

	@Override
	public int getStoreNumber(long id) {
		String jpql = "SELECT b.storeNumber FROM Book b WHERE b.id = ?";
		Query query = getEntityManager().createQuery(jpql);
		Integer storeNumber = (Integer) query.setParameter(1, id).getSingleResult();
		
		return storeNumber;		
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items) {
		String hql = "Update Book b "
					 + "Set b.salesAmount = b.salesAmount + ?, b.storeNumber = b.storeNumber - ? "
					 + "Where b.id = ?";
		ArrayList<ShoppingCartItem> cartItems = new ArrayList<ShoppingCartItem>(items);
		for(int i = 0; i < cartItems.size(); i++) {
			getEntityManager().createQuery(hql)
						.setParameter(1, cartItems.get(i).getQuantity())
						.setParameter(2, cartItems.get(i).getQuantity())
						.setParameter(3, cartItems.get(i).getBook().getId())
						.executeUpdate();			
		}
		
	}

}
