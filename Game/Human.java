import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Human extends Enemy{
    private static boolean noHumans = true;
    static int humanWidth;
    static int humanHeight;
    
    private Human(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor){
        super(boardWidth, xLoc, yLoc, direction, scalingFactor);
        frameCount = 4;
        leftImageArray = new BufferedImage[frameCount];
        rightImageArray = new BufferedImage[frameCount];
        //rightImage = new ImageIcon("images/Trash.png");
        //leftImage = new ImageIcon("images/Trash.png");
        leftImage = createCharacterImage("images/Sonny run west.png");
        rightImage = createCharacterImage("images/Sonny run east.png");
        Image tmp1 = leftImage.getScaledInstance((int)(leftImage.getWidth()*scalingFactor), (int)(leftImage.getHeight()*scalingFactor), Image.SCALE_SMOOTH);
	int TYPE_INT_ARGB=2;
	leftImage = new BufferedImage((int)(leftImage.getWidth()*scalingFactor), (int)(leftImage.getHeight()*scalingFactor), TYPE_INT_ARGB);
	leftImage.getGraphics().drawImage(tmp1,0,0,null);

	tmp1 = rightImage.getScaledInstance((int)(rightImage.getWidth()*scalingFactor), (int)(rightImage.getHeight()*scalingFactor), Image.SCALE_SMOOTH);
	rightImage = new BufferedImage((int)(rightImage.getWidth()*scalingFactor), (int)(rightImage.getHeight()*scalingFactor), TYPE_INT_ARGB);
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
    public static Human humanFactory(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor){
        if (noHumans){
            return new Human(boardWidth, xLoc, yLoc, direction, scalingFactor);
        }
        else{
            addHuman(xLoc, yLoc, direction, humanWidth, humanHeight);
            return null;
        }
        
    }
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
