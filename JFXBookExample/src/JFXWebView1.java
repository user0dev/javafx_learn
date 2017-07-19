
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User0dev <user0dev@yandex.ru>
 */
public class JFXWebView1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        WebView wv = new WebView();
        FlowPane rootNode = new FlowPane();
        rootNode.setAlignment(Pos.CENTER);
        WebEngine we = wv.getEngine();
        rootNode.getChildren().add(wv);
        we.load("https://yandex.ru");
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }
    
}
