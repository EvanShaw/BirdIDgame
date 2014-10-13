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
	private Score sessionScore;
	private ArrayList<JButton> buttons;
	private JLabel imageLabel;
	private int correctBirdIndex;

	private final Color defaultColor;

	public BirdIdGUI(ArrayList<Bird> birds, User currentUser,
			Difficulty chosenDifficulty) {
		super("BirdIdGUI");

		this.birds = birds;
		this.currentUser = currentUser;
		this.chosenDifficulty = chosenDifficulty;
		sessionScore = new Score(0, chosenDifficulty.getNumQuestions());
		scoreLabel = new JLabel("Score: " + sessionScore.getNumCorrectAnswers());
		imageLabel = new JLabel();
		buttons = new ArrayList<JButton>();

		// basic window operations
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(760, 500);

		// setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());

		JPanel southButtonPanel = new JPanel(new GridLayout(2, 0));
		contain.add(southButtonPanel, BorderLayout.SOUTH, SwingConstants.CENTER);

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

		imageLabel.setIcon(new ImageIcon(currentBird.getImagePath()));

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
			sessionScore.incrementScore();
			scoreLabel.setText("Score: " + sessionScore.getNumCorrectAnswers());
		} else {
			sourceButton.setBackground(Color.RED);
			for (int i = 0; i < buttons.size(); i++) {
				if (buttons.get(i).getText().equals(currentBird.getBirdName())) {
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

		sessionScore.incrementAnsweredQuestions();
		if (sessionScore.getNumAnsweredQuestions() == sessionScore
				.getNumQuestions()) {
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

		scoreLabel.setText("You Answered "
				+ sessionScore.getNumCorrectAnswers() + "/"
				+ sessionScore.getNumQuestions() + " correctly");

		currentUser.getUserScore().addToTotalScore(
				sessionScore.getNumCorrectAnswers(),
				sessionScore.getNumQuestions());
		currentUser.writeChangesToFile(currentUser.getUserScore());

		String[] options = { "Take Another Quiz", "Return To Login" };

		int index = JOptionPane.showOptionDialog(null,
				"Well done " + currentUser.getUserName() + ", you answered "
						+ sessionScore.getNumCorrectAnswers() + "/"
						+ sessionScore.getNumQuestions()
						+ " correctly. Your new total score is "
						+ currentUser.getUserScore().getTotalScore() + ".",
				"End Of Quiz", 0, JOptionPane.QUESTION_MESSAGE, null, options,
				0);
		if (options[index].equals(options[1])) {
			StartScreen start = new StartScreen();
			start.setVisible(true);
			this.setVisible(false);

		} else {
			DifficultyGui gui = new DifficultyGui(currentUser);
			gui.setVisible(true);
			this.setVisible(false);

		}
	}

	/**
	 * When there are more questions to come, this method resets the buttons for
	 * the new question, calling displayQuestion() to re-populate the buttons.
	 * 
	 * @params defaultColor - uses the variables from the actionPerformed method
	 *         to reset the changed button to its original color.
	 */
	public void resetAfterAction() {
		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// sets all buttons to default color
				for (int i = 0; i < buttons.size(); i++) {
					buttons.get(i).setBackground(defaultColor);
				}
				displayQuestion();
			}
		});
		timer.setRepeats(false);
		timer.start(); // timer.stop();
	}
}
