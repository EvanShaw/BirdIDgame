/**
 * A Difficulty object that determines the number of buttons and pictures
 * a given user will be provided for a given session 
 */
public class Difficulty {
	private int numOfButtons;
	private int numOfQuestions;
	private String difficulty;
	
	/**
	 * Initialize the difficulty to easy by default
	 */
	public Difficulty() {
		this.difficulty = "easy";
		numOfButtons = 3;
		numOfQuestions = 5;
	}
	
	/**
	 * Set the difficulty based on user choice; edit fields accordingly
	 * @param difficulty
	 */
	public void setDifficulty(String difficulty) {
		if (difficulty.equalsIgnoreCase("easy")) {
			difficulty = "Easy";
			numOfButtons = 3;
			numOfQuestions = 5;
		} else if (difficulty.equalsIgnoreCase("medium")) {
			difficulty = "Medium";
			numOfButtons = 4;
			numOfQuestions = 15;
		} else if (difficulty.equalsIgnoreCase("hard")) {
			difficulty = "Hard";
			numOfButtons = 5;
			numOfQuestions = 25;
		}
	}
	
	/**
	 * Allows access to difficulty field
	 */
	public String getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Allows access to numOfButtons field
	 */
	public int getNumButtons() {
		return numOfButtons;
	}
	
	/**
	 * Allows access to numOfQuestions field
	 */
	public int getNumQuestions() {
		return numOfQuestions;
	}
}
