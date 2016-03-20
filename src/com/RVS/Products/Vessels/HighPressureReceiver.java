package com.RVS.Products.Vessels;

import com.Josh.Message;
import com.RVS.Products.Vessel;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Josh on 3/9/16.
 */
public class HighPressureReceiver extends Vessel {

	private String description;
	private int diameter;
	private BigDecimal length;
	private Orient orientation;
	private BigDecimal priceList;
	private Material material;

	public HighPressureReceiver(String model) {
		super(model);

	}

	public String formattedModel(HashMap<Integer,Integer> mapDiameterLength) {
		String model, diameter, length;

		Message.consoleMessage("Formatting model name");

		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();

		Message.consoleMessage("Model: " + diameter + "-" + length);


		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			model = "HHPR";
			if (length.equals("")) {
				return model + diameter;
			}
		} else if (this.getOrientation().equals(Orient.VERTICAL)) {
			model = "VHPR";
			if (length.equals("")) {
				return model + diameter;
			}
		} else {
			model = "High Pressure Receiver";
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

		return orientation + "High Pressure Receiver";
	}
	
}
