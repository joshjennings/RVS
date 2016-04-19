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
public class Recirculator extends Vessel {

	private String model, description;
	private int diameter;
	private BigDecimal length;
	private Orient orientation;
	private BigDecimal priceList;
	private Material material;

	public Recirculator(String model) {
		super(model);
		this.model = model;
		this.diameter = 0;
	}

	@Override
	public String formattedModel(HashMap<Integer,Integer> mapDiameterLength) { //this method assumes that vessel diameter is defined
		String model, diameter, length;

		Message.consoleMessage("Formatting model name");

		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();

		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			model = "HR";
			if (length.equals("")) {
				return model + diameter;
			}
		} else if (this.getOrientation().equals(Orient.VERTICAL)) {
			model = "VR";
			if (length.equals("")) {
				return model + diameter;
			}
		} else {
			model = "Recirculator";
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

		return orientation + "Recirculator";
	}

	@Override
	public String toString() {
		return this.getModel();
	}
	
}