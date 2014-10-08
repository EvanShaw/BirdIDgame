import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener{

	private User user;
	private Difficulty chosenDifficulty;
	private JButton logIn;
	private JButton newUser;
	private JButton hard;
	private JButton easy;
	private JTextField userName;
	private JLabel enterUserName;
	
	public StartScreen() {
		super("StartScreen");
		
		user = new User();
		chosenDifficulty = new Difficulty();
		logIn = new JButton("Log in");
		newUser = new JButton("Create new username");
		hard = new JButton("Hard");
		easy = new JButton("Easy");
		userName = new JTextField("Enter Username");
		enterUserName = new JLabel(
				"If this your first time click the create new username button",SwingConstants.CENTER);
		
		

		this.setSize(500,200);
		this.setLayout(new BorderLayout());
	
		//setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		
		
		contain.add(new JLabel("Welcome To Curlew's Bird Identification Quiz"),BorderLayout.NORTH);
		
		
		JPanel center=new JPanel(new GridLayout(2,0));
		center.add(userName);
		center.add(enterUserName);
		
		
		JPanel userBtnPanel= new JPanel(new FlowLayout());
		contain.add(userBtnPanel,BorderLayout.SOUTH);
		contain.add(center,BorderLayout.CENTER);
		
		
		userBtnPanel.add(logIn);
		userBtnPanel.add(newUser);
		
		
		
		
		
		logIn.addActionListener(this);
		newUser.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		
		
		/* TODO: Temporary invocation of BirdIdGUI. Ideally we want only the user fields and information to appear 
		 * first, then after the user information is entered, a box asking the user what difficulty they would
		 * like will display, and then THAT will invoke BirdIdGUI.
		 */
		BirdLibrary library = new BirdLibrary();
		BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(), chosenDifficulty);
		myFrame.setVisible(true);
	}
}
