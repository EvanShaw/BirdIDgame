//$$ TODO: Class needs commented -- one Javadoc /** */ comment above each class & method.

public class Bird {
	private String imagePath;
	private String birdName;
	
	public Bird(String birdName, String imagePath) {
		this.birdName = birdName;
		this.imagePath = imagePath;
	}
	
	//$$ TODO: this method seems reasonable, but what about including a .equals(Object) method
	//$$ that compares Bird objects with each other, based on their name?  (Debatable... but consider whether this would be useful or not.)
	public boolean isMatching(String birdChosen) {
		return birdName.equals(birdChosen);
	}
	public String getBirdName(){
		return birdName;
	}
	public String getImagePath(){
		return imagePath;
	}
}