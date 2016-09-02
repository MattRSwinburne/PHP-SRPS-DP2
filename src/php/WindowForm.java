package php;

import java.awt.*;
import javax.swing.*;

public class WindowForm {
	private JFrame frame;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowForm window = new WindowForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public WindowForm() {
		Initialize();
	}
	
	private void Initialize()
	{
		frame = new JFrame("People Health Pharmacy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));
		GUIwrapper gui = new GUIwrapper();
		frame.add(gui.TabbedPane());
		frame.pack();
		frame.setLocationRelativeTo(null); // must go after .pack() to work
	}
}
