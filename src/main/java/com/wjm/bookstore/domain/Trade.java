package com.wjm.bookstore.domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="Trade_Table")
@Entity
public class Trade {

	// Trade 对象对应的 id
	private Long tradeId;

	// 交易的时间
	private Date tradeTime;

	// 和 Trade 关联的 User 的 userId
	private User user;
		
	// Trade 关联的多个 TradeItem
	private Set<TradeItem> items = new LinkedHashSet<TradeItem>();

	@GeneratedValue
	@Id
	@Column(name="Trade_Id")
	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
	@Column(name="Trade_Time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	@JoinColumn(name="User_Id")
	@ManyToOne(fetch=FetchType.EAGER)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setItems(Set<TradeItem> items) {
		this.items = items;
	}
	
	@OneToMany(mappedBy="trade")
	public Set<TradeItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Trade{" +
				"tradeId=" + tradeId +
				", tradeTime=" + tradeTime +
				'}';
	}
}
