package com.example.demo.form;

public class AddForm {
	private String name;
	private Integer condition;
	private String brand;
	private Integer price;
	private String description;
	private String parent;
	private String child;
	private String grandChild;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public String getGrandChild() {
		return grandChild;
	}
	public void setGrandChild(String grandChild) {
		this.grandChild = grandChild;
	}
	@Override
	public String toString() {
		return "AddForm [name=" + name + ", condition=" + condition + ", brand=" + brand + ", price=" + price
				+ ", description=" + description + ", parent=" + parent + ", child=" + child + ", grandChild="
				+ grandChild + "]";
	}
	
	
	
	
	
}
