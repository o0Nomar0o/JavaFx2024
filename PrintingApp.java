package com.example.projects;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrintingApp extends Application {
    private GridPane root = new GridPane();
    private TextArea ta = new TextArea();
    private ComboBox<Printer> cb = new ComboBox<>();
    private Button btn = new Button("Print Text");
    private Label lb1 = new Label("Status");
    private Label lb2 = new Label(".....");
    ObservableList<Printer> p = FXCollections.observableArrayList(Printer.getAllPrinters());


    @Override
    public void start(Stage st) throws Exception {

        root.add(ta,0,0,2,6);
        root.addColumn(0,cb,lb1);
        root.addColumn(1,btn,lb2);
        cb.setItems(p);
        cb.getSelectionModel().selectFirst();

        btn.setOnAction(e->Print());

        Scene sc = new Scene(root,300,300);
        st.setScene(sc);
        st.setTitle("Print Document");
        st.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void Print(){
        Printer pt = cb.getValue();
        PrinterJob pj = PrinterJob.createPrinterJob(pt);
        if ( pj != null && pj.showPrintDialog(null)){

            Label labelToPrint = new Label("");

            PageLayout pageLayout = pj.getJobSettings().getPageLayout();
            double scaleX = pageLayout.getPrintableWidth()/ labelToPrint.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight()/ labelToPrint.getBoundsInParent().getHeight();
            labelToPrint.setScaleX(scaleX);
            labelToPrint.setScaleY(scaleY);

            if (pj.printPage(labelToPrint)) {
                pj.endJob();
                lb2.setText("Print success");
            } else {
                lb2.setText("Failed to print");
            }

        }
    }
}
