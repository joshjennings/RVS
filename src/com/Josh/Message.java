package com.Josh;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.System.out;

/**
 * This class contains standard message methods of various functionality.
 * @author Josh Jennings
 */
public class Message {

	/**
	 * This method simply adds a date to the console output.
	 */
	public static void consoleMessage(String message) {
		String timeStamp = new SimpleDateFormat("[yyyy/MM/dd|HH:mm:ss.SSS] ").format(Calendar.getInstance().getTime());
		out.println(timeStamp + message);
	}

	@SuppressWarnings("unused")
	public static void messageBox(String message, String title)
	{
		Stage stage = new Stage();
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

	static Stage stage;
	static boolean btnYesClicked;

	@SuppressWarnings("unused")
	public static boolean confirmationBox(String message, String title, String textYes, String textNo) {
		btnYesClicked = false;
		stage = new Stage();
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
}
