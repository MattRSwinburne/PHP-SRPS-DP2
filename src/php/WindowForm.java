package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowForm {
	private JFrame frame;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowForm window = new WindowForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public WindowForm() {
		Initialize();
	}
	
	private void Initialize()
	{
		frame = new JFrame("People Health Pharmacy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));
		
		//add labels
		Label itemLabel = new Label("Item");
		itemLabel.setAlignment(Label.RIGHT);
		itemLabel.setBounds(67, 63, 26, 22);
		frame.getContentPane().add(itemLabel);
		
		Label QuantityLabel = new Label("Quantity");
		QuantityLabel.setAlignment(Label.RIGHT);
		QuantityLabel.setBounds(39, 91, 54, 22);
		frame.getContentPane().add(QuantityLabel);
		
		Label PriceLabel = new Label("Price");
		PriceLabel.setAlignment(Label.RIGHT);
		PriceLabel.setBounds(60, 119, 33, 22);
		frame.getContentPane().add(PriceLabel);
		
		TextField itemTextField = new TextField();
		itemTextField.setBounds(99, 63, 180, 22);
		frame.getContentPane().add(itemTextField);
		
		TextField quantityTextField = new TextField();
		quantityTextField.setBounds(99, 92, 180, 22);
		frame.getContentPane().add(quantityTextField);
		
		TextField priceTextField = new TextField();
		priceTextField.setBounds(99, 120, 180, 22);
		frame.getContentPane().add(priceTextField);
		
		// Add Button
		Button addButton = new Button("ADD");
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
		addButton.setBounds(99, 158, 69, 22);
		frame.getContentPane().add(addButton);
		
		// Clear button
		Button clearButton = new Button("CLEAR");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemTextField.setText("");
				quantityTextField.setText("");
				priceTextField.setText("");	
			}
		});
		clearButton.setBounds(210, 158, 69, 22);
		frame.getContentPane().add(clearButton);
		
		// Exit button
		Button exitButton = new Button("EXIT");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(300, 200, 69, 22);
		frame.getContentPane().add(exitButton);
		
		frame.pack();
		frame.setLocationRelativeTo(null); // must go after .pack() to work
	}
}
