import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.imageio.*;

/**
The parent class of all the characters in the game except the player.

@author Maura Swift
*/
public class GameCharacter {
	BufferedImage leftImage;
	BufferedImage rightImage;
    int frameCount = 10;
    BufferedImage[] leftImageArray = new BufferedImage[frameCount];
    BufferedImage[] rightImageArray = new BufferedImage[frameCount];
    static int picNum = 0;
	int width;
	int height;
	int xLoc;
	int yLoc;
	int dir;
	int farLeft;
	int farRight;
	static ArrayList<int[]> enemyAtt = new ArrayList<int[]>(); 

	/**
	Constructs a GameCharacter with a given location and direction

	@param boardWidth the width of the board that the character is on
	@param xLoc the x-coordinate of the character
	@param yLoc the y-coordinate of the character
	@param direction the direction that the character will travel. Value of 1 or -1
	*/
	public GameCharacter(int boardWidth, int xLoc, int yLoc, int direction){
        	this.xLoc=xLoc;
        	this.yLoc=yLoc;
        	dir = direction;
	}//constructor


	/**
	Returns a Message object when the character is hit

	@return a Message object describing the result of the hit
	*/
	public Message hit(){
		return null;
	}//hit

	/**
	Returns the new x-coordinate of the character after it has moved

	@return the x-coordinate of the character after moving
	*/
	public void move(){
		int counter = 0;
		Iterator enemyAttIterator = enemyAtt.iterator();
		while (enemyAttIterator.hasNext()){
			enemyAtt.get(counter)[0] += enemyAtt.get(counter)[2]*5;
			if(enemyAtt.get(counter)[0] < farLeft){
				enemyAtt.get(counter)[0] = farRight;
			}
			else if(enemyAtt.get(counter)[0] > farRight){
				enemyAtt.get(counter)[0] = farLeft;
			}
			counter++;
			enemyAttIterator.next();
		}
	}//move

	/**
	Returns the x-coordinate of the character

	@return the x-coordinate of the character
	*/	
	public int getX(){
		return xLoc;
	}//getX

	/**
	Returns the y-coordinate of the character

	@return the y-coordinate of the character
	*/
	public int getY(){
		return yLoc;
	}//getY


	/**
	Assigns the given value to xLoc

	@param x the value to assign to xLoc
	*/
	public void setX(int x){
		xLoc = x;
	}//setX

	/**
	Assigns the given value to yLoc

	@param y the value to assign to yLoc
	*/
	public void setY(int y){
		yLoc = y;
	}//setY

	/**
	Assigns the given value to dir

	@param dir the value to assign to dir
	*/
	public void setDir(int dir){
		this.dir = dir;
	}//setDir

	/**
	Returns the ImageIcon associated with the character

	@return the ImageIcon for the character
	*/
	public BufferedImage getRightImage(){
		return rightImage;
	}//getImage
	
	public BufferedImage getLeftImage(){
		return leftImage;
	}//getImage
    
    public BufferedImage[] getRightImageArray(){
        return rightImageArray;
    }//getImage
    
    public BufferedImage[] getLeftImageArray(){
        return leftImageArray;
    }//getImage

	public ArrayList<int[]> getEnemyAtt(){
		return enemyAtt;
	}
    
    public void clearEnemyAtt(){
        ArrayList<int[]> tempArrayList = enemyAtt;
        enemyAtt.removeAll(tempArrayList);
    }
	/**
	Returns the width of the character image

	@return the width of the character image
	*/
	public int getImgWidth(){
		return width;
	}

	/**
	Returns the height of the character image

	@return the height of the character image
	*/
	public int getImgHeight(){
		return height;
	}

	/**
	Returns the x- and y-coordinates and direction of the character as a String

	@return a String representation of the character location and direction
	*/
	public String toString(){
		return "x: "+xLoc+" y: "+yLoc+" dir: "+dir;
	}//toString
	
    public BufferedImage createImage(String filename){ // takes in a direction!!
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(filename));
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
        // TODO: Change this method so you can load other orc animation bitmaps
    }
    
    public int getPicNum() {
        return picNum;
    }
    
    public int getFrameCount() {
        return frameCount;
    }
    
    public void increasePicNum() {
        picNum = (picNum + 1) % frameCount;
    }
    
}//GameCharacter

