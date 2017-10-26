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

    public static Double MARGIN = 17.0;

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


        ArrayList<Line> lines = new ArrayList<>();

        lines.add(new Line(w / 2, 0, w / 2, h));

        for (int i = 1; i <= 3; i++) {
            lines.add(new Line(0, h))
        }

        for (Line ln : lines) {
            ln.setStrokeWidth(0.2);
            ln.getStrokeDashArray().addAll(2.0, 6.0);
        }

        pane.getChildren().addAll(lines);

//        System.out.println("Sending print job...");
//        boolean printed = printerJob.printPage(pageLayout, pane);
//        if (printed) {
//            System.out.println("Ending job");
//            printerJob.endJob();
//        } else {
//            System.out.println("Не удалось создать задание на печать");
//        }

        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
