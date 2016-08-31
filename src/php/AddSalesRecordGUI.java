package php;

import java.awt.*;
import javax.swing.*;

public class AddSalesRecordGUI {
	
	public static void AddSalesContent(JPanel panel) {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		Label itemLabel = new Label("Item");
		itemLabel.setAlignment(Label.RIGHT);
		
		Label quantityLabel = new Label("Quantity");
		quantityLabel.setAlignment(Label.RIGHT);
		
		Label priceLabel = new Label("Price");
		priceLabel.setAlignment(Label.RIGHT);
		
		TextField itemTextField = new TextField();
		itemTextField.setPreferredSize(new Dimension(180, 22));
		
		TextField quantityTextField = new TextField();
		quantityTextField.setPreferredSize(new Dimension(180, 22));
		
		TextField priceTextField = new TextField();
		priceTextField.setPreferredSize(new Dimension(180, 22));
		
		Button addButton = new Button("ADD");
		Button clearButton = new Button("CLEAR");
		Button exitButton = new Button("EXIT");
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemLabel)
					.addComponent(quantityLabel)
					.addComponent(priceLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemTextField)
					.addComponent(quantityTextField)
					.addComponent(priceTextField))
		);
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemLabel)
					.addComponent(itemTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(quantityLabel)
					.addComponent(quantityTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(priceLabel)
					.addComponent(priceTextField))
		);
		
		
		//AddSalesRecordFunctionality.AddFunctionality(panel);
	}
}
