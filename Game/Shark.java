import java.swing.*;

public class Shark extends Enemy{
	private ImageIcon image;
	
	public Shark(int boardWidth, int randomIndex){
		super(boardWidth, randomIndex);
	}
	public Shark(int boardWidth){
		super(boardWidth);
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