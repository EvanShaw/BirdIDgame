/**
 * A Difficulty object that determines the number of buttons and pictures
 * a given user will be provided for a given session 
 */
public class Difficulty {
	int numOfButtons;
	int numOfPictures;
	String difficulty;
	
	/**
	 * The number of buttons and number of pictures is determined by what difficulty the user sends in
	 * @param difficulty
	 */
	public Difficulty(String difficulty) {
		this.difficulty = difficulty;
		if (difficulty.equalsIgnoreCase("Easy")) {
			numOfButtons = 3;
			numOfPictures = 20;
		} else if (difficulty.equalsIgnoreCase("Hard")) {
			numOfButtons = 5; 
			numOfPictures = 40;
		} else {
			numOfButtons = 3;
			numOfPictures = 20;
		}
		
	}
	public int getNumButtons() {
		return numOfButtons;
	}
	public int getNumPictures() {
		return numOfPictures;
	}
}
