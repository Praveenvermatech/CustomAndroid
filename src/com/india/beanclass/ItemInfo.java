package com.india.beanclass;

public class ItemInfo {

	private String itemName;
	private String category;
	private String quantity;
	private String price;
	private String dot;
	
	
	
	public ItemInfo(String itemName, String category, String quantity,
			String price, String dot) {
		super();
		this.itemName = itemName;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.dot = dot;
	}
	@Override
	public String toString() {
		return "ItemInfo [itemName=" + itemName + ", category=" + category
				+ ", quantity=" + quantity + ", price=" + price + ", dot="
				+ dot + "]";
	}
	public String getItemName() {
		return itemName;
	}
	public String getCategory() {
		return category;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getPrice() {
		return price;
	}
	public String getDot() {
		return dot;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setDot(String dot) {
		this.dot = dot;
	}
}
