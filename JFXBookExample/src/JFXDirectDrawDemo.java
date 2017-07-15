
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class JFXDirectDrawDemo extends Application {

    GraphicsContext gc;
    Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BROWN, Color.PINK};
    int colorInd = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FlowPane rootNode = new FlowPane();
        Canvas canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        rootNode.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Draw Directly to а Canvas.");
        primaryStage.setScene(new Scene(rootNode, 450, 450));

        Button btChangeColor = new Button("Change color");

        btChangeColor.setOnAction((ae) -> {
            gc.setFill(colors[colorInd]);
            gc.setStroke(colors[colors.length - colorInd - 1]);

            colorInd++;
            if (colorInd >= colors.length) {
                colorInd = 0;
            }

            gc.strokeLine(0, 0, 200, 200);
            gc.strokeOval(100, 100, 200, 200);
            gc.strokeRect(0, 200, 50, 200);
            gc.fillOval(0, 0, 20, 20);
            gc.fillRect(100, 320, 300, 40);
            gc.fillText("This is drawn on the canvas.", 60, 50);
        });

        rootNode.getChildren().addAll(canvas, btChangeColor);
        gc.strokeLine(0, 0, 200, 200);
        gc.strokeOval(100, 100, 200, 200);
        gc.strokeRect(0, 200, 50, 200);
        gc.fillOval(0, 0, 20, 20);
        gc.fillRect(100, 320, 300, 40);
        gc.setFont(new Font(20));
        gc.fillText("This is drawn on the canvas.", 60, 50);

        primaryStage.show();
    }
}
