package jfxbrowser;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by user0dev on 20.07.17.
 */
public class JFXBrowser extends Application {
    private Stage primaryStage;
    private WebEngine engine;
    private WebView view;


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox rootNode = new VBox();
        HBox controls = new HBox();
        rootNode.getChildren().add(controls);

    }
}
