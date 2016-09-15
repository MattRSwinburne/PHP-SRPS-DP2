package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddProductGUI extends JPanel {
	final static String[] categories = { "Tablet", "Liquid", "Balm", "Other" };

	JComboBox<String> category;

	JTextField nameField;
	JTextField descField;
	JTextField stockField;

	Button addButton;
	Button clearButton;

	public AddProductGUI()
	{
		Initialize();

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel categoryLabel = new JLabel("Category");
		JLabel nameLabel = new JLabel("Name");
		JLabel descriptionLabel = new JLabel("Description");
		JLabel stockLabel = new JLabel("Stock");

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(categoryLabel)
						.addComponent(nameLabel)
						.addComponent(descriptionLabel)
						.addComponent(stockLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(category)
						.addComponent(nameField)
						.addComponent(descField)
						.addComponent(stockField)
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
						.addComponent(nameLabel)
						.addComponent(nameField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(descriptionLabel)
						.addComponent(descField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(stockLabel)
						.addComponent(stockField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(addButton)
						.addComponent(clearButton))
				);
	}

	private void AddButtonFunction()
	{
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// validate input
				Boolean inputError = false;
				if (nameField.getText().equals("") ||
						descField.getText().equals("") ||
						stockField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "please do not leave any fields blank!");
					inputError = true;
				}
				else
				{
					try {
						Integer.parseInt(stockField.getText());
					} catch (NumberFormatException e) {
						inputError = true;
						JOptionPane.showMessageDialog(null, "Please only enter whole numbers in the stock box!");
					}
					if (!inputError)
					{
						// add it to the database
						DatabaseIO.addProduct(category.getSelectedItem().toString(), descField.getText(), nameField.getText(), Integer.parseInt(stockField.getText()));
						JOptionPane.showMessageDialog(null, "Product successfully added");
						nameField.setText("");
						descField.setText("");
						stockField.setText("");
					}
				}
			}
		});
	}

	private void ClearButtonFunction()
	{
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				category.setSelectedIndex(0);
				nameField.setText("");
				descField.setText("");
				stockField.setText("");
			}
		});
	}

	private void Initialize()
	{
		category = new JComboBox<String>(categories);
		nameField = new JTextField();
		descField = new JTextField();
		stockField = new JTextField();
		addButton = new Button("ADD");
		clearButton = new Button("CLEAR");

		AddButtonFunction();
		ClearButtonFunction();
	}
}
