import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BirdIdGUI extends JFrame implements ActionListener {

	int score;
	JLabel scoreLabel;
	JLabel ImageLabel;
	ArrayList<Bird> birds;
	Bird currentBird;
	int index;
	JButton birdBtn1;
	JButton birdBtn2;
	JButton birdBtn3;
	
	
	
	public BirdIdGUI(ArrayList<Bird> birds) {
		super("BirdIdGUI");
		
		//setting the fields
		score = 0;
		scoreLabel = new JLabel("Score: " + score);
		this.birds = birds;
		index = 0;
		currentBird = birds.get(index);
		
		//basic window operations
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 600);

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
		
		//Add images
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
		
		contain.add(image, BorderLayout.CENTER);
	}
	
	


	public void buttonPopulator(){
		Random r = new Random();
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
	
	@Override

	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		if (sourceButton.getText().equals(currentBird.getBirdName())) {
			sourceButton.setBackground(Color.GREEN);
			score++;
		} else {
			sourceButton.setBackground(Color.RED);
			
		}
		index++;
		currentBird = birds.get(index);
		if (index == birds.size()) {
			//end program
		}
	}
}
