import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;

/**
* An Enemy object is a subclass of GameCharacter.
* All obstacles that should be avoided are subclasses of Enemy
* @author Tom Comiskey
*/
public class Enemy extends GameCharacter{
        private ImageIcon image;
	
	private Scanner fileScanner;
	// made static so that all enemies will have the same question index
	private static int questionIndex;
	// all enemies will have the same list of questions
	private static ArrayList<Question> questions;	
	
	//this constructor only called once to set the static variables for all other enemies
	/**
	* Enemy constructor that calls the GameCharacter contructor.
	* (Will not in the future but currently) Reads in a filename to
	* to create the list of questions that will be asked during gameplay
	* @param boardWidth is the size of the game board
	* @param xLoc is the x location of the top left of the enemy image
	* @param yLoc is the y location of the top left of the enemy image
	* @param direction is which direction this enemy will move in
	* @param fileName is the txt file that contains the question list	
	*/
	public Enemy(int boardWidth, int xLoc, int yLoc, int direction, String fileName){
		// calls the GameCharacter constructor
		super(boardWidth, xLoc, yLoc, direction);
		// makes an arraylist to hold question objects
		questions = new ArrayList<Question>();
		// saves the file that hols the quesitons
		File qAndAFile = new File(fileName);
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
	
	// simple constructor used for all other enemies besides the first initialized
	
	/**
	* Simple constructor that does not instantiate question array.
	* @param boardWidth is the size of the game board
	* @param xLoc is the x location of the top left of the enemy image
	* @param yLoc is the y location of the top left of the enemy image
	* @param direction is which direction this enemy will move in
	*/
	public Enemy(int boardWidth, int xLoc, int yLoc, int direction){
		super(boardWidth, xLoc, yLoc, direction);
	}
	
	/**
	* Method that is called if Player collides with Enemy
	* Picks a question to ask the Player
	* @return Message object that contains a question that will be asked.
	*/
	public Message hit(){
        	questionIndex++;
        	if(questionIndex == questions.size()){
        		questionIndex = 0;
        	}
		return questions.get(questionIndex);
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
		return "Enemy located at x = " + xLoc + "y = " + yLoc;
	}

}
