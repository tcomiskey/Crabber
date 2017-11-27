import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;

public class Bonus extends GameCharacter{
    private static ImageIcon leftImage = new ImageIcon("images/clam.png");
    private static ImageIcon rightImage = new ImageIcon("images/clam.png");
    
    // Bonus character constructor
    private Bonus(int boardWidth, int xLoc, int yLoc, int direction) {
        // need to find an image and use it here
        super(boardWidth, xLoc, yLoc, direction);
    }
    
    // Hit method that is called when the player collides with a bonus
    public Message hit() {
        return new BonusMessage();
    }
    
    public static Bonus makeBonus(Bonus b, int boardWidth, int y){
        int x = (int)(Math.random()*(boardWidth-leftImage.getIconWidth()));
        System.out.println("Bonus made at "+x+" "+y);
        if(b == null){
            return new Bonus(boardWidth, x, y, 0);
        }
        else{
            b.setX(x);
            b.setY(y);
            return b;
        }
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
        return leftImage.getIconWidth();
    }
    
    public int getHeight(){
        return leftImage.getIconHeight();
    }
    
    public ImageIcon getLeftImage(){
        return leftImage;
    }//getImage
    
    public ImageIcon getRightImage(){
        return rightImage;
    }//getImage
}
