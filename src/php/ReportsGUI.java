package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ReportsGUI {
	TextField weeklyTextField = new TextField();
	TextField monthlyTextField = new TextField();
		
	
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
		
		//Dimension buttonSize = new Dimension(50, 30);
		
	
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(weeklyLabel)
						.addComponent(monthlyLabel)
			
			));
			layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(weeklyLabel))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(monthlyLabel))
					
			);
	}

	
	
	
	
}
