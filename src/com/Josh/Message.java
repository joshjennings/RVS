package com.Josh;

import com.RVS.Products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * This class contains standard message methods of various functionality.
 * @author Josh Jennings
 */
public class Message {
	static Stage stage;
	static boolean btnYesClicked;
	static Region spacer;

	public static void initialize() {
		spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
	}

	/**
	 * This method simply adds a date to the console output.
	 */
	public static void consoleMessage(String message) {
		String timeStamp = new SimpleDateFormat("[yyyy/MM/dd|HH:mm:ss.SSS] ").format(Calendar.getInstance().getTime());
		out.println(timeStamp + message);
	}

	@SuppressWarnings("unused")
	public static void messageBox(String message, String title) {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

//		Image warning = new Image("../images/warning.png",50,50,true,true);
//		ImageView imageView = new ImageView(warning);

		Label lbl = new Label();
		lbl.setText(message);

		HBox information = new HBox(/*imageView,*/lbl);
		//TODO: figure out how to properly space the following text. Currently too crowded.
		information.setSpacing(20);
		information.setPadding(new Insets(100));

		Button btnOK = new Button("OK");
		btnOK.setOnAction(e -> stage.close());

		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btnOK);
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
	}

	@SuppressWarnings("unused")
	public static boolean confirmationBox(String message, String title, String textYes, String textNo) {
		btnYesClicked = false;
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

		Label lbl = new Label();
		lbl.setText(message);

		Button btnYes = new Button();
		btnYes.setText(textYes);
		btnYes.setOnAction(e -> btnYes_Clicked() );

		Button btnNo = new Button();
		btnNo.setText(textNo);
		btnNo.setOnAction(e -> btnNo_Clicked() );

		HBox paneBtn = new HBox(20);
		paneBtn.getChildren().addAll(btnYes, btnNo);
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, paneBtn);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();

		return btnYesClicked;
	}

	private static void btnYes_Clicked() {
		stage.close();
		btnYesClicked = true;
	}

	private static void btnNo_Clicked() {
		stage.close();
		btnYesClicked = false;
	}

	static String input;

	@SuppressWarnings("unused")
	public static String inputBox(String message, String title) {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(400);
		stage.setMinHeight(200);

		Label lbl = new Label(message);

		TextField textField = new TextField();

		Button buttonEnter = new Button("Enter");
		buttonEnter.setOnAction(e -> buttonEnterClicked(textField.getText()) );

		Button buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(e -> buttonCancelClicked() );

		HBox paneBtn = new HBox(20);
		paneBtn.getChildren().addAll(buttonEnter, buttonCancel);
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl,textField,paneBtn);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();

		return input;
	}

	private static void buttonEnterClicked(String text) {
		Message.consoleMessage(text);
		stage.close();
		input = text;
	}

	private static void buttonCancelClicked() {
		stage.close();
		input = "DONOTENTERanyNewPRODUCTinHERErightNOW";
	}

	public static String selectProduct(String message, String title, ObservableList<Product> list) {
		//TODO: Center the Enter and Cancel buttons
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(400);
		stage.setMinHeight(200);

		Label lbl = new Label(message);

		//use String model parameter in Product class to build ObservableList<String>
		List<String> productList = list.stream().map(Product::getModel).collect(Collectors.toList());
		ObservableList<String> productListString = FXCollections.observableList(productList);

		//TextField textField = new TextField();
		ComboBox<String> productBox = new ComboBox<>(productListString);
		productBox.getItems().addAll();

		Button buttonEnter = new Button("Enter");
		buttonEnter.setOnAction(e -> buttonEnterClicked(productBox.getValue()) );

		Button buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(e -> buttonCancelClicked() );

		HBox paneBtn = new HBox(20);
		//paneBtn.getChildren().addAll(spacer, buttonEnter, buttonCancel, spacer);
		paneBtn.getChildren().addAll(buttonEnter, buttonCancel);
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl,productBox,paneBtn);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();

		return input;
	}
}
