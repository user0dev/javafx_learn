import javafx.application.Application;
import javafx.scene.Scene;
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
        Separator separator
        rootNode.getChildren().addAll(buttonList);

        primaryStage.show();
    }
}
