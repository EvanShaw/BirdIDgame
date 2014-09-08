

	
	import javax.swing.*;

import java.awt.*;
	


	public class BirdIdGUI extends JFrame {
		
		public BirdIdGUI()
		{
			super("BirdIdGUI");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(500, 500);
			
			Container contain = this.getContentPane();

		contain.add(new JLabel("Bird I dentification program"), BorderLayout.NORTH);
		
			
			JButton birdBtn1 = new JButton("Bird name 1");
			contain.add(birdBtn1, BorderLayout.SOUTH);
			JButton birdBtn2 = new JButton("Bird name 2");
			JButton birdBtn3 = new JButton("Bird name 3");
			contain.add(birdBtn2, BorderLayout.SOUTH);
			contain.add(birdBtn3, BorderLayout.SOUTH);
			
			
		}

		public static void main(String[] args) {
			
			EventQueue.invokeLater(new Runnable()
			{
				
				public void run() {
					BirdIdGUI myFrame = new BirdIdGUI();
					myFrame.setVisible(true);								
				}
			});
			
		}

	}


