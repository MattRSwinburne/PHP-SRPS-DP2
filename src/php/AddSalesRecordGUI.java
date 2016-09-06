package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;


public class AddSalesRecordGUI extends JPanel {
	JTextField itemTextField;
	JTextField quantityTextField;
	JTextField priceTextField;
	
	JDateChooser dateChooser;
	
	Button addButton;
	Button clearButton;
	
	public AddSalesRecordGUI()
	{
		InitializeLocalVars();
		InitializeContent();
	}
	
	private void InitializeLocalVars()
	{
		itemTextField = new JTextField();
		quantityTextField = new JTextField();
		priceTextField = new JTextField();
		//date chooser
		dateChooser = new JDateChooser();

		addButton = new Button("ADD");
		clearButton = new Button("CLEAR");
	}

	
	private void InitializeContent() {
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		JLabel itemLabel = new JLabel("Item");
		JLabel quantityLabel = new JLabel("Quantity");
		JLabel priceLabel = new JLabel("Price");

		//label for date chooser
		JLabel dateLabel = new JLabel("Date");
		
		AddButtonFunction();
		ClearButtonFunctionality();
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
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
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(itemLabel)
					.addComponent(itemTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(quantityLabel)
					.addComponent(quantityTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(priceLabel)
					.addComponent(priceTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(dateLabel)
					.addComponent(dateChooser))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
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
				// clear them
				itemTextField.setText("");
				quantityTextField.setText("");
				priceTextField.setText("");	
			}
		});
	}
}
