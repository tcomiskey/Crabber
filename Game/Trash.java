import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Trash extends Enemy{
	private static boolean noTrash = true;
    static int trashWidth;
    static int trashHeight;

	private Trash(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor){
        super(boardWidth, xLoc, yLoc, direction, scalingFactor);
        frameCount = 8;
        leftImageArray = new BufferedImage[frameCount];
        rightImageArray = new BufferedImage[frameCount];
		//rightImage = new ImageIcon("images/Trash.png");
		//leftImage = new ImageIcon("images/Trash.png");
        leftImage = createCharacterImage("images/reverseTrash.png");
        rightImage = createCharacterImage("images/trash.png");
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
        trashWidth = width;
        trashHeight = height;
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
	public static Trash trashFactory(int boardWidth, int xLoc, int yLoc, int direction, double scalingFactor){
		if (noTrash){
			return new Trash(boardWidth, xLoc, yLoc, direction, scalingFactor);
		}
		else{
			addTrash(xLoc, yLoc, direction, trashWidth, trashHeight);
			return null;
		}
		
	}
	private static void addTrash(int xLocation, int yLocation, int direction, int currentWidth, int currentHeight){
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = currentWidth;
		xydir[4] = currentHeight;
		enemyAtt.add(xydir);
		
	}
	public boolean getNoTrash(){
		return noTrash;
	}
    
    public void setNoTrash(boolean trashBoolean) {
        noTrash = trashBoolean;
    }
    
	public String toString(){
		return "Trash located at x = " +  xLoc + " y = " + yLoc;
	}
}	
