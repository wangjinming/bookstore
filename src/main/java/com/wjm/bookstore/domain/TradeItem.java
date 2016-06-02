package com.wjm.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="Trade_Item_Table")
@Entity
public class TradeItem {
	
	public TradeItem() {
		
	}
	
	private Long tradeItemId;
	
	private int quantity;
	
	//和 TradeItem 关联的 Book
	private Book book;
	
	private Trade trade;
	
	@GeneratedValue
	@Id
	@Column(name="Trade_Item_Id")
	public Long getTradeItemId() {
		return tradeItemId;
	}

	public void setTradeItemId(Long tradeItemId) {
		this.tradeItemId = tradeItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@JoinColumn(name="Trade_Id")
	@ManyToOne()
	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	
	@JoinColumn(name="Book_Id")
	@ManyToOne()
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
}
