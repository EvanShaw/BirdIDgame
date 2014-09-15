//import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetBirds {
	public static void main(String[] args) {
		ArrayList<Bird> birds = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("src/starter_birdlist.csv"));
			scanner.nextLine();
			String[] splitter = {};
	        while(scanner.hasNextLine()){
	        	splitter = scanner.nextLine().split(",");
		        birds.add(new Bird(splitter[0], splitter[1]));
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * Will delete these lines. It's just a test. I called the 6th index at the end
		 * so you'd be able to see how the exception throws properly too, since the file
		 * ends at 6 birds, index 5.
		System.out.println(birds.get(0).getBirdName() + " " + birds.get(0).getImagePath());
		System.out.println(birds.get(1).getBirdName() + " " + birds.get(1).getImagePath());
		System.out.println(birds.get(2).getBirdName() + " " + birds.get(2).getImagePath());
		System.out.println(birds.get(3).getBirdName() + " " + birds.get(3).getImagePath());
		System.out.println(birds.get(4).getBirdName() + " " + birds.get(4).getImagePath());
		System.out.println(birds.get(5).getBirdName() + " " + birds.get(5).getImagePath());
		System.out.println(birds.get(6).getBirdName() + " " + birds.get(6).getImagePath());
		 */
		
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
				BirdIdGUI myFrame = new BirdIdGUI(birds);
				myFrame.setVisible(true);
		//	}
		//});
	}
}