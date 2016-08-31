package php;

import java.awt.*;
import javax.swing.*;

public class GUIwrapper {
	public static JTabbedPane TabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel addSalesGUI = new JPanel();
		AddSalesRecordGUI.AddSalesContent(addSalesGUI);
		tabbedPane.addTab("Add Sales", null, addSalesGUI, null);
		return tabbedPane;
	}	
}
