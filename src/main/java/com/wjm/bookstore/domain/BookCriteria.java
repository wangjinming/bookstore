package com.wjm.bookstore.domain;

public class BookCriteria {

	private float minPrice = 0;
	private float maxPrice = Float.MAX_VALUE;
	
	private int pageNo;

	private int pageSize = 3;

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public BookCriteria(float minPrice, float maxPrice, int pageNo) {
		super();
		this.setMinPrice(minPrice);
		this.setMaxPrice(maxPrice);
		this.setPageNo(pageNo);
	}
	
	public BookCriteria(int pageNo) {
		this.setPageNo(pageNo);
	}

	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

}
