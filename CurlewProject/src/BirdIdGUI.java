import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		this.setSize(500, 500);

		//setting the panels and layouts
		Container contain = this.getContentPane();
		contain.setLayout(new BorderLayout());
		JPanel southButtonPanel = new JPanel(new FlowLayout());
		contain.add(southButtonPanel, BorderLayout.SOUTH);
		
		contain.add(scoreLabel, BorderLayout.NORTH);

		//creating the buttons
		 birdBtn1 = new JButton("Bird name 1");
		 birdBtn2 = new JButton("Bird name 2");
		 birdBtn3 = new JButton("Bird name 3");
		southButtonPanel.add(birdBtn1);
		southButtonPanel.add(birdBtn2);
		southButtonPanel.add(birdBtn3);

		//adding listeners to buttons
		birdBtn1.addActionListener(this);
		birdBtn2.addActionListener(this);
		birdBtn3.addActionListener(this);
		
		//Add images
		
		JLabel imageLabel = new JLabel(new ImageIcon());
		contain.add(imageLabel, BorderLayout.CENTER);
		
		
		
	}
	public void buttonPopulator(ArrayList<Bird> birdPop,int index,Bird currentBird){
		Random r=new Random();
		Random birdCount=new Random();
		int randNum=r.nextInt(3);
		int birdIndex=birdCount.nextInt(birdPop.size());
		
		
		if(randNum==0){
			birdBtn1.setText(birdPop.get(birdIndex).getBirdName());
		}else if(randNum==1){
			birdBtn2.setText(birdPop.get(birdIndex).getBirdName());
		}else{
			birdBtn3.setText(birdPop.get(birdIndex).getBirdName());
		}
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		//if (sourceButton.getText().equals(CORRECT)) {
			sourceButton.setBackground(Color.GREEN);
			score++;
		//} else {
			sourceButton.setBackground(Color.RED);
		//	CORRECT.setBackground(Color.GREEN);
		//}
			
	}
}