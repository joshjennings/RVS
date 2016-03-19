package com.RVS.Products.Vessels;

import com.Josh.Message;
import com.RVS.Products.Vessel;

/**
 * Created by Josh on 3/9/16.
 */
public class Recirculator extends Vessel {

	public Recirculator(String model) {
		super(model);

	}

	@Override
	public String formattedModel() {
		String model, diameter, length;

		//TODO:
		Message.consoleMessage("Formatting model");

		diameter = (this.getDiameter() != 0) ? String.valueOf(this.getDiameter()) : "";
		length = (this.getLength() != null) ? this.getLength().toString() : "";


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
