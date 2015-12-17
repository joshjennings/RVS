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
public class Product {

	String model, description;
	int diameter;
	BigDecimal length;
	Orient orientation;
	BigDecimal priceList;
	Material material;


	/**
	 * Standard Product() constructor. Initializes to a horizontal carbon vessel with zero dimensions.
	 */
	public Product() {
		this.model = "";
		this.diameter = 0;
		this.length = new BigDecimal(0.0);
		this.orientation = Orient.HORIZONTAL;
		this.priceList = new BigDecimal(0.0);
		this.material = Material.CARBON;
	}

	public Product(String model, int diameter, Orient orientation) {
		this.model = model;
		this.diameter = diameter;
		this.orientation = orientation;
	}

//	public Product(String model, int diameter, BigDecimal length, Orient orientation, Material material) {
//		this.model = model;
//		this.diameter = diameter;
//		this.length = length;
//		this.orientation = orientation;
//		this.material = material;
//	}

	@SuppressWarnings("unused")
	/**
	 * This constructor builds a standard product with the following parameters.
	 * @param model Model of the product in String format.
	 * @param diameter Diameter in int format.
	 * @param length Length of the product in double format.
	 * @param orientation Vessel orientation using the Orient enumeration.
	 * @param priceList Price of the selected item.
	 * @param material Material of the product using the Material enumeration.
	 */
	public Product(String model, int diameter, BigDecimal length, Orient orientation, BigDecimal priceList, Material material) {
		this.model = model;
		this.diameter = diameter;
		this.length = length;
		this.orientation = orientation;
		this.priceList = priceList;
		this.material = material;
	}

	@SuppressWarnings("unused")
	public enum Material {
		CARBON, STAINLESS, COMBINATION
	}

	@SuppressWarnings("unused")
	public enum Orient {
		HORIZONTAL, VERTICAL
	}

	@SuppressWarnings("unused")
	public String getModel() {
		Message.consoleMessage("Collecting product model: " + this.model);
		return this.model;
	}

	@SuppressWarnings("unused")
	public String getDescription() {
		Message.consoleMessage("Collecting product description: " + this.description);
		if (this.orientation == Orient.VERTICAL) {
			return "Vertical " + this.model + this.diameter;
		} else if (this.orientation == Orient.HORIZONTAL) {
			return "Horizontal " + this.model + this.diameter;
		} else {
			return "Unknown Orientation";
		}
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

	@SuppressWarnings("unused")
	public static ObservableList<Product> constructListOfStandardProducts() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("MRP",48,Orient.VERTICAL));
		productList.add(new Product("MRP",72,Orient.VERTICAL));
		productList.add(new Product("MPC",24,Orient.HORIZONTAL));

		return FXCollections.observableList(productList);
	}
}
