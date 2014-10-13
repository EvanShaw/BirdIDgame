/**
 * A score object that keeps track of a users score
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
	public void incrementScore() {
		numCorrectAnswers++;
	}
	public void incrementAnsweredQuestions() {
		numAnsweredQuestions++;
	}
	public void addToTotalScore(int sessionScore, int sessionNumQuestions) {
		numCorrectAnswers += sessionScore;
		numQuestions += sessionNumQuestions;
		totalScore = numCorrectAnswers + "/" + numQuestions;
	}
	public int getNumAnsweredQuestions() {
		return numAnsweredQuestions;
	}
	public int getNumCorrectAnswers() {
		return numCorrectAnswers;
	}
	public int getNumQuestions() {
		return numQuestions;
	}
	public String getTotalScore() {
		return totalScore;
	}
}
