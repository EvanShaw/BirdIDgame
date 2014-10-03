//$$ TODO: Class needs commented -- one Javadoc /** */ comment above each class & method.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
	JButton birdBtn4;
	JButton birdBtn5;
	JButton[] buttons;
	private JLabel image;  //$$ TODO: potentially confusing name, since it's not of type Image -- consider "imageLabel"?
	
	public BirdIdGUI(ArrayList<Bird> birds) {
		super("BirdIdGUI");
		
		score = 0;
		scoreLabel = new JLabel("Score: " + score);
		//$$ TODO: Why do you use this.birds, but not this.score , this.scoreLabel, etc?  Either works, but be consistent!
		this.birds = birds;
		index = 0;
		currentBird = birds.get(index);
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
		birdBtn1 = new JButton();
		birdBtn2 = new JButton();
		birdBtn3 = new JButton();
		birdBtn4 = new JButton();
		birdBtn5 = new JButton();
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
		image = new JLabel();
		image.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
		image.setHorizontalAlignment(SwingConstants.CENTER);
		contain.add(image, BorderLayout.CENTER);
	}

	public void buttonPopulator(){
		//$$ TODO: The following buttonPopulator() code in this method is pretty ugly.
		//$$    Consider using Java's built-in shuffle() for the birds and/or buttons,
		//$$    -- for the birds, you could just take the first N birds, after shuffling...
		
		//$$ TODO: r is not a very good name.  "rand" or "rng" (for random number generator) would be better.  
		Random r = new Random();
		//$$ TODO: Get rid of old/crufty commented out code. 
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
	
	@Override
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
	
	public void endProgram() {
		
			scoreLabel.setText("You Answered "+score+"/"+birds.size()+"correctly");
			
		
		
	}
	
	public void resetAfterAction(final JButton srcBtn, final Color defaultColor) {
		Timer timer = new Timer(2000, new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
				currentBird = birds.get(index);
				image.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
				srcBtn.setBackground(defaultColor);
				buttonPopulator();
		    }
		});
		timer.setRepeats(false);
		timer.start();
		timer.stop();
	}
}
