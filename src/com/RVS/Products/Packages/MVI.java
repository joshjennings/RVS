package com.RVS.Products.Packages;

import com.RVS.Products.Package;
import com.RVS.Products.Vessels.Intercooler;
import com.RVS.Products.Vessels.OilPot;

/**
 * Created by manufacturing9 on 4/29/2016.
 *
 * @author Josh Jennings
 */
public class MVI extends Package {

	Intercooler intercooler;
	OilPot oilPot;

	public MVI(String model) {
		super(model);
	}

	@Override
	public Boolean isModelComplete() {
		return false;
	}

}
