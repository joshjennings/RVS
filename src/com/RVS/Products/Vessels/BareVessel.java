package com.RVS.Products.Vessels;

import com.RVS.Products.Vessel;

import java.util.HashMap;

/**
 * Created by Josh on 3/9/16.
 */
public class BareVessel extends Vessel {

	public BareVessel(String model) {
		super(model);
	}
	
	@Override
	public String formattedModel(HashMap<Integer,Integer> mapDiameterLength) {
		return null;
	}
}
