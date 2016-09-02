package php;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;


public class AddSalesRecordGUI {
	TextField itemTextField = new TextField();
	TextField quantityTextField = new TextField();
	TextField priceTextField = new TextField();
	//date chooser
	JDateChooser dateChooser = new JDateChooser();
	

	Button addButton = new Button("ADD");
	Button clearButton = new Button("CLEAR");
	
	public void AddSalesContent(JPanel panel) {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		Dimension labelSize = new Dimension(50, 22);
		//Dimension labelSize = new Dimension(62, 22);
		
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
		
		//label for date chooser
		Label dateLabel = new Label("Date");
		dateLabel.setAlignment(Label.RIGHT);
		dateLabel.setName("dateLabel");
		dateLabel.setMaximumSize(labelSize);
		
		Dimension textFieldMinSize = new Dimension(100, 22);
		Dimension textFieldPrefSize = new Dimension(180, 22);
		Dimension textFieldMaxSize = new Dimension(300, 22);
		
		itemTextField.setName("itemTextField");
		itemTextField.setMinimumSize(textFieldMinSize);
		itemTextField.setPreferredSize(textFieldPrefSize);
		itemTextField.setMaximumSize(textFieldMaxSize);
		
		quantityTextField.setName("quantityTextField");
		quantityTextField.setMinimumSize(textFieldMinSize);
		quantityTextField.setPreferredSize(textFieldPrefSize);
		quantityTextField.setMaximumSize(textFieldMaxSize);
		
		priceTextField.setName("priceTextField");
		priceTextField.setMinimumSize(textFieldMinSize);
		priceTextField.setPreferredSize(textFieldPrefSize);
		priceTextField.setMaximumSize(textFieldMaxSize);
		
		//date chooser
		dateChooser.setName("dateChooser");
		dateChooser.setMinimumSize(textFieldMinSize);
		dateChooser.setPreferredSize(textFieldPrefSize);
		dateChooser.setMaximumSize(textFieldMaxSize);
		
		Dimension buttonSize = new Dimension(50, 30);
		
		addButton.setName("addButton");
		addButton.setMaximumSize(buttonSize);
		AddButtonFunction();

		clearButton.setName("clearButton");
		clearButton.setMaximumSize(buttonSize);
		ClearButtonFunctionality();
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemLabel)
					.addComponent(quantityLabel)
					.addComponent(priceLabel)
					.addComponent(dateLabel))
					
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(itemTextField)
					.addComponent(quantityTextField)
					.addComponent(priceTextField)
					.addComponent(dateChooser)
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
					.addComponent(dateLabel)
					.addComponent(dateChooser))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(addButton)
					.addComponent(clearButton))
		);
	}
	
	private void AddButtonFunction() {
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vItem;
				int vQuantity;
				float vPrice;
			
				if (itemTextField.getText().equals("") ||
					quantityTextField.getText().equals("") ||
					priceTextField.getText().equals(""))
					//|| dateChooser.getDate().equals(null))
				{
				JOptionPane.showMessageDialog(null, "please do not leave any fields blank!");
				}
				else
				{
					// validate input
					Boolean inputError = false;
					vItem = itemTextField.getText();
					try {
						vQuantity = Integer.parseInt(quantityTextField.getText());
					} catch (NumberFormatException e) {
						inputError = true;
						JOptionPane.showMessageDialog(null, "Please only enter whole numbers in the quantity box!");
					}
					try {
						vPrice = Float.valueOf(priceTextField.getText());
					} catch (NumberFormatException e) {
						inputError = true;
						JOptionPane.showMessageDialog(null, "Please only enter decimal numbers in the price box!");
					}
					if (!inputError)
					{
						JOptionPane.showMessageDialog(null, "item successfully added");
						itemTextField.setText("");
						quantityTextField.setText("");
						priceTextField.setText("");
						dateChooser.setDate(null);
					}
				}					
			}
		});
	}
	
	private void ClearButtonFunctionality() {
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// refresh the text fields
				itemTextField.getText();
				quantityTextField.getText();
				priceTextField.getText();
				dateChooser.getDate();
				// clear them
				itemTextField.setText("");
				quantityTextField.setText("");
				priceTextField.setText("");
				dateChooser.setDate(null);
			}
		});
	}
}
