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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MonthlyReportGUI extends JPanel
{

	private JTable table;
	private DefaultTableModel model;
	private JButton genButton;
	private ReportsGatherer rg;
	private ArrayList<HashMap<String, Integer>> mnRpt;
	private JPanel buttonPanel;
	private JButton btnSavePDF;
	
	public MonthlyReportGUI()
	{
		
		initialise();
		
	}
	
	public void initialise()
	{
		
		setLayout(new BorderLayout());
		rg = new ReportsGatherer();
		mnRpt = rg.gatherMonthly();
		model = new DefaultTableModel(new Object[]{"Month", "Product", "Qty Sold"}, 0);
		table = new JTable();
		fillTableModel();
		SalesContentChangeListener();
		
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		genButton = new JButton("Generate CSV");
		buttonPanel.add(genButton);
		
		btnSavePDF = new JButton("Save PDF");
		buttonPanel.add(btnSavePDF);
		genButton.addActionListener(
			new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent ae) {

					JFileChooser jfc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv", "CSV");
					jfc.setFileFilter(filter);
					int confirmed = jfc.showDialog(null, "Save CSV");
					
					if(confirmed == JFileChooser.APPROVE_OPTION)
					{	
						CSVGenerator cg = new CSVGenerator(jfc.getSelectedFile().getAbsolutePath(), 1);
						try {
							cg.generate(mnRpt);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Error Saving CSV", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
				}
				
			}
				
		);
		btnSavePDF.addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						JFileChooser jfc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf", "PDF");
						jfc.setFileFilter(filter);
						int confirmed = jfc.showDialog(null, "Save PDF");
						
						if(confirmed == JFileChooser.APPROVE_OPTION)
						{
							PDFOperations pdf = new PDFOperations(jfc.getSelectedFile().getAbsolutePath());
							try {
								pdf.createMonthlySalesPDF(table);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Error Saving PDF", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				
				
				
				);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		
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
	 * Fills the monthly table with data
	 * @param wkRpt		data from database
	 */
	private void fillTableModel()
	{
		
		model.setRowCount(0);
		Set set;
		Iterator iterator;
		int month;
		for(int i = 0; i < mnRpt.size(); i++)
		{
			
			month = i + 1;
			set = mnRpt.get(i).entrySet();
			iterator = set.iterator();
			while(iterator.hasNext())
			{
				
				Map.Entry mentry = (Map.Entry) iterator.next();
				model.addRow(new Object[]{month, mentry.getKey(), mentry.getValue()});
				
			}
			
		}
		
	}
	
	
}
