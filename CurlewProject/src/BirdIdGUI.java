import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BirdIdGUI extends JFrame implements ActionListener {

	private int score;
	private JLabel scoreLabel;
	private ArrayList<Bird> birds;
	private Bird currentBird;
	private int index;
	//change these buttons to an array of them
	JButton birdBtn1;
	JButton birdBtn2;
	JButton birdBtn3;
	private JLabel image;
	
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
		
	}
	
	public void resetAfterAction(JButton sourceButton, Color defaultColor) {
		Timer timer = new Timer(3000, new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
				currentBird = birds.get(index);
				image.setIcon(new ImageIcon("src/thumb_"+currentBird.getImagePath()));
				//sourceButton.setBackground(defaultColor);
				buttonPopulator();
		    }
		});
		timer.start();
		timer.setRepeats(false); 
		timer.stop();
	}
}