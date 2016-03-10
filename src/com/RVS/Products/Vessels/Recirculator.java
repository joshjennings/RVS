package com.RVS.Products.Vessels;

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
		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			model = "HR";
		} else if (this.getOrientation().equals(Orient.VERTICAL)) {
			model = "VR";
		} else {
			model = "Recirculator";
		}

		diameter = (this.getDiameter() != 0) ? String.valueOf(this.getDiameter()) : "";
		length = (!this.getLength().equals(null)) ? this.getLength().toString() : "";

		return model + diameter + "-" + length;
	}
	
}
