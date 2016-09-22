package php;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class PrintFunction extends Application {
	ViewRecordsGUI viewReport = new ViewRecordsGUI();
	ReportsGUI reportsGUI = new ReportsGUI();
	
    final static String january = "January";
    final static String february = "February";
    final static String march = "March";
    final static String april = "April";
    final static String may = "May";
    final static String june = "June";
    final static String jully = "Jully";
    final static String august = "August";
    final static String september = "September";
    final static String october = "October";
    final static String november = "November";
    final static String december = "December";
  
  

    @Override public void start(Stage stage) {
        stage.setTitle("PHP Sales");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Monthly Sales Report");
        xAxis.setLabel("Month");       
        yAxis.setLabel("Quantity");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2016");       
        series1.getData().add(new XYChart.Data(january, 25601.34));
        series1.getData().add(new XYChart.Data(february, 20148.82));
        series1.getData().add(new XYChart.Data(march, 10000));
        series1.getData().add(new XYChart.Data(april, 35407.15));
        series1.getData().add(new XYChart.Data(may, 12000));      
        series1.getData().add(new XYChart.Data(june, 19000));
        series1.getData().add(new XYChart.Data(jully, 2000));
        series1.getData().add(new XYChart.Data(august, 22000));
        series1.getData().add(new XYChart.Data(september, 32000));
        series1.getData().add(new XYChart.Data(october, 12800));
        series1.getData().add(new XYChart.Data(november, 14000));
        series1.getData().add(new XYChart.Data(december, 42000));


        
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}