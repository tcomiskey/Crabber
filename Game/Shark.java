import javax.swing.*;

public class Shark extends Enemy{
	private ImageIcon image;
	
	public Shark(int boardWidth, int xLoc, int yLoc, String fileName){
		super(boardWidth, xLoc, yLoc, fileName);
        	image = new ImageIcon("images/shark.png");
        	width = image.getIconWidth();
		height = image.getIconHeight();
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width; //335
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
	}
	public Shark(int boardWidth, int xLoc, int yLoc){
		super(boardWidth, xLoc, yLoc);
       	 	image = new ImageIcon("images/shark.png");
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
		return "Shark located at x = " + xLoc + " y = " + yLoc;
	}
}	
