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

	//$$ TODO: Consider moving the score-keeping (model/data) out to another class/object (thus separating it somewhat from the view/display code)
	private int score;
	private JLabel scoreLabel;
	private ArrayList<Bird> birds;
	private Bird currentBird;
	private int index; //$$ TODO: Not a good intention-revealing variable name.

	//$$ TODO: Change these buttons to an array of them
	JButton birdBtn1;
	JButton birdBtn2;
	JButton birdBtn3;
	private JLabel image;  //$$ TODO: potentially confusing name, since it's not of type Image -- consider "imageLabel"?
	
	public BirdIdGUI(ArrayList<Bird> birds) {
		super("BirdIdGUI");
		
		score = 0;
		scoreLabel = new JLabel("Score: " + score);
		//$$ TODO: Why do you use this.birds, but not this.score , this.scoreLabel, etc?  Either works, but be consistent!
		this.birds = birds;
		index = 0;
		currentBird = birds.get(index);
		
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
		birdBtn1 = new JButton();
		birdBtn2 = new JButton();
		birdBtn3 = new JButton();
		buttonPopulator();
		southButtonPanel.add(birdBtn1);
		southButtonPanel.add(birdBtn2);
		southButtonPanel.add(birdBtn3);

		//adding listeners to buttons
		birdBtn1.addActionListener(this);
		birdBtn2.addActionListener(this);
		birdBtn3.addActionListener(this);
		
		//adding images
		image = new JLabel();
		image.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
		image.setHorizontalAlignment(SwingConstants.CENTER);
		contain.add(image, BorderLayout.CENTER);
	}

	/**
	 * Accesses Birds from the list randomly to create the button labels for each question.
	 */
	public void buttonPopulator(){
		//$$ TODO: The following buttonPopulator() code in this method is pretty ugly.
		//$$    Consider using Java's built-in shuffle() for the birds and/or buttons,
		//$$    -- for the birds, you could just take the first N birds, after shuffling...
		
		//$$ TODO: r is not a very good name.  "rand" or "rng" (for random number generator) would be better.  
		Random r = new Random();
		//$$ TODO: Get rid of old/crusty commented out code. 
		//Random birdCount = new Random();
		int randBtnChooser = r.nextInt(3);
		int randOtherIndex1 = r.nextInt(birds.size());
		while (randOtherIndex1 == index) {
			randOtherIndex1 = r.nextInt(birds.size());
		}
		int randOtherIndex2 = r.nextInt(birds.size());
		while (randOtherIndex2 == index || randOtherIndex2 == randOtherIndex1) {
			randOtherIndex2 = r.nextInt(birds.size());
		}
		//int birdIndex = birdCount.nextInt(birds.size());
		
		if (randBtnChooser == 0) {
			birdBtn1.setText(currentBird.getBirdName());
			birdBtn2.setText(birds.get(randOtherIndex1).getBirdName());
			birdBtn3.setText(birds.get(randOtherIndex2).getBirdName());
		} else if (randBtnChooser == 1) {
			birdBtn2.setText(currentBird.getBirdName());
			birdBtn1.setText(birds.get(randOtherIndex1).getBirdName());
			birdBtn3.setText(birds.get(randOtherIndex2).getBirdName());			
		} else {
			birdBtn3.setText(currentBird.getBirdName());
			birdBtn1.setText(birds.get(randOtherIndex1).getBirdName());
			birdBtn2.setText(birds.get(randOtherIndex2).getBirdName());			
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
			score++;
			scoreLabel.setText("Score: " + score);

		} else {
			sourceButton.setBackground(Color.RED);
			
		}
		index++;
		if (index == birds.size()) {
			endProgram();
		} else {
			resetAfterAction(sourceButton, defaultColor);
		}
	}

	/**
	 * Displays the final result screen of the program after the questions are done.
	 */
	public void endProgram() {
		
			scoreLabel.setText("You Answered "+score+"/"+birds.size()+"correctly");
			
		
		
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
				currentBird = birds.get(index);
				image.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
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