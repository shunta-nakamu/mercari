package com.example.demo.domain;

public class Items {

	private Integer id;
	private String name;
	private Integer condition;
	private String brand;
	private Integer price;
	private Integer category;
	private Integer shipping;
	private String description;
	private String name_all;
	private String name_parent;
	private String name_child;
	private String name_grand_child;
	
	public String getName_parent() {
		return name_parent;
	}
	public void setName_parent(String name_parent) {
		this.name_parent = name_parent;
	}
	public String getName_child() {
		return name_child;
	}
	public void setName_child(String name_child) {
		this.name_child = name_child;
	}
	public String getName_grand_child() {
		return name_grand_child;
	}
	public void setName_grand_child(String name_grand_child) {
		this.name_grand_child = name_grand_child;
	}
	public String getName_all() {
		return name_all;
	}
	public void setName_all(String name_all) {
		this.name_all = name_all;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
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
	public Integer getShipping() {
		return shipping;
	}
	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", condition=" + condition + ", brand=" + brand + ", price="
				+ price + ", category=" + category + ", shipping=" + shipping + ", description=" + description
				+ ", name_all=" + name_all + ", name_parent=" + name_parent + ", name_child=" + name_child
				+ ", name_grand_child=" + name_grand_child + "]";
	}
	
	
	
}
