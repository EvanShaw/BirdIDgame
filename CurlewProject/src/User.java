import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;

public class User {
	private String userName;
	private String recommendedDifficulty;
	private Score userScore;
	
	public User(String userName) {
		this.userName = userName;
		File userInfo = new File("userInfo.csv");
		if (userInfo.isFile()) {
			try {
				Scanner scanner = new Scanner(new File("userInfo.csv"));
				if (isPreviousUser(scanner)) {
				} else {
					addUser(userInfo);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				userInfo.createNewFile();
				addUser(userInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean isPreviousUser(Scanner scanner) {
		String[] splitStrings = {};
		boolean userFound = false;
		while (scanner.hasNextLine()) {
			splitStrings = scanner.nextLine().split(",");
			userFound = userName.equals(splitStrings[0]);
		}
		return userFound;
	}
	public void addUser(File userInfo) {
		try {
			FileWriter writer = new FileWriter(userInfo, true);
			writer.append(userName + ", 0/0\n");
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void writeChangesToFile(String userName, Score sessionScore) {
		//Construct the new file that will later be renamed to the original filename.
	      
		File userInfo = new File("userInfo.csv");
		File tempFile = new File(userInfo.getAbsolutePath() + ".tmp");

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		    Scanner scanner = new Scanner(userInfo);
		    
		    //Read from the original file and write to the new
		    //unless content matches data to be removed.
		    String oldScore = "";
			String[] splitStrings = {};
		    while (scanner.hasNextLine()) {
		    	splitStrings = scanner.nextLine().split(",");
		    	
		        if (!splitStrings[0].equals(userName)) {
		        	pw.println(scanner.nextLine());
			        pw.flush();
		        } else if (splitStrings[0].equals(userName)) {
		        	oldScore = splitStrings[1];
		        }
		    }
		    
		    String[] splitOldScore = oldScore.split("/");
		    
		    String totalCorrect = splitOldScore[0];
		    String totalQuestions = splitOldScore[1];
		    int intTotalCorrect = Integer.parseInt(totalCorrect);
		    int intTotalQuestions = Integer.parseInt(totalQuestions);
		    System.out.println(totalCorrect);
		    System.out.println(totalQuestions);
		    
		    int correctAnswers = sessionScore.getNumCorrectAnswers();
		    int numQuestions = sessionScore.getNumQuestions();
		    System.out.println(correctAnswers);
		    System.out.println(numQuestions);
		    
		    Score newScore = new Score(correctAnswers + intTotalCorrect, numQuestions + intTotalQuestions);
		    
		   // pw.printLn(userName + ", " + newScore.getTotalScore());
		    
		    scanner.close();
		    pw.close();

		    //Rename the new file to the filename the original file had.
		    if (!tempFile.renameTo(userInfo)) {
		    	System.out.println("Could not rename file");
		    } 
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
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
