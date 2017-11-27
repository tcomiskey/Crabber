import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Bonus extends GameCharacter{
    private static BufferedImage leftImage;
    private static BufferedImage rightImage;
    
    // Bonus character constructor
    private Bonus(int boardWidth, int xLoc, int yLoc, int direction) {
        // need to find an image and use it here
        super(boardWidth, xLoc, yLoc, direction);
        try {
            leftImage = ImageIO.read(new File("images/clam.png"));
            rightImage = ImageIO.read(new File("images/clam.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Hit method that is called when the player collides with a bonus
    public Message hit() {
        return new BonusMessage();
    }
    
    public static Bonus makeBonus(Bonus b, int boardWidth, int y){
        int x = (int)(Math.random()*(boardWidth-leftImage.getWidth()));
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
        return leftImage.getWidth();
    }
    
    public int getHeight(){
        return leftImage.getHeight();
    }
    
    public BufferedImage getLeftImage(){
        return leftImage;
    }//getImage
    
    public BufferedImage getRightImage(){
        return rightImage;
    }//getImage
}
