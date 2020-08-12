package com.arifhidayat.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {

	File transactionsFile = new File("transactions.txt");
	private double runningTotal;

	public void transactionsReport(String itemName, double itemPrice) throws FileNotFoundException {
		PrintWriter writer = null;
		String transaction = itemName + "|" + String.format("%.2f", itemPrice);
		writer = new PrintWriter(new FileOutputStream(transactionsFile.getAbsoluteFile(), true));
		writer.append(transaction + "\n");
		writer.flush();
		writer.close();
	}

	public void salesReport() throws FileNotFoundException {
		Map<String, Integer> salesMap = new HashMap<String, Integer>();

		Scanner salesScan = new Scanner(transactionsFile);

		while (salesScan.hasNextLine()) {
		String[] salesArray = salesScan.nextLine().split("\\|");

		for (int i = 0; i < salesArray.length; i++) {
			if (!salesMap.containsKey(salesArray[i])) {
				salesMap.put(salesArray[i], 1);
				i++;
			} else {
				int count = salesMap.get(salesArray[i]);
				salesMap.put(salesArray[i], count + 1);
				i++;
			}
		}
		}

		Date currentDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy h_mm_ss a");
		String dateTime = ft.format(currentDate);
		File salesReport = new File(dateTime + ".txt");
		if (!salesReport.exists()) {
			try {
				salesReport.createNewFile();
			} catch (IOException e) {
				System.out.println("File log exists");
			}
		}
		File sales = new File(dateTime + ".txt");

		PrintWriter writer = null;
		if(salesMap.containsKey("Biscuits")) {
			String salesLog = String.format("%-19s", "Biscuits ") + salesMap.get("Biscuits");
			try {
				writer = new PrintWriter(new FileOutputStream(sales.getAbsoluteFile(), true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.append(salesLog + "\n");
			writer.flush();
			double pctotal = 3.05 * salesMap.get("Biscuits");
			runningTotal += pctotal;
		}

		if(salesMap.containsKey("Chips")) {
			String salesLog = String.format("%-19s", "Chips ") + salesMap.get("Chips");
			try {
				writer = new PrintWriter(new FileOutputStream(sales.getAbsoluteFile(), true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.append(salesLog + "\n");
			writer.flush();
			double stotal = 3.05 * salesMap.get("Chips");
			runningTotal += stotal;
		}

		if(salesMap.containsKey("Tango")) {
			String salesLog = String.format("%-19s", "Tango ") + salesMap.get("Tango");
			try {
				writer = new PrintWriter(new FileOutputStream(sales.getAbsoluteFile(), true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.append(salesLog + "\n");
			writer.flush();
			double gwtotal = 3.05 * salesMap.get("Tango");
			runningTotal += gwtotal;
		}

		if(salesMap.containsKey("Oreo")) {
			String salesLog = String.format("%-19s", "Oreo ") + salesMap.get("Oreo");
			try {
				writer = new PrintWriter(new FileOutputStream(sales.getAbsoluteFile(), true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.append(salesLog + "\n");
			writer.flush();
			double cptotal = 3.05 * salesMap.get("Oreo");
			runningTotal += cptotal;
		}

		if(salesMap.containsKey("Chocolate")) {
			String salesLog = String.format("%-19s", "Chocolate ") + salesMap.get("Chocolate");
			try {
				writer = new PrintWriter(new FileOutputStream(sales.getAbsoluteFile(), true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.append(salesLog + "\n");
			writer.flush();
			double mptotal = 3.05 * salesMap.get("Chocolate");
			runningTotal += mptotal;
		}

		String salesLog = "Total Sales to Date: Rp" + String.format("%.2f", runningTotal);
		try {
			writer = new PrintWriter(new FileOutputStream(sales.getAbsoluteFile(), true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.append(salesLog + "\n");
		writer.flush();
		writer.close();
		salesScan.close();
	}

}
