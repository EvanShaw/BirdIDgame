import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
				boolean isPreviousUser = false;
				Scanner scanner = new Scanner(new File("userInfo.csv"));
				String[] splitStrings = {};
				while (scanner.hasNextLine()) {
					splitStrings = scanner.nextLine().split(",");
					if (splitStrings[0].equals(userName)) {
						isPreviousUser = true;
						String oldScore = splitStrings[1];
						
						String[] splitOldScore = oldScore.split("/");
						String totalCorrect = splitOldScore[0];
						String totalQuestions = splitOldScore[1];
						int intTotalCorrect = Integer.parseInt(totalCorrect);
						int intTotalQuestions = Integer.parseInt(totalQuestions);
						
						userScore = new Score(intTotalCorrect, intTotalQuestions);
					}
				}
				scanner.close();
				if (isPreviousUser == false) {
					addUser(userInfo);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				userInfo.createNewFile();
				FileWriter tempWriter = new FileWriter(userInfo, true);
				tempWriter.write("User,Score\n");
				tempWriter.flush();
				tempWriter.close();
				addUser(userInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addUser(File userInfo) {
		try {
			FileWriter writer = new FileWriter(userInfo, true);
			userScore = new Score(0, 0);
			writer.write(userName + "," + userScore.getTotalScore() + "\n");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeChangesToFile(Score newScore) {
		// Construct the new file that will later be renamed to the original
		// filename.

		File userInfo = new File("userInfo.csv");
		File tempFile = new File(userInfo.getAbsolutePath() + ".tmp");

		try {
			
			FileWriter writer = new FileWriter(tempFile);
			Scanner scanner = new Scanner(userInfo);

			// Read from the original file and write to the new one. If content matches
			// data to be updated, update data before writing to the new file.
			String[] splitStrings = {};
			while (scanner.hasNextLine()) {
				splitStrings = scanner.nextLine().split(",");
				if (splitStrings[0].equals(userName)) {
					writer.write(userName + "," + newScore.getTotalScore() + "\n"); 
				} else {
					writer.write(splitStrings[0] + "," + splitStrings[1] + "\n");
				}
			}
			
			
			scanner.close();
			writer.flush();
			writer.close();

			// Rename the new file to the name of the original file.
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
