import javax.swing.*;

public class Trash extends Enemy{
	private ImageIcon image;
	
	public Trash(int boardWidth, int xLoc, int yLoc, int direction, String fileName){
		super(boardWidth, xLoc, yLoc, direction, fileName);
	        image = new ImageIcon("images/Trash.png");
		width = image.getIconWidth();
		height = image.getIconHeight();
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width; //335
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
	}
	public Trash(int boardWidth, int xLoc, int yLoc, int direction){
		super(boardWidth, xLoc, yLoc, direction);
		image = new ImageIcon("images/Trash.png");
		width = image.getIconWidth();
		height = image.getIconHeight();
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width; //335
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
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
		return "Trash located at x = " +  xLoc + " y = " + yLoc;
	}
}	
