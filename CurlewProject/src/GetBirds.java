import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GetBirds {
	public static void main(String[] args) {
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
}
