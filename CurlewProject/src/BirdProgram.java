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
				StartScreen startScreen = new StartScreen();
				startScreen.setVisible(true);
		}
		});
	}
}