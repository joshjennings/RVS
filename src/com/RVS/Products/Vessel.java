package com.RVS.Products;

import com.Josh.Message;
import com.RVS.Products.Vessels.Recirculator;
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
public abstract class Vessel extends Product {

	private String description;
	private int diameter;
	private BigDecimal length;
	private Orient orientation;
	private BigDecimal priceList;
	private Material material;

	public abstract String formattedModel();

	public Vessel() {
		super();
	}

	public Vessel(String model) {
		super(model);
//		this.model = model;
		this.description = model;
		this.orientation = Orient.UNASSIGNED;
	}

	//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public int getDiameter() {
		return this.diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public Orient getOrientation() {
		return this.orientation;
	}

	public String getOrientationString() {
		String orientation = "null";
		if (this.getOrientation().equals(Orient.HORIZONTAL)) {
			orientation = "Horizontal";
		}
		if (this.getOrientation().equals(Orient.VERTICAL)) {
			orientation = "Vertical";
		}
		return orientation;
	}

	public void setOrientation(Orient orientation) {
		this.orientation = orientation;
	}

//	public void setPriceList(BigDecimal priceList) {
//		this.priceList = priceList;
//	}
//
//	public void setMaterial(Material material) {
//		this.material = material;
//	}

	@Override
	public String toString() {
		return this.model;
	}

	public Pane editWindow() {
		Message.consoleMessage("Displaying item edit pane for item: " + this.getDescription());
		GridPane gridPane = new GridPane();
		//gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

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
		inputModel.setText(""); //TextField is filled in the inputSize if statement below

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
		//set listening event for ComboBox clicking event
		inputSize.valueProperty().addListener((observable, oldValue, newValue) -> {
			Message.consoleMessage("Changing selected property's size from " + oldValue + " to " + newValue + ".");
			this.setDiameter(Integer.parseInt(newValue.getName()));
		});
		//if diameter of instance is already selected, fill in the ComboBox
		if (this.diameter != 0) { //if zero, skip because it's not been instantiated yet
			ComboBoxItem selectedDiameter = new ComboBoxItem("", false);
			//iterate through list to find assigned size
			for (ComboBoxItem listOfDiameters : inputSize.getItems()) {
				//if assigned size matches an item in the list, save that matching number and break loop
				try { //try-catch required since ComboBox includes non-integer Strings
					if (this.diameter == Integer.parseInt(listOfDiameters.getName())) {
						selectedDiameter = listOfDiameters;
						break;
					}
				} catch (Exception e) {
					Message.consoleMessage("Skipping an entry during iteration.");
				} //if Exception caught, do nothing
			}
			//select matching size in list
			inputSize.setValue(selectedDiameter);
			//set model text

			if (this.getLength() == null) { //if length isn't set, don't use it
				this.formattedModel();
//				inputModel.setText("Vessel" + this.getDiameter()); //TODO: change "Vessel" to model
			} else { //if length is set, include it
				inputModel.setText("Vessel" + this.getDiameter() + "-" + this.getLength()); //TODO: change "Vessel"
			}

			//TODO: define what to do if matching list item was not found

		}

		inputOrientation.getItems().addAll("Vertical", "Horizontal");
		inputOrientation.valueProperty().addListener((observable, oldValue, newValue) -> {
			Message.consoleMessage("Changing selected property's orientation from " + oldValue + " to " + newValue + ".");
			if (newValue.equals("Horizontal")) {
				this.setOrientation(Orient.HORIZONTAL);
			}
			if (newValue.equals("Vertical")) {
				this.setOrientation(Orient.VERTICAL);
			}
		});
		//set orientation ComboBox - if not unassigned, get orientation and set it
		if (!this.getOrientation().equals(Orient.UNASSIGNED)) {inputOrientation.setValue(this.getOrientationString());}

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

		public String getName() {
			return this.name;
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
