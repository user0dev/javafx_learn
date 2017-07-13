/**
 * Created by user0dev on 13.07.17.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;


public class JFXLabelDemo extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(this.getClass().getName());
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(new Label(this.getClass().getCanonicalName()));
        primaryStage.setScene(new Scene(pane, 300, 200));
        primaryStage.show();
    }
}
