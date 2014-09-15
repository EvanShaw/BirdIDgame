public class Bird {
	private String imagePath;
	private String birdName;
	
	public Bird(String birdName, String imagePath) {
		this.birdName = birdName;
		this.imagePath = imagePath;
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