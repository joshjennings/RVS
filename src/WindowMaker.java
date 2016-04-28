import com.RVS.Accessories.*;
import com.RVS.Products.Product;
import com.RVS.Products.Vessel;
import com.RVS.Products.Vessels.*;
import com.RVS.WindowMakerProcessor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.Josh.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	ObservableList<Product> productObservableList;
	HBox buttonBar;

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
		Message.consoleMessage("--------------------------------");
		Message.consoleMessage(" ");
	}

	private void initialize() {
		//initiate database connection
//		databaseConnection = WindowMakerProcessor.getConnection(); //SQL database connection

		//define horizontal spacer that will grow with the window
		spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		//populate data lists
		productList = Product.constructListOfStandardProducts();
		featureList = Feature.constructListOfStandardFeatures();
		mapDiameterLength = Vessel.makeDiameterLengthMap();

		List<Product> productList = new ArrayList<>();
		productObservableList = FXCollections.observableList(productList);
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
		centerPane = new TableView<>();

		genericPaneWrapper = new ScrollPane();
		//fit to containing view pane
		genericPaneWrapper.setFitToWidth(true);
		genericPaneWrapper.setFitToHeight(true);

		Message.consoleMessage("Adding center pane.");

		//CREATE TREEVIEW
		root.setExpanded(true);

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
		buttonBar = new HBox();
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
		buttonAddProduct.setOnAction(event -> WindowMakerProcessor.addProduct(treeView, root, productObservableList, buttonBar));
		buttonAddFeature.setOnAction(event -> WindowMakerProcessor.addFeature(treeView, featureList));
		buttonDeleteItem.setOnAction(event -> WindowMakerProcessor.deleteItem(treeView, buttonBar));
		buttonEditItem.setOnAction(event -> editItem(treeView.getSelectionModel().getSelectedItem().getValue()));

		VBox leftPane = new VBox(treeView, buttonBar);
		VBox.setVgrow(treeView, Priority.ALWAYS); //always grow the TreeView vertically when modifying window.

		//CREATE TABLEVIEW
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
//		TableColumn<Product, String> columnListPrice = new TableColumn<>("List Price");
//		columnListPrice.setMinWidth(100);
//		columnListPrice.setPrefWidth(150);
//		columnListPrice.setCellValueFactory(new PropertyValueFactory<>("priceListFormatted"));

		//add columns to table
		//noinspection unchecked
		centerPane.getColumns().addAll(columnModel, columnDesc/*, columnListPrice*/);
		centerPane.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		centerPane.setMinWidth(columnModel.getMinWidth() + columnDesc.getMinWidth()/* + columnListPrice.getMinWidth()*/);
		//add observable list to table
		centerPane.setItems(productObservableList);

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

	private void editItem(Product product) {
		Message.consoleMessage("Entering item edit window.");
		//will always include "back" button. Instantiate, control, and then move on.
		//buttons
		Image leftArrowImage = new Image(getClass().getResourceAsStream("leftArrow.png"));
		ImageView leftArrow = new ImageView(leftArrowImage);
		leftArrow.setFitWidth(30);
		leftArrow.setFitHeight(30);
		Button backToTable = new Button("Back to Pricing Overview", leftArrow); //improve on the back arrow - make an image
		//control objects
		backToTable.setOnAction(event -> {
			Message.consoleMessage("Returning to Product Table view");
			treeView.setDisable(false);
			buttonBar.setDisable(false);
			genericPaneWrapper.setContent(centerPane);

			//update existing TableView items
			centerPane.getColumns().get(1).setVisible(false);
			centerPane.getColumns().get(1).setVisible(true);
			//following doesn't work
//			treeView.setVisible(false);
//			treeView.setVisible(true);
		});

		//pre-define GridPane for all cases of the edit window
		Pane productDetailPane;
		productDetailPane = WindowMakerProcessor.editItem(product);

		//disable the TreeView to avoid selecting another TreeItem that is different than what is shown in editWindow()
		treeView.setDisable(true);
		buttonBar.setDisable(true);

		//add all elements to the edit window grid
		VBox gridPaneVbox = new VBox(backToTable, new Separator(), productDetailPane);
		//use element padding
		gridPaneVbox.setPadding(new Insets(10));
		gridPaneVbox.setSpacing(10);
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



}