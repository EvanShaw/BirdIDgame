import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DifficultyGui extends JFrame implements ActionListener {

	/**
	 * This Class creates a Gui for the user to choose their difficulty for the
	 * quiz.
	 */
	private JLabel chooseDifficulty;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	private JLabel[] labels;
	private User theUser;
	private Difficulty difficulty;

	public DifficultyGui(User theUser) {
		super("Choose Diffiuclty");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.theUser = theUser;
		
		this.setSize(300, 150);
		this.setLayout(new BorderLayout());

		easy = new JButton("Easy");
		medium = new JButton("Medium");
		hard = new JButton("Hard");
		difficulty = new Difficulty();
		chooseDifficulty = new JLabel("Choose Difficulty:");
		chooseDifficulty.setFont(chooseDifficulty.getFont().deriveFont(17.0f));
		chooseDifficulty.setHorizontalAlignment(JTextField.CENTER);
		
		this.add(chooseDifficulty, BorderLayout.NORTH);
		JPanel south = new JPanel(new GridLayout(2, 1));
		this.add(south, BorderLayout.SOUTH);

		JPanel southButtons = new JPanel(new FlowLayout());
		southButtons.add(easy);
		southButtons.add(medium);
		southButtons.add(hard);
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		south.add(southButtons);

		JPanel southLabels = new JPanel(new GridLayout(3, 3));
		labels = new JLabel[]{new JLabel("Easy:"), new JLabel("Medium:"), new JLabel("Hard:"), new JLabel("3 Options"), new JLabel("4 Options"), new JLabel("5 Options"), new JLabel("20 Questions"), new JLabel("30 Questions"), new JLabel("40 Questions")};
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(labels[i].getFont().deriveFont(10.0f));
			labels[i].setHorizontalAlignment(JTextField.CENTER);
			southLabels.add(labels[i]);
		}
		south.add(southLabels);
	}



	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		difficulty.setDifficulty(sourceButton.getText());

		BirdLibrary library = new BirdLibrary();
		BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(), theUser, difficulty);
		myFrame.setVisible(true);
		


	}

}
