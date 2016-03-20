package com.RVS.Products.Vessels;

import com.Josh.Message;
import com.RVS.Products.Vessel;

import java.util.HashMap;

/**
 * Created by Josh on 3/9/16.
 */
public class SurgeDrum extends Vessel {



	@Override
	public String formattedModel(HashMap<Integer,Integer> mapDiameterLength) { //this method assumes that vessel diameter is defined
		String model, diameter, length;

		Message.consoleMessage("Formatting model name");

		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();

		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			model = "HSD";
			if (length.equals("")) {
				return model + diameter;
			}
		} else if (this.getOrientation().equals(Orient.VERTICAL)) {
			model = "VSD";
			if (length.equals("")) {
				return model + diameter;
			}
		} else {
			model = "Surge Drum";
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

		return orientation + "Surge Drum";
	}
	
}
