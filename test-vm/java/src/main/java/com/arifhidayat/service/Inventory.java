package com.arifhidayat.service;

public interface Inventory {

	String getSlotId();
	String getItemName();
	double getPrice();
	String getItemType();
	int getQuantity();
	void setQuantity(int quantity);
	void setItemName(String itemName);
}
