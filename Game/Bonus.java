import java.io.*;
import java.util.*;
import java.lang.Math;
public class Bonus extends GameCharacter{
    	private static ArrayList<String> bonusMessage = new ArrayList<String>(4);

    	public Bonus(int x, int y) {
        //super(x,y);
    	}
    
	public Question hit() {
		bonusMessage.add("You ate a worm! Enemies will be slower!");
		bonusMessage.add("");
		bonusMessage.add("");
		bonusMessage.add("");
		return new Question(bonusMessage);
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
