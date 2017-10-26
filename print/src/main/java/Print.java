import javafx.application.Application;
import javafx.collections.ObservableSet;
import javafx.geometry.VPos;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



/**
 * Created by user0dev on 26.10.17.
 */
public class Print extends Application {

    @Override
    public void start(Stage stage) {
//        ObservableSet<Printer> printers = Printer.getAllPrinters();
//        for (Printer printer : printers) {
//            System.out.println(printer);
//        }
//        Printer defPrint = Printer.getDefaultPrinter();
//        if (defPrint == null) {
//            System.err.println("There isn't default printer");
//            System.exit(1);
//        }
//        PrinterAttributes pa = defPrint.getPrinterAttributes();
//        System.out.println(pa.getSupportedCollations());
//        System.out.println(pa.getSupportedPageOrientations());
//        System.out.println(pa.getSupportedPapers());
//        System.out.println(pa.getSupportedPaperSources());
//        System.out.println(pa.getSupportedPrintColors());
//        System.out.println(pa.getSupportedPrintSides());
//        System.out.println(pa.getSupportedPrintQuality());
//        System.out.println(pa.getMaxCopies());
//
//        PageLayout pageLayout = defPrint.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
//        System.out.println(pageLayout.getPrintableHeight());
//        System.out.println(pageLayout.getPrintableWidth());
//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        JobSettings jobSettings = printerJob.getJobSettings();
//
//        System.out.println(jobSettings);

        GridPane gridPane = new GridPane();

        Canvas canvas = new Canvas(200, 200);

        GraphicsContext gc = canvas.getGraphicsContext2D();


        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setTextBaseline(VPos.TOP);
        gc.fillText("8A", canvas.getWidth() - 10, 10);
        gc.text

        gridPane.add(canvas, 0, 0);
        //gridPane

        stage.setScene(new Scene(gridPane));

        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
