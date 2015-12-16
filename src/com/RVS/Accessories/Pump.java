package com.RVS.Accessories;

/**
 * This class defines a pump object.
 * @author Josh Jennings
 */
public class Pump {

	private String modelRVS;
	private Double priceList;

	public Pump() {
		modelRVS = "";
		priceList = 0.0;
	}

	public Pump(String model, Double price) {
		this.modelRVS = model;
		this.priceList = price;
	}

}
