package php;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUIwrapper {
	public JTabbedPane TabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel homeGUI = new JPanel();
		HomeGUI home = new HomeGUI();
		home.HomeContent(homeGUI);
		
		JPanel addSalesGUI = new AddSalesSideBar();
		
		JPanel viewrecordsGUI = new JPanel();
		ViewRecordsGUI viewRecords = new ViewRecordsGUI();
		viewRecords.ViewRecordsContent(viewrecordsGUI);
		
		JPanel reportsGUI = new JPanel();
		ReportsGUI reports = new ReportsGUI();
		reports.ReportsContent(reportsGUI);
		
		tabbedPane.addTab("Home", null, homeGUI, null);
		tabbedPane.addTab("Add Sales", null, addSalesGUI, null);
		tabbedPane.addTab("View Sales", null, viewrecordsGUI, null);
		tabbedPane.addTab("Reports", null, reportsGUI, null);

		return tabbedPane;
	}	
}
