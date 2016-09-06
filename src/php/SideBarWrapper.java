package php;

import java.awt.BorderLayout;
import java.awt.Button;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SideBarWrapper extends JPanel
{
	protected Vector<Button> sideButtons = new Vector<Button>();
	protected JPanel sideBar = new JPanel();
	
	public SideBarWrapper()
	{
		Initialize();
	}
	
	protected void Initialize()
	{
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));
		GenerateButtons();
		setLayout(new BorderLayout());
		
		add(Content(), BorderLayout.CENTER);
		add(PageHeadings(), BorderLayout.NORTH);
		
		for (Button button : sideButtons)
		{
			sideBar.add(button, BorderLayout.LINE_END);
		}
		add(sideBar, BorderLayout.LINE_END);
	}
	
	protected void GenerateButtons() {}
	
	protected JPanel Content()
	{
		return new JPanel();
	}
	
	protected JPanel PageHeadings()
	{
		return new JPanel();
	}
}
