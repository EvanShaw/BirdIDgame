import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BirdIdGUI extends JFrame implements ActionListener {

	int score;
	JLabel scoreLabel;
	JLabel ImageLabel;
	
	public BirdIdGUI() {

		super("BirdIdGUI");
		
		//setting the fields
		score = 0;
		scoreLabel = new JLabel("Score: " + score);
		
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
		JButton birdBtn1 = new JButton("Bird name 1");
		JButton birdBtn2 = new JButton("Bird name 2");
		JButton birdBtn3 = new JButton("Bird name 3");
		southButtonPanel.add(birdBtn1);
		southButtonPanel.add(birdBtn2);
		southButtonPanel.add(birdBtn3);

		//adding listeners to buttons
		birdBtn1.addActionListener(this);
		birdBtn2.addActionListener(this);
		birdBtn3.addActionListener(this);
		
		//Add images
		
		JLabel imageLabel = new JLabel(new ImageIcon());
		contain.add(imageLabel,BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		//Scanner scanner;
		try {
			Scanner scanner = new Scanner(new File("src/starter_birdlist.csv"));
			scanner.useDelimiter(",");
	        while(scanner.hasNext()){
	            System.out.print(scanner.next()+"|");
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				BirdIdGUI myFrame = new BirdIdGUI();
				myFrame.setVisible(true);
			}
		});

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