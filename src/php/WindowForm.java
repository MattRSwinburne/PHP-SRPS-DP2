package php;

import java.awt.*;
import java.awt.event.*;
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
		//frame.setPreferredSize(new Dimension(800,600));
		
		frame.add(GUIwrapper.TabbedPane());
		
		/*
		// Add Button
		
		
		addButton.setBounds(99, 158, 69, 22);
		frame.getContentPane().add(addButton);
		
		// Clear button
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemTextField.setText("");
				quantityTextField.setText("");
				priceTextField.setText("");	
			}
		});
		clearButton.setBounds(210, 158, 69, 22);
		frame.getContentPane().add(clearButton);
		
		*/
		frame.pack();
		frame.setLocationRelativeTo(null); // must go after .pack() to work
	}
}
