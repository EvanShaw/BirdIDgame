import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Creates the GUI for the user to play the game with.
 */
public class BirdIdGUI extends JFrame implements ActionListener {

	private Score sessionScore;
	private Difficulty chosenDifficulty;
	private JLabel scoreLabel;
	private ArrayList<Bird> birds;
	private Bird currentBird;
	private int birdListIndex; 
	
	JButton[] buttons;
	private JLabel imageLabel;  
	
	/* TODO: chosenDifficulty.getNumButtons() is unusable at the moment. We need to figure how to only create a 
	 * certain number of buttons... 
	 */
	   
	public BirdIdGUI(ArrayList<Bird> birds, Difficulty chosenDifficulty) {
		super("BirdIdGUI");
		
		this.birds = birds;
		this.chosenDifficulty = chosenDifficulty;
		sessionScore = new Score();
		scoreLabel = new JLabel("Score: " + sessionScore.getScore());
		birdListIndex = 0;
		currentBird = birds.get(birdListIndex);
		buttons = new JButton[5];
		
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
		JButton birdBtn1 = new JButton();
		JButton birdBtn2 = new JButton();
		JButton birdBtn3 = new JButton();
		JButton birdBtn4 = new JButton();
		JButton birdBtn5 = new JButton();
		
		buttons[0] = birdBtn1;
		buttons[1] = birdBtn2;
		buttons[2] = birdBtn3;
		buttons[3] = birdBtn4;
		buttons[4] = birdBtn5;
		
		buttonPopulator();
		
		for (int i = 0; i < 5; i++) {
			southButtonPanel.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		//adding images
		imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contain.add(imageLabel, BorderLayout.CENTER);
	}

	/**
	 * Accesses Birds from the list randomly to create the button labels for each question.
	 */
	public void buttonPopulator(){
		//$$ TODO: The following buttonPopulator() code in this method is pretty ugly.
		//$$    Consider using Java's built-in shuffle() for the birds and/or buttons,
		//$$    -- for the birds, you could just take the first N birds, after shuffling...
		 
		Random rand = new Random();
		int randBtnChooser = rand.nextInt(3);
		int randOtherIndex1 = rand.nextInt(birds.size());
		while (randOtherIndex1 == birdListIndex) {
			randOtherIndex1 = rand.nextInt(birds.size());
		}
		int randOtherIndex2 = rand.nextInt(birds.size());
		while (randOtherIndex2 == birdListIndex || randOtherIndex2 == randOtherIndex1) {
			randOtherIndex2 = rand.nextInt(birds.size());
		}
		
		if (randBtnChooser == 0) {
			buttons[0].setText(currentBird.getBirdName());
			buttons[1].setText(birds.get(randOtherIndex1).getBirdName());
			buttons[2].setText(birds.get(randOtherIndex2).getBirdName());
		} else if (randBtnChooser == 1) {
			buttons[1].setText(currentBird.getBirdName());
			buttons[0].setText(birds.get(randOtherIndex1).getBirdName());
			buttons[2].setText(birds.get(randOtherIndex2).getBirdName());			
		} else {
			buttons[2].setText(currentBird.getBirdName());
			buttons[0].setText(birds.get(randOtherIndex1).getBirdName());
			buttons[1].setText(birds.get(randOtherIndex2).getBirdName());			
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
		birdListIndex++;
		if (birdListIndex == chosenDifficulty.getNumPictures()) {
			endProgram();
		} else {
			resetAfterAction(sourceButton, defaultColor);
		}
	}

	/**
	 * Displays the final result screen of the program after the questions are done.
	 */
	public void endProgram() {
		
			scoreLabel.setText("You Answered "+sessionScore.getScore()+"/"+chosenDifficulty.getNumPictures()+"correctly");
			
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
				currentBird = birds.get(birdListIndex);
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
