package com.arifhidayat.service.impl;

import com.arifhidayat.service.Inventory;

public class Chip implements Inventory {

	//Data Entity
	private String slotId;
	private String itemName;
	private double price;
	private String itemType;
	private int quantity = 10;

	//Constructors
	public Chip(String slotId, String itemName, double price, String itemType) {
		this.slotId = slotId;
		this.itemName = itemName;
		this.price = price;
		this.itemType = itemType;
	}
	public Chip(String itemName) {
		this.itemName = itemName;
	}

	//Getters & Setters
	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
