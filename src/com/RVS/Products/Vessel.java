package com.RVS.Products;

import com.Josh.Message;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.math.BigDecimal;

/**
 * Created by Josh on 12/16/2015.
 *
 * @author Josh Jennings
 */
public class Vessel extends Product {

	private String model, description;
	private int diameter;
	private BigDecimal length;
	private Orient orientation;
	private BigDecimal priceList;
	private Material material;

	public Vessel() {
		super();
	}

	public Vessel(String model) {
		this.model = model;
		this.description = model;
//		Message.consoleMessage("Diameter: " + this.diameter);
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public void setOrientation(Orient orientation) {
		this.orientation = orientation;
	}

	public void setPriceList(BigDecimal priceList) {
		this.priceList = priceList;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return this.model;
	}

	public Pane editWindow() {
		GridPane gridPane = new GridPane();
		Message.consoleMessage("Displaying item edit pane for item: " + this.getDescription());

		//CREATE PRODUCT DETAIL PANE
		//define GridPane objects

		//text labels
		Label lblModel = new Label("Model");
		Label lblSize = new Label("Size");
		Label lblOrientation = new Label("Orientation");
		Label lblDescription = new Label("Description");
		Label lblList = new Label("List");
		Label lblMultiplier = new Label("Multiplier");
		Label lblNet = new Label("Net");
		//input controls
		TextField inputModel = new TextField();
		ComboBox<ComboBoxItem> inputSize = new ComboBox<>();
		ComboBox<String> inputOrientation = new ComboBox<>();
		TextField inputDescription = new TextField();
		//manage input controls
		inputSize.getItems().addAll(
				new ComboBoxItem("Pipe", false),
				new ComboBoxItem("8", true),
				new ComboBoxItem("10", true),
				new ComboBoxItem("12", true),
				new ComboBoxItem("16", true),
				new ComboBoxItem("20", true),
				new ComboBoxItem("", false), //TODO: make this line a separator line!
				new ComboBoxItem("Plate", false),
				new ComboBoxItem("24", true),
				new ComboBoxItem("36", true),
				new ComboBoxItem("48", true),
				new ComboBoxItem("54", true),
				new ComboBoxItem("60", true),
				new ComboBoxItem("72", true),
				new ComboBoxItem("84", true),
				new ComboBoxItem("96", true),
				new ComboBoxItem("108", true),
				new ComboBoxItem("120", true),
				new ComboBoxItem("144", true)
		);
		inputSize.setCellFactory(listView -> new ListCell<ComboBoxItem>() {
			@Override
			public void updateItem(ComboBoxItem item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
					setDisable(false);
				} else {
					setText(item.toString());
					setDisable(!item.isSelectable());
				}
			}
		});
		//if diameter of instance is already selected, fill in the ComboBox
		if (this.diameter != 0) { //if zero, skip because it's not been instantiated yet
			ComboBoxItem selectedDiameter = new ComboBoxItem("",false);
			//iterate through list to find assigned size
			for (ComboBoxItem listOfDiameters : inputSize.getItems()) {
				//if assigned size matches an item in the list, assign to
				if (this.diameter == Integer.parseInt(listOfDiameters.name)) {
					selectedDiameter = listOfDiameters;
					break;
				}
			}
			//select matching size in list
			inputSize.setValue(selectedDiameter);
			//TODO: define what to do if matching list item was not found

		}
		//set Lambda for ComboBox clicking event
//		inputSize.setOnMouseReleased(event -> { /* DOESN'T WORK YET!!!!!!!!!!!! setOnMouseClick is the wrong method */
//			String diameter = inputSize.getValue().name;
//			this.setDiameter(Integer.parseInt(diameter));
//		});
		inputSize.valueProperty().addListener(new ChangeListener<ComboBoxItem>() {
			@Override
			public void changed(ObservableValue<? extends ComboBoxItem> observable, ComboBoxItem oldValue, ComboBoxItem newValue) {
				Message.consoleMessage("Changing selected property's size from " + oldValue + " to " + newValue + ".");
				String diameter = newValue.name;
				//TODO: change instance field with "diameter"
			}
		});
		inputOrientation.getItems().addAll("Vertical", "Horizontal");
		inputDescription.setEditable(false);
		inputDescription.setText(this.getDescription());

		//add objects to GridPane
		gridPane.add(lblModel, 0, 0);
		gridPane.add(inputModel, 1, 0);
		gridPane.add(lblSize, 2, 0);
		gridPane.add(inputSize, 3, 0);
		gridPane.add(lblOrientation, 4, 0);
		gridPane.add(inputOrientation, 5, 0);
		gridPane.add(lblDescription, 0, 1);
		gridPane.add(inputDescription, 1, 1);
		gridPane.add(new Label(""), 0, 2);
		gridPane.add(lblList, 0, 3);
		gridPane.add(lblMultiplier, 0, 4);
		gridPane.add(lblNet, 0, 5);

		return gridPane;
	}

	public static class ComboBoxItem {

		private final String name;
		private final boolean selectable;

		public ComboBoxItem(String name, boolean selectable) {
			this.name = name;
			this.selectable = selectable;
		}

		public boolean isSelectable() {
			return selectable;
		}

		@Override
		public String toString() {
			return name;
		}
	}


}
