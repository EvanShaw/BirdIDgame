/**
 * A score object that keeps track of a users score
 */
public class Score {
	private int score;
	
	public Score() {
		score = 0;
	}
	public void incrementScore() {
		score++;
	}
	public int getScore() {
		return score;
	}
}
