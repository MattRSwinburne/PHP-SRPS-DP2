package php;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportsGUI {
	TextField weeklyTextField = new TextField();
	TextField monthlyTextField = new TextField();
	JScrollPane reportTableScoller = new JScrollPane();
	JTable reportTable = new JTable();
	Button searchBtn = new Button("Search");

	public void ReportsContent(JPanel panel) {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		Dimension labelSize = new Dimension(50, 22);

		Label weeklyLabel = new Label("Weekly");
		weeklyLabel.setAlignment(Label.RIGHT);
		weeklyLabel.setName("weeklyLabel");
		weeklyLabel.setMaximumSize(labelSize);

		Label monthlyLabel = new Label("Monthly");
		monthlyLabel.setAlignment(Label.RIGHT);
		monthlyLabel.setName("monthlyLabel");
		monthlyLabel.setMaximumSize(labelSize);

		// Dimension buttonSize = new Dimension(50, 30);
		Dimension buttonSize = new Dimension(50, 30);
		searchBtn.setName("searchButton");
		searchBtn.setMaximumSize(buttonSize);
		SearchButtonFunction();

		reportTable.setModel(new DefaultTableModel(new Object[][] { { null, null, null } },
				new String[] { "SALEID", "PRODUCT_ID", "SALE_DATE" , "QTYSOLD" }));
		reportTableScoller.setViewportView(reportTable);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(weeklyLabel)
						.addComponent(monthlyLabel).addComponent(searchBtn))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reportTableScoller,
						GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(weeklyLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(monthlyLabel))
				.addComponent(searchBtn)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(reportTableScoller, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE))

		);
	}
	
	 private void SearchButtonFunction() {
	        searchBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	                String startDate = "2016-09-02"; // Take these dates from cleander or somthing
	                String endDate = "2016-09-20";

	                
	                DatabaseIO.getSalesByDateRange(startDate, endDate);

	                DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
	                model.setRowCount(0);
	                reportTable.setModel(GetModelToTable(reportTable, DatabaseIO.reportSaleList));
	                
	                
	                class MyComponent extends JComponent {
	                	   Slice[] slices = { new Slice(5, Color.black), 
	                	   new Slice(33, Color.green),
	                	   new Slice(20, Color.yellow), new Slice(15, Color.red) };
	                	   MyComponent() {}
	                	   public void paint(Graphics g) {
	                	      drawPie((Graphics2D) g, getBounds(), slices);
	                	   }
	                	   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
	                	      double total = 0.0D;
	                	      for (int i = 0; i < slices.length; i++) {
	                	         total += slices[i].value;
	                	      }
	                	      double curValue = 0.0D;
	                	      int startAngle = 0;
	                	      for (int i = 0; i < slices.length; i++) {
	                	         startAngle = (int) (curValue * 360 / total);
	                	         int arcAngle = (int) (slices[i].value * 360 / total);
	                	         g.setColor(slices[i].color);
	                	         g.fillArc(area.x, area.y, area.width, area.height, 
	                	         startAngle, arcAngle);
	                	         curValue += slices[i].value;
	                	      }
	                	   }
	                	   
	                	}
	                class Slice {
	                	   double value;
	                	   Color color;
	                	   public Slice(double value, Color color) {  
	                	      this.value = value;
	                	      this.color = color;
	                	   }
	                	}
	                
	                
	                
	            }
	     
	        });
	    }

	    private DefaultTableModel GetModelToTable(JTable jTable, List<Sale> objList) {
	        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
	        Object[] rowData = new Object[4];
	        for (int i = 0; i < objList.size(); i++) {
	            rowData[0] = objList.get(i).productName;
	            rowData[1] = objList.get(i).qtySold;
	           // rowData[2] = objList.get(i).GetRRP();
	            rowData[2] = objList.get(i).saleDate;
	            model.addRow(rowData);
	            System.out.println(rowData[3]);
	        }
	        return model;
	    }

}
