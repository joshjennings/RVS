package com.RVS.Accessories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manufacturing9 on 2/4/2016.
 */
public class Feature {
	String model;

	public Feature(String model) {
		this.model = model;
	}

	public static ObservableList<String> constructListOfStandardFeatures() {
		List<String> productList = new ArrayList<>();

		productList.add("Pump");
		productList.add("Control Panel");

		return FXCollections.observableList(productList);
	}

	public static Feature createNewPump() { return new Feature("Pump"); }

	public static Feature createNewLFA() { return new Feature("LFA"); }

	public static Feature createNewLevelColumn() { return new Feature("Level Column"); }

	public static Feature createNewControlPanel() { return new Feature("Pump"); }

	public static Feature createNew() { return new Feature("Pump"); }
}
