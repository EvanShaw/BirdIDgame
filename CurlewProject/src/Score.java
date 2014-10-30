/**
 * A score object that keeps track of a user's score
 */
public class Score {
	private int numCorrectAnswers;
	private int numQuestions;
	private int numAnsweredQuestions;
	private String totalScore;
	
	public Score(int numCorrectAnswers, int numQuestions) {
		this.numCorrectAnswers = numCorrectAnswers;
		this.numQuestions = numQuestions;
		totalScore = numCorrectAnswers + "/" + numQuestions;
	}
	/**
	 * Increases score when correct answer is selected
	 */
	public void incrementScore() {
		numCorrectAnswers++;
	}
	/**
	 * Increases total answered after each question
	 */
	public void incrementAnsweredQuestions() {
		numAnsweredQuestions++;
	}
	/**
	 * Adds game results to total score for user
	 * @param sessionScore, sessionNumQuestions
	 */
	public void addToTotalScore(int sessionScore, int sessionNumQuestions) {
		numCorrectAnswers += sessionScore;
		numQuestions += sessionNumQuestions;
		totalScore = numCorrectAnswers + "/" + numQuestions;
	}
	/**
	 * Allows access to numAnsweredQuestions field
	 */
	public int getNumAnsweredQuestions() {
		return numAnsweredQuestions;
	}
	/**
	 * Allows access to numCorrectAnswers field
	 * @return
	 */
	public int getNumCorrectAnswers() {
		return numCorrectAnswers;
	}
	/**
	 * Allows access to numQuestions field
	 */
	public int getNumQuestions() {
		return numQuestions;
	}
	/**
	 * Allows access to totalScore field
	 */
	public String getTotalScore() {
		return totalScore;
	}
}
