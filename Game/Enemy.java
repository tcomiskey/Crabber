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
	private Scanner fileScanner;
	// made static so that all enemies will have the same question index
	private static int questionIndex;
	// all enemies will have the same list of questions
	private static ArrayList<Question> questions;
	
	// simple constructor used for all other enemies besides the first initialized
	
	/**
	* Simple constructor that does not instantiate question array.
	* @param boardWidth is the size of the game board
	* @param xLoc is the x location of the top left of the enemy image
	* @param yLoc is the y location of the top left of the enemy image
	* @param direction is which direction this enemy will move in
	*/
	private Enemy(int boardWidth, int xLoc, int yLoc, int direction){
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
