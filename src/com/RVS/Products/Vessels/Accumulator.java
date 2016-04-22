package com.RVS.Products.Vessels;

import com.Josh.Message;
import com.RVS.Products.Vessel;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Josh on 3/9/16.
 *
 * @author Josh Jennings
 */
public class Accumulator extends Vessel {

	private String model, description;
	private int diameter;
	private BigDecimal length;
	private Orient orientation;
	private BigDecimal priceList;
	private Material material;

	public Accumulator(String model) {
		super(model);
		this.model = model;
	}

//	public String getModel() {
//		return this.model;
//	}
//
//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public String getDescription() {
//		return this.description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public int getDiameter() {
//		return this.diameter;
//	}
//
//	public void setDiameter(int diameter) {
//		this.diameter = diameter;
//	}

	public String formattedModelString(HashMap<Integer, Integer> mapDiameterLength) {
		String model, diameter, length;

		Message.consoleMessage("Formatting model name");

		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();

		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			model = "HA";
			if (length.equals("")) {
				return model + diameter;
			}
		} else if (this.getOrientation().equals(Orient.VERTICAL)) {
			model = "VA";
			if (length.equals("")) {
				return model + diameter;
			}
		} else {
			model = "Accumulator";
			if (length.equals("")) {
				return model + " " + diameter;
			}
		}

		return model + diameter + "-" + length;
	}

	@Override
	public String formattedDescription() {
		String orientation;

		if (this.getOrientation() == Orient.HORIZONTAL) {
			orientation = "Horizontal ";
		} else if (this.getOrientation() == Orient.VERTICAL) {
			orientation = "Vertical ";
		} else {
			orientation = "";
		}

		return orientation + "Accumulator";
	}
	
}
