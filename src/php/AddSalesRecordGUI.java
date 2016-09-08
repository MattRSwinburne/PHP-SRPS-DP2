package php;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

import com.toedter.calendar.JDateChooser;


public class AddSalesRecordGUI extends JPanel {
	JComboBox<String> category;
	JComboBox<String> productBox;

	JDateChooser dateChooser;

	JSpinner quantity;

	Button addButton;
	Button clearButton;


	public AddSalesRecordGUI()
	{
		Initialize();

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel categoryLabel = new JLabel("Category");
		JLabel productLabel = new JLabel("Product");
		JLabel dateLabel = new JLabel("Date");
		JLabel quantityLabel = new JLabel("Quantity");

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(categoryLabel)
						.addComponent(productLabel)
						.addComponent(dateLabel)
						.addComponent(quantityLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(category)
						.addComponent(productBox)
						.addComponent(dateChooser)
						.addComponent(quantity)
						.addGroup(layout.createSequentialGroup()
								.addComponent(addButton)
								.addComponent(clearButton)))
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(categoryLabel)
						.addComponent(category))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(productLabel)
						.addComponent(productBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(dateLabel)
						.addComponent(dateChooser))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(addButton)
						.addComponent(clearButton))
				);
	}

	private void AddButtonFunction() {
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product product = DatabaseIO.getProduct((String)productBox.getSelectedItem());

				int selectedQuantity;
				Boolean inputError = false;
				try {
					quantity.commitEdit();
				}
				catch (Exception e) {
					inputError = true;
					// Edited value is invalid, spinner.getValue() will return
					// the last valid value, you could revert the spinner to show that:
					JComponent editor = quantity.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor)editor).getTextField().setValue(quantity.getValue());
					}
				}
				selectedQuantity = (int)quantity.getValue();


				// check available stock
				if (selectedQuantity > product.productStock)
				{
					JOptionPane.showMessageDialog(null, "There is not enough stock available for this purchase");
					inputError = true;
				}
				
				if (!inputError)
				{
					java.sql.Date sqlDate = new java.sql.Date(dateChooser.getDate().getTime());
					DatabaseIO.addSale(product.productID, sqlDate, selectedQuantity);
					JOptionPane.showMessageDialog(null, "The sale has been recorded");
				}
			}
		});
	}

	private void ClearButtonFunction() {
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category.setSelectedIndex(0);
				productBox.setSelectedIndex(0);
				dateChooser.setDate(Calendar.getInstance().getTime());
				quantity.setValue(1);
			}
		});
	}

	private void ProductDropDownContent()
	{
		category.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				productBox.removeAllItems();
				for (String p : DatabaseIO.getProductByCategory((String)category.getSelectedItem()))
				{
					productBox.addItem(p);
				}
			}
		});
	}

	private void Initialize()
	{
		category = new JComboBox<String>(DatabaseIO.getCategories());
		productBox = new JComboBox<String>(DatabaseIO.getProductByCategory((String)category.getSelectedItem()));
		ProductDropDownContent();
		//date chooser
		dateChooser = new JDateChooser(Calendar.getInstance().getTime());

		quantity = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		// left align it
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)quantity.getEditor();
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.LEADING);

		addButton = new Button("ADD");
		clearButton = new Button("CLEAR");

		AddButtonFunction();
		ClearButtonFunction();
	}
}
