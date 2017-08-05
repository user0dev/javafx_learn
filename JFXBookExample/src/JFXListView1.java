import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by user0dev on 21.07.17.
 */
public class JFXListView1 extends Application {
    private final String OUPUT_TEXT = "Changed transport: ";
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        VBox rootNode = new VBox(10);
        rootNode.setAlignment(Pos.CENTER);
        rootNode.setPadding(new Insets(10));
        stage.setTitle(this.getClass().getName());

        ListView<String> listView = new ListView<String>(FXCollections.observableArrayList("Car", "Bus", "Train", "Tram", "Byke", "Airplan"));
        listView.setFixedCellSize(24);

        listView.setPrefHeight(listView.getItems().size() * listView.getFixedCellSize()+2);
        Label output = new Label(OUPUT_TEXT);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                output.setText(OUPUT_TEXT + newValue);
            }
        });
        rootNode.getChildren().addAll(listView, output);
        System.out.println(listView.getFixedCellSize());
        System.out.println(listView.getItems().size());
        stage.setScene(new Scene(rootNode, Color.RED));
        stage.show();
    }
}
