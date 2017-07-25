import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by user on 25.07.2017.
 */
public class JFXComboBox extends Application {
    private final String BUTTON_MASK = "ComboBox<>.setValue(\"%s\")";

    public static void main(String[] args) {
        launch(args);
    }
//    public void handleEdit(InputMethodEvent event) {
//
//    }
    @Override
    public void start(Stage stage) {
        VBox rootNode = new VBox(10);
        rootNode.setPadding(new Insets(10));
        ComboBox<String> cb = new ComboBox<String>(FXCollections.observableArrayList("Машина", "Велосипед",
                "Автобус", "Пешком"));
        cb.setEditable(true);
        TextField tf = new TextField();
        Button button = new Button("ComboBox<>.setValue()");
        button.setMinWidth(250);
        button.setPrefWidth(button.getMinWidth());
        rootNode.getChildren().addAll(cb, tf, button);

        /*tf.setOnInputMethodTextChanged(new EventHandler<InputMethodEvent>() {
            @Override
            public void handle(InputMethodEvent event) {
                button.setText(String.format(BUTTON_MASK, tf.getText()));
            }
        });*/

//        tf.textProperty().addListener((observable, oldValue, newValue) -> {
//            button.setText(String.format(BUTTON_MASK, newValue));
//        });
        tf.setOnAction(event -> {
            button.setText(String.format(BUTTON_MASK, tf.getText()));
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cb.setValue(tf.getText());
            }
        });
        button.setText(String.format(BUTTON_MASK, tf.getText()));
        TextField output = new TextField();
        output.setEditable(false);
        rootNode.getChildren().add(output);
        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                output.setText(cb.getValue());
            }
        });
        stage.setScene(new Scene(rootNode));

        stage.show();

    }
}
