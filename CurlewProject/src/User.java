import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class User {
	private String userName;
	private String recommendedDifficulty;
	private Score userScore;
	
	public User() {
	}
	public void creatNewUser(String userName) {
		
	}
	public void getUserInfo(String userName) {
		
		try {
			Scanner scanner = new Scanner(new File("src/Users"));
			scanner.nextLine();
			String[] splitStrings = {};
	        while(scanner.hasNextLine()) {
	        	splitStrings = scanner.nextLine().split(",");
	        	
	        	//userName = splitStrings[0];
	        	userName = "Evan";
	        	
	        	System.out.println(userName);
	        	
	        	//oldUserScore = new Score((Integer) splitStrings[1], (Integer)splitStrings[2]);
	        	userScore = new Score(80, 115);
	        	
	        	System.out.println(userScore.getTotalScore());
	        	
	        	//recommendedDifficulty = splitStrings[2];
	        	recommendedDifficulty = "easy";
	        	
	        	System.out.println(recommendedDifficulty);
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void writeChangesToFile(String userName, Score score) {
		
	}
	public String getUserName() {
		return userName;
	}
	public Score getUserScore() {
		return userScore;
	}
	public String getDifficulty() {
		return recommendedDifficulty;
	}
}
