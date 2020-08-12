package com.arifhidayat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.arifhidayat.service.impl.Chip;
import com.arifhidayat.view.FileLog;
import com.arifhidayat.service.Inventory;
import com.arifhidayat.view.Menu;
import com.arifhidayat.view.SalesReport;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN = "";

	private static final String PURCHASE_MENU_OPTION_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";

	private static final String FEEDMONEY_MENU_OPTION_ONE = "Rp6000";
	private static final String FEEDMONEY_MENU_OPTION_TWO = "Rp8000";
	private static final String FEEDMONEY_MENU_OPTION_FIVE = "Rp10000";
	private static final String FEEDMONEY_MENU_OPTION_TEN = "Rp20000";
	private static final String FEEDMOENY_MENU_OPTION_TREE = "Rp50000";
	private static final String FEEDMONEY_MENU_OPTION_EXIT = "Exit";

	private static final String YESNO_MENU_OPTION_YES = "Yes";
	private static final String YESNO_MENU_OPTION_NO = "No";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_MONEY, PURCHASE_MENU_OPTION_PRODUCT,
			PURCHASE_MENU_OPTION_FINISH };
	private static final String[] FEEDMONEY_MENU_OPTIONS = { FEEDMONEY_MENU_OPTION_ONE, FEEDMONEY_MENU_OPTION_TWO,
			FEEDMONEY_MENU_OPTION_FIVE, FEEDMONEY_MENU_OPTION_TEN, FEEDMOENY_MENU_OPTION_TREE, FEEDMONEY_MENU_OPTION_EXIT };
	private static final String[] YESNO_MENU_OPTIONS = { YESNO_MENU_OPTION_YES, YESNO_MENU_OPTION_NO };

	private Menu menu;
	private double moneyIn;
	private int currentQuantity;
	private int runCounter;
	List<Inventory> inventoryList = new ArrayList<>();
	FileLog transactions = new FileLog();
	SalesReport sales = new SalesReport();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
		File logFile = new File("log.txt");
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.out.println("File log exists");
			}
		}
		if (runCounter < 1) {
			File inventory = new File("vendingmachine.csv");
			Scanner invScan = new Scanner(inventory);
			while (invScan.hasNextLine()) {
				String[] invData = invScan.nextLine().split("\\|");
				if (invData[3].equals("Chip")) {
					Inventory chipType = new Chip(invData[0], invData[1], Double.parseDouble(invData[2]), invData[3]);
					inventoryList.add(chipType);
				}
			}
			runCounter = 1;
			invScan.close();
		} // run counter bracket
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				purchaseMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("\nThank you.");
				System.exit(1);
			} else if (choice.equals(MAIN_MENU_OPTION_HIDDEN)) {
				sales.salesReport();
			}
		}
	}

	public void displayItems() {
		// Prints out the vending machine inventory from csv file
		String soldOut = "\"Sold Out\"";
		System.out.println();
		System.out.println(String.format("%-19s", "|Slot ID|") + String.format("%-19s", "|Item|")
				+ String.format(" |Price|\t\t") + String.format("%-19s", "|Qty|"));
		for (Inventory item : inventoryList) {
			if (item.getQuantity() == 0) {
				item.setItemName(soldOut);
			}
			System.out.println(String.format("%-19s", item.getSlotId()) + String.format("%-19s", item.getItemName())
					+ "  " + String.format("%.2f", item.getPrice()) + "\t\t  " + String.format("%-19s", item.getQuantity()));
		}
	}

	public void purchaseMenu() throws FileNotFoundException {
		// Menu to either add money, select items, or finish with purchase
		String purchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
		if (purchase.equals(PURCHASE_MENU_OPTION_MONEY)) {
			feedMoney();
		} else if (purchase.equals(PURCHASE_MENU_OPTION_PRODUCT)) {
			selectPurchase();
		} else if (purchase.equals(PURCHASE_MENU_OPTION_FINISH)) {
			finishTransaction();

		}
	}

	public void feedMoney() throws FileNotFoundException {
		// Menu to add money to vending machine
		String purchase = (String) menu.getChoiceFromOptions(FEEDMONEY_MENU_OPTIONS);
		if (purchase.equals(FEEDMONEY_MENU_OPTION_ONE)) {
			moneyIn += 6000;
			transactions.logFeedMoney(moneyIn);
		} else if (purchase.equals(FEEDMONEY_MENU_OPTION_TWO)) {
			moneyIn += 8000;
			transactions.logFeedMoney(moneyIn);
		} else if (purchase.equals(FEEDMONEY_MENU_OPTION_FIVE)) {
			moneyIn += 10000;
			transactions.logFeedMoney(moneyIn);
		} else if (purchase.equals(FEEDMONEY_MENU_OPTION_TEN)) {
			moneyIn += 20000;
			transactions.logFeedMoney(moneyIn);
		}
		else if (purchase.equals(FEEDMOENY_MENU_OPTION_TREE)) {
			moneyIn += 50000;
			transactions.logFeedMoney(moneyIn);
		}
		else if (purchase.equals(FEEDMONEY_MENU_OPTION_EXIT)) {
			System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
			purchaseMenu();
		}
		System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
		feedMoney();
	}

	public String selectPurchase() throws FileNotFoundException {
		// Menu to select items for purchase
		displayItems();
		System.out.println("Current balance: Rp" + String.format("%.2f", moneyIn));
		Scanner slotLocation = new Scanner(System.in);

		System.out.print("\nSelect item by Slot ID: ");
		String slot = slotLocation.nextLine().toUpperCase();
		for (Inventory item : inventoryList) {
			currentQuantity = item.getQuantity();
			if (slot.equals(item.getSlotId()) && currentQuantity == 0) {
				System.out.println("\nItem is sold out.");
				selectPurchase();
			}
			if (slot.equals(item.getSlotId()) && moneyIn < item.getPrice()) {
				insufficientFunds();
			} else {
				selectSlotId(slot, item);
			}
		}
		System.out.println("\nDo you want to select an item?; ");
		String yesNo = (String) menu.getChoiceFromOptions(YESNO_MENU_OPTIONS);
		if (yesNo.equals(YESNO_MENU_OPTION_YES)) {
			selectPurchase();
		} else if (yesNo.equals(YESNO_MENU_OPTION_NO)) {
			purchaseMenu();
		}
		slotLocation.close();
		return slot;
	}

	public void insufficientFunds() throws FileNotFoundException {
		System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
		System.out.println("\nInsufficient Funds!");
		System.out.println("Do you want to add more money?");
		String noYes = (String) menu.getChoiceFromOptions(YESNO_MENU_OPTIONS);
		if (noYes.equals(YESNO_MENU_OPTION_YES)) {
			feedMoney();
		} else if (noYes.equals(YESNO_MENU_OPTION_NO)) {
			System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
			purchaseMenu();
		}
	}

	public void selectSlotId(String slot, Inventory item) throws FileNotFoundException {
		// Call method here
		if (slot.equals(item.getSlotId())) {
			moneyIn -= item.getPrice();
			sales.transactionsReport(item.getItemName(), item.getPrice());
			item.setQuantity(currentQuantity - 1);
			if (item.getItemType().equals("Chip")) {
				System.out.println("\nCrunch Crunch, Yum!");
				transactions.logPurchase(item.getItemName(), slot, moneyIn);
				System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
				System.out.println("\nDo you want to select another item?; ");
				String noYes = (String) menu.getChoiceFromOptions(YESNO_MENU_OPTIONS);
				if (noYes.equals(YESNO_MENU_OPTION_YES)) {
					selectPurchase();
				} else if (noYes.equals(YESNO_MENU_OPTION_NO)) {
					System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
					purchaseMenu();
				}
			} else if (item.getItemType().equals("Candy")) {
				System.out.println("\nMunch Munch, Yum!");
				transactions.logPurchase(item.getItemName(), slot, moneyIn);
				System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
				System.out.println("\nDo you want to select another item?; ");
				String noYes = (String) menu.getChoiceFromOptions(YESNO_MENU_OPTIONS);
				if (noYes.equals(YESNO_MENU_OPTION_YES)) {
					selectPurchase();
				} else if (noYes.equals(YESNO_MENU_OPTION_NO)) {
					System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
					purchaseMenu();
				}
			} else if (item.getItemType().equals("Drink")) {
				System.out.println("\nGlug Glug, Yum!");
				transactions.logPurchase(item.getItemName(), slot, moneyIn);
				System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
				System.out.println("\nDo you want to select another item?; ");
				String noYes = (String) menu.getChoiceFromOptions(YESNO_MENU_OPTIONS);
				if (noYes.equals(YESNO_MENU_OPTION_YES)) {
					selectPurchase();
				} else if (noYes.equals(YESNO_MENU_OPTION_NO)) {
					System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
					purchaseMenu();
				}
			} else if (item.getItemType().equals("Gum")) {
				System.out.println("\nChew Chew, Yum!");
				transactions.logPurchase(item.getItemName(), slot, moneyIn);
				System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
				System.out.println("\nDo you want to select another item?; ");
				String noYes = (String) menu.getChoiceFromOptions(YESNO_MENU_OPTIONS);
				if (noYes.equals(YESNO_MENU_OPTION_YES)) {
					selectPurchase();
				} else if (noYes.equals(YESNO_MENU_OPTION_NO)) {
					System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
					purchaseMenu();
				}
			}
		}
	}

	public void finishTransaction() throws FileNotFoundException {
		transactions.logChangeBack(moneyIn);
		changeBack();
		System.out.println("\nCurrent balance: Rp" + String.format("%.2f", moneyIn));
		run();
	}

	public void changeBack() throws FileNotFoundException {
		if (moneyIn > 0) {
			String quarter = "Quarter";
			int quarterCount = 0;
			String dime = "dime";
			int dimeCount = 0;
			String nickel = "nickel";
			int nickelCount = 0;

			while (moneyIn >= 0.24) {
				quarterCount++;
				moneyIn -= 0.25;
			}
			while (moneyIn >= 0.09) {
				dimeCount++;
				moneyIn -= 0.10;
			}
			while (moneyIn > 0.04) {
				nickelCount++;
				moneyIn -= 0.05;
			}
			if (quarterCount > 1) {
				System.out.println("\n" + quarterCount + " " + quarter + "s returned");
			} else if (quarterCount == 1) {
				System.out.println("\n" + quarterCount + " " + quarter + " returned");
			}
			if (dimeCount > 1) {
				System.out.println("\n" + dimeCount + " " + dime + "s returned");
			} else if (dimeCount == 1) {
				System.out.println("\n" + dimeCount + " " + dime + " returned");
			}
			if (nickelCount > 1) {
				System.out.println("\n" + nickelCount + " " + nickel + "s returned");
			} else if (nickelCount == 1) {
				System.out.println("\n" + nickelCount + " " + nickel + " returned");
			}
		}
		transactions.logChangeBack(moneyIn);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
