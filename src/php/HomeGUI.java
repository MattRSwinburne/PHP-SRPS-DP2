package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HomeGUI {
	TextField usernameTextField = new TextField();
	TextField passwordTextField = new TextField();


	Button loginButton = new Button("Login");
	Button exitButton = new Button("Exit");
	
	public void HomeContent(JPanel panel) {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		Dimension labelSize = new Dimension(50, 22);
		
		Label usernameLabel = new Label("Username");
		usernameLabel.setAlignment(Label.RIGHT);
		usernameLabel.setName("usernameLabel");
	    usernameLabel.setMaximumSize(labelSize);
		
		Label passwordLabel = new Label("Password");
		passwordLabel.setAlignment(Label.RIGHT);
		passwordLabel.setName("passwordLabel");
		passwordLabel.setMaximumSize(labelSize);
		
		
		Dimension textFieldMinSize = new Dimension(100, 22);
		Dimension textFieldPrefSize = new Dimension(180, 22);
		Dimension textFieldMaxSize = new Dimension(300, 22);
		
		usernameTextField.setName("usernameTextField");
		usernameTextField.setMinimumSize(textFieldMinSize);
		usernameTextField.setPreferredSize(textFieldPrefSize);
		usernameTextField.setMaximumSize(textFieldMaxSize);
		
		passwordTextField.setName("passwordTextField");
		passwordTextField.setMinimumSize(textFieldMinSize);
		passwordTextField.setPreferredSize(textFieldPrefSize);
		passwordTextField.setMaximumSize(textFieldMaxSize);
	
		Dimension buttonSize = new Dimension(50, 30);
		
		loginButton.setName("loginButton");
		loginButton.setMaximumSize(buttonSize);
		LoginButtonFunction();

		exitButton.setName("exitButton");
		exitButton.setMaximumSize(buttonSize);
		ExitButtonFunctionality();
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(usernameLabel)
					.addComponent(passwordLabel)
		
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(usernameTextField)
					.addComponent(passwordTextField)
					.addGroup(layout.createSequentialGroup()
						.addComponent(loginButton)
						.addComponent(exitButton)))
		));
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(usernameLabel)
					.addComponent(usernameTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(passwordLabel)
					.addComponent(passwordTextField))
				
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(loginButton)
					.addComponent(exitButton))
		);
	}
	
	private void LoginButtonFunction() {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				if (usernameTextField.getText().equals("") ||
						passwordTextField.getText().equals(""))
				{
				JOptionPane.showMessageDialog(null, "please enter username/password!");
				}
							
			}
		});
	}
	
	private void ExitButtonFunctionality() {
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
	}
}
