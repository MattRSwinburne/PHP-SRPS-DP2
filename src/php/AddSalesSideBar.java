package php;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AddSalesSideBar extends SideBarWrapper {
	Button addSales;
	Button addProduct;
	Button modifyProduct;
	Button modifyStockLevel;

	JPanel cards;
	JPanel headings;

	final static String addSalesLabel = "Add a Sales Record";
	final static String addProductLabel = "Add a Product";
	final static String modifyProductLabel = "Modify a Product";

	protected JPanel Content()
	{
		cards = new JPanel(new CardLayout());
		cards.setPreferredSize(new Dimension(500, 300));
		
		JPanel AddSalesRecord = new AddSalesRecordGUI();
		JPanel AddProduct = new AddProductGUI();
		JPanel ModifyProduct = new ModifyProductGUI();
		
		cards.add(AddSalesRecord, addSalesLabel);
		cards.add(AddProduct, addProductLabel);
		cards.add(ModifyProduct, modifyProductLabel);
		return cards;
	}

	protected JPanel PageHeadings()
	{
		headings = new JPanel(new CardLayout());
		headings.add(new JLabel(addSalesLabel, SwingConstants.CENTER), addSalesLabel);
		headings.add(new JLabel(addProductLabel, SwingConstants.CENTER), addProductLabel);
		headings.add(new JLabel(modifyProductLabel, SwingConstants.CENTER), modifyProductLabel);
		return headings;
	}

	protected void GenerateButtons()
	{
		addSales = new Button(addSalesLabel);
		addProduct = new Button(addProductLabel);
		modifyProduct = new Button(modifyProductLabel);
		modifyStockLevel = new Button("Modify Stock Levels");

		sideButtons.addElement(addSales);
		sideButtons.addElement(addProduct);
		sideButtons.addElement(modifyProduct);
		sideButtons.addElement(modifyStockLevel);

		AddSalesButtonEvent();
		AddProductButtonEvent();
		ModifyProductButtonEvent();
	}

	private void AddSalesButtonEvent()
	{
		addSales.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, addSalesLabel);
				cl = (CardLayout)headings.getLayout();
				cl.show(headings, addSalesLabel);
			}
		});
	}

	private void AddProductButtonEvent()
	{
		addProduct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, addProductLabel);
				cl = (CardLayout)headings.getLayout();
				cl.show(headings, addProductLabel);
			}
		});
	}
	
	private void ModifyProductButtonEvent()
	{
		modifyProduct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, modifyProductLabel);
				cl = (CardLayout)headings.getLayout();
				cl.show(headings, modifyProductLabel);
			}
		});
	}
}