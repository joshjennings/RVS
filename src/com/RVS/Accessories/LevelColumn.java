package com.RVS.Accessories;

import com.Josh.Message;
import com.RVS.Products.Product;
import com.sun.activation.registries.MailcapParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Josh on 12/16/2015.
 *
 * @author Josh Jennings
 */
public class LevelColumn extends Product {

	Integer levelEyes;
	Boolean withProbe;

	public LevelColumn(String model) {
		super(model);
		this.levelEyes = 1;
		this.withProbe = false;
	}

	public void setLevelEyes(Integer quantityOfLevelEyes) {
		this.levelEyes = quantityOfLevelEyes;
	}

	public Pane editWindow() {
		Message.consoleMessage("Displaying edit window for Level Column.");

		GridPane levelColumnEditPane = new GridPane();

		//Labels
		Label lblSize = new Label("Size");
		Label lblModel = new Label("Model");

		//Controls
		ComboBox<Integer> levelEyeSelectionBox = new ComboBox<>();
		TextField inputModel = new TextField(this.getModel());

		//Fill controls
		ObservableList<Integer> levelEyeList = FXCollections.observableList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
		levelEyeSelectionBox.setItems(levelEyeList);
		inputModel.setEditable(false);

		//Listeners
		levelEyeSelectionBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			this.setLevelEyes(newValue);
			this.setModel("#" + newValue + " Level column");
			if (newValue == 1) {
				this.setDescription("Level column with " + newValue + " level eye");
			} else {
				this.setDescription("Level column with " + newValue + " level eyes");
			}
			inputModel.setText(this.getModel());
		});

		levelColumnEditPane.addRow(0, lblSize, levelEyeSelectionBox, lblModel, inputModel);

		return levelColumnEditPane;
	}

	@Override
	public Boolean isModelComplete() {
		return false;
	}
}
