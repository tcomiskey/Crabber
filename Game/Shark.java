import javax.swing.*;
import java.util.*;
import java.io.*;

public class Shark extends Enemy{
	//boolean to keep track if a shark object has been made
	private static boolean noSharks = true;
	// arraylists for all xlocations and ylocations of sharks even though only one real object
	
	private Shark(int boardWidth, int xLocation, int yLocation, int direction){
		super(boardWidth, xLocation, yLocation, direction);
        Scanner fileScanner;
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
        
		if (direction == -1) {
	       	 	leftImage = new ImageIcon("images/shark.png");
		}
		else {
		    	rightImage = new ImageIcon("images/reverseShark.png");
		}
        width = leftImage.getIconWidth();
		height = rightImage.getIconHeight();
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width; 
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = width;
		xydir[4] = height;
		enemyAtt.add(xydir);
		
		
	}
	//Really only need one shark object and just a series of images to be displayed
	public static Shark sharkFactory(int boardWidth, int xLocation, int yLocation, int direction){
		if (noSharks){
            noSharks = false;
			return new Shark(boardWidth, xLocation, yLocation, direction);
		}
		else{
			addShark(xLocation, yLocation, direction, width, height);
			return null;
		}
		
	}
	private static void addShark(int xLocation, int yLocation, int direction, int sharkWidth, int sharkHeight){
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = sharkWidth;
		xydir[4] = sharkHeight;
		enemiesAtt.add(xydir);
		
	}
	
	public boolean getNoSharks(){
		return noSharks;
	}

	public String toString(){
		return "Shark located at x = " + xLoc + " y = " + yLoc;
	}
	
}	
