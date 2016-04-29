package com.RVS.Products;

import com.Josh.Message;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	private BigDecimal priceList;

	public abstract Boolean isModelComplete();

	public Product() {}

	public Product(String model) {
		this.model = model;
		this.description = model;
		this.priceList = BigDecimal.valueOf(0.0);
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

	public void setPriceList(BigDecimal price) {
		this.priceList = price;
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
		//return formatted price
		return currencyFormatter.format(this.priceList);
	}

	public void setPriceListFormatted(Double price) {
		Message.consoleMessage("YOU'VE REACHED THE PRODUCT.SETPRICELISTFORMATTED METHOD!!!");
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

	public static HashMap<String, BigDecimal> constructPriceList() {
		HashMap<String, BigDecimal> hashMap = new HashMap<>();

		hashMap.put("HR06-126", BigDecimal.valueOf(60));
		hashMap.put("HR08-127", BigDecimal.valueOf(80));
		hashMap.put("HR10-129", BigDecimal.valueOf(100));
		hashMap.put("HR108-177", BigDecimal.valueOf(1080));
		hashMap.put("HR120-183", BigDecimal.valueOf(1200));
		hashMap.put("HR12-130", BigDecimal.valueOf(120));
		hashMap.put("HR14-130.625", BigDecimal.valueOf(140));
		hashMap.put("HR144-195", BigDecimal.valueOf(1440));
		hashMap.put("HR16-132", BigDecimal.valueOf(160));
		hashMap.put("HR18-132.65", BigDecimal.valueOf(180));
		hashMap.put("HR20-134", BigDecimal.valueOf(200));
		hashMap.put("HR24-135", BigDecimal.valueOf(240));
		hashMap.put("HR30-138", BigDecimal.valueOf(300));
		hashMap.put("HR36-141", BigDecimal.valueOf(360));
		hashMap.put("HR42-144", BigDecimal.valueOf(420));
		hashMap.put("HR48-147", BigDecimal.valueOf(480));
		hashMap.put("HR54-150", BigDecimal.valueOf(540));
		hashMap.put("HR60-153", BigDecimal.valueOf(600));
		hashMap.put("HR66-156", BigDecimal.valueOf(660));
		hashMap.put("HR72-159", BigDecimal.valueOf(720));
		hashMap.put("HR84-165", BigDecimal.valueOf(840));
		hashMap.put("HR96-171", BigDecimal.valueOf(960));
		hashMap.put("VR06-126", BigDecimal.valueOf(66));
		hashMap.put("VR08-127", BigDecimal.valueOf(88));
		hashMap.put("VR10-129", BigDecimal.valueOf(110));
		hashMap.put("VR108-177", BigDecimal.valueOf(1188));
		hashMap.put("VR120-183", BigDecimal.valueOf(1320));
		hashMap.put("VR12-130", BigDecimal.valueOf(132));
		hashMap.put("VR14-130.625", BigDecimal.valueOf(154));
		hashMap.put("VR144-195", BigDecimal.valueOf(1584));
		hashMap.put("VR16-132", BigDecimal.valueOf(176));
		hashMap.put("VR18-132.65", BigDecimal.valueOf(198));
		hashMap.put("VR20-134", BigDecimal.valueOf(220));
		hashMap.put("VR24-135", BigDecimal.valueOf(264));
		hashMap.put("VR30-138", BigDecimal.valueOf(330));
		hashMap.put("VR36-141", BigDecimal.valueOf(396));
		hashMap.put("VR42-144", BigDecimal.valueOf(462));
		hashMap.put("VR48-147", BigDecimal.valueOf(528));
		hashMap.put("VR54-150", BigDecimal.valueOf(594));
		hashMap.put("VR60-153", BigDecimal.valueOf(660));
		hashMap.put("VR66-156", BigDecimal.valueOf(726));
		hashMap.put("VR72-159", BigDecimal.valueOf(792));
		hashMap.put("VR84-165", BigDecimal.valueOf(924));
		hashMap.put("VR96-171", BigDecimal.valueOf(1056));


		return hashMap;
	}
}
