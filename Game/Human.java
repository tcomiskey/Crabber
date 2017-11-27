import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Human extends Enemy{
    public Human(int boardWidth, int xLoc, int yLoc, int direction){
        super(boardWidth, xLoc, yLoc, direction);
        //rightImage = new ImageIcon("images/Sonny stand west.png");
        leftImage = createImage("images/orc_forward_west.png");
        rightImage = createImage("images/orc_forward_east.png");
        width = leftImage.getWidth();
        height = rightImage.getHeight();
        for(int i = 0; i < frameCount; i++) {
            leftImageArray[i] = leftImage.getSubimage(width*i, 0, width, height);
            rightImageArray[i] = rightImage.getSubimage(width*i, 0, width, height);
        }
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
    
	public String toString(){
		return "Human located at x = " + xLoc + " y = " + yLoc;
	}
}
