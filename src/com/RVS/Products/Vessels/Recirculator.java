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

	public Recirculator(String model) {
		super(model);
	}

//	@Override
//	public void setModel() {
//		String model;
//
//		if (this.getDiameter() != 0) {
//			if (this.getOrientation() == Orient.HORIZONTAL) {
//				model = "HR" + this.getDiameter() +
//			} else if (this.getOrientation() == Orient.VERTICAL) {
//
//			}
//		}
//		super.setModel(model);
//	}

	@Override
	public String formattedModelString(HashMap<Integer, Integer> mapDiameterLength) { //this method assumes that vessel diameter is defined
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
		String description, orientation;

		if (this.getOrientation() == Orient.HORIZONTAL) {
			orientation = "Horizontal ";
		} else if (this.getOrientation() == Orient.VERTICAL) {
			orientation = "Vertical ";
		} else {
			orientation = "";
		}

		description = orientation + "Recirculator";

		//this.setDescription(description);
		//instance description property should be set separately - we have getters/setters, use them!

		return description;
	}
	
}
