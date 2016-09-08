package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewRecordsGUI extends JPanel {
	private JTable table  = new JTable();
	private DefaultTableModel model = new DefaultTableModel(new Object[]{"Sale ID", "Product", "Qty Sold", "Sale Date"}, 0);
	public ViewRecordsGUI() {

		initialise();

	}
	private void initialise() {
		setLayout(new GridLayout(1, 0, 0, 0));
		DatabaseIO.getSales();
		
		fillTable();
		SalesContentChangeListener();

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		scrollPane.setViewportView(table);
		table.setModel(model);

	}
	
	private void fillTable()
	{
		if (model.getRowCount() > 0)
		{
			model.setRowCount(0);
		}
		for(Sale sale:DatabaseIO.saleList){
			model.addRow(new Object[]{sale.getSaleID(), sale.getProductName(), sale.getQtySold(), sale.getSaleDate()});
		}
	}

	private void SalesContentChangeListener()
	{
		table.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent arg0)
			{
				fillTable();
			}
			
			// don't care about losing focus
			public void focusLost(FocusEvent arg0) {}
		});
	}
}


