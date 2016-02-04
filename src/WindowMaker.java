import com.RVS.Products.Product;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.Josh.Message;

import java.util.Objects;

/**
 * This primary (main) class instantiates a window for data entry and creation.
 * @author Josh Jennings
 */
public class WindowMaker extends Application {

	Region spacer;
	TreeView<Product> treeView;
	ObservableList<Product> productList;
	Button buttonAddProduct, buttonAddFeature, buttonDeleteItem, buttonEditItem;

	public static void main(String[] args) {
		//out.println("TEST");
		//TODO: add new method to create splash screen

		//TODO: pre-load product data -> models, descriptions, prices

		//TODO: update the splash screen with loading status

		//TODO: move primary window to another class
		//PrimaryWindow primaryWindow = new PrimaryWindow(args);
		//according to http://stackoverflow.com/questions/31173540/exception-in-thread-main-java-lang-runtimeexception-unable-to-construct-appli
		//   the launch() method must be in the main method. launch() does not return until window is closed. Maybe a new thread?
		launch(args);
	}

	@Override public void start(Stage primaryStage) {
		productList = Product.constructListOfStandardProducts();

		//notify of window creation commencing
		Message.consoleMessage("Window initialization beginning.");

		//initialize everything
		Message.initialize();

		//define horizontal spacer that will grow with the window
		spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		//create topPane
		Node topPane = topPane();

		//create leftPane
		Node leftPane = leftPane();

		//create primaryPane
		Node centerPane = centerPane();

		//create bottomPane
		Node bottomPane = bottomPane();

		//create BorderPane (root)
		BorderPane rootPane = new BorderPane();
		rootPane.setTop(topPane);
		rootPane.setLeft(leftPane);
		rootPane.setCenter(centerPane);
		rootPane.setBottom(bottomPane);

		//create scene
		Scene scene = new Scene(rootPane);

		//create stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("RVS Pricing Program");
		Message.consoleMessage("Showing window.");
		primaryStage.show();
		Message.consoleMessage("Window displayed.");
	}

	/**
	 * This method will create the topPane of the root BorderPane layout.
	 * @return Returns the Node that will be displayed in the topPane.
	 */
	private Node topPane() {
		Message.consoleMessage("Adding top pane.");

		TextField salesPerson = new TextField();
		salesPerson.setEditable(true);
		salesPerson.setMaxWidth(400);
		salesPerson.setMinWidth(100);
		salesPerson.setPrefWidth(200);
		salesPerson.setPromptText("Sales Engineer");

		HBox topPane = new HBox(10, spacer, salesPerson);
		topPane.setPadding(new Insets(10));

		return topPane;
	}

	/**
	 * This method will create the leftPane of the root BorderPane layout.
	 * @return Returns the Node that will be displayed in the leftPane.
	 */
	private Node leftPane() {
		Message.consoleMessage("Adding left pane.");

		TreeItem<Product> root = new TreeItem<>(new Product("root"));
		root.setExpanded(true);
		//TODO: find a way to set the TreeView width

		treeView = new TreeView<>(root);
		treeView.setShowRoot(false);
		treeView.setOnMouseClicked(event -> {
			//if a TreeView item is selected, enable the Edit button
			//otherwise, don't do anything (but log it on the console)
			try {
				if (treeView.getSelectionModel().getSelectedItem() != null) {
					buttonEditItem.setDisable(false);
				}
			} catch (Exception e) {
				Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			}
		});

		//buttonBar contains buttons for controlling nodes in TreeView
		HBox buttonBar = new HBox();
		buttonAddProduct = new Button("Add Product");
		buttonAddFeature = new Button("Add Feature");
		buttonDeleteItem = new Button("Remove Item");
		buttonEditItem = new Button("Edit Item");
		//buttonEditItem ("Edit Item") is instantiated in the preamble
		buttonAddProduct.setMinWidth(50);
		buttonAddProduct.setPrefWidth(100);
		buttonAddFeature.setMinWidth(50);
		buttonAddFeature.setPrefWidth(100);
		buttonAddFeature.setDisable(true);
		buttonDeleteItem.setMinWidth(50);
		buttonDeleteItem.setPrefWidth(100);
		buttonDeleteItem.setDisable(true);
		buttonEditItem.setMinWidth(50);
		buttonEditItem.setPrefWidth(100);
		buttonEditItem.setDisable(true);
		buttonBar.getChildren().addAll(buttonAddProduct,buttonAddFeature,buttonDeleteItem,buttonEditItem);

		//TODO: add new buttons to create new TreeView nodes
		buttonAddProduct.setOnAction(event -> addProduct());
		buttonAddFeature.setOnAction(event -> addFeature());
		buttonDeleteItem.setOnAction(event -> deleteItem());
		buttonEditItem.setOnAction(event -> editItem());

		VBox leftPane = new VBox(treeView,buttonBar);
		VBox.setVgrow(treeView,Priority.ALWAYS); //always grow the TreeView vertically when modifying window.

		return leftPane;
	}

	/**
	 * This method will create the centerPane of the root BorderPane layout.
	 * @return Returns the Node that will be displayed in the centerPane.
	 */
	private Node centerPane() {
		Message.consoleMessage("Adding center pane.");
		//create TableView
		TableView<Product> centerPane = new TableView<>();
		//add items to the table
//		centerPane.getItems().add(new ProductList("MRP-72V","Vertical recirculator package",new BigDecimal(43528)));
//		centerPane.getItems().add(new ProductList("MVI-48V","Vertical intercooler package",new BigDecimal(32978)));
//		centerPane.getItems().add(new ProductList("HOP-10","Horizontal oil pot",new BigDecimal(1823)));
//		centerPane.getItems().add(new Product("MRP", 48, 171.0, Product.Orient.VERTICAL, new BigDecimal(12345), Product.Material.CARBON));

		//CREATE TABLE COLUMNS
		//model column
		TableColumn<Product,String> columnModel = new TableColumn<>("Model");
		columnModel.setMinWidth(100);
		columnModel.setPrefWidth(150);
		columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		//description column
		TableColumn<Product,String> columnDesc = new TableColumn<>("Description");
		columnDesc.setMinWidth(200);
		columnDesc.setPrefWidth(300);
		columnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
		//price column
		TableColumn<Product,String> columnListPrice = new TableColumn<>("List Price");
		columnListPrice.setMinWidth(100);
		columnListPrice.setPrefWidth(150);
		columnListPrice.setCellValueFactory(new PropertyValueFactory<>("priceListFormatted"));

		//noinspection unchecked
		centerPane.getColumns().addAll(columnModel,columnDesc,columnListPrice);

		return centerPane;
	}

	/**
	 * This method will create the bottomPane of the root BorderPane layout.
	 * @return Returns the Node that will be displayed in the bottomPane.
	 */
	private Node bottomPane() {
		Message.consoleMessage("Adding bottom pane.");
		//create control buttons
		Button buttonOK = new Button("OK");
		Button buttonCancel = new Button("Cancel");

		//create radio button drop down
		RadioButton rbOne = new RadioButton("One");
		RadioButton rbTwo = new RadioButton("Two");
		RadioButton rbThree = new RadioButton("Three");
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(rbOne, rbTwo, rbThree);

		VBox selectionBox = new VBox(10);
		selectionBox.setPadding(new Insets(10));
		selectionBox.getChildren().addAll(rbOne, rbTwo, rbThree);

		TitledPane dropDownBox = new TitledPane("Numbers", selectionBox);
		dropDownBox.setCollapsible(true);

		//create bottom pane - removed dropdown selection box
		HBox bottomPane = new HBox(10, spacer, buttonOK, buttonCancel);
		bottomPane.setPadding(new Insets(10));

		return bottomPane;
	}

	/**
	 * This class will construct new items for a TreeView.
	 * @param title Title of the new node/leaf item.
	 * @param parent Parent of the new node/leaf item.
	 * @return Returns the new TreeItem created in this method. Returned object can be used to create sub-nodes.
	 */
	private TreeItem<Product> makeTreeItem(String title, TreeItem<Product> parent) {
		Message.consoleMessage("Adding TreeView item. Item: " + title + " | ChildTo: " + parent.getValue().getModel());
		TreeItem<Product> newItem = new TreeItem<>(new Product(title));
		newItem.setExpanded(true);
		parent.getChildren().add(newItem);
		Message.consoleMessage("TreeItem Product count: " + parent.getChildren().size());
		return newItem;
	}

	private void addProduct() {
		try {
			//display message box to select proper product
			String nameProduct = Message.selectProduct("Please enter the product name.", "Product Name", productList);
			//if the two strings do not equal each other, make the TreeItem
			if (!Objects.equals(nameProduct, "DONOTENTERanyNewPRODUCTinHERErightNOW")) {makeTreeItem(nameProduct, treeView.getRoot());}
		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			//TODO: add pop up box notifying user to select an item.
			Message.messageBox("Please select an item in the list.","Notification");
		}

		if (!treeView.getRoot().isLeaf()) {
			buttonAddFeature.setDisable(false);
			buttonDeleteItem.setDisable(false);
			buttonEditItem.setDisable(false);
		}
	}

	private void addFeature() {
		try {
			TreeItem<Product> selectedItem = treeView.getSelectionModel().getSelectedItem();
			makeTreeItem("Test", selectedItem);
		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			//TODO: add pop up box notifying user to select an item.
			Message.messageBox("Please select an item in the list.","Notification");
		}
	}

	private void deleteItem() {
		String selectedItem = treeView.getSelectionModel().getSelectedItem().getValue().getModel();
		Message.consoleMessage("Removing item from TreeView: " + selectedItem);
		treeView.getSelectionModel().getSelectedItem().getParent().getChildren().remove(treeView.getSelectionModel().getSelectedItem());

		if (treeView.getRoot().isLeaf()) {
			Message.consoleMessage("Empty product list. Disabling Feature, Delete, and Edit buttons.");
			buttonAddFeature.setDisable(true);
			buttonDeleteItem.setDisable(true);
			buttonEditItem.setDisable(true);
		}

		treeView.getSelectionModel().clearSelection();
	}

	private void editItem() {
		Message.consoleMessage("Displaying item edit pane for item: " + treeView.getSelectionModel().getSelectedItem().getValue().getModel());


	}

}