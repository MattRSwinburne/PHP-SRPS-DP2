package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewRecordsGUI extends JPanel {
	private JTable table;
	private DefaultTableModel model;

	private int numberOfSales;

	public ViewRecordsGUI() {

		initialise();

	}
	private void initialise() {
		setLayout(new GridLayout(1, 0, 0, 0));
		DatabaseIO.getSales();
		model = new DefaultTableModel(new Object[]{"Sale ID", "Product", "Qty Sold", "Sale Date"}, 0);
		table = new JTable();
		numberOfSales = 0;

		fillTableModel();
		SalesContentChangeListener();

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		scrollPane.setViewportView(table);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
	}

	public void fillTableModel()
	{
		if (numberOfSales != DatabaseIO.saleList.size())
		{
			model.setRowCount(0);
			for(Sale sale:DatabaseIO.saleList){
				model.addRow(new Object[]{sale.getSaleID(), sale.getProductName(), sale.getQtySold(), sale.getSaleDate()});
			}
			numberOfSales = DatabaseIO.saleList.size();
		}
	}

	private void SalesContentChangeListener()
	{
		table.addFocusListener(new FocusListener()
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


