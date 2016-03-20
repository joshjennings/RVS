package com.RVS.Products;

import com.Josh.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * This class is initiated during startup and pre-loads the standard products into a list.
 *
 * @author Josh Jennings
 */
public abstract class Product {

	String model, description;
	Material material;

	public Product() {}

	public Product(String model) {
		this.model = model;
	}

	@Override public String toString() {
		return this.model;
	}

	@SuppressWarnings("unused")
	public enum Material {
		CARBON, STAINLESS, COMBINATION
	}

	@SuppressWarnings("unused")
	public enum Orient {
		HORIZONTAL, VERTICAL, UNASSIGNED
	}

	public String getModel() {
		Message.consoleMessage("Collecting product model: " + this.model);
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		Message.consoleMessage("Collecting product description: " + this.description);
		if (this.description == null) {
			return "";
		} else {
			return this.description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	//	@SuppressWarnings("unused")
//	public BigDecimal getPriceList() {
//		Message.consoleMessage("Collecting product price: " + this.priceList.toString());
//		return this.priceList;
//	}
//
//	@SuppressWarnings("unused")
//	/**
//	 * This method is built solely for the TableColumn such that the price is correctly formatted.
//	 * @return Returns a string that is formatted with the current locale's currency symbol and commas.
//	 */
//	public String getPriceListFormatted() {
//		//Set locale and number formatter object
//		Locale currentLocale = new Locale("en","US");
//		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
//		//output to console
//		Message.consoleMessage("Collecting product price: " + currencyFormatter.format(this.priceList));
//		//return formatted price
//		return currencyFormatter.format(this.priceList);
//	}

	@SuppressWarnings("unused")
	public static ObservableList<String> constructListOfStandardProducts() {
		List<String> productList = new ArrayList<>();

		productList.add("MRP");
		productList.add("MPC");
		productList.add("MVI");
		productList.add("HPR");
		productList.add("TSR");
		productList.add("Recirculator");
		productList.add("Intercooler");
		productList.add("Accumulator");
		productList.add("Surge Drum");
		productList.add("Oil Pot");

		return FXCollections.observableList(productList);
	}



//	public static Product createNewBareMRP() {
//		return new Product("MRP");
//	}
//
//	public static Product createNewBareMPC() {
//		return new Product("MPC");
//	}
//
//	public static Product createNewBareMVI() {
//		return new Product("MVI");
//	}
//
//	public static Product createNewBareHPR() {
//		return new Product("HPR");
//	}
//
//	public static Product createNewBareTSR() {
//		return new Product("TSR");
//	}
//
//	public static Product createNewBareRecirculator() {
//		return new Product("Recirculator");
//	}
//
//	public static Product createNewBareIntercooler() {
//		return new Product("Intercooler");
//	}
//
//	public static Product createNewBareAccumulator() {
//		return new Product("Accumulator");
//	}
//
//	public static Product createNewBareSD() {
//		return new Product("Surge Drum");
//	}
//
//	public static Product createNewBareOP() {
//		return new Product("Oil Pot");
//	}


}
