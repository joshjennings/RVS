package com.RVS.Accessories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is initiated during startup and pre-loads the standard products into a list.
 *
 * @author Josh Jennings
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
		productList.add("Level Column");

		return FXCollections.observableList(productList);
	}

	@SuppressWarnings("unused")
	public static Feature createNewPump() { return new Feature("Pump"); }

	@SuppressWarnings("unused")
	public static Feature createNewLFA() { return new Feature("LFA"); }

	@SuppressWarnings("unused")
	public static Feature createNewLevelColumn() { return new Feature("Level Column"); }

	@SuppressWarnings("unused")
	public static Feature createNewControlPanel() { return new Feature("Pump"); }

	@SuppressWarnings("unused")
	public static Feature createNew() { return new Feature("Pump"); }
}
