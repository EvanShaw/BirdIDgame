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
	public String getDifficulty() {
		return difficulty;
	}
	public int getNumButtons() {
		return numOfButtons;
	}
	public int getNumQuestions() {
		return numOfQuestions;
	}
}
