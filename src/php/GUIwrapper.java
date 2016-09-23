package php;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class GUIwrapper extends JPanel {
	private final String loginLabel = "Login";
	private final String contentLabel = "Content";

	public GUIwrapper()
	{
		setLayout(new CardLayout());
		setPreferredSize(new Dimension(600, 350));

		LoginGUI loginGUI = new LoginGUI();
		JTabbedPane content = TabbedPane();

		add(loginGUI, loginLabel);
		add(content, contentLabel);

		loginGUI.LoginButtonFunction(this, contentLabel);
	}
	
	private JTabbedPane childTabbedPane()
	{
		
		JTabbedPane cPane = new JTabbedPane();
		
		cPane.addTab("Weekly", null, new WeeklyReportGUI(), null);
		cPane.addTab("Monthly", null, new MonthlyReportGUI(), null);
		
		return cPane;
	}

	private JTabbedPane TabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel addSalesGUI = new AddSalesSideBar();

		JPanel viewrecordsGUI = new ViewRecordsGUI();


		tabbedPane.addTab("Add Sales", null, addSalesGUI, null);
		tabbedPane.addTab("View Sales", null, viewrecordsGUI, null);
		tabbedPane.addTab("Reports", null, childTabbedPane(), null);

		return tabbedPane;
	}
}
