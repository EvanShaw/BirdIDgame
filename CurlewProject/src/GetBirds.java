import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GetBirds {
	public static void main(String[] args) {
		ArrayList<Bird> birds = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("src/starter_birdlist.csv"));
			scanner.useDelimiter(",");
			scanner.nextLine();
	        while(scanner.hasNextLine()){
	            birds.add(new Bird(scanner.next(), scanner.next()));
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(birds.get(1).birdName + birds.get(1).imagePath);
		
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				BirdIdGUI myFrame = new BirdIdGUI();
				myFrame.setVisible(true);
			}
		});

	}
}
