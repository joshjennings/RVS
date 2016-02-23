package com.RVS.Accessories;

import com.RVS.Products.Product;

/**
 * This class defines a pump object.
 * @author Josh Jennings
 */
public class Pump extends Product {

	private String modelRVS;
	private Double priceList;

	public Pump() {
		modelRVS = "";
		priceList = 0.0;
	}

	public Pump(String model) {
		this.modelRVS = model;
	}

	public Pump(String model, Double price) {
		this.modelRVS = model;
		this.priceList = price;
	}

}
