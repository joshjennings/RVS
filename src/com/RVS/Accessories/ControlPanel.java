package com.RVS.Accessories;

import com.RVS.Products.Product;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Created by manufacturing9 on 2/23/2016.
 */
public class ControlPanel extends Product {
	private String model;

	public ControlPanel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return this.model;
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
