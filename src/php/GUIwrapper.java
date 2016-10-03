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

		tabbedPane.addTab("Add Sales", new AddSalesSideBar());
		tabbedPane.addTab("View Sales", new ViewRecordsGUI());
		tabbedPane.addTab("Reports", new ReportsGUI());
		tabbedPane.addTab("Sales Prediction", new PredictionGUI());

		return tabbedPane;
	}
}
