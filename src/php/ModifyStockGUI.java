package php;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;


public class ModifyStockGUI extends JPanel {
	JComboBox<String> category;
	JComboBox<String> productBox;

	JSpinner quantity;

	Button updateButton;
	Button clearButton;
	
	Product product;


	public ModifyStockGUI()
	{
		Initialize();

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel categoryLabel = new JLabel("Category");
		JLabel productLabel = new JLabel("Product");
		JLabel quantityLabel = new JLabel("Quantity");

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(categoryLabel)
						.addComponent(productLabel)
						.addComponent(quantityLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(category)
						.addComponent(productBox)
						.addComponent(quantity)
						.addGroup(layout.createSequentialGroup()
								.addComponent(updateButton)
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
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(updateButton)
						.addComponent(clearButton))
				);
	}

	private void UpdateButtonFunction() {
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				product.productStock = (int)quantity.getValue();
				DatabaseIO.updateProduct(product);
				JOptionPane.showMessageDialog(null, "The stock of " + product.productName + " has been updated");
			}
		});
	}

	private void ClearButtonFunction() {
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category.setSelectedIndex(0);
				productBox.setSelectedIndex(0);
				quantity.setValue(1);
			}
		});
	}
	
	private void CategoryBoxContentChangeListener()
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
	
	private void ProductBoxContentChangeListener()
	{
		productBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				product = DatabaseIO.getProduct((String)productBox.getSelectedItem());
				quantity.setValue(product.productStock);
			}
		});
	}

	private void Initialize()
	{
		category = new JComboBox<String>(DatabaseIO.getCategories());
		productBox = new JComboBox<String>(DatabaseIO.getProductByCategory((String)category.getSelectedItem()));
		CategoryBoxContentChangeListener();
		ProductBoxContentChangeListener();
		
		product = DatabaseIO.getProduct((String)productBox.getSelectedItem());

		quantity = new JSpinner(new SpinnerNumberModel(product.productStock, 0, Integer.MAX_VALUE, 1));
		// left align it
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)quantity.getEditor();
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.LEADING);

		updateButton = new Button("UPDATE");
		clearButton = new Button("CLEAR");
		
		UpdateButtonFunction();
		ClearButtonFunction();
	}
}
