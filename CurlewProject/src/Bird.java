public class Bird {
	private String imagePath;
	private String birdName;
	
	public Bird(String imagePath, String birdName) {
		this.imagePath = imagePath;
		this.birdName = birdName;
	}
	
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