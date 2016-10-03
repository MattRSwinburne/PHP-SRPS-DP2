package php;

import javax.swing.JTabbedPane;

public class ReportsGUI extends JTabbedPane
{
	public ReportsGUI()
	{
		addTab("Weekly", new WeeklyReportGUI());
		addTab("Monthly", new MonthlyReportGUI());
	}
}
