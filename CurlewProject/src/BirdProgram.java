import java.awt.EventQueue;


public class BirdProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BirdLibrary library = new BirdLibrary();
				BirdIdGUI myFrame = new BirdIdGUI(library.getLibrary());
				myFrame.setVisible(true);
		}
		});
	}

}
