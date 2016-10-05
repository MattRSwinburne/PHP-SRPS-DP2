package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ModifyProductGUI extends JPanel {
	JComboBox<String> categoryBox;
	JComboBox<String> productBox;
	JComboBox<String> newCategoryBox;

	JTextField nameField;
	JTextField descField;

	Button updateButton;
	Button clearButton;

	Product product;


	public ModifyProductGUI()
	{
		Initialize();

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel modifyProductLabel = new JLabel("Modify a Product");

		JLabel categoryLabel = new JLabel("Category");
		JLabel productLabel = new JLabel("Product");
		JLabel newCategoryLabel = new JLabel("New Category");
		JLabel nameLabel = new JLabel("Name");
		JLabel descriptionLabel = new JLabel("Description");

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(separator)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(categoryLabel)
								.addComponent(productLabel)
								.addComponent(newCategoryLabel)
								.addComponent(nameLabel)
								.addComponent(descriptionLabel))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(modifyProductLabel)
								.addComponent(categoryBox)
								.addComponent(productBox)
								.addComponent(newCategoryBox)
								.addComponent(nameField)
								.addComponent(descField)
								.addGroup(layout.createSequentialGroup()
										.addComponent(updateButton)
										.addComponent(clearButton))))
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(modifyProductLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(categoryLabel)
						.addComponent(categoryBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(productLabel)
						.addComponent(productBox))
				.addComponent(separator)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(newCategoryLabel)
						.addComponent(newCategoryBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(nameLabel)
						.addComponent(nameField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(descriptionLabel)
						.addComponent(descField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(updateButton)
						.addComponent(clearButton))
				);
	}

	private void UpdateButtonFunction() {
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// validate input
				if (nameField.getText().equals("") ||
						descField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "please do not leave any fields blank!");
				}
				else
				{
					product.productCategory = (String)newCategoryBox.getSelectedItem();
					product.productName = nameField.getText();
					product.productDescription = descField.getText();
					DatabaseIO.updateProduct(product);
					String productName = product.productName;
					categoryBox.setSelectedItem(product.productCategory);
					productBox.setSelectedItem(productName);
					JOptionPane.showMessageDialog(null, "Product successfully updated");
				}
			}

		});
	}

	private void ClearButtonFunction() {
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryBox.setSelectedIndex(0);
				productBox.setSelectedIndex(0);
			}
		});
	}

	private void CategoryBoxContentChangeListener()
	{
		categoryBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				productBox.removeAllItems();
				for (String p : DatabaseIO.getProductByCategory((String)categoryBox.getSelectedItem()))
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
				nameField.setText(product.productName);
				descField.setText(product.productDescription);
			}
		});
	}

	private void Initialize()
	{
		categoryBox = new JComboBox<String>(DatabaseIO.getCategories());
		CategoryBoxContentChangeListener();

		productBox = new JComboBox<String>(DatabaseIO.getProductByCategory((String)categoryBox.getSelectedItem()));
		ProductBoxContentChangeListener();

		newCategoryBox = new JComboBox<String>(DatabaseIO.getCategories());

		product = DatabaseIO.getProduct((String)productBox.getSelectedItem());

		nameField = new JTextField(product.productName);

		descField = new JTextField(product.productDescription);

		updateButton = new Button("UPDATE");
		UpdateButtonFunction();

		clearButton = new Button("CLEAR");
		ClearButtonFunction();
	}
}
