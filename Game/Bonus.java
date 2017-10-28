import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;
public class Bonus extends GameCharacter{

    	public Bonus(int boardWidth, int xLoc, int yLoc) {
        	super(boardWidth, xLoc, yLoc);
    	}
    
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
	
	public ImageIcon getImage(){
		return image;
	}//getImage

    
}
