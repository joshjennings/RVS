package com.RVS.Accessories;

import com.RVS.Products.Product;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * This class defines a pump object.
 * @author Josh Jennings
 */
public class Pump extends Product {

	private String modelRVS;
	private Double priceList;

	public Pump() {
		modelRVS = "";
		priceList = 0.0;
	}

	public Pump(String model) {
		this.modelRVS = model;
	}

	public Pump(String model, Double price) {
		this.modelRVS = model;
		this.priceList = price;
	}

	@Override
	public String toString() {
		return this.modelRVS;
	}

	public Pane editWindow() {
		GridPane controlPanelEditPane = new GridPane();

		//Labels
		Label lblModel = new Label("Model");

		//Controls
		TextField inputModel = new TextField(this.modelRVS);

		controlPanelEditPane.addRow(0, lblModel, inputModel);

		return controlPanelEditPane;
	}
}
