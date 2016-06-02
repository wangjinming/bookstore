package com.wjm.bookstore.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="Book_Table")
@Entity
public class Book {

	private Long id;
	private String author;

	private String title;
	private float price;

	private Date publishingDate;
	private int salesAmount;

	private int storeNumber;
	private String remark;
	
	@GeneratedValue
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="Publish_Date")
	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	
	@Column(name="Sales_Amount")
	public int getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}

	@Column(name="Store_Number")
	public int getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title
				+ ", price=" + price + ", publishingDate=" + publishingDate
				+ ", salesAmount=" + salesAmount + ", storeNumber="
				+ storeNumber + ", remark=" + remark + "]\n\n";
	}

}
