package com.RVS.Accessories;

import com.RVS.Products.Product;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by manufacturing9 on 2/23/2016.
 *
 * @author Josh Jennings
 */
public class ControlPanel extends Product {

	public ControlPanel(String model) { super(model); }

	public Pane editWindow() {
		GridPane controlPanelEditPane = new GridPane();

		//Labels
		Label lblModel = new Label("Model");

		//Controls
		TextField inputModel = new TextField(this.getModel());

		controlPanelEditPane.addRow(0, lblModel, inputModel);

		return controlPanelEditPane;
	}

	@Override
	public Boolean isModelComplete() {
		return false;
	}
}
