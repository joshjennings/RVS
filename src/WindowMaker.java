import com.RVS.Accessories.*;
import com.RVS.Products.Product;
import com.RVS.Products.Vessel;
import com.RVS.Products.Vessels.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.Josh.Message;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * This primary (main) class instantiates a window for data entry and creation.
 *
 * @author Josh Jennings
 */
public class WindowMaker extends Application {

	Region spacer;
	TreeView<Product> treeView;
	ObservableList<String> productList;
	ObservableList<String> featureList;
	HashMap<Integer, Integer> mapDiameterLength;
	Button buttonAddProduct, buttonAddFeature, buttonDeleteItem, buttonEditItem;
	BorderPane rootPane;
	static Connection databaseConnection;

	//centerPane
	TreeItem<Product> root;
	TableView<Product> centerPane;
	ScrollPane genericPaneWrapper;

	public static void main(String[] args) {
		Message.consoleMessage("Program launch arguments: ");
		if (args.length == 0) {
			Message.consoleMessage("   <None>");
		} else {
			for (int counter = 0; counter < args.length; counter++) {
				Message.consoleMessage("   " + (counter + 1) + ".) " + args[counter]);
			}
		}
		//TODO: add new method to create splash screen

		//TODO: pre-load product data -> models, descriptions, prices

		//TODO: update the splash screen with loading status

		//TODO: move primary window to another class
		//PrimaryWindow primaryWindow = new PrimaryWindow(args);
		//according to 'http://stackoverflow.com/questions/31173540/exception-in-thread-main-java-lang-
		// runtimeexception-unable-to-construct-appli' the launch() method must be in the main method.
		// launch() does not return until window is closed. Maybe a new thread?
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//initialize everything
		Message.consoleMessage("Initializing data.");
		//databaseConnection = WindowMaker.getConnection(); //SQL database connection
		initialize(); //
		Message.initialize(); //

//		if (databaseConnection == null) {
//			Message.consoleMessage("Connection is null.");
//		}
//
//		try {
//			Statement statement = databaseConnection.createStatement();
//			String select = "select title, year, price from movie order by year";
//			ResultSet rows = statement.executeQuery(select);
//
//			while (rows.next()) {
//				Message.consoleMessage("SQL DATA: " + rows.getString("title"));
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}


		//notify of window creation commencing
		Message.consoleMessage("Window initialization beginning.");

		//create BorderPane (root)
		rootPane = new BorderPane();
		rootPane.setTop(topPane());
		rootPane.setCenter(centerPane());
		rootPane.setBottom(bottomPane());

		//create scene
		Scene scene = new Scene(rootPane);

		//create stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("RVS Pricing Program");
		primaryStage.getIcons().add(new Image("java.png"));
		primaryStage.setMinWidth(1100.0);
		Message.consoleMessage("Showing window.");
		primaryStage.show();
		Message.consoleMessage("Window displayed.");
	}

	private void initialize() {
		//define horizontal spacer that will grow with the window
		spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		//populate data lists
		productList = Product.constructListOfStandardProducts();
		featureList = Feature.constructListOfStandardFeatures();
		mapDiameterLength = Vessel.makeDiameterLengthMap();
	}

	/**
	 * This method will create the topPane of the root BorderPane layout.
	 *
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
	 * This method will create the centerPane of the root BorderPane layout.
	 *
	 * @return Returns the Node that will be displayed in the centerPane.
	 */
	private Node centerPane() {
		root = new TreeItem<>(new BareVessel("root"));
//		Message.consoleMessage(root.getValue().getModel());
		centerPane = new TableView<>();

		genericPaneWrapper = new ScrollPane();
		//fit to containing view pane
		genericPaneWrapper.setFitToWidth(true);
		genericPaneWrapper.setFitToHeight(true);

		Message.consoleMessage("Adding center pane.");

		//CREATE TREEVIEW
		root.setExpanded(true);

		Message.consoleMessage(root.toString());

		treeView = new TreeView<>(root);

		treeView.setShowRoot(false);
		treeView.setMinWidth(340);
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
		buttonAddProduct.setMinWidth(95);
		buttonAddProduct.setPrefWidth(100);
		buttonAddFeature.setMinWidth(95);
		buttonAddFeature.setPrefWidth(100);
		buttonAddFeature.setDisable(true);
		buttonDeleteItem.setMinWidth(100);
		buttonDeleteItem.setPrefWidth(100);
		buttonDeleteItem.setDisable(true);
		buttonEditItem.setMinWidth(85);
		buttonEditItem.setPrefWidth(100);
		buttonEditItem.setDisable(true);
		buttonBar.getChildren().addAll(buttonAddProduct, buttonAddFeature, buttonDeleteItem, buttonEditItem);
		buttonBar.setPrefWidth(400);
		buttonBar.setMaxWidth(800);

		//set actions for each button
		buttonAddProduct.setOnAction(event -> addProduct());
		buttonAddFeature.setOnAction(event -> addFeature());
		buttonDeleteItem.setOnAction(event -> deleteItem());
		buttonEditItem.setOnAction(event -> editItem(treeView.getSelectionModel().getSelectedItem().getValue()));

		VBox leftPane = new VBox(treeView, buttonBar);
		VBox.setVgrow(treeView, Priority.ALWAYS); //always grow the TreeView vertically when modifying window.

		//CREATE TABLEVIEW
		//centerPane = new TableView<>();
		//add items to the table
//		centerPane.getItems().add(new ProductList("MRP-72V","Vertical recirculator package",new BigDecimal(43528)));
//		centerPane.getItems().add(new ProductList("MVI-48V","Vertical intercooler package",new BigDecimal(32978)));
//		centerPane.getItems().add(new ProductList("HOP-10","Horizontal oil pot",new BigDecimal(1823)));
//		centerPane.getItems().add(new Product("MRP",
//				48,
//				171.0,
//				Product.Orient.VERTICAL,
//				new BigDecimal(12345),
//				Product.Material.CARBON));

		//CREATE TABLE COLUMNS
		//model column
		TableColumn<Product, String> columnModel = new TableColumn<>("Model");
		columnModel.setMinWidth(100);
		columnModel.setPrefWidth(150);
		columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		//description column
		TableColumn<Product, String> columnDesc = new TableColumn<>("Description");
		columnDesc.setMinWidth(200);
		columnDesc.setPrefWidth(300);
		columnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
		//price column
		TableColumn<Product, String> columnListPrice = new TableColumn<>("List Price");
		columnListPrice.setMinWidth(100);
		columnListPrice.setPrefWidth(150);
		columnListPrice.setCellValueFactory(new PropertyValueFactory<>("priceListFormatted"));

		//noinspection unchecked
		centerPane.getColumns().addAll(columnModel, columnDesc, columnListPrice);
		centerPane.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		centerPane.setMinWidth(columnModel.getMinWidth() + columnDesc.getMinWidth() + columnListPrice.getMinWidth());

		//ADD BOTH PANES TO THE SPLITPANE
		genericPaneWrapper.setContent(centerPane);
		SplitPane splitPane = new SplitPane(leftPane, genericPaneWrapper);
		splitPane.setOrientation(Orientation.HORIZONTAL);
		splitPane.setDividerPosition(0, 0.2f);
		SplitPane.setResizableWithParent(leftPane, false); //static method

		return splitPane;
	}

	/**
	 * This method will create the bottomPane of the root BorderPane layout.
	 *
	 * @return Returns the Node that will be displayed in the bottomPane.
	 */
	private Node bottomPane() {
		Message.consoleMessage("Adding bottom pane.");
		//create control buttons
		Button buttonOK = new Button("OK");
		Button buttonClose = new Button("Close");

		//set button actions
		buttonClose.setOnAction(event -> {
			Message.consoleMessage("Closing program");
			Stage stageToClose = (Stage) buttonClose.getScene().getWindow();
			stageToClose.close();
		});

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
		HBox bottomPane = new HBox(10, spacer, buttonOK, buttonClose);
		bottomPane.setPadding(new Insets(10));

		return bottomPane;
	}

	/**
	 * This class will construct new items for a TreeView.
	 *
	 * @param title Title of the new node/leaf item.
	 * @param parent Parent of the new node/leaf item.
	 * @return Returns the new TreeItem created in this method. Returned object can be used to create sub-nodes.
	 */
	private TreeItem<Product> makeTreeItem(String title, TreeItem<Product> parent) {
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
		Message.consoleMessage("TreeItem Product count: " + parent.getChildren().size());
		return newItem;
	}

	private void addProduct() {
		try {
			TreeItem<Product> newProductItem;

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
			buttonAddFeature.setDisable(false);
			buttonDeleteItem.setDisable(false);
			buttonEditItem.setDisable(false);
		}
	}

	private void addSubFeatures(TreeItem<Product> newProductItem) {
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

	private void addFeature() {
		//attempt to collect the selected TreeItem
		try {
			//attempt to collect selection
			TreeItem<Product> selectedItem = treeView.getSelectionModel().getSelectedItem();
			//display window with Feature selection list
			String nameOfFeature = Message.selectProduct("Please select the feature to add.",
					"Feature Selection",
					featureList);
			//add Feature to selected Product
			makeTreeItem(nameOfFeature, selectedItem);
		} catch (Exception e) {
			Message.consoleMessage("Exception handled on button click. No TreeView item selected.");
			Message.messageBox("Please select an item in the list.", "Notification");
		}
	}

	private void deleteItem() {
		String selectedItem = treeView.getSelectionModel().getSelectedItem().getValue().getModel();
		Message.consoleMessage("Removing item from TreeView: " + selectedItem);
		treeView.getSelectionModel().getSelectedItem().getParent().getChildren().remove(
				treeView.getSelectionModel().getSelectedItem());

		if (treeView.getRoot().isLeaf()) {
			Message.consoleMessage("Empty product list. Disabling Feature, Delete, and Edit buttons.");
			buttonAddFeature.setDisable(true);
			buttonDeleteItem.setDisable(true);
			buttonEditItem.setDisable(true);
		}

		treeView.getSelectionModel().clearSelection();
	}

	private void editItem(Product product) {
		//will always include "back" button. Instantiate, control, and then move on.
		//buttons
		Button backToTable = new Button("<- Back to Pricing Overview"); //improve on the back arrow - make an image
		//control objects
		backToTable.setOnAction(event -> {
			Message.consoleMessage("Returning to Product Table view");
			treeView.setDisable(false);
			buttonAddProduct.setDisable(false);
			buttonAddFeature.setDisable(false);
			buttonDeleteItem.setDisable(false);
			buttonEditItem.setDisable(false);
			genericPaneWrapper.setContent(centerPane);
		});

		//pre-define GridPane for all cases of the edit window
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

		//disable the TreeView to avoid selecting another TreeItem that is different than what is shown in editWindow()
		treeView.setDisable(true);
		buttonAddProduct.setDisable(true);
		buttonAddFeature.setDisable(true);
		buttonDeleteItem.setDisable(true);
		buttonEditItem.setDisable(true);

		VBox gridPaneVbox = new VBox(backToTable, new Separator(), productDetailPane);
		gridPaneVbox.setPadding(new Insets(10));
		genericPaneWrapper.setContent(gridPaneVbox);
	}

	@SuppressWarnings("unused")
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

	private static Connection getConnection() {
		Message.consoleMessage("Connecting to database.");
		Connection connection = null;
		try {
			Message.consoleMessage("Here1");
			Class.forName("com.mysql");
			Message.consoleMessage("Here2");
			String url = "jdbc:mysql://localhost/movies";
			Message.consoleMessage(url);
			String user = "root";
			String password = "JavaRocks";
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}