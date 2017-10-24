import javax.swing.*;


public class Character {
	ImageIcon image;
	int width;
	int height;
	int xLoc;
	int yLoc;
	int dir;
	
	public Character(){
		image = new ImageIcon("brown rectangle.png");
		width = image.getIconWidth();
		height = image.getIconHeight();
	}//constructor
	
	public int hit(){
		return 0;
	}//hit
	
	public int move(){
		xLoc += dir*5;
		return xLoc;
	}//move
	
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
	
}//Character

