/**
 * Created by user0dev on 17.07.17.
 */
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;

public class JFXRadioButton2 extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    public static void main(String[] args) {
        launch(args);
    }
/*    public void radioBottonAction(ActionEvent event) {
        Color color = Color.WHITE;
        if (event.getSource() instanceof RadioButton) {
            RadioButton rb = (RadioButton) event.getSource();
            switch (rb.getText()) {
                case "Red":
                    color = Color.RED;
                    break;
                case "Blue":
                    color = Color.BLUE;
                    break;
                case "Green":
                    color = Color.GREEN;
                    break;
            }
        }
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }*/
    @Override
    public void start(Stage primaryStage) {
        FlowPane rootNode = new FlowPane(10, 10);
        primaryStage.setTitle(this.getClass().getName());
        primaryStage.setScene(new Scene(rootNode, 400, 400));
        ToggleGroup group = new ToggleGroup();
        RadioButton rbRed = new RadioButton("Red"),
                rbGreen = new RadioButton("Green"),
                rbBlue = new RadioButton("Blue");
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(rbRed, rbBlue, rbGreen);
        rootNode.setAlignment(Pos.CENTER);
        canvas = new Canvas(150, 150);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        rootNode.getChildren().addAll(vBox, canvas);
//        rbRed.setOnAction(this::radioBottonAction);
//        rbBlue.setOnAction(this::radioBottonAction);
//        rbGreen.setOnAction(this::radioBottonAction);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton rb = (RadioButton)newValue;
                primaryStage.setTitle("Selected color: " + rb.getText());
                Color color = Color.WHITE;
                switch (rb.getText()) {
                    case "Red":
                        color = Color.RED;
                        break;
                    case "Blue":
                        color = Color.BLUE;
                        break;
                    case "Green":
                        color = Color.GREEN;
                        break;
                }
                gc.setFill(color);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            }
        });
        primaryStage.show();

    }
}