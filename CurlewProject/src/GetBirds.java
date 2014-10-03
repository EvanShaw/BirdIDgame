//$$ TODO: Class needs commented -- one Javadoc /** */ comment above each class & method.

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//$$ TODO: As I noted before, "GetBirds" is a verb-like name.  Class names should be noun-like.
//$$     An improvement would be "BirdGetter"... but even then it's not clear what the class really *represents*
//$$     or models.  If it's the object in charge of keeping track of all the birds, it would be better to call it
//$$     BirdLibrary or BirdCollection or BirdList ...
//$$
//$$ TODO: More generally, right now this code is in charge of both launching the main program *and* 
//$$      reading the data from the spreadsheet & creating the birds.  It would be better to separate some
//$$      of these responsibilities into different files.

public class GetBirds {
	public static void main(String[] args) {
		//reading the bird data from the source file and storing into an array of Bird objects
		final ArrayList<Bird> birds = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("src/stage2_materials/full_birdlist.csv"));
			scanner.nextLine();
			//$$ TODO: The String[] array is not the "splitter".  It contains the result of the splitting.
			//$$     e.g., A better intention-revealing name would be "rowItems".
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