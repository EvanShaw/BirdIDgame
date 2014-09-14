public class Bird {
	String imagePath;
	String birdName;
	
	public Bird(String imagePath, String birdName) {
		this.imagePath = imagePath;
		this.birdName = birdName;
	}
	
	public boolean isMatching(String birdChosen) {
		return birdName.equals(birdChosen);
	}
}