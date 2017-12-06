import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Shark extends Enemy{
	//boolean to keep track if a shark object has been made
	private static boolean noSharks = true;
    static int sharkWidth;
    static int sharkHeight;
    Scanner fileScanner;
    int totalWidth;
	
    /**
     Shark constructor creates the arraylist of questions for all of the enemies, and theOneAndOnlyShark object that holds all the other shark information.
     
     @author Tom Comiskey and Erin Hitchner
     @param boardWidth the width of the game screen
     @param xLocation the x coordinate for upper left pixel of the image
     @param yLocation the y coordinate for upper left pixel of the image
     @param direction the direction the object will move on the screen, decides image direction
     */
	private Shark(int boardWidth, int xLocation, int yLocation, int direction){
		super(boardWidth, xLocation, yLocation, direction);
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
        
        //THIS IS THE BUFFEREDIMAGE STUFF
        frameCount = 4;
        leftImageArray = new BufferedImage[frameCount];
        rightImageArray = new BufferedImage[frameCount];
        leftImage = createImage("images/reverseShark.png");
        rightImage = createImage("images/shark.png");
        width = leftImage.getWidth() / frameCount;
        height = rightImage.getHeight();
        for(int i = 0; i < frameCount; i++) {
            leftImageArray[i] = leftImage.getSubimage(width*i, 0, width, height);
            rightImageArray[i] = rightImage.getSubimage(width*i, 0, width, height);
        }
        //leftImage = new ImageIcon("images/shark.png");
        //rightImage = new ImageIcon("images/reverseShark.png");
        sharkWidth = width;
        sharkHeight = height;
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width;
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
        totalWidth = farRight - farLeft;
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = width;
		xydir[4] = height;
		enemyAtt.add(xydir);
		
		
	}
	//Really only need one shark object and just a series of images to be displayed
    /**
     Static factory method 
     */
	public static Shark sharkFactory(int boardWidth, int xLocation, int yLocation, int direction){
		if (noSharks){
            noSharks = false;
			return new Shark(boardWidth, xLocation, yLocation, direction);
		}
		else{
			addShark(xLocation, yLocation, direction, sharkWidth, sharkHeight);
			return null;
		}
		
	}
	private static void addShark(int xLocation, int yLocation, int direction, int currentWidth, int currentHeight){
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = currentWidth;
		xydir[4] = currentHeight;
		enemyAtt.add(xydir);
		
	}
	
	public boolean getNoSharks(){
		return noSharks;
	}
    
    public void setNoSharks(boolean sharkBoolean) {
        noSharks = sharkBoolean;
    }

	public String toString(){
		return "Shark located at x = " + xLoc + " y = " + yLoc;
	}
    
    public int getTotalWidth() {
        return totalWidth;
    }
	
}	
