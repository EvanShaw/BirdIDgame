import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

public class DifficultyGui extends JFrame implements ActionListener {

	/**
	 * This Class creates a Gui for the user to choose their difficulty for the
	 * quiz.
	 */
	private JLabel chooseDifficulty;
	private JLabel questionSelectorLabel;
	private JRadioButton questionByImage;
	private JRadioButton questionByName;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	private JLabel[] labels;
	private User theUser;
	private Difficulty difficulty;
	private ButtonGroup group;

	public DifficultyGui(User theUser) {
		super("Choose Difficulty");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.theUser = theUser;
		
		this.setSize(300, 220);
		this.setLayout(new BorderLayout());

		easy = new JButton("Easy");
		medium = new JButton("Medium");
		hard = new JButton("Hard");
		difficulty = new Difficulty();
		chooseDifficulty = new JLabel("Choose Difficulty:");
		chooseDifficulty.setFont(chooseDifficulty.getFont().deriveFont(17.0f));
		chooseDifficulty.setHorizontalAlignment(JTextField.CENTER);
		
		questionSelectorLabel = new JLabel("Answer questions by");
		questionByImage = new JRadioButton("image");
		questionByImage.setSelected(true);
		questionByName = new JRadioButton("name");
		group = new ButtonGroup();
		group.add(questionByImage);
		group.add(questionByName);
		
		JPanel north = new JPanel(new GridLayout(2, 1));
		this.add(north, BorderLayout.NORTH);
		north.add(questionSelectorLabel);
		JPanel radioButtonPanel = new JPanel(new FlowLayout());
		north.add(radioButtonPanel);
		radioButtonPanel.add(questionByImage);
		radioButtonPanel.add(questionByName);
		
		this.add(chooseDifficulty, BorderLayout.CENTER);
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
		if (group.isSelected(questionByImage.getModel())) {
			BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(), theUser, difficulty);
			myFrame.setVisible(true);
		} else if (group.isSelected(questionByName.getModel())) {
			BirdIdGUIAlternate myFrame = new BirdIdGUIAlternate(library.getLibrary(), theUser, difficulty);
			myFrame.setVisible(true);
		} else {
			throw new NullPointerException();
		}
		this.dispose();
	}
}
