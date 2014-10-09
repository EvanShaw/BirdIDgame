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
		userName = new JTextField("Enter username");
		enterUserName = new JLabel(
				"Enter username. If this your first time click the create new username button");
		//Container center = new Container();
		//center.setLayout(new GridLayout());

		this.setSize(600, 500);
		this.setLayout(new BorderLayout());
	
		//setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		JPanel difficultyPanel = new JPanel(new FlowLayout());
		contain.add(difficultyPanel, BorderLayout.SOUTH);
		//contain.add(newUser, BorderLayout.NORTH);
		contain.add(enterUserName, BorderLayout.CENTER);
		contain.add(logIn, BorderLayout.NORTH);
		difficultyPanel.add(easy);
		difficultyPanel.add(hard);
		
		logIn.addActionListener(this);
		easy.addActionListener(this);
		hard.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		if (event.getSource().equals(easy) || event.getSource().equals(hard)) {
			chosenDifficulty.setDifficulty(sourceButton.getText());
		}
		if (event.getSource().equals(logIn)) {
			
		}
	
		/* TODO: Temporary invocation of BirdIdGUI. Ideally we want only the user fields and information to appear 
		 * first, then after the user information is entered, a box asking the user what difficulty they would
		 * like will display, and then THAT will invoke BirdIdGUI.
		 */
		BirdLibrary library = new BirdLibrary();
		BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(), chosenDifficulty);
		myFrame.setVisible(true);
	}
}
