package com.RVS.Products.Vessels;

import com.Josh.Message;
import com.RVS.Products.Vessel;

import java.util.HashMap;

/**
 * Created by manufacturing9 on 4/29/2016.
 *
 * @author Josh Jennings
 */
public class OilPot extends Vessel {

	public OilPot(String model) {
		super(model, Orient.HORIZONTAL);
	}

	@Override
	public String formattedModelString(HashMap<Integer, Integer> mapDiameterLength) { //this method assumes that vessel diameter is defined
		String model, diameter, length;

		Message.consoleMessage("Formatting model name for oil pot");

		diameter = String.valueOf(this.getDiameter());
		length = mapDiameterLength.get(this.getDiameter()).toString();

		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			model = "HOP";
			if (length.equals("")) {
				return model + diameter;
			}
		} else if (this.getOrientation().equals(Orient.VERTICAL)) {
			model = "VOP";
			if (length.equals("")) {
				return model + diameter;
			}
		} else {
			model = "Oil Pot";
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

		description = orientation + "Oil Pot";

		return description;
	}

	@Override
	public HashMap<Integer, Integer> makeDiameterLengthMap() {
		HashMap<Integer, Integer> hashMap = new HashMap<>();

		HashMap<Integer, Double> mapHeadDepth = makeHeadDepthMap();

		mapHeadDepth.forEach((k, v) -> {
			hashMap.put(k, (int) Math.round(119.0 + (2.0 * v)));
			//Message.consoleMessage(k.toString() + " : " + hashMap.get(k).toString());
		});

		return hashMap;
	}
}
