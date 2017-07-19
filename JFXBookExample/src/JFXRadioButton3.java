import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by user0dev on 17.07.17.
 */
public class JFXRadioButton3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        FlowPane rootNode = new FlowPane(10, 10);
        primaryStage.setTitle(this.getClass().getName());
        Scene scene = new Scene(rootNode, 400, 400);
        scene.setFill(Color.DARKGREY);
        primaryStage.setScene(scene);
        
        RadioButton[] buttonList = {
            new RadioButton("Train"),
            new RadioButton("Tram"),
            new RadioButton("Bus"),
            new RadioButton("Car")
        };
        ToggleGroup tg = new ToggleGroup();
        for (RadioButton rb : buttonList) {
            rb.setToggleGroup(tg);

        }
        Separator separator = new Separator();
        separator.setPrefWidth(rootNode.getWidth() - 60);
        rootNode.getChildren().addAll(buttonList);
        rootNode.setAlignment(Pos.CENTER);
        Button button = new Button("Confirm transport");
        rootNode.getChildren().addAll(separator, button);
        
        button.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                RadioButton rb = (RadioButton)tg.getSelectedToggle();
                if(rb != null) {
                    primaryStage.setTitle("Selected transport: " + rb.getText());
                } else {
                    primaryStage.setTitle("Selected transport: nothing");
                }
                
            }
        });
        
        primaryStage.show();
    }
}
