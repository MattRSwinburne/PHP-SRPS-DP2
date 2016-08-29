import java.awt.*;
import javax.swing.*;

public class WindowForm {
	
	public static void CreateWindow()
	{
		JFrame frame = new JFrame("People Health Pharmacy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));
		
		frame.pack();
		frame.setLocationRelativeTo(null); // must go after .pack() to work
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		CreateWindow();
	}
}
