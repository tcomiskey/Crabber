import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;

public class Bonus extends GameCharacter{
	// Bonus character constructor 
    	public Bonus(int boardWidth, int xLoc, int yLoc, int direction) {
    		// need to find an image and use it here
        	super(boardWidth, xLoc, yLoc, direction);
    	}
    	// Hit method that is called when the player collides with a bonus
	public Message hit() {
		return new BonusMessage();
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
	
	public ImageIcon getLeftImage(){
		return leftImage;
	}//getImage

    public ImageIcon getRightImage(){
        return rightImage;
    }//getImage
}
