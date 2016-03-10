package com.RVS.Products.Vessels;

import com.RVS.Products.Vessel;

/**
 * Created by Josh on 3/9/16.
 */
public class BareVessel extends Vessel {

	public BareVessel(String model) {
		super(model);
	}
	
	@Override
	public String formattedModel() {
		return null;
	}
}
