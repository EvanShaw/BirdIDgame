import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class User {
	private String userName;
	private String totalScore;
	private String recommendedDifficulty;
	
	public User() {
	}
	public User(String userName) {
		this.userName = userName;
		
		try {
			Scanner scanner = new Scanner(new File("src/Users"));
			scanner.nextLine();
			String[] splitStrings = {};
	        while(scanner.hasNextLine()) {
	        	splitStrings = scanner.nextLine().split(",");
	        	
	        	userName = splitStrings[0];
	        	
	        	System.out.println(userName);
	        	
	        	totalScore = splitStrings[1];
	        	
	        	System.out.println(totalScore);
	        	
	        	recommendedDifficulty = splitStrings[2];
	        	System.out.println(recommendedDifficulty);
	        }
	        scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void createNewUser(String newUser) {
		
	}
	public String getUserName() {
		return userName;
	}
	public String getUserScore() {
		return totalScore;
	}
	public String getDifficulty() {
		return recommendedDifficulty;
	}
}
