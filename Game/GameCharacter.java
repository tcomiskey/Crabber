import javax.swing.*;


public class GameCharacter {
	ImageIcon image;
	int width;
	int height;
	int xLoc;
	int yLoc;
	int dir = -1;
	int farLeft;
	int farRight;
	
	public GameCharacter(int boardWidth, int xLoc, int yLoc){
		image = new ImageIcon("brown rectangle.png");
        	this.xLoc=xLoc;
        	this.yLoc=yLoc;
		width = image.getIconWidth();
        System.out.println(image.getIconWidth());
		height = image.getIconHeight();
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width; //335
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
			
	}//constructor
	
	public Message hit(){
		return null;
	}//hit
	
	public int move(){
		xLoc += dir*5;
		if(xLoc < farLeft){
			xLoc = farRight;
		}
		else if(xLoc > farRight){
			xLoc = farLeft;
		}
			
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

	public int getImgWidth(){
		return width;
	}

	public int getImgHeight(){
		return height;
	}

	public String toString(){
		return "x: "+xLoc+" y: "+yLoc+" dir: "+dir;
	}//toString
	
}//GameCharacter

