package php;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AddSalesSideBar extends SideBarWrapper {
	Button addSales;
	Button addProduct;
	Button modifyProduct;
	Button modifyStock;

	JPanel cards;

	final String addSalesLabel = "Add a Sales Record";
	final String addProductLabel = "Add a Product";
	final String modifyProductLabel = "Modify a Product";
	final String modifyStockLabel = "Modify Stock Level";

	protected JPanel Content()
	{
		cards = new JPanel(new CardLayout());

		JPanel AddSalesRecord = new AddSalesRecordGUI();
		JPanel AddProduct = new AddProductGUI();
		JPanel ModifyProduct = new ModifyProductGUI();
		JPanel ModifyStock = new ModifyStockGUI();

		cards.add(AddSalesRecord, addSalesLabel);
		cards.add(AddProduct, addProductLabel);
		cards.add(ModifyProduct, modifyProductLabel);
		cards.add(ModifyStock, modifyStockLabel);
		return cards;
	}

	protected void GenerateButtons()
	{
		addSales = new Button(addSalesLabel);
		addProduct = new Button(addProductLabel);
		modifyProduct = new Button(modifyProductLabel);
		modifyStock = new Button(modifyStockLabel);

		sideButtons.addElement(addSales);
		sideButtons.addElement(addProduct);
		sideButtons.addElement(modifyProduct);
		sideButtons.addElement(modifyStock);

		AddSalesButtonEvent();
		AddProductButtonEvent();
		ModifyProductButtonEvent();
		ModifyStockButtonEvent();
	}

	private void AddSalesButtonEvent()
	{
		addSales.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, addSalesLabel);
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
			}
		});
	}

	private void ModifyStockButtonEvent()
	{
		modifyStock.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, modifyStockLabel);
			}
		});
	}
}