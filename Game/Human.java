import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
A class of obstacles that are found in the land level

@author Tom Comiskey and Erin Hitchner
*/
public class Human extends Enemy{
    private static boolean noHumans = true;
    static int humanWidth;
    static int humanHeight;

    /**
     Human constructor creates the arraylist of questions for all of the enemies, and theOneAndOnlyHuman object that holds all the other shark information.
     
     @author Tom Comiskey and Erin Hitchner
     @param boardWidth the width of the game screen
     @param xLocation the x coordinate for upper left pixel of the image
     @param yLocation the y coordinate for upper left pixel of the image
     @param direction the direction the object will move on the screen, decides image direction
     */
    private Human(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor){
        super(boardWidth, xLoc, yLoc, direction, scalingFactor);
        frameCount = 4;
        leftImageArray = new BufferedImage[frameCount];
        rightImageArray = new BufferedImage[frameCount];
        //rightImage = new ImageIcon("images/Trash.png");
        //leftImage = new ImageIcon("images/Trash.png");
        leftImage = createCharacterImage("images/Sonny run west.png");
        rightImage = createCharacterImage("images/Sonny run east.png");
        int newWidth = (int)(leftImage.getWidth()*scalingFactor-leftImage.getWidth()*scalingFactor%frameCount);	//makes image width divisible by frameCount
        Image tmp1 = leftImage.getScaledInstance(newWidth, (int)(leftImage.getHeight()*scalingFactor), Image.SCALE_SMOOTH);
	int TYPE_INT_ARGB=2;
	leftImage = new BufferedImage(newWidth, (int)(leftImage.getHeight()*scalingFactor), TYPE_INT_ARGB);
	leftImage.getGraphics().drawImage(tmp1,0,0,null);

	tmp1 = rightImage.getScaledInstance(newWidth, (int)(rightImage.getHeight()*scalingFactor), Image.SCALE_SMOOTH);
	rightImage = new BufferedImage(newWidth, (int)(rightImage.getHeight()*scalingFactor), TYPE_INT_ARGB);
	rightImage.getGraphics().drawImage(tmp1,0,0,null);

	width = leftImage.getWidth() / frameCount;
        height = rightImage.getHeight();
        for(int i = 0; i < frameCount; i++) {
            leftImageArray[i] = leftImage.getSubimage(width*i, 0, width, height);
            rightImageArray[i] = rightImage.getSubimage(width*i, 0, width, height);
        }
        humanWidth = width;
        humanHeight = height;
        //Sets bounds on how far off screen a character can go before it loops around
        int usableWidth = (boardWidth/width) + 3*width; //335
        farLeft = -(usableWidth)/2;
        farRight = boardWidth+(usableWidth)/2;
        int[] xydir = new int[5];
        xydir[0] = xLoc;
        xydir[1] = yLoc;
        xydir[2] = direction;
        xydir[3] = width;
        xydir[4] = height;
        enemyAtt.add(xydir);
    }

    /**
     Static factory method for creating Human objects

     @param boardWidth the width of the board that the human is on
     @param xLocation the x-coordinate of the human
     @param yLocation the y-coordinate of the human
     @param direction the direction that the human will move, 1 for right, -1 for left
     @param scalingFactor multiplier by which to scale the image to the screen size
     @return Returns a new human if there is not one already made, otherwise returns null
     */
    public static Human humanFactory(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor){
        if (noHumans){
            return new Human(boardWidth, xLoc, yLoc, direction, scalingFactor);
        }
        else{
            addHuman(xLoc, yLoc, direction, humanWidth, humanHeight);
            return null;
        }
        
    }

    /**
	Part of Singleton, adds human attributes to the list of enemy attributes
	@param xLocation the x-coordinate of the human
	@param yLocation the y-coordinate of the human
	@param direction the direction that the human will move, 1 for right, -1 for left
	@param currentWidth the current width of the human
	@param currentHeight the current height of the human
	*/
    private static void addHuman(int xLocation, int yLocation, int direction, int currentWidth, int currentHeight){
        int[] xydir = new int[5];
        xydir[0] = xLocation;
        xydir[1] = yLocation;
        xydir[2] = direction;
        xydir[3] = currentWidth;
        xydir[4] = currentHeight;
        enemyAtt.add(xydir);
        
    }
    public boolean getNoHumans(){
        return noHumans;
    }
    
    public void setNoHumans(boolean humanBoolean) {
        noHumans = humanBoolean;
    }
    
	public String toString(){
		return "Human located at x = " + xLoc + " y = " + yLoc;
	}
}
