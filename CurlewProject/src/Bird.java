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
	
	/**
	 * Compares bird object with obj
	 * @param obj
	 * @return boolean
	 */
	
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} 
		if (obj == null) {
			return false;
		}
		if (this.getClass() == obj.getClass()) {
			Bird other = (Bird) obj;
			return this.imagePath.equals(other.imagePath) && this.birdName.equals(other.birdName); 
		}
		return false;
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