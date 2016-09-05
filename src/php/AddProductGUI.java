package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddProductGUI extends JPanel {
	final String[] categories = { "Tablet", "Liquid", "Balm", "Other" };
	
	JComboBox<String> category;
	
	JTextField nameField;
	JTextArea descField;
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
		
		AddButtonFunction();
		ClearButtonFunction();
		
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
		descField = new JTextArea();
		descField.setLineWrap(true);
		descField.setWrapStyleWord(true);
		descField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		stockField = new JTextField();
		addButton = new Button("ADD");
		clearButton = new Button("CLEAR");
	}
}
