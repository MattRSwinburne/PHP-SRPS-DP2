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
	Map<String, Integer> Month1Sales;
	Map<String, Integer> Month2Sales;
	Map<String, Integer> Month3Sales;
	Map<String, Integer> prediction;

	private void MakePrediction()
	{
		for (Product p : DatabaseIO.productList)
		{
			Integer deltaWeek1Week2, deltaWeek2Week3, deltaAverage;
			deltaWeek1Week2 = Month2Sales.get(p.productName) - Month1Sales.get(p.productName);
			deltaWeek2Week3 = Month3Sales.get(p.productName) - Month2Sales.get(p.productName);
			deltaAverage = (int)Math.ceil((deltaWeek1Week2 + deltaWeek2Week3) / 2.0);
			prediction.put(p.productName, deltaAverage);
		}
	}

	private void FillWeeklySalesList()
	{
		for (Product aProduct : DatabaseIO.productList)
		{
			Month1Sales.put(aProduct.getProductName(), 0);
			Month2Sales.put(aProduct.getProductName(), 0);
			Month3Sales.put(aProduct.getProductName(), 0);
		}

		Calendar month1 = (Calendar)Calendar.getInstance();
		Calendar month2 = (Calendar)month1.clone();
		Calendar month3 = (Calendar)month1.clone();
		month1.add(Calendar.MONTH, -3);
		month2.add(Calendar.MONTH, -2);
		month3.add(Calendar.MONTH, -1);

		for (Sale sale : DatabaseIO.saleList)
		{
			Calendar saleDate = new GregorianCalendar();
			saleDate.setTime(sale.getSaleDate());
			if (saleDate.after(month1) && saleDate.before(month1))
			{
				Integer temp = Month1Sales.get(sale.productName);
				Month1Sales.put(sale.productName, temp+1);
			}
			else if (saleDate.after(month2) && saleDate.before(month3))
			{
				Integer temp = Month2Sales.get(sale.productName);
				Month2Sales.put(sale.productName, temp+1);
			}
			else if (saleDate.after(month3))
			{
				Integer temp = Month3Sales.get(sale.productName);
				Month3Sales.put(sale.productName, temp+1);
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

		int charHeight = 15;
		int charWidth = 5;

		public void paint (Graphics g)
		{
			XOffset = getWidth()/10;
			YOffset = getHeight()/10;
			XArea = getWidth() - (2 * XOffset);
			YArea = getHeight() - (2 * YOffset) - 20;

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
			g.drawLine(XOffset, YOffset, XOffset, getHeight()-YOffset - 20);
			g.drawLine(XOffset, getHeight()-YOffset - 20, getWidth()-XOffset, getHeight()-YOffset - 20);
			// x axis labels
			for (int i = 1; i < 5; i++)
				g.drawLine(XOffset + (XArea/5) * i, getHeight()-YOffset - 20, XOffset + (XArea/5) * i, getHeight()-YOffset);
			for (int i = 1; i < 4; i++)
				g.drawString("Month " + i, XOffset + (XArea/5) * i - charWidth * 3, getHeight()-YOffset + charHeight);
			g.drawString("Prediction", XOffset + (XArea/5) * 4 - charWidth * 5, getHeight()-YOffset + charHeight);
			// y axis labels
			Integer YRange = FindMostSales();
			if (YRange == 0)
				YRange = 1;
			Integer month1 = Month1Sales.get(product.productName);
			Integer month2 = Month2Sales.get(product.productName);
			Integer month3 = Month3Sales.get(product.productName);
			Integer pred = prediction.get(product.productName);
		
			g.drawLine(XOffset, YOffset + (YRange-month1)*YArea/YRange, XOffset - 10, YOffset + (YRange-month1)*YArea/YRange);
			g.drawString(month1.toString(), XOffset - 10 - month1.toString().length()*charWidth, YOffset + (YRange-month1)*YArea/YRange + 3);
			
			g.drawLine(XOffset, YOffset + (YRange-month2)*YArea/YRange, XOffset - 10, YOffset + (YRange-month2)*YArea/YRange);
			
			g.drawLine(XOffset, YOffset + (YRange-month3)*YArea/YRange, XOffset - 10, YOffset + (YRange-month3)*YArea/YRange);
			
			g.drawLine(XOffset, YOffset + (YRange-pred)*YArea/YRange, XOffset - 10, YOffset + (YRange-pred)*YArea/YRange);
			
			g.setColor(c);
		}
		private Integer FindMostSales()
		{
			Integer most = 0;

			most = Month1Sales.get(product.productName);
			if (most < Month2Sales.get(product.productName))
				most = Month2Sales.get(product.productName);
			if (most < Month3Sales.get(product.productName))
				most = Month3Sales.get(product.productName);
			if (most < prediction.get(product.productName))
				most = prediction.get(product.productName);

			return most;
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
		Month1Sales = new HashMap<>();
		Month2Sales = new HashMap<>();
		Month3Sales = new HashMap<>();
		prediction = new HashMap<>();
		FillWeeklySalesList();
		MakePrediction();
		categoryBox = new JComboBox<String>(DatabaseIO.getCategories());
		CategoryBoxContentChangeListener();

		productBox = new JComboBox<String>(DatabaseIO.getProductByCategory((String)categoryBox.getSelectedItem()));
		ProductBoxContentChangeListener();
		
		product = DatabaseIO.getProduct((String)productBox.getSelectedItem());

		drawArea = new DrawArea();
	}
}
