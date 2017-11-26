import javax.swing.*;

public class Trash extends Enemy{
	private boolean noTrash = true;

	private Trash(int boardWidth, int xLoc, int yLoc, int direction){
		super(boardWidth, xLoc, yLoc, direction);
		rightImage = new ImageIcon("images/Trash.png");
		leftImage = new ImageIcon("images/Trash.png");
		width = rightImage.getIconWidth();
		height = rightImage.getIconHeight();
		//Sets bounds on how far off screen a character can go before it loops around
		int usableWidth = (boardWidth/width) + 3*width; //335
		farLeft = -(usableWidth)/2;
		farRight = boardWidth+(usableWidth)/2;
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = width;
		xydir[4] = height;
		enemiesAtt.add(xydir);
	}
	public static Trash trashFactory(int boardWidth, int xLoc, int yLoc, int direction){
		if (noTrash){
			return new Trash(boardWidth, xLoc, yLoc, direction);
		}
		else{
			addTrash(xLocation, yLocation, direction);
			return null;
		}
		
	}
	private void addTrash(int xLocation, int yLocation, int direction){
		int[] xydir = new int[5];
		xydir[0] = xLocation;
		xydir[1] = yLocation;
		xydir[2] = direction;
		xydir[3] = width;
		xydir[4] = height;
		enemiesAtt.add(xydir);
		
	}
	public boolean getNoTrash(){
		return noTrash;
	}	
	public String toString(){
		return "Trash located at x = " +  xLoc + " y = " + yLoc;
	}
}	
