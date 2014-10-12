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
	private int numQuestionsAnswered;
	private int numQuestions;
	private int correctBirdIndex;
	private int sessionScore;
	private ArrayList<JButton> buttons;
	private JLabel imageLabel;
	private final Color defaultColor;

	public BirdIdGUI(ArrayList<Bird> birds, User currentUser,
			Difficulty chosenDifficulty) {
		super("BirdIdGUI");

		this.birds = birds;
		this.currentUser = currentUser;
		this.chosenDifficulty = chosenDifficulty;
		sessionScore = 0;
		scoreLabel = new JLabel("Score: " + sessionScore);
		imageLabel = new JLabel();
		numQuestionsAnswered = 0;
		numQuestions = chosenDifficulty.getNumQuestions();
		buttons = new ArrayList<JButton>();

		// basic window operations
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 500);

		// setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		JPanel southButtonPanel = new JPanel(new FlowLayout());
		contain.add(southButtonPanel, BorderLayout.SOUTH);
		contain.add(scoreLabel, BorderLayout.NORTH);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contain.add(imageLabel, BorderLayout.CENTER);

		// creating the buttons
		for (int i = 0; i < chosenDifficulty.getNumButtons(); i++) {
			buttons.add(new JButton());
			southButtonPanel.add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}
		defaultColor = buttons.get(0).getBackground();

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
		if (sourceButton.getText().equals(currentBird.getBirdName())) {
			sourceButton.setBackground(Color.GREEN);
			sessionScore++;
			scoreLabel.setText("Score: " + sessionScore);
		} else {
			sourceButton.setBackground(Color.RED);
			for (int i = 0; i < buttons.size(); i++) {
				if (buttons.get(i).equals(currentBird.getBirdName())) {
					buttons.get(i).setBackground(Color.GREEN);
				}
			}
		}
		/*
		 * Remove the correct bird from the array so that it is not randomly
		 * selected in the future
		 */
		birds.remove(correctBirdIndex);
		Collections.shuffle(birds);

		numQuestionsAnswered++;
		if (numQuestionsAnswered == numQuestions) {
			endProgram();
		} else {
			resetAfterAction();
		}
	}

	/**
	 * Displays the final result screen of the program after the questions are
	 * done.
	 */
	public void endProgram() {
		scoreLabel.setText("You Answered " + sessionScore + "/"
				+ numQuestions + "correctly");
		currentUser.getUserScore().addToTotalScore(sessionScore, numQuestions);
		currentUser.writeChangesToFile(currentUser.getUserName(), currentUser.getUserScore());
	}

	/**
	 * When there are more questions to come, this method resets the buttons for
	 * the new question, calling displayQuestion() to re-populate the buttons.
	 * 
	 * @params defaultColor - uses the variables from the actionPerformed method
	 * to reset the changed button to its original color.
	 */
	public void resetAfterAction() {
		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < buttons.size(); i++) { //sets all buttons to default
					buttons.get(i).setBackground(defaultColor);
				}
			}
		});
		timer.setRepeats(false); timer.start(); timer.stop();
		displayQuestion();
	}
}