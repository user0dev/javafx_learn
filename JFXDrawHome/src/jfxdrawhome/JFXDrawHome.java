/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxdrawhome;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author User0dev <user0dev@yandex.ru>
 */
public class JFXDrawHome extends Application {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group rootNode = new Group();
        rootNode.getChildren().add(canvas);
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, WIDTH, HEIGHT / 2);
        gc.setFill(Color.GREEN);
        gc.fillRect(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
        gc.setFill(Color.DARKGOLDENROD);
        gc.fillRect(300, 250, 200, 200);
        gc.setFill(Color.FIREBRICK);
        gc.fillRect(330, 145, 20, 70);
        gc.setFill(Color.DARKCYAN);
        gc.fillPolygon(new double[]{280, 400, 520}, new double[]{250, 160, 250}, 3);
        gc.setFill(Color.SADDLEBROWN);
        gc.fillRect(360, 310, 80, 80);
        gc.setFill(Color.DODGERBLUE);
        gc.fillRect(366, 316, 31, 31);        
        gc.fillRect(403, 316, 31, 31);
        gc.fillRect(366, 353, 31, 31);
        gc.fillRect(403, 353, 31, 31);
        
        gc.setFill(Color.SADDLEBROWN);
        gc.fillRect(120, 170, 10, 250);
        
        gc.setFill(Color.CHARTREUSE);
        gc.fillPolygon(new double[]{50, 200, 160, 90}, new double[]{280, 280, 150, 150}, 4);
        
        gc.setFill(Color.rgb(240, 240, 240));
        gc.fillOval(120, 30, 140, 70);
        gc.fillOval(170, 20, 140, 70);
        
        primaryStage.setScene(new Scene(rootNode, WIDTH, HEIGHT));
        primaryStage.show();
        
    }
    
}
