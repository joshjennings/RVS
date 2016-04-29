package com.RVS.Products.Packages;

import com.RVS.Products.Package;
import com.RVS.Products.Vessels.OilPot;
import com.RVS.Products.Vessels.SurgeDrum;

/**
 * Created by manufacturing9 on 4/29/2016.
 *
 * @author Josh Jennings
 */
public class MPC extends Package {

	SurgeDrum surgeDrum;
	OilPot oilPot;

	public Boolean isModelComplete() {
		return false;
	}
}
