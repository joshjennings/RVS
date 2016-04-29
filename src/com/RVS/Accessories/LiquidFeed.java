package com.RVS.Accessories;

import com.RVS.Products.Product;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by Josh on 12/16/2015.
 *
 * @author Josh Jennings
 */
public class LiquidFeed extends Product {
	private String model;

	public LiquidFeed(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return this.model;
	}

	public Pane editWindow() { //TODO: edit this to display correct window
		GridPane controlPanelEditPane = new GridPane();

		//Labels
		Label lblModel = new Label("Model");

		//Controls
		TextField inputModel = new TextField(this.model);

		controlPanelEditPane.addRow(0, lblModel, inputModel);

		return controlPanelEditPane;
	}

	@Override
	public Boolean isModelComplete() {
		return false;
	}
}
