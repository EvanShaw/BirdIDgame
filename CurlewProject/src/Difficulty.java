/**
 * A Difficulty object that determines the number of buttons and pictures
 * a given user will be provided for a given session 
 */
public class Difficulty {
	private int numOfButtons;
	private int numOfPictures;
	private String difficulty;
	
	/**
	 * Initialize the difficulty to easy by default
	 */
	public Difficulty() {
		this.difficulty = "easy";
		numOfButtons = 3;
		numOfPictures = 20;
		
	}
	public void setDifficulty(String difficulty) {
		if (difficulty.equalsIgnoreCase("easy")) {
			difficulty = "Easy";
			numOfButtons = 3;
			numOfPictures = 20;
		} else if (difficulty.equalsIgnoreCase("hard")) {
			difficulty = "Hard";
			numOfButtons = 5;
			numOfPictures = 40;
		}
	}
	public String getDifficulty() {
		return difficulty;
	}
	public int getNumButtons() {
		return numOfButtons;
	}
	public int getNumPictures() {
		return numOfPictures;
	}
}
