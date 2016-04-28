package com.RVS;

import com.Josh.Message;
import com.RVS.Accessories.*;
import com.RVS.Products.Product;
import com.RVS.Products.Vessel;
import com.RVS.Products.Vessels.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by manufacturing9 on 4/28/2016.
 *
 * @author Josh Jennings
 */
public class WindowMakerProcessor {

	private static TreeItem<Product> root;
	private static ObservableList<Product> productObservableList;

	public static void addProduct(TreeTableView<Product> treeView,
	                              TreeItem<Product> rootTreeItem,
	                              ObservableList<Product> listOfProductsForTable,
	                              HBox buttonBox) {
		Message.consoleMessage("Adding a product.");
		root = rootTreeItem;
		productObservableList = listOfProductsForTable;

		try {
			TreeItem<Product> newProductItem;
			ObservableList<String> productList = Product.constructListOfStandardProducts();

			//display message box to select proper product
			String nameProduct = Message.selectProduct("Please enter the product name.",
					"Product Selection",
					productList);

			//if Cancel or Close pushed or null item selected -> do nothing and return
			//if legitimate item selected and Enter click -> make the TreeItem
			if (Objects.equals(nameProduct, "DONOTENTERanyNewPRODUCTinHERErightNOW") || Objects.equals(nameProduct, null)) {
				Message.consoleMessage("Add Product window cancelled.");
				return;
			} else {
				newProductItem = makeTreeItem(nameProduct, treeView.getRoot());
			}

			//automatically add subfeatures
			addSubFeatures(newProductItem);


		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			Message.messageBox("Please select an item in the list.", "Notification");
		}

		//if there are no remaining TreeItems remaining, make specific buttons unavailable
		if (!treeView.getRoot().isLeaf()) {
			for (int counter = 0; counter < buttonBox.getChildren().size(); counter++) {
				buttonBox.getChildren().get(counter).setDisable(false);
			}
//			buttonAddFeature.setDisable(false);
//			buttonDeleteItem.setDisable(false);
//			buttonEditItem.setDisable(false);
		}
	}

	public static void addFeature(TreeTableView<Product> treeView, ObservableList<String> listOfFeatures) {
		//attempt to collect the selected TreeItem
		try {
			//attempt to collect selection
			TreeItem<Product> selectedItem = treeView.getSelectionModel().getSelectedItem();
			//display window with Feature selection list
			String nameOfFeature = Message.selectProduct("Please select the feature to add.",
					"Feature Selection",
					listOfFeatures);
			//add Feature to selected Product
			makeTreeItem(nameOfFeature, selectedItem);
		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			Message.messageBox("Please select an item in the list.", "Notification");
		}
	}

	public static Pane editItem(Product product) {
		Pane productDetailPane;
		//determine if there is a predefined instance method for the edit window in question
		//each if statement must define productDetailPane so that it may be passed after all if statements
		if (product instanceof ControlPanel) {
			productDetailPane = ((ControlPanel) product).editWindow();
		} else if (product instanceof Vessel) {
			productDetailPane = ((Vessel) product).editWindow();
		} else if (product instanceof Pump) {
			productDetailPane = ((Pump) product).editWindow();
		} else if (product instanceof LevelColumn) {
			productDetailPane = ((LevelColumn) product).editWindow();
		} else if (product instanceof LiquidFeed) {
			productDetailPane = ((LiquidFeed) product).editWindow();
		} else {
			GridPane gridPane = new GridPane();
			Message.consoleMessage("Displaying item edit pane for item: " + product.getModel());

			productDetailPane = new Pane();
			productDetailPane.getChildren().add(gridPane);
		}

		return productDetailPane;
	}

	private static void addSubFeatures(TreeItem<Product> newProductItem) {
		Message.consoleMessage("Automatically adding subfeatures");

		switch (newProductItem.getValue().getModel()) {
			case "MRP":
				Message.consoleMessage("Adding features for MRP");
				makeTreeItem("Recirculator", newProductItem);
				makeTreeItem("Level column", newProductItem);
				makeTreeItem("Oil pot", newProductItem);
				makeTreeItem("Pumps", newProductItem);
				makeTreeItem("Control Panel", newProductItem);
				makeTreeItem("Liquid feed", newProductItem);
				break;
			case "MPC":
				Message.consoleMessage("Adding features for MPC");
				makeTreeItem("Surge Drum", newProductItem);
				makeTreeItem("Oil pot", newProductItem);
				break;
			case "MVI":
				Message.consoleMessage("Adding features for MVI");
				makeTreeItem("Intercooler", newProductItem);
				makeTreeItem("Oil pot", newProductItem);
				break;
			case "HPR":
				Message.consoleMessage("Adding features for HPR");
				makeTreeItem("HPR", newProductItem);
				break;
			case "TSR":
				Message.consoleMessage("Adding features for TSR");
				makeTreeItem("Thermosyphon", newProductItem);
				break;
			case "Recirculator":
				Message.consoleMessage("Adding features for Recirculator");
				makeTreeItem("Recirculator", newProductItem);
				break;
			case "Intercooler":
				Message.consoleMessage("Adding features for Intercooler");
				makeTreeItem("Intercooler", newProductItem);
				break;
			case "Accumulator":
				Message.consoleMessage("Adding features for Accumulator");
				makeTreeItem("Accumulator", newProductItem);
				break;
		}
	}

	/**
	 * This class will construct new items for a TreeView.
	 *
	 * @param title  Title of the new node/leaf item.
	 * @param parent Parent of the new node/leaf item.
	 * @return Returns the new TreeItem created in this method. Returned object can be used to create sub-nodes.
	 */
	private static TreeItem<Product> makeTreeItem(String title, TreeItem<Product> parent) {
		Message.consoleMessage("Adding TreeView item. Item: " + title + " | ChildTo: " + parent.getValue().getModel());

		TreeItem<Product> newItem;
		switch (title) {
			case "Vessel":
				newItem = new TreeItem<>(new BareVessel(title));
				break;
			case "Pumps":
				newItem = new TreeItem<>(new Pump(title));
				break;
			case "Control Panel":
				newItem = new TreeItem<>(new ControlPanel(title));
				break;
			case "Level Column":
				newItem = new TreeItem<>(new LevelColumn(title));
				break;
			case "Liquid Feed":
				newItem = new TreeItem<>(new LiquidFeed(title));
				break;
			case "Coil":
				newItem = new TreeItem<>(new Coil(title));
				break;
			case "Recirculator":
				newItem = new TreeItem<>(new Recirculator(title));
				break;
			case "Thermosyphon":
				newItem = new TreeItem<>(new Thermosyphon(title));
				break;
			case "Surge Drum":
				newItem = new TreeItem<>(new SurgeDrum(title));
				break;
			case "Accumulator":
				newItem = new TreeItem<>(new Accumulator(title));
				break;
			case "HPR":
				newItem = new TreeItem<>(new HighPressureReceiver("High Pressure Receiver"));
				break;
			case "Intercooler":
				newItem = new TreeItem<>(new Intercooler(title));
				break;
			default:
				newItem = new TreeItem<>(new BareVessel(title));
		}
		newItem.setExpanded(true);
		parent.getChildren().add(newItem);
		if (parent != root) productObservableList.add(newItem.getValue());
		return newItem;
	}

	public static void deleteItem(TreeTableView<Product> treeView, HBox buttonContainerToAddOrRemoveProducts) {
		String selectedItem = treeView.getSelectionModel().getSelectedItem().getValue().getModel();
		Message.consoleMessage("Removing item from TreeView: " + selectedItem);
		treeView.getSelectionModel().getSelectedItem().getParent().getChildren().remove(
				treeView.getSelectionModel().getSelectedItem());

		if (treeView.getRoot().isLeaf()) {
			Message.consoleMessage("Empty product list. Disabling Feature, Delete, and Edit buttons.");
			for (int counter = 0; counter < buttonContainerToAddOrRemoveProducts.getChildren().size(); counter++) {
				if (((Button) buttonContainerToAddOrRemoveProducts.getChildren().get(counter)).getText().equals("Add Product")) {
					buttonContainerToAddOrRemoveProducts.getChildren().get(counter).setDisable(false);
				} else {
					buttonContainerToAddOrRemoveProducts.getChildren().get(counter).setDisable(true);
				}
			}
		}

		treeView.getSelectionModel().clearSelection();
	}

	public static Connection getConnection() {
		Message.consoleMessage("Connecting to database.");
		Connection connection = null;
		try {
			Message.consoleMessage("Here1");
			Class.forName("com.mysql.jdbc.Driver");
			Message.consoleMessage("Here2");
			String url = "jdbc:mysql://localhost/movies";
			Message.consoleMessage(url);
			String user = "root";
			String password = "JavaRocks";
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			Message.consoleMessage("ERROR!");
			e.printStackTrace();
		}

		return connection;
	}
}
