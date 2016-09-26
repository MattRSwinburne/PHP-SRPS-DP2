package php;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PredictionGUI extends JPanel
{
	JComboBox<String> categoryBox;
	JComboBox<String> productBox;

	JPanel drawArea;

	Product product;

	public PredictionGUI()
	{
		Initialize();

		JLabel categoryLabel = new JLabel("Category", SwingConstants.CENTER);
		JLabel productLabel = new JLabel("Product", SwingConstants.CENTER);
		
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0.1;
		constraints.ipadx = 10;
		add(categoryLabel,constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.ipadx = 0;
		add(categoryBox,constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.weightx = 0.1;
		constraints.ipadx = 10;
		add(productLabel,constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.ipadx = 0;
		add(productBox,constraints);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.ipadx = 0;
		add(drawArea,constraints);
	}

	public class DrawArea extends JPanel
	{
		int XOffset;
		int YOffset;
		
		DrawArea()
		{
			
		}
		
		public void paint (Graphics g)
		{
			XOffset = getWidth()/10;
			YOffset = getHeight()/10;

			g.clearRect(0, 0, getWidth(), getHeight());
			drawBarGraph(g);
		}

		//Draw the bar graph
		public void drawBarGraph(Graphics g)
		{
			drawAxes(g);
			
		}

		//Draw the axes for the graph
		public void drawAxes (Graphics g)
		{
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.drawLine(XOffset, YOffset, XOffset, getHeight()-YOffset-20);
			g.drawLine(XOffset, getHeight()-YOffset-20, getWidth()-XOffset, getHeight()-YOffset-20);
			g.setColor(c);
		}
	}

	private void CategoryBoxContentChangeListener()
	{
		categoryBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				productBox.removeAllItems();
				for (String p : DatabaseIO.getProductByCategory((String)categoryBox.getSelectedItem()))
				{
					productBox.addItem(p);
				}
			}
		});
	}

	private void ProductBoxContentChangeListener()
	{
		productBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				product = DatabaseIO.getProduct((String)productBox.getSelectedItem());
				drawArea.repaint();
			}
		});
	}

	private void Initialize()
	{
		categoryBox = new JComboBox<String>(DatabaseIO.getCategories());
		CategoryBoxContentChangeListener();

		productBox = new JComboBox<String>(DatabaseIO.getProductByCategory((String)categoryBox.getSelectedItem()));
		ProductBoxContentChangeListener();

		drawArea = new DrawArea();
	}
}
