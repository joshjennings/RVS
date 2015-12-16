package com.RVS.Products;

import sun.plugin.liveconnect.OriginNotAllowedException;

import java.math.BigDecimal;

/**
 * This class is initiated during startup and pre-loads the standard products into a list.
 *
 * @author Josh Jennings
 */
public class Product {

	String model;
	int diameter;
	double length;
	Orient orientation;
	BigDecimal priceList;
	Material material;


	/**
	 * Standard Product() constructor. Initializes to a horizontal carbon vessel with zero dimensions.
	 */
	public Product() {
		this.model = "";
		this.diameter = 0;
		this.length = 0.0;
		this.orientation = Orient.HORIZONTAL;
		this.priceList = new BigDecimal(0.0);
		this.material = Material.CARBON;
	}

	/**
	 * This constructor builds a standard product with the following parameters.
	 * @param model Model of the product in String format.
	 * @param diameter Diameter in int format.
	 * @param length Length of the product in double format.
	 * @param orientation Vessel orientation using the Orient enumeration.
	 * @param priceList Price of the selected item.
	 * @param material Material of the product using the Material enumeration.
	 */
	public Product(String model, int diameter, double length, Orient orientation, BigDecimal priceList, Material material) {
		this.model = model;
		this.diameter = diameter;
		this.length = length;
		this.orientation = orientation;
		this.priceList = priceList;
		this.material = material;
	}

	public enum Material {
		CARBON, STAINLESS, COMBINATION
	}

	public enum Orient {
		HORIZONTAL, VERTICAL
	}
}
