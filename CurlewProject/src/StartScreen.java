import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
		//fields
	private User theUser;
	private JButton logIn;
	

	private JTextField userName;
	private JLabel enterUserName;

	public StartScreen() {
		super("Bird Identification Quiz");

		logIn = new JButton("Log in/ register");
	

		userName = new JTextField("Enter Username");
		enterUserName = new JLabel(
				"If this your first time click the create new username button",
				SwingConstants.CENTER);

		this.setSize(500, 200);
		this.setLayout(new BorderLayout());

		// setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		contain.add(new JLabel("Welcome To Curlew's Bird Identification Quiz"),
				BorderLayout.NORTH);

		JPanel center = new JPanel(new GridLayout(2, 0));
		center.add(userName);
		

		JPanel userBtnPanel = new JPanel(new FlowLayout());
		contain.add(userBtnPanel, BorderLayout.SOUTH);
		contain.add(center, BorderLayout.CENTER);

		userBtnPanel.add(logIn);
		

		logIn.addActionListener(this);
			}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		
		theUser = new User(userName.getText());
		DifficultyGui setDifficulty = new DifficultyGui(theUser);
		setDifficulty.setVisible(true);
		this.dispose();
		
			
	}
}
