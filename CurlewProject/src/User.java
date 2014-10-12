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
	public void creatNewUser(String userName) {
		
	}
	public void getUserInfo(String userName) {
		/*
		 * FileWriter writer = new FileWriter("userInfo.csv"); writer.write("");
		 * writer.close(); Scanner scanner = new Scanner(userInfo);
		 */
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
		    System.out.println(splitOldScore[0]);
		    System.out.println(splitOldScore[1]);
		    
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
