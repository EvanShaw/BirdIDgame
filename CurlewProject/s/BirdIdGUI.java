

	import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

	public class BirdIdGUI extends JFrame {
		
		public BirdIdGUI()
		{
			super("Image Shower!");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(600, 500);
			
			Container pane = this.getContentPane();

			pane.add(new JLabel("Are you happy?"), BorderLayout.NORTH);
			
			JButton happyBtn = new JButton("I'm happy!");
			pane.add(happyBtn, BorderLayout.SOUTH);
			
			try {
				
				BufferedImage birdImg = ImageIO.read(this.getClass().getResource("images/BlueFootedBooby.png"));
				JLabel imageLabel = new JLabel(new ImageIcon(birdImg));
				pane.add(imageLabel,BorderLayout.CENTER);
			}
			catch (Exception ex)
			{
				JLabel errorLabel = new JLabel("Error while loading image");
				pane.add(errorLabel,BorderLayout.CENTER);
			}
			
			
		}

		public static void main(String[] args) {
			
			ArrayList<String> test = new ArrayList<String>();
			for (int i = 0; i <= 10; i++) {
				test.add("poop");
			}
			String target = "poop";
			int count = 0;
			
			
			for (int i = 0; i < test.size(); i++) {
				if (test.get(i).equals(target)) {
					test.remove(i);
					break;
				}
			}
				
			System.out.println(test.toString());
			
			EventQueue.invokeLater(new Runnable()
			{
				@Override
				public void run() {
					BirdIdGUI myFrame = new BirdIdGUI();
					myFrame.setVisible(true);								
				}
			});
			
		}

	}
