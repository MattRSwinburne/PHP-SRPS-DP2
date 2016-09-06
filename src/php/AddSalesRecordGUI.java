package php;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;


public class AddSalesRecordGUI extends JPanel {
	JComboBox<String> category;
	JComboBox<String> product;

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

		AddButtonFunction();
		ClearButtonFunctionality();

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(categoryLabel)
						.addComponent(productLabel)
						.addComponent(dateLabel)
						.addComponent(quantityLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(category)
						.addComponent(product)
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
						.addComponent(product))
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

			}
		});
	}

	private void ClearButtonFunctionality() {
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category.setSelectedIndex(0);
				product.setSelectedIndex(0);
				dateChooser.setDate(Calendar.getInstance().getTime());
				quantity.setValue(1);
			}
		});
	}

	private void Initialize()
	{
		category = new JComboBox<String>(AddProductGUI.categories);
		product = new JComboBox<String>(AddProductGUI.categories);
		//date chooser
		dateChooser = new JDateChooser(Calendar.getInstance().getTime());

		quantity = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		// left align it
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)quantity.getEditor();
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.LEADING);

		addButton = new Button("ADD");
		clearButton = new Button("CLEAR");
	}
}
