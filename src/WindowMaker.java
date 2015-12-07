import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.lang.System.*;

/**
 * Created by Josh on 12/2/2015.
 */
public class WindowMaker {

    public static void main(String[] args) {
        //out.println("TEST");
        //TODO: add new method to create splash screen

        //TODO: pre-load product data -> models, descriptions, prices

        //TODO: update the splash screen with loading status

        //TODO: move primary window to another class
        PrimaryWindow primaryWindow = new PrimaryWindow(args);
    }

}