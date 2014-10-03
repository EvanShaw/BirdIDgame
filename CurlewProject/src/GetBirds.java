//$$ TODO: Class needs commented -- one Javadoc /** */ comment above each class & method.

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetBirds {
	public static void main(String[] args) {
		//reading the bird data from the source file and storing into an array of Bird objects
		final ArrayList<Bird> birds = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("src/stage2_materials/full_birdlist.csv"));
			scanner.nextLine();
			String[] splitter = {};
	        while(scanner.hasNextLine()){
	        	splitter = scanner.nextLine().split(",");
	        	String birdName = splitter[0];
	        	if (!splitter[3].equals("")) {
	        		 birdName += " (" + splitter[3] + ")";
	        	}
		        birds.add(new Bird(birdName, "stage2_materials/photos/" + splitter[2]));
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BirdIdGUI myFrame = new BirdIdGUI(birds);
				myFrame.setVisible(true);
		}
		});
	}
}