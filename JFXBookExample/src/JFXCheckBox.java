import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




/**
 * Created by user0dev on 20.07.17.
 */
public class JFXCheckBox extends Application {
    private final String CH_COLOR = "Выбранные цвета: ";
    private GraphicsContext gc;
    private Canvas canvas;
    private Label output;
    private CheckBox red, green, blue;
    public static void main(String[] args) {
        launch(args);
    }
    public void handlerCheckBox(ActionEvent event) {
        int colorRed = 0;
        int colorBlue = 0;
        int colorGreen = 0;
        String selectedColor = "";
        if (red.isSelected()) {
            colorRed = 255;
            selectedColor += "красный";
        }
        if (green.isSelected()) {
            if (!selectedColor.isEmpty()) {
                selectedColor += ", ";
            }
            colorGreen = 255;
            selectedColor += "зеленый";
        }
        if (blue.isSelected()) {
            colorBlue = 255;
            if (!selectedColor.isEmpty()) {
                selectedColor += ", ";
            }
            selectedColor += "синий";
        }
        output.setText(CH_COLOR + selectedColor);
        gc.setFill(Color.rgb(colorRed, colorGreen, colorBlue));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(this.getClass().getName());
        VBox layoutRoot = new VBox(10);
        HBox layoutLevel2 = new HBox(10);
        VBox layoutCheckBox = new VBox(10);
        output = new Label();
        canvas = new Canvas(200, 200);
        gc = canvas.getGraphicsContext2D();
        red = new CheckBox("Красный");
        green = new CheckBox("Зеленый");
        blue = new CheckBox("Синий");
        red.setOnAction(this::handlerCheckBox);
        green.setOnAction(this::handlerCheckBox);
        blue.setOnAction(this::handlerCheckBox);
        Separator separator = new Separator();
        layoutRoot.setAlignment(Pos.CENTER);
        layoutLevel2.setAlignment(Pos.CENTER);
        layoutCheckBox.setAlignment(Pos.CENTER_LEFT);
        layoutRoot.getChildren().addAll(layoutLevel2, separator, output);
        layoutLevel2.getChildren().addAll(layoutCheckBox, canvas);
        layoutCheckBox.getChildren().addAll(red, green, blue);
        primaryStage.setScene(new Scene(layoutRoot, 400, 400));
        handlerCheckBox(null);
        primaryStage.show();
    }
}
