package com.RVS.Products.Vessels;

import com.Josh.Message;
import com.RVS.Products.Vessel;

import java.util.HashMap;

/**
 * Created by Josh on 3/9/16.
 */
public class Recirculator extends Vessel {

	public Recirculator(String model) {
		super(model);

	}

	@Override
	public String formattedModel(HashMap<Integer,Integer> mapDiameterLength) { //this method assumes that vessel diameter is defined
		String model, diameter, length;

		Message.consoleMessage("Formatting model name");

		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();

//		diameter = (this.getDiameter() != 0) ? String.valueOf(this.getDiameter()) : "";
//		length = (this.getLength() != null) ? mapDiameterLength.get(this.getDiameter()).toString() : "";

		Message.consoleMessage("Model: " + diameter + "-" + length);


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
	
}
