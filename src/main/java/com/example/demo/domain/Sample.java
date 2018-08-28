package com.example.demo.domain;

public class Sample {
	private Integer id;
	private String name_big;
	private String name_middle;
	private String name_small;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName_big() {
		return name_big;
	}
	public void setName_big(String name_big) {
		this.name_big = name_big;
	}
	public String getName_middle() {
		return name_middle;
	}
	public void setName_middle(String name_middle) {
		this.name_middle = name_middle;
	}
	public String getName_small() {
		return name_small;
	}
	public void setName_small(String name_small) {
		this.name_small = name_small;
	}
	@Override
	public String toString() {
		return "Sample [id=" + id + ", name_big=" + name_big + ", name_middle=" + name_middle + ", name_small="
				+ name_small + "]";
	}
	
	
}
