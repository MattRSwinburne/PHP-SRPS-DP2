package php;

import java.awt.*;
import javax.swing.*;

public class AddSalesRecordGUI {
	
	public static void AddSalesContent(JPanel panel) {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		Dimension labelSize = new Dimension(50, 22);
		
		Label itemLabel = new Label("Item");
		itemLabel.setAlignment(Label.RIGHT);
		itemLabel.setName("itemLabel");
		itemLabel.setMaximumSize(labelSize);
		
		Label quantityLabel = new Label("Quantity");
		quantityLabel.setAlignment(Label.RIGHT);
		quantityLabel.setName("quantityLabel");
		quantityLabel.setMaximumSize(labelSize);
		
		Label priceLabel = new Label("Price");
		priceLabel.setAlignment(Label.RIGHT);
		priceLabel.setName("priceLabel");
		priceLabel.setMaximumSize(labelSize);
		
		Dimension textFieldMinSize = new Dimension(100, 22);
		Dimension textFieldPrefSize = new Dimension(180, 22);
		Dimension textFieldMaxSize = new Dimension(300, 22);
		
		TextField itemTextField = new TextField();
		itemTextField.setName("itemTextField");
		itemTextField.setMinimumSize(textFieldMinSize);
		itemTextField.setPreferredSize(textFieldPrefSize);
		itemTextField.setMaximumSize(textFieldMaxSize);
		
		TextField quantityTextField = new TextField();
		quantityTextField.setName("quantityTextField");
		quantityTextField.setMinimumSize(textFieldMinSize);
		quantityTextField.setPreferredSize(textFieldPrefSize);
		quantityTextField.setMaximumSize(textFieldMaxSize);
		
		TextField priceTextField = new TextField();
		priceTextField.setName("priceTextField");
		priceTextField.setMinimumSize(textFieldMinSize);
		priceTextField.setPreferredSize(textFieldPrefSize);
		priceTextField.setMaximumSize(textFieldMaxSize);
		
		Dimension buttonSize = new Dimension(50, 30);
		
		Button addButton = new Button("ADD");
		addButton.setName("addButton");
		addButton.setMaximumSize(buttonSize);
		
		Button clearButton = new Button("CLEAR");
		clearButton.setName("clearButton");
		clearButton.setMaximumSize(buttonSize);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemLabel)
					.addComponent(quantityLabel)
					.addComponent(priceLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemTextField)
					.addComponent(quantityTextField)
					.addComponent(priceTextField)
					.addGroup(layout.createSequentialGroup()
						.addComponent(addButton)
						.addComponent(clearButton)))
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
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(addButton)
					.addComponent(clearButton))
		);
		
		
		//AddSalesRecordFunctionality.AddFunctionality(panel);
	}
}
