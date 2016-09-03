package php;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SideBarWrapper extends JPanel
{
	protected Vector<Button> sideButtons = new Vector<Button>();
	protected JPanel content;
	protected JPanel sideBar = new JPanel();
	
	public SideBarWrapper()
	{
		InitializeLocalVars();
		Initialize();
	}
	
	protected void Initialize()
	{
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));
		GenerateButtons();
		setLayout(new BorderLayout());
		
		add(Content(), BorderLayout.CENTER);
		
		
		for (Button button : sideButtons)
		{
			sideBar.add(button, BorderLayout.LINE_END);
		}
		add(sideBar, BorderLayout.LINE_END);
	}
	
	protected void InitializeLocalVars() {}
	protected void GenerateButtons() {}
	
	protected JPanel Content()
	{
		return new JPanel();
	}
}
