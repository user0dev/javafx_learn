import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.event.ActionEvent;



public class JFXEventDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void methodClose(ActionEvent event) {
        Platform.exit();
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(this.getClass().getName());
        VBox rootNode = new VBox();
        rootNode.setAlignment(Pos.CENTER);
        HBox hLineNode = new HBox(10);
        hLineNode.setAlignment(Pos.CENTER);
        Button btAlpha = new Button("Alpha");
        Button btBeta = new Button("Beta");
        hLineNode.getChildren().addAll(btAlpha, btBeta);
        Label lbText = new Label("Нажмите любую кнопку");
        btAlpha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lbText.setText("Alpha была нажата");
            }
        });
        btBeta.setOnAction((ae)-> lbText.setText("Beta была нажата"));
        Button btClose = new Button("Close");
        btClose.setOnAction(this::methodClose);
        rootNode.getChildren().addAll(hLineNode, lbText, btClose);

        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }
}