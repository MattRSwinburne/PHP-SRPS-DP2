package php;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddSalesSideBar extends SideBarWrapper {
	Button addSales;
	Button addProduct;
	Button modifyProduct;
	Button modifyStockLevel;
	
	JPanel cards;
	JPanel headings;
	
	final static String addSalesName = "AddSales";
	final static String addProductName = "AddProduct";
	
	protected JPanel Content()
	{
		cards = new JPanel(new CardLayout());
		cards.setPreferredSize(new Dimension(500, 300));
		JPanel AddSalesRecord = new AddSalesRecordGUI();
		JPanel AddProduct = new AddProductGUI();
		cards.add(AddSalesRecord, addSalesName);
		cards.add(AddProduct, addProductName);
		return cards;
	}
	
	protected JPanel PageHeadings()
	{
		headings = new JPanel(new CardLayout());
		headings.add(new JLabel("Add a Sales Record"), addSalesName);
		headings.add(new JLabel("Add a Product"), addProductName);
		return headings;
	}
	
	protected void GenerateButtons()
	{
		addSales = new Button("Add a Sales Record");
		addProduct = new Button("Add a Product");
		modifyProduct = new Button("Modify Product");
		modifyStockLevel = new Button("Modify Stock Levels");
		
		sideButtons.addElement(addSales);
		sideButtons.addElement(addProduct);
		sideButtons.addElement(modifyProduct);
		sideButtons.addElement(modifyStockLevel);
		
		AddSalesButtonEvent();
		AddProductButtonEvent();
	}
	
	private void AddSalesButtonEvent()
	{
		addSales.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, addSalesName);
				cl = (CardLayout)headings.getLayout();
				cl.show(headings, addSalesName);
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
				cl.show(cards, addProductName);
				cl = (CardLayout)headings.getLayout();
				cl.show(headings, addProductName);
			}
		});
	}
}