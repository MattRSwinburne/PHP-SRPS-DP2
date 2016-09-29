package php;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class PredictionGUI extends JPanel
{
	JComboBox<String> categoryBox;
	JComboBox<String> productBox;

	JPanel drawArea;

	Product product;
	Map<String, Integer> Week1Sales;
	Map<String, Integer> Week2Sales;
	Map<String, Integer> Week3Sales;
	Map<String, Integer> prediction;

	private void MakePrediction()
	{
		for (Product p : DatabaseIO.productList)
		{
			Integer deltaWeek1Week2, deltaWeek2Week3, deltaAverage;
			deltaWeek1Week2 = Week2Sales.get(p.productName) - Week1Sales.get(p.productName);
			deltaWeek2Week3 = Week3Sales.get(p.productName) - Week2Sales.get(p.productName);
			deltaAverage = (int)Math.ceil((deltaWeek1Week2 + deltaWeek2Week3) / 2.0);
			prediction.put(p.productName, deltaAverage);
		}
	}
	
	private void FillWeeklySalesList()
	{
		for (Product aProduct : DatabaseIO.productList)
		{
			Week1Sales.put(aProduct.getProductName(), 0);
			Week2Sales.put(aProduct.getProductName(), 0);
			Week3Sales.put(aProduct.getProductName(), 0);
		}

		Calendar week1 = (Calendar)Calendar.getInstance();
		Calendar week2 = (Calendar)week1.clone();
		Calendar week3 = (Calendar)week1.clone();
		week1.add(Calendar.WEEK_OF_YEAR, -3);
		week2.add(Calendar.WEEK_OF_YEAR, -2);
		week3.add(Calendar.WEEK_OF_YEAR, -1);
		
		for (Sale sale : DatabaseIO.saleList)
		{
			Calendar saleDate = new GregorianCalendar();
			saleDate.setTime(sale.getSaleDate());
			if (saleDate.after(week1) && saleDate.before(week2))
			{
				Integer temp = Week1Sales.get(sale.productName);
				Week1Sales.put(sale.productName, temp+1);
			}
			else if (saleDate.after(week2) && saleDate.before(week3))
			{
				Integer temp = Week2Sales.get(sale.productName);
				Week2Sales.put(sale.productName, temp+1);
			}
			else if (saleDate.after(week3))
			{
				Integer temp = Week3Sales.get(sale.productName);
				Week3Sales.put(sale.productName, temp+1);
			}
		}
	}

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
		int XArea;
		int YArea;

		public void paint (Graphics g)
		{
			XOffset = getWidth()/10;
			YOffset = getHeight()/10;
			XArea = getWidth() - (2 * XOffset);
			YArea = getHeight() - (2 * YOffset);

			g.clearRect(0, 0, getWidth(), getHeight());
			drawBarGraph(g);
		}

		//Draw the bar graph
		public void drawBarGraph(Graphics g)
		{
			drawAxis(g);
			
		}

		//Draw the axes for the graph
		public void drawAxis (Graphics g)
		{
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.drawLine(XOffset, YOffset, XOffset, getHeight()-YOffset);
			g.drawLine(XOffset, getHeight()-YOffset, getWidth()-XOffset, getHeight()-YOffset);
			for (int i = 1; i < 5; i++)
			{
				g.drawLine(XOffset + (XArea/5) * i, getHeight()-YOffset, XOffset + (XArea/5) * i, getHeight()-YOffset + 20);
			}
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
		Week1Sales = new HashMap<>();
		Week2Sales = new HashMap<>();
		Week3Sales = new HashMap<>();
		prediction = new HashMap<>();
		FillWeeklySalesList();
		MakePrediction();
		categoryBox = new JComboBox<String>(DatabaseIO.getCategories());
		CategoryBoxContentChangeListener();

		productBox = new JComboBox<String>(DatabaseIO.getProductByCategory((String)categoryBox.getSelectedItem()));
		ProductBoxContentChangeListener();

		drawArea = new DrawArea();
	}
}
