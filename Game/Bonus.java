import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Bonus extends GameCharacter{
    private static Image leftImage;
    private static Image rightImage;
    private static boolean noBonus = true;
    private static int bonusWidth;
    private static int bonusHeight;
    
    // Bonus character constructor
    private Bonus(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor) {
        // need to find an image and use it here
        super(boardWidth, xLoc, yLoc, direction, scalingFactor);
        System.out.println(scalingFactor);
       	bonusWidth = (int)(50*scalingFactor);
	bonusHeight = (int)(50*scalingFactor);
        try {
            		leftImage = ImageIO.read(new File("images/clam.png"));
			leftImage = leftImage.getScaledInstance(bonusWidth, bonusHeight, Image.SCALE_SMOOTH);
			rightImage = ImageIO.read(new File("images/clam.png"));
			rightImage = rightImage.getScaledInstance(bonusWidth, bonusHeight, Image.SCALE_SMOOTH);
        	} catch (IOException e) {
            		e.printStackTrace();
        	}
    }
    
    // Hit method that is called when the player collides with a bonus
    public Message hit() {
        return new BonusMessage();
    }
    
    public static Bonus makeBonus(int boardWidth, int y, double scalingFactor){
        int x = (int)(Math.random()*(boardWidth-50)); // hardcoded width???
        System.out.println("Bonus made at "+x+" "+y);
        if(noBonus == true){
            noBonus = false;
            return new Bonus(boardWidth, x, y, 0, scalingFactor);
        }
        return null;
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
    
    public int getWidth(){
        return bonusWidth;
    }
    
    public int getHeight(){
        return bonusHeight;
    }
    
    public Image getLeftImage(){
        return leftImage;
    }//getImage
    
    public Image getRightImage(){
        return rightImage;
    }//getImage
    
    public void setNoBonus(boolean bonusBoolean) {
        noBonus = bonusBoolean;
    }
}
