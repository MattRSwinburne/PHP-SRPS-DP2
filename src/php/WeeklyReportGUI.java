package php;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WeeklyReportGUI extends JPanel
{

	private JTable table;
	private DefaultTableModel model;
	private JButton genButton;
	private ReportsGatherer rg;
	private ArrayList<HashMap<String, Integer>> wkRpt;
	
	public WeeklyReportGUI()
	{
		
		initialise();
		
	}
	
	public void initialise()
	{
		
		setLayout(new BorderLayout());
		rg = new ReportsGatherer();
		wkRpt = rg.gatherWeekly();
		model = new DefaultTableModel(new Object[]{"Week", "Product", "Qty Sold"}, 0);
		table = new JTable();
		fillTableModel();
		SalesContentChangeListener();
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		
		genButton = new JButton("Generate CSV");
		genButton.addActionListener(
			new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent ae) {

					JFileChooser jfc = new JFileChooser();
					int confirmed = jfc.showDialog(null, "CSV File");
					
					if(confirmed == JFileChooser.APPROVE_OPTION)
					{
						
						CSVGenerator cg = new CSVGenerator(jfc.getSelectedFile().getAbsolutePath(), 0);
						try {
							cg.generate(wkRpt);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Error Saving CSV", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
				}
				
			}
				
		);
		add(genButton, BorderLayout.SOUTH);
		
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
	
	/**
	 * Fills the weekly table with data
	 * @param wkRpt		data from database
	 */
	private void fillTableModel()
	{
		
		model.setRowCount(0);
		Set set;
		Iterator iterator;
		int week;
		for(int i = 0; i < wkRpt.size(); i++)
		{
			
			week = i + 1;
			set = wkRpt.get(i).entrySet();
			iterator = set.iterator();
			while(iterator.hasNext())
			{
				
				Map.Entry mentry = (Map.Entry) iterator.next();
				model.addRow(new Object[]{week, mentry.getKey(), mentry.getValue()});
				
			}
			
		}
		
	}
	
	
}
