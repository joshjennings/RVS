package com.RVS.Products;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.math.BigDecimal;

/**
 * Created by Josh on 12/16/2015.
 * @author Josh Jennings
 */
public class Vessel extends Product {
	String model, description;
	int diameter;
	BigDecimal length;
	Orient orientation;
	BigDecimal priceList;
	Material material;

	public Vessel() {
		super();
	}

	public Vessel(String model) {
		this.model = model;
	}

	public Pane editWindow() {
		GridPane controlPanelEditPane = new GridPane();

		//Labels
		Label lblModel = new Label("Model");

		//Controls
		TextField inputModel = new TextField(this.model);

		controlPanelEditPane.addRow(0, lblModel, inputModel);

		return controlPanelEditPane;
	}
}
