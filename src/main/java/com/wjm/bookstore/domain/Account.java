package com.wjm.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Account_Table")
@Entity
public class Account {

	private Long accountId;
	private float balance;
	
	@GeneratedValue
	@Id
	@Column(name="Account_Id")
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Account(Long accountId, int balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}

	public Account() {
	}
}
