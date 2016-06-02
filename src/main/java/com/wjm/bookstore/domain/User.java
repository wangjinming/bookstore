package com.wjm.bookstore.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="user_table")
@Entity
public class User {
	
	private Long userId;
	private String username;
	private Account account;
	 
	private Set<Trade> trades = new LinkedHashSet<Trade>();

	public User() {
		
	}
	
	@GeneratedValue
	@Id
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="USER_NAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JoinColumn(name="Account_Id", unique=true)
	@OneToOne(fetch=FetchType.LAZY)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.REMOVE}, mappedBy="user")
	public Set<Trade> getTrades() {
		return trades;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", account=" + account +
				'}';
	}
}
