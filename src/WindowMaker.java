import com.RVS.Products.Product;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	TreeView<String> treeView;
	ObservableList<Product> productList;
	Button buttonEditItem = new Button("Edit Item");

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

		Message.consoleMessage("Window creation beginning.");

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
		primaryStage.setTitle("Window Tester");
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

		TreeItem<String> root = new TreeItem<>("root");
		root.setExpanded(true);
		//TODO: find a way to set the TreeView width

//		makeTreeItem("Liquid Feed Assembly", root);
//		makeTreeItem("Coil", root);
//		TreeItem<String> pumps = makeTreeItem("Pumps", root);
//		makeTreeItem("Teikoku", pumps);
//		makeTreeItem("Cornell", pumps);

		treeView = new TreeView<>(root);
		treeView.setShowRoot(false);
		treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//if the selected item is not null, try to set the button to enabled
				//otherwise, don't do anything (but log it on the console
				try {
					if (!treeView.getSelectionModel().getSelectedItem().equals(null)) {
						buttonEditItem.setDisable(false);
					}
				} catch (Exception e) {
					Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
				}
			}
		});

		//buttonBar contains buttons for adding new nodes to TreeView
		HBox buttonBar = new HBox();
		Button buttonAddProduct = new Button("Add Product");
		Button buttonAddFeature = new Button("Add Feature");
		Button buttonSubNode = new Button("Remove Item");
		//buttonEditItem ("Edit Item") is instantiated in the preamble
		buttonAddProduct.setMinWidth(50);
		buttonAddProduct.setPrefWidth(100);
		buttonAddFeature.setMinWidth(50);
		buttonAddFeature.setPrefWidth(100);
		buttonSubNode.setMinWidth(50);
		buttonSubNode.setPrefWidth(100);
		buttonEditItem.setMinWidth(50);
		buttonEditItem.setPrefWidth(100);
		buttonEditItem.setDisable(true);
		buttonBar.getChildren().addAll(buttonAddProduct,buttonAddFeature,buttonSubNode,buttonEditItem);

		//TODO: add new buttons to create new TreeView nodes
		buttonAddProduct.setOnAction(event -> addProduct());
		buttonAddFeature.setOnAction(event -> addFeature());
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

		//create bottom pane
		HBox bottomPane = new HBox(10, dropDownBox, spacer, buttonOK, buttonCancel);
		bottomPane.setPadding(new Insets(10));

		return bottomPane;
	}

	/**
	 * This class will construct new items for a TreeView.
	 * @param title Title of the new node/leaf item.
	 * @param parent Parent of the new node/leaf item.
	 * @return Returns the new TreeItem created in this method. Returned object can be used to create sub-nodes.
	 */
	private TreeItem<String> makeTreeItem(String title, TreeItem<String> parent) {
		Message.consoleMessage("Adding TreeView item. Item: " + title + " | ChildTo: " + parent.toString());
		TreeItem<String> newItem = new TreeItem<>(title);
		newItem.setExpanded(true);
		parent.getChildren().add(newItem);
		return newItem;
	}

	private void addProduct() {
		try {
			TreeItem<String> selectedItem = treeView.getRoot();
			String nameProduct = Message.selectProduct("Please enter the product name.", "Product Name", productList);
			//if the two strings do not equal each other, make the TreeItem
			if (!Objects.equals(nameProduct, "DONOTENTERanyNewPRODUCTinHERErightNOW")) {makeTreeItem(nameProduct, selectedItem);}
		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			//TODO: add pop up box notifying user to select an item.
			Message.messageBox("Please select an item in the list.","Notification");
		}
	}

	private void addFeature() {
		try {
			TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
			makeTreeItem("Test", selectedItem);
		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			//TODO: add pop up box notifying user to select an item.
			Message.messageBox("Please select an item in the list.","Notification");
		}
	}

	private void editItem() {
		try {

		} catch (Exception e) {

		}
	}

}