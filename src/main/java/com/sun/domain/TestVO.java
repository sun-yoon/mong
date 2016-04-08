package com.sun.domain;

public class TestVO {

	private long mem_snum;
	private String category;
	public long getMem_snum() {
		return mem_snum;
	}
	public void setMem_snum(long mem_snum) {
		this.mem_snum = mem_snum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "TestVO [mem_snum=" + mem_snum + ", category=" + category + "]";
	}
	
}
