import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Created by user on 02.08.2017.
 */
public class JFXScrollPane extends Application {
    @Override
    public void start(Stage stage) {
        Label scrlLabel = new Label (
        "А ScrollPane streamlines the process of\n" +
            "adding scroll bars to а window whose\n" +
            "contents exceed the window's dimensions.\n" +
            "It also enables а control to fit in a\n" +
            "smaller space than it otherwise would.\n" +
            "As such, it often provides а superior\n" +
            "approach over using individual scroll bars."
        );
        FlowPane rootNode = new FlowPane(10, 10);
        ScrollPane scrollPane = new ScrollPane();
        rootNode.getChildren().add(scrollPane);
        scrollPane.setContent(scrlLabel);
        scrollPane.setPrefViewportHeight(80);
        scrollPane.setPrefViewportWidth(130);
        scrollPane.setPannable(false);
        Button reset = new Button("Reset");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scrollPane.setHvalue(0);
                scrollPane.setVvalue(0);
            }
        });
        rootNode.getChildren().add(reset);
        stage.setScene(new Scene(rootNode, 200, 200));
        rootNode.setAlignment(Pos.CENTER);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
