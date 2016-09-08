package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewRecordsGUI extends JPanel {
	private JTable table;
	public ViewRecordsGUI() {
		
		initialise();
		
	}
	private void initialise() {
		setLayout(new GridLayout(1, 0, 0, 0));
		DatabaseIO.getSales();
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Sale ID", "Product", "Qty Sold", "Sale Date"}, 0);
			for(Sale sale:DatabaseIO.saleList){
		          model.addRow(new Object[]{sale.getSaleID(), sale.getProductName(), sale.getQtySold(), sale.getSaleDate()});
		     }
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		
	}
	
}


