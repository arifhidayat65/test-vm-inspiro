package com.arifhidayat.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLog {

	File logFile = new File("log.txt");

	public void logFeedMoney(double moneyIn) throws FileNotFoundException {
		//convert moneyIn to string
		PrintWriter writer = null;
		String feedMoneyLog = currentDateTime() + " FEED MONEY: Rp" + String.format("%.2f", moneyIn);
		writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
		writer.append(feedMoneyLog + "\n");
		writer.flush();
		writer.close();
	}


	public void logPurchase(String itemName, String slotId, double moneyIn) throws FileNotFoundException {
		PrintWriter writer = null;
		String purchaseLog = currentDateTime() + " " + itemName + " " + slotId + " Rp" + String.format("%.2f", moneyIn);
		writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
		writer.append(purchaseLog + "\n");
		writer.flush();
		writer.close();
	}


	public void logChangeBack(double moneyIn) throws FileNotFoundException {
		PrintWriter writer = null;//new PrintWriter(logFile.getAbsoluteFile());
		String changeBackLog = currentDateTime() + " GIVE CHANGE: Rp" + String.format("%.2f", moneyIn);
		writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
		writer.append(changeBackLog + "\n");
		writer.flush();
		writer.close();
	}

	public String currentDateTime() {
		Date dNow = new Date( );
	      SimpleDateFormat ft =
	      new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss a");

	      String dateTime = ft.format(dNow);
	   return dateTime;
	}
}
