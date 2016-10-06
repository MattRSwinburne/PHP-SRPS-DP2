package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewProductLevelsGUI extends JPanel {
	private JTable table;
	private DefaultTableModel model;

	private int numberOfProducts;

	public ViewProductLevelsGUI() {

		initialise();

	}
	private void initialise() {
		setLayout(new GridLayout(1, 0, 0, 0));
		DatabaseIO.getProducts();
		model = new DefaultTableModel(new Object[]{"Product", "Category", "Description", "In Stock"}, 0);
		table = new JTable();
		numberOfProducts = 0;

		fillTableModel();
		ProductContentChangeListener();

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		scrollPane.setViewportView(table);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
	}

	private void fillTableModel()
	{
		if (numberOfProducts != DatabaseIO.productList.size())
		{
			model.setRowCount(0);
			for(Product product:DatabaseIO.productList){
				model.addRow(new Object[]{product.getProductName(), product.getProductCategory(), product.getProductDescription(), product.getProductStock()});
			}
			numberOfProducts = DatabaseIO.productList.size();
		}
	}

	private void ProductContentChangeListener()
	{
		addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent arg0)
			{
				fillTableModel();
			}

			// don't care about losing focus
			public void focusLost(FocusEvent arg0) {}
		});
	}
}