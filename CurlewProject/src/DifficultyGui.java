import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class DifficultyGui extends JFrame implements ActionListener {

	/**
	 * This Class creates a Gui for the user to choose their difficulty for the quiz.
	 */
	private JButton easy;
	private JButton hard;
	private Difficulty difficult;
	
	public DifficultyGui(){
		super("DifficultyGui");	
		this.setSize(200,100);
		this.setLayout(new BorderLayout());
		easy=new JButton("Easy");
		hard=new JButton("Hard");
		difficult=new Difficulty();
		this.add(new JLabel("Choose Difficulty:"),BorderLayout.NORTH);
		JPanel south= new JPanel(new FlowLayout());
		
		south.add(easy);
		south.add(hard);
		this.add(south,BorderLayout.SOUTH);
		
	}
	//public static void main(String[] args) {
		//DifficultyGui gui= new DifficultyGui();
		//gui.setVisible(true);
	//}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
				difficult.setDifficulty(sourceButton.getText());
			
		
			BirdLibrary library = new BirdLibrary();
				BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(),difficult);
				myFrame.setVisible(true);
		
	}
	
	
	
	

}
