package php;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoginGUI extends JPanel {

	private final String username = "";
	private final String password = "";

	JTextField usernameField;
	JPasswordField passwordField;

	Button loginButton;
	Button clearButton;

	public LoginGUI() {
		Initialize();

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(usernameLabel)
								.addComponent(passwordLabel))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(usernameField)
								.addComponent(passwordField)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(loginButton)
						.addComponent(clearButton))
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(usernameLabel)
						.addComponent(usernameField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(passwordLabel)
						.addComponent(passwordField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(loginButton)
						.addComponent(clearButton))
				);
	}

	public void LoginButtonFunction(final JPanel loginBlock, final String contentLabel) {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*
				if (usernameField.getText().equals("") ||
						passwordField.getPassword().equals(""))
				{
					JOptionPane.showMessageDialog(null, "please enter username/password!");

				}
				 */
				if (usernameField.getText().equals(username) ||
						passwordField.getPassword().equals(password))
				{
					CardLayout cl = (CardLayout)loginBlock.getLayout();
					cl.show(loginBlock, contentLabel);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The username and/or password is incorrect");
				}
			}
		});
	}

	private void ClearButtonFunction() {
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText("");
				passwordField.setText("");
			}
		});
	}

	private void Initialize()
	{
		usernameField = new JTextField();
		passwordField = new JPasswordField();

		loginButton = new Button("Login");
		clearButton = new Button("Clear");

		ClearButtonFunction();
	}
}
