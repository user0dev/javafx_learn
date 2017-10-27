import javafx.application.Application;
import javafx.collections.ObservableSet;
import javafx.geometry.VPos;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Created by user0dev on 26.10.17.
 */
public class Print extends Application {

    public static double MARGIN = 20.0;

    public ArrayList<Text> genAnaliz(double x, double y, double width, double height, String numClass, String name, String age) {
        ArrayList<Text> list = new ArrayList<>();

        Font font = Font.font("serif", 16);

        double left = x + MARGIN;
        double top = y + MARGIN;

        double realW = width - MARGIN * 2;
        double realH = height - MARGIN * 2;

        Text tNumClass = new Text(left, top, numClass);
        tNumClass.setTextAlignment(TextAlignment.RIGHT);
        tNumClass.setFont(font);
        list.add(tNumClass);

        Text analiz = new Text(left, y + height / 3, "Анализ мочи");
        analiz.setTextAlignment(TextAlignment.CENTER);
        analiz.setFont(font);
        list.add(analiz);

        Text tName = new Text(left, analiz.getY() + analiz.getLayoutBounds().getHeight() + realH / 40, name);
        tName.setTextAlignment(TextAlignment.LEFT);
        tName.setFont(font);
        list.add(tName);

        Text tAge = new Text(left, tName.getY() + tName.getLayoutBounds().getHeight() + realH / 40, age);
        tAge.setTextAlignment(TextAlignment.RIGHT);
        tAge.setFont(font);
        list.add(tAge);

        for (Text t : list) {
            t.setWrappingWidth(realW);
            t.setTextOrigin(VPos.TOP);

        }


        return list;
    }

    @Override
    public void start(Stage stage) {
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        for (Printer printer : printers) {
            System.out.println(printer);
        }
        Printer defPrint = Printer.getDefaultPrinter();
        if (defPrint == null) {
            System.err.println("There isn't default printer");
            System.exit(1);
        }
        System.out.println("Default printer: " + defPrint.getName());
        PrinterAttributes pa = defPrint.getPrinterAttributes();
        System.out.println(pa.getSupportedCollations());
        System.out.println(pa.getSupportedPageOrientations());
        System.out.println(pa.getSupportedPapers());
        System.out.println(pa.getSupportedPaperSources());
        System.out.println(pa.getSupportedPrintColors());
        System.out.println(pa.getSupportedPrintSides());
        System.out.println(pa.getSupportedPrintQuality());
        System.out.println(pa.getMaxCopies());

        PageLayout pageLayout = defPrint.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, MARGIN, MARGIN, MARGIN, MARGIN);
        System.out.println(pageLayout.getPrintableHeight());
        System.out.println(pageLayout.getPrintableWidth());
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        JobSettings jobSettings = printerJob.getJobSettings();
        jobSettings.setPageLayout(pageLayout);
        System.out.println(jobSettings);


//        GridPane gridPane = new GridPane();
//
//        Canvas canvas = new Canvas(200, 200);
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//
//        gc.setFill(Color.WHITE);
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        gc.setFill(Color.BLACK);
//        gc.setTextAlign(TextAlignment.RIGHT);
//        gc.setTextBaseline(VPos.TOP);
//        gc.fillText("8A", canvas.getWidth() - 10, 10);
//        //gc.text
//
//        gridPane.add(canvas, 0, 0);
//        //gridPane
//

        Pane pane = new Pane();


        stage.setScene(new Scene(pane, pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight()));

        Double w = pageLayout.getPrintableWidth();
        Double h = pageLayout.getPrintableHeight();

        Double fullH = h + MARGIN * 2;
        Double fullW = w + MARGIN * 2;

        Double left = -MARGIN;
        Double top = -MARGIN;


        ArrayList<Line> lines = new ArrayList<>();

        lines.add(new Line(w / 2, 0, w / 2, h));
        lines.add(new Line(0, h / 2, w, h / 2));

//        for (int i = 1; i <= 3; i++) {
//            lines.add(new Line(0, right + fullH * i / 4, w, right + fullH * i / 4));
//        }

        for (Line ln : lines) {
            ln.setStrokeWidth(0.2);
            ln.getStrokeDashArray().addAll(2.0, 6.0);
        }

        pane.getChildren().addAll(lines);

        pane.getChildren().addAll(genAnaliz(left, top, fullW / 2, fullH / 2, "8a", "Артамонова Яна", "14 лет"));
        pane.getChildren().addAll(genAnaliz(left, h / 2, fullW / 2, fullH / 2, "8a", "Волков Тимур", "14 лет"));
        pane.getChildren().addAll(genAnaliz(w / 2, top, fullW / 2, fullH / 2, "8a", "Ефимова Юлия", "14 лет"));
        pane.getChildren().addAll(genAnaliz(w / 2, h / 2, fullW / 2, fullH / 2, "8a", "Золотова Оксана", "14 лет"));

        System.out.println("Sending print job...");
        boolean printed = printerJob.printPage(pageLayout, pane);
        if (printed) {
            System.out.println("Ending job");
            printerJob.endJob();
        } else {
            System.out.println("Не удалось создать задание на печать");
        }

        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
