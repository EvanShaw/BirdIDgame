import java.awt.EventQueue;

/**
 * The class that runs the program.
 */
public class BirdProgram {

	/**
	 * The main method that is run to create the necessary objects & GUI.
	 * @param args - no other variables are used directly by the method
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BirdLibrary library = new BirdLibrary();
				
				/* TODO Send in easy as default difficulty at the moment. Introduction of users and a welcome screen
				   should remedy this. */
				Difficulty chosenDifficulty = new Difficulty("easy");
				BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary(), chosenDifficulty);
				myFrame.setVisible(true);
		}
		});
	}
}