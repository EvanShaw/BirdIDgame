import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Creates the GUI for the user to play the game with.
 */
public class BirdIdGUI extends JFrame implements ActionListener {

	private User currentUser;
	private Difficulty chosenDifficulty;
	private JLabel scoreLabel;
	private ArrayList<Bird> birds;
	private Bird currentBird;
	private int sumOfQuestions;
	private int correctBirdIndex;
	private int sessionScore;
	private ArrayList<JButton> buttons;
	private JLabel imageLabel;

	public BirdIdGUI(ArrayList<Bird> birds, User currentUser,
			Difficulty chosenDifficulty) {
		super("BirdIdGUI");

		this.birds = birds;
		this.currentUser = currentUser;
		this.chosenDifficulty = chosenDifficulty;
		sessionScore = 0;
		scoreLabel = new JLabel("Score: " + sessionScore);
		imageLabel = new JLabel();
		sumOfQuestions = chosenDifficulty.getNumQuestions();
		buttons = new ArrayList<JButton>();

		// basic window operations
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 500);

		// setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		JPanel flowLayout=new JPanel(new FlowLayout());
		JPanel southButtonPanel = new JPanel(new GridLayout(2,0));
		flowLayout.add(southButtonPanel);
		contain.add(southButtonPanel, BorderLayout.SOUTH,SwingConstants.CENTER);
		contain.add(scoreLabel, BorderLayout.NORTH);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contain.add(imageLabel, BorderLayout.CENTER);

		// creating the buttons
		for (int i = 0; i < chosenDifficulty.getNumButtons(); i++) {
			buttons.add(new JButton());
			southButtonPanel.add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}

		displayQuestion();
	}

	/**
	 * Accesses Birds from the list randomly to create the button labels for
	 * each question.
	 */
	public void displayQuestion() {

		Random rand = new Random();
		Collections.shuffle(birds);
		correctBirdIndex = rand.nextInt(chosenDifficulty.getNumButtons());
		currentBird = birds.get(correctBirdIndex);

		imageLabel.setIcon(new ImageIcon("src/thumb_"
				+ currentBird.getImagePath()));

		for (int i = 0; i < chosenDifficulty.getNumButtons(); i++) {
			buttons.get(i).setText(birds.get(i).getBirdName());
		}

	}

	/**
	 * Handles the action listening for all GUI button interaction.
	 * 
	 * @Override
	 */
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		Color defaultColor = sourceButton.getBackground();
		if (sourceButton.getText().equals(currentBird.getBirdName())) {
			sourceButton.setBackground(Color.GREEN);
			sessionScore++;
			scoreLabel.setText("Score: " + sessionScore);

		} else {
			sourceButton.setBackground(Color.RED);

		}

		/*
		 * Remove the correct bird from the array so that it is not randomly
		 * selected in the future
		 */
		birds.remove(correctBirdIndex);
		Collections.shuffle(birds);

		sumOfQuestions++;
		if (sumOfQuestions == chosenDifficulty.getNumQuestions()) {
			endProgram();
		} else {
			resetAfterAction(sourceButton, defaultColor);
		}
	}

	/**
	 * Displays the final result screen of the program after the questions are
	 * done.
	 */
	public void endProgram() {

		scoreLabel.setText("You Answered " + sessionScore + "/"
				+ chosenDifficulty.getNumQuestions() + "correctly");
		currentUser.getUserScore().addToTotalScore(sessionScore, sumOfQuestions);
		currentUser.writeChangesToFile(currentUser.getUserName(), currentUser.getUserScore());

	}

	/**
	 * When there are more questions to come, this method resets the screen for
	 * the new question and calls populate() to re-populate the buttons.
	 * 
	 * @params srcBtn, defaultColor - uses the variables from the
	 *         actionPerformed method to reset the changed button to its
	 *         original color
	 */
	public void resetAfterAction(final JButton srcBtn, final Color defaultColor) {
		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//temporary test
				endProgram();
				srcBtn.setBackground(defaultColor);
				// TODO set all buttons using data fields to default color, not
				// just source
			}
		});
		/*
		 * timer.setRepeats(false); timer.start(); timer.stop();
		 */
		displayQuestion();
	}
}
