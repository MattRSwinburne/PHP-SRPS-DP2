package php;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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

	private JTabbedPane TabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel addSalesGUI = new AddSalesSideBar();
		JPanel viewrecordsGUI = new ViewRecordsGUI();
		JPanel reportsGUI = new JPanel();
		ReportsGUI reports = new ReportsGUI();
		reports.ReportsContent(reportsGUI);
		JPanel predictionGUI = new PredictionGUI();

		tabbedPane.addTab("Add Sales", addSalesGUI);
		tabbedPane.addTab("View Sales", viewrecordsGUI);
		tabbedPane.addTab("Reports", reportsGUI);
		tabbedPane.addTab("Sales Prediction", predictionGUI);

		return tabbedPane;
	}
}
