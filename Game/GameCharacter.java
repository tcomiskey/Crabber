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
	
	public GameCharacter(int boardWidth, int xLoc, int yLoc, int direction){
		//image = new ImageIcon("images/brown rectangle.png");
        	this.xLoc=xLoc;
        	this.yLoc=yLoc;
        dir = direction;
		
			
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

