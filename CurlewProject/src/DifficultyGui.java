import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DifficultyGui extends JFrame implements ActionListener {

	/**
	 * This Class creates a Gui for the user to choose their difficulty for the
	 * quiz.
	 */
	private JButton easy;
	private JButton hard;
	private Difficulty difficulty;

	public DifficultyGui() {
		super("Choose Diffiuclty");
		this.setSize(200, 100);

		this.setLayout(new BorderLayout());

		easy = new JButton("Easy");
		hard = new JButton("Hard");
		difficulty = new Difficulty();
		this.add(new JLabel("Choose Difficulty:"), BorderLayout.NORTH);
		JPanel south = new JPanel(new FlowLayout());

		easy = new JButton("Easy");
		hard = new JButton("Hard");
		difficulty = new Difficulty();
		this.add(new JLabel("Choose Difficulty:"), BorderLayout.NORTH);

		south.add(easy);
		south.add(hard);
		easy.addActionListener(this);
		hard.addActionListener(this);
		
		this.add(south, BorderLayout.SOUTH);

	}



	@Override
	public void actionPerformed(ActionEvent event) {
		JButton sourceButton = (JButton) event.getSource();
		difficulty.setDifficulty(sourceButton.getText());

		BirdLibrary library = new BirdLibrary();
		BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(), difficulty);
		myFrame.setVisible(true);


	}

}
