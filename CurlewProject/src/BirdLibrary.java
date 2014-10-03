//$$ TODO: Class needs commented -- one Javadoc /** */ comment above each class & method.

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads the bird data from the source files and stores it into an array of Bird objects.
 */
public class BirdLibrary {

	final ArrayList<Bird> birds;
	
	public BirdLibrary() {
		birds = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("src/stage2_materials/full_birdlist.csv"));
			scanner.nextLine();
			String[] splitStrings = {};
	        while(scanner.hasNextLine()){
	        	splitStrings = scanner.nextLine().split(",");
	        	String birdName = splitStrings[0];
	        	if (!splitStrings[3].equals("")) {
	        		 birdName += " (" + splitStrings[3] + ")";
	        	}
		        birds.add(new Bird(birdName, "stage2_materials/photos/" + splitStrings[2]));
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Bird> getLibrary() {
		return birds;
	}
}