package com.RVS.Products.Packages;

import com.RVS.Accessories.Pump;
import com.RVS.Products.Package;
import com.RVS.Products.Vessels.OilPot;
import com.RVS.Products.Vessels.Recirculator;

/**
 * Created by manufacturing9 on 4/29/2016.
 *
 * @author Josh Jennings
 */
public class MRP extends Package {

	Recirculator recirculator;
	OilPot oilPot;
	Pump pumps;

	public Boolean isModelComplete() {
		return false;
	}
}
