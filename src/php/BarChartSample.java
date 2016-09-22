package php;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class BarChartSample extends Application {
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
        
        //viewReport.fillTableModel();
   /*     XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(austria, 57401.85));
        series2.getData().add(new XYChart.Data(brazil, 41941.19));
        series2.getData().add(new XYChart.Data(france, 45263.37));
        series2.getData().add(new XYChart.Data(italy, 117320.16));
        series2.getData().add(new XYChart.Data(usa, 14845.27));  
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(austria, 45000.65));
        series3.getData().add(new XYChart.Data(brazil, 44835.76));
        series3.getData().add(new XYChart.Data(france, 18722.18));
        series3.getData().add(new XYChart.Data(italy, 17557.31));
        series3.getData().add(new XYChart.Data(usa, 92633.68));  */
        
        Scene scene  = new Scene(bc,800,600);
       // bc.getData().addAll(series1, series2, series3);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}