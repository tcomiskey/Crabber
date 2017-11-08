import javax.swing.*;

public class Shark extends Enemy{
	private ImageIcon image;
	private boolean sharkMade;
	
	private Shark(int boardWidth, int xLoc, int yLoc, int direction){
		super(boardWidth, xLoc, yLoc, direction);
		if (direction == -1) {
	       	 	image = new ImageIcon("images/shark.png");
		}
		else {
		    image = new ImageIcon("images/reverseShark.png");
		}
	       	 	width = image.getIconWidth();
			height = image.getIconHeight();
			//Sets bounds on how far off screen a character can go before it loops around
			int usableWidth = (boardWidth/width) + 3*width; //335
			farLeft = -(usableWidth)/2;
			farRight = boardWidth+(usableWidth)/2;
	}
		
	public void factory(int boardWidth, int xLoc, int yLoc, int direction){
		// makes an arraylist to hold question objects
		questions = new ArrayList<Question>();
		// saves the file that hols the quesitons
		File qAndAFile = new File("QuestionAndAnswerList.txt");
		if (qAndAFile.exists()){
			try{
			fileScanner = new Scanner(qAndAFile);
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			// while there is still information in the file
			while(fileScanner.hasNext()){
				// creates a temporary arraylist to hold the question and three answers
				ArrayList <String> questionSpecifics = new ArrayList<String>(4);
				// adds the question and the three answers to the questionSpecifics list
				for (int newLineCount = 0; newLineCount<4; newLineCount++){
					questionSpecifics.add(fileScanner.nextLine());
				}
				// adds a new Question object that contains the question and answers
				questions.add(new Question(questionSpecifics));
			}
		}
		// randomly pick a question from the list to ask first to avoid repeats with repeat playthroughs
		questionIndex = (int) Math.floor(Math.random()*questions.size());
		
	}
	
	
	public int getX(){
		return xLoc;
	}//getX
	
	public int getY(){
		return yLoc;
	}//getY
	
	public void setX(int x){
		xLoc = x;
	}//setX
	
	public void setY(int y){
		yLoc = y;
	}//setY
	
	public void setDir(int dir){
		this.dir = dir;
	}//setDir
	
	public ImageIcon getImage(){
		return image;
	}//getImage
	public String toString(){
		return "Shark located at x = " + xLoc + " y = " + yLoc;
	}
}	
