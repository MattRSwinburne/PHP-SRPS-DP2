package php;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import javafx.stage.Stage;


public class ReportsGUI {
	
	JDateChooser startDate = new JDateChooser();
	JDateChooser endDate = new JDateChooser();
	Button printButton = new Button("PRINT");
	JRadioButton weeklyRadioButton = new JRadioButton("Weekly");
	JRadioButton monthlyRadioButton = new JRadioButton("Monthly");
	

	JLabel monthlyLabel = new JLabel("Monthly");
	JLabel startDateLabel = new JLabel("Start Date");
	JLabel endDateLabel = new JLabel("End Date");
	
	
	public void ReportsContent(JPanel panel) {
		
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		Dimension buttonSize = new Dimension(50, 30);
		Dimension dateFieldSize = new Dimension(150, 22);
		
		printButton.setMaximumSize(buttonSize);
		endDate.setMaximumSize(dateFieldSize);
		startDate.setMaximumSize(dateFieldSize);
		
		printButtonFunction();
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(weeklyRadioButton)
						.addComponent(startDateLabel)
						.addComponent(endDateLabel))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(monthlyRadioButton)
						.addComponent(startDate)
						.addComponent(endDate)
						.addComponent(printButton))
					
			
			);
			layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(weeklyRadioButton)
						.addComponent(monthlyRadioButton))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(startDateLabel)
						.addGroup(layout.createSequentialGroup()
						.addComponent(startDate)))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(endDateLabel)
						.addGroup(layout.createSequentialGroup()
						.addComponent(endDate)
						.addComponent(printButton)))
					
			);
	}

	private void printButtonFunction() {
		printButton.addActionListener(new ActionListener() {
			private Stage stage;

			public void actionPerformed(ActionEvent arg0) {
				
				PrintFunction output = new PrintFunction();
				output.create(null);
				//output.openFile();
				//output.addRecords();
				//output.closeFile();
				//JOptionPane.showMessageDialog(null, "report printed");	
			
			}
		});
	}
	
	
	
}
