package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddSalesRecordFunctionality {
	/*
	public static void AddFunctionality(JPanel panel)
	{
		
	}
	
	private static void AddButton(JButton addButton, JPanel panel)
	{
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
	*/
}
