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

	private String model, description;
	private Material material;
	private BigDecimal priceList, priceListFormatted;

	public Product() {}

	public Product(String model) {
		this.model = model;
		this.description = model;
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
		return this.model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
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
		Locale currentLocale = new Locale("en", "US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
		//output to console
		Message.consoleMessage("Collecting product price: " + currencyFormatter.format(this.priceList));
		//return formatted price
		return currencyFormatter.format(this.priceList);
	}

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
}
