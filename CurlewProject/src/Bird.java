/**
 * A Bird object, with a name and associated image along with accessor methods.
 */
public class Bird {
	private String imagePath;
	private String birdName;
	
	public Bird(String birdName, String imagePath) {
		this.birdName = birdName;
		this.imagePath = imagePath;
	}
	
	//$$ TODO: this method seems reasonable, but what about including a .equals(Object) method
	//$$ that compares Bird objects with each other, based on their name?  (Debatable... but consider whether this would be useful or not.)
	/**
	 * Checks whether the bird name sent in is the same as the current Bird object.
	 * @param birdChosen
	 * @return result of String equals method on the two Strings as boolean
	 */
	public boolean isMatching(String birdChosen) {
		return birdName.equals(birdChosen);
	}

	/**
	 * Allows external access of the Bird's name.
	 * @return birdName
	 */
	public String getBirdName(){
		return birdName;
	}

	/**
	 * Allows external access of the Bird's image path.
	 * @return imagePath
	 */
	public String getImagePath(){
		return imagePath;
	}
}