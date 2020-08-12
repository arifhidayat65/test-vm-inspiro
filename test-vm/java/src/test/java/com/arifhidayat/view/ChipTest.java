package com.arifhidayat.view;

import com.arifhidayat.service.impl.Chip;
import org.junit.Assert;
import org.junit.Test;

public class ChipTest {

	Chip testChip = new Chip("W5", "Chipper", 1.99, "Chip");

	@Test
	public void testGetSlotId() {
		String actualResult = testChip.getSlotId();
		Assert.assertEquals("W5", actualResult);
	}

	@Test
	public void testSetSlotId() {
		testChip.setSlotId("W7");
		String actualResult = testChip.getSlotId();
		Assert.assertEquals("W7", actualResult);
	}

	@Test
	public void testGetItemName() {
		String actualResult = testChip.getItemName();
		Assert.assertEquals("Chipper", actualResult);
	}

	@Test
	public void testSetItemName() {
		testChip.setItemName("Cow Chip");
		String actualResult = testChip.getItemName();
		Assert.assertEquals("Cow Chip", actualResult);
	}

	@Test
	public void testGetPrice() {
		double actualResult = testChip.getPrice();
		Assert.assertEquals(1.99, actualResult, 0.00);
	}

	@Test
	public void testSetPrice() {
		testChip.setPrice(0.09);
		double actualResult = testChip.getPrice();
		Assert.assertEquals(0.09, actualResult, 0.00);
	}

	@Test
	public void testGetItemType() {
		String actualResult = testChip.getItemType();
		Assert.assertEquals("Chip", actualResult);
	}

	@Test
	public void testSetItemType() {
		testChip.setItemType("Dung");
		String actualResult = testChip.getItemType();
		Assert.assertEquals("Dung", actualResult);
	}

	@Test
	public void testGetQuantity() {
		int actualResult = testChip.getQuantity();
		Assert.assertEquals(5, actualResult);
	}

	@Test
	public void testSetQuantity() {
		testChip.setQuantity(1000000);
		int actualResult = testChip.getQuantity();
		Assert.assertEquals(1000000, actualResult);
	}

}
