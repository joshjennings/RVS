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
public class BareVessel extends Vessel {

	private String model, description;
	private int diameter;
	private BigDecimal length;
	private Orient orientation;
	private BigDecimal priceList;
	private Material material;

	public BareVessel(String model) {
		super(model);
		this.model = model;
	}

	@Override
	public String getModel() {
		return this.model;
	}
	
	@Override
	public String formattedModel(HashMap<Integer,Integer> mapDiameterLength) {
		String model, diameter, length;

		Message.consoleMessage("Formatting model name");

		model = "Vessel";
		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();


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

		return orientation + "Vessel";
	}
}
