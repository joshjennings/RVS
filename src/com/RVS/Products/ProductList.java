package com.RVS.Products;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.Josh.Message;

/**
 * This class defines what a list of products should look like: model, description, and price.
 * @author Josh Jennings
 */
public class ProductList {
	String model, description;
	BigDecimal priceList;

//	public ProductList() {
//		Message.consoleMessage("ProductList instance instantiated. Empty.");
//		model = "";
//		description = "";
//		priceList = new BigDecimal(0.0);
//	}

	public ProductList(String model, String description, BigDecimal priceList) {
		Message.consoleMessage("ProductList instance instantiated. Model:" + model + " | Desc: " + description +  " | Price: " + priceList);
		this.model = model;
		this.description = description;
		this.priceList = priceList;
	}

	@SuppressWarnings("unused")
	public String getModel() {
		Message.consoleMessage("Collecting product model: " + this.model);
		return this.model;
	}

	@SuppressWarnings("unused")
	public String getDescription() {
		Message.consoleMessage("Collecting product description: " + this.description);
		return this.description;
	}

	@SuppressWarnings("unused")
	public BigDecimal getPriceList() {
		Message.consoleMessage("Collecting product price: " + this.priceList.toString());
		return this.priceList;
	}

	@SuppressWarnings("unused")
	/**
	 * This method is built solely for the TableColumn such that the price is correctly formatted.
	 * @return Returns a string that is formatted with the current locale's currency symbol and commas.
	 */
	public String getPriceListFormatted() {
		//Set locale and number formatter object
		Locale currentLocale = new Locale("en","US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
		//output to console
		Message.consoleMessage("Collecting product price: " + currencyFormatter.format(this.priceList));
		//return formatted price
		return currencyFormatter.format(this.priceList);
	}
}
