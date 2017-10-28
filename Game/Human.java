import javax.swing.*;

public class Human extends Enemy{
    private ImageIcon image;
    
    public Human(int boardWidth, int xLoc, int yLoc, String fileName){
    	super(boardWidth, xLoc, yLoc, fileName);
        image = new ImageIcon("Sonny stand west.png");
    }
              
    public Human(int boardWidth, int xLoc, int yLoc){
        super(boardWidth, xLoc, yLoc);
        image = new ImageIcon("Sonny stand west.png");
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
		return "Human located at x = " + xLoc + " y = " + yLoc;
	}
}
