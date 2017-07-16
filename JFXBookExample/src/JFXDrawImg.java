
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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
public class JFXDrawImg extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        FlowPane rootNode = new FlowPane();
        ImageView img = new ImageView("cat.jpg");
        rootNode.getChildren().add(img);
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }
}
