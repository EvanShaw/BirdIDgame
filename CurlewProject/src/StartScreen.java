import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {

	private User theUser;
	private JButton logIn;
	private JButton about;
	private JTextField userName;
	private JLabel welcome;
	
	public StartScreen() {
		super("Bird Identification Quiz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		welcome = new JLabel("Welcome to Curlew's Bird Identification Quiz!");
		welcome.setFont(welcome.getFont().deriveFont(18.0f));
		welcome.setHorizontalAlignment(JTextField.CENTER);
		logIn = new JButton("Log In/Register");
		about= new JButton("About");
		userName = new JTextField("Enter Username");
		userName.setFont(userName.getFont().deriveFont(15.0f));
		userName.setHorizontalAlignment(JTextField.CENTER);

		this.setSize(500, 200);
		this.setLayout(new BorderLayout());

		// setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		contain.add(welcome, BorderLayout.NORTH);
		contain.add(userName, BorderLayout.CENTER);
		JPanel userBtnPanel = new JPanel(new FlowLayout());
		contain.add(userBtnPanel, BorderLayout.SOUTH);
		userBtnPanel.add(logIn);
		userBtnPanel.add(about);
		logIn.addActionListener(this);
		about.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		if(sourceButton==logIn){
			
		theUser = new User(userName.getText());
		DifficultyGui setDifficulty = new DifficultyGui(theUser);
		setDifficulty.setVisible(true);
		this.dispose();
		} else {
			JOptionPane.showMessageDialog(null,"All bird photographs (with the exception of American Bittern, which is in the public domain) \n " +
					"are copyright (2011-2014) Susa Stonedahl, all rights reserved.\n"+"Special thanks to Brett Mignin and Matt Leja for beta testing");
		}
	}
}
