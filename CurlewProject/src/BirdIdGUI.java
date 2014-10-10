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

	private Score sessionScore;
	private Difficulty chosenDifficulty;
	private JLabel scoreLabel;
	private ArrayList<Bird> birds;
	private Bird[] birdsUsed;
	private Bird currentBird;
	private int sumOfQuestions; 
	private ArrayList<JButton> buttons;
	private JLabel imageLabel;
	   
	public BirdIdGUI(ArrayList<Bird> birds, Difficulty chosenDifficulty) {
		super("BirdIdGUI");
		
		this.birds = birds;
		this.chosenDifficulty = chosenDifficulty;
		sessionScore = new Score();
		scoreLabel = new JLabel("Score: " + sessionScore.getScore());
		sumOfQuestions = 0;
		currentBird = birds.remove(0);
		buttons = new ArrayList<JButton>();
		
		//basic window operations
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 500);

		//setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		JPanel southButtonPanel = new JPanel(new FlowLayout());
		contain.add(southButtonPanel, BorderLayout.SOUTH);
		contain.add(scoreLabel, BorderLayout.NORTH);

		//creating the buttons
		
		for (int i = 0; i < chosenDifficulty.getNumButtons(); i++) {
			buttons.add(new JButton());
			southButtonPanel.add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}
		
		buttonPopulator();
		
		//adding images
		imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contain.add(imageLabel, BorderLayout.CENTER);
		this.validate();
	}

	/**
	 * Accesses Birds from the list randomly to create the button labels for each question.
	 */
	public void buttonPopulator(){
		
		Random rand = new Random();
		int correctButton = rand.nextInt(chosenDifficulty.getNumButtons());
		
		boolean containsAnswer = false;
		Collections.shuffle(birds);
		for (int i = 0; i < chosenDifficulty.getNumButtons(); i++) {
			if (!birds.get(i).equals(currentBird)) {
				buttons.get(i).setText(birds.get(i).getBirdName());
			} else {
				buttons.get(i).setText(birds.get(i).getBirdName());
				containsAnswer = true;
			}
		}
		
		if (!containsAnswer) {
			buttons.get(correctButton).setText(currentBird.getBirdName());
		}
	}

	/**
	 * Handles the action listening for all GUI button interaction.
	 * @Override
	 */
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		Color defaultColor = sourceButton.getBackground();
		if (sourceButton.getText().equals(currentBird.getBirdName())) {
			sourceButton.setBackground(Color.GREEN);
			sessionScore.incrementScore();
			scoreLabel.setText("Score: " + sessionScore.getScore());

		} else {
			sourceButton.setBackground(Color.RED);
				
		}
		if (sumOfQuestions == chosenDifficulty.getNumQuestions()) {
			endProgram();
		} else {
			resetAfterAction(sourceButton, defaultColor);
		}
		currentBird = birds.remove(0);
	}

	/**
	 * Displays the final result screen of the program after the questions are done.
	 */
	public void endProgram() {
		
			scoreLabel.setText("You Answered "+sessionScore.getScore()+"/"+chosenDifficulty.getNumQuestions()+"correctly");
			
	}

	/**
	 * When there are more questions to come, this method resets the screen for the
	 * new question and calls populate() to re-populate the buttons.
	 * @params srcBtn, defaultColor - uses the variables from the actionPerformed
	 * 		method to reset the changed button to its original color
	 */
	public void resetAfterAction(final JButton srcBtn, final Color defaultColor) {
		Timer timer = new Timer(2000, new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
				currentBird = birds.get(sumOfQuestions);
				imageLabel.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
				srcBtn.setBackground(defaultColor);
				//TODO set all buttons using data fields to default color, not just source
				buttonPopulator();
		    }
		});
		timer.setRepeats(false);
		timer.start();
		timer.stop();
	}
}
