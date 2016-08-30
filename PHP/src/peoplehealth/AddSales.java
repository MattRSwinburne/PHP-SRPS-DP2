package peoplehealth;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddSales {

	private JFrame frame;
	TextField item;
	TextField quantity;
	TextField price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSales window = new AddSales();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public AddSales() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Label label = new Label("Item");
		label.setAlignment(Label.RIGHT);
		label.setBounds(67, 63, 26, 22);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("Quantity");
		label_1.setAlignment(Label.RIGHT);
		label_1.setBounds(39, 91, 54, 22);
		frame.getContentPane().add(label_1);
		
		Label label_2 = new Label("Price");
		label_2.setAlignment(Label.RIGHT);
		label_2.setBounds(60, 119, 33, 22);
		frame.getContentPane().add(label_2);
		
		TextField item = new TextField();
		item.setBounds(99, 63, 180, 22);
		frame.getContentPane().add(item);
		
		TextField quantity = new TextField();
		quantity.setBounds(99, 92, 180, 22);
		frame.getContentPane().add(quantity);
		
		TextField price = new TextField();
		price.setBounds(99, 120, 180, 22);
		frame.getContentPane().add(price);
		
		
		Button button = new Button("ADD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vItem;
				int vQuantity;
				float vPrice;
				
				
				try{
					vItem = (item.getText());
					vQuantity = Integer.parseInt(quantity.getText());
					vPrice = Float.valueOf(price.getText());
					JOptionPane.showMessageDialog(null, "item successfully added");
					
				}catch(Exception e){
					if ((item.getText().equals("")|| (quantity.getText().equals(""))|| price.getText().equals("")))
					{
					JOptionPane.showMessageDialog(null, "please do not leave any fields blank!");
					
					}
				}
				
				//item.setText("");
				//quantity.setText("");
				//price.setText("");
				
				
			}
		});
		button.setBounds(99, 158, 69, 22);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("CLEAR");
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
					item.setText("");
					quantity.setText("");
					price.setText("");
					
			}
		});
		button_1.setBounds(210, 158, 69, 22);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("EXIT");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setBounds(355, 229, 69, 22);
		frame.getContentPane().add(button_2);
	}
}
