
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class JFXToggleButton extends Application {
    public static final String BUTTON_PRESS = "ToggleButton нажата";
    public static final String BUTTON_LEAVE = "ToggleButton отпущена";
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primary) {
        FlowPane rootNode = new FlowPane(10, 10);
        primary.setTitle(BUTTON_LEAVE);
        Label label = new Label(BUTTON_LEAVE);
        ToggleButton button = new ToggleButton("ToggleButton");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if(button.isSelected()) {
                    primary.setTitle(BUTTON_PRESS);
                    label.setText(BUTTON_PRESS);
                } else {
                    primary.setTitle(BUTTON_LEAVE);
                    label.setText(BUTTON_LEAVE);
                }
            }
        });
        rootNode.getChildren().addAll(button, label);
        primary.setScene(new Scene(rootNode));
        primary.show();
    }
}