import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.util.concurrent.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Board extends JFrame{
	private int boardWidth;
	private int boardHeight;
	private int difficulty; //1 = easy, 2 = medium, 3 = hard
	private int numberOfRows; // number of rows in the game board
	private boolean win; // parameter used for stopping game
	private boolean isOcean; // false for land, true for ocean to change background and enemy types
	private static Player player; // the controllable player 
	private ArrayList<Boolean> safeRows = new ArrayList<Boolean>(); // a list of all of the rows that do not contain enemies used for checkpoints
	private static Shark theOneAndOnlyShark;
	private static Trash theOneAndOnlyTrash;
    private static Human theOneAndOnlyHuman;
    private static Bird theOneAndOnlyBird;
    private Bonus bonus;
    private long startTime;
    private long remainingTime;	//time left in the level in milliseconds
    private long levelTime = 30000; //time to complete the level in milliseconds
    private double scalingFactor;
    private int collisionBuffer;
    
	/** Board Constructor sets up the model by creating the player and
	filling an ArrayList of enemies that will represent every obstacle in 
	the game. Also sets the size of the game board and the difficulty is used
	to scale the number of enemies in the game. 
     
     @author Erin Hitchner and Tom Comiskey
     @param difficulty sets easy, medium, or hard based on user input in the view
	*/
	
	public Board(int difficulty, boolean isOcean, int boardWidth, int boardHeight, double scalingFactor){
		// set the initial values for the board attributes
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		System.out.println("Board H " + boardHeight);
		this.difficulty = difficulty;
		this.isOcean = isOcean;	
		this.scalingFactor = scalingFactor;
		collisionBuffer = (int) (8*scalingFactor);
		win = true;
		player = new Player(this.boardWidth, this.boardHeight, this.scalingFactor);
		numberOfRows = boardHeight/player.getPlayerHeight()-1;
		System.out.println(numberOfRows);
        	startTime = System.currentTimeMillis();
        	remainingTime = levelTime;
        	bonus = generateBonus();
        
        
		// xcoord and ycoord are variables to hold the values of the enemy coordinates
		int xcoord = 0;
		int ycoord = boardHeight-(2*player.getPlayerHeight());
		// row is a counter for what row the enemies are in
		int row = 1;
		// set all rows to safe until they get populated with an enemy
		for (int i = 0; i<300; i++){
			safeRows.add(new Boolean(true));
		}
		// set a number of enemies and how many per row based on difficulty
		int numEnemiesConstant = 100*difficulty;
		int numPerRowConstant = 2 + difficulty;
		// picks left as the current direction of movement;
        	int currentDirection = -1;
        
        
        if (isOcean == true) {
        	Shark.setNoSharks(true);
		// first enemy will use complex constuctor to instantiate static array of questions
        	// the first shark must be instantiated with its factory such that it can hold an array of attributes for other sharks
		System.out.println(Shark.getNoSharks());
       	    	theOneAndOnlyShark = Shark.sharkFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
            	System.out.println(Shark.getNoSharks());
            	System.out.println("Usable Width " +  theOneAndOnlyShark.getTotalWidth());
            	System.out.println(theOneAndOnlyShark.getTotalWidth()/numPerRowConstant);
        	// the first trash must be instantiated with its factory such that it can hold an array of attributes for other trash
		theOneAndOnlyTrash = Trash.trashFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
		// counter for how many enemies have been placed in the current row
		int enemiesInRow = 1;
		// xcoord will be incremented based on the game difficulty*width of the player
		xcoord += theOneAndOnlyShark.getImgWidth() + (theOneAndOnlyShark.getTotalWidth()-numPerRowConstant*theOneAndOnlyShark.getImgWidth())/numPerRowConstant;
		// since an enemy was added to this row the safeRow value is set to false
		safeRows.set(row, new Boolean(false));
		// looping until total number of enemies needed for game difficulty are instantiated
		for(int i = 1; row < numberOfRows/*multiplier to get number of enemies required for the difficulty*/; i++){
		    if (row%2 == 1){ // odd rows will be sharks
		        Shark.sharkFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
		        //xcoord += theOneAndOnlyShark.getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
                	xcoord = xcoord + theOneAndOnlyShark.getImgWidth() + (theOneAndOnlyShark.getTotalWidth()-numPerRowConstant*theOneAndOnlyShark.getImgWidth())/numPerRowConstant;
		    }
		    else{ // even rows will be trash
		       	Trash.trashFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
		        //xcoord += theOneAndOnlyTrash.getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
                xcoord = xcoord + theOneAndOnlyTrash.getImgWidth() + (theOneAndOnlyShark.getTotalWidth()-numPerRowConstant*theOneAndOnlyTrash.getImgWidth())/numPerRowConstant;
		    }
		    enemiesInRow++;
		    // if you max out the number of enemies for a row, pick next row to fill (1 or 2 rows up)
		    if (enemiesInRow == numPerRowConstant){
		        double randomRowSpacing = Math.random();
		        // pick randomly with a slight bias what row to go to next
		        if (randomRowSpacing < 0.4){
		            ycoord -= player.getPlayerHeight();
		            row++;
		            //sets the next row to be used as unsafe
		            safeRows.set(row, new Boolean(false));
		        }
		        else{
		            ycoord -= 2*player.getPlayerHeight();
		            row+=2;
		            //sets the next row to be used as unsafe
		            safeRows.set(row, new Boolean(false));
		        }
		        // picks a random new xcoord to use when starting a new row to create staggered enemies
		        xcoord = (int) Math.floor(Math.random() * 4 * player.getPlayerWidth());
		        // resets the counter
		        enemiesInRow = 0;
		        // picks a direction randomly
		        double randomDir = Math.random();
		        if (randomDir < 0.5){
		            currentDirection = -1;
		        }
		        else{
		            currentDirection = 1;
		        }
		    } // if enemiesInRow
		    
        } // for loop
        }
        
        else {
            System.out.println("LAND HO");
            bonus.setX(-1*bonus.getWidth());
            bonus.setY(-1*bonus.getHeight());
            // first enemy will use complex constuctor to instantiate static array of questions
            // the first shark must be instantiated with its factory such that it can hold an array of attributes for other sharks
            theOneAndOnlyHuman = Human.humanFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
            // the first trash must be instantiated with its factory such that it can hold an array of attributes for other trash
            theOneAndOnlyBird = Bird.birdFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
            // counter for how many enemies have been placed in the current row
            int enemiesInRow = 1;
            // xcoord will be incremented based on the game difficulty*width of the player
            xcoord += theOneAndOnlyHuman.getImgWidth() + (theOneAndOnlyShark.getTotalWidth()-numPerRowConstant*theOneAndOnlyHuman.getImgWidth())/numPerRowConstant;
            // since an enemy was added to this row the safeRow value is set to false
            safeRows.set(row, new Boolean(false));
            // looping until total number of enemies needed for game difficulty are instantiated
            for(int i = 1; row < numberOfRows/*multiplier to get number of enemies required for the difficulty*/; i++){
                if (row%2 == 1){ // odd rows will be sharks
                    Human.humanFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
                    //xcoord += theOneAndOnlyHuman.getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
                    xcoord = xcoord + theOneAndOnlyHuman.getImgWidth() + (theOneAndOnlyShark.getTotalWidth()-numPerRowConstant*theOneAndOnlyHuman.getImgWidth())/numPerRowConstant;
                }
                else{ // even rows will be trash
                    Bird.birdFactory(boardWidth, xcoord, ycoord, currentDirection, scalingFactor);
                    //xcoord += theOneAndOnlyBird.getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
                    xcoord = xcoord + theOneAndOnlyBird.getImgWidth() + (theOneAndOnlyShark.getTotalWidth()-numPerRowConstant*theOneAndOnlyBird.getImgWidth())/numPerRowConstant;
                }
                enemiesInRow++;
                // if you max out the number of enemies for a row, pick next row to fill (1 or 2 rows up)
                if (enemiesInRow == numPerRowConstant){
                    double randomRowSpacing = Math.random();
                    // pick randomly with a slight bias what row to go to next
                    if (randomRowSpacing < 0.4){
                        ycoord -= player.getPlayerHeight();
                        row++;
                        //sets the next row to be used as unsafe
                        safeRows.set(row, new Boolean(false));
                    }
                    else{
                        ycoord -= 2*player.getPlayerHeight();
                        row+=2;
                        //sets the next row to be used as unsafe
                        safeRows.set(row, new Boolean(false));
                    }
                    // picks a random new xcoord to use when starting a new row to create staggered enemies
                    xcoord = (int) Math.floor(Math.random() * 4 * player.getPlayerWidth());
                    // resets the counter
                    enemiesInRow = 0;
                    // picks a direction randomly
                    double randomDir = Math.random();
                    if (randomDir < 0.5){
                        currentDirection = -1;
                    }
                    else{
                        currentDirection = 1;
                    }
                }  
                
            }
        }
        System.out.println("in board bonus is " + bonus);
	}
    /**
     Move method that is called to move all of the enemy characters.
     
     @author Tom Comiskey
     */
	public void moveCharacter(){
		theOneAndOnlyShark.move();
	}
    
    /**
     Method called when placing the player back to the last visited safe row.
     Loops through array of saferows made when creating enemies to find the closest safe row to the player's collision
     */
	public void resetPlayer(){
		// finds the current row of the character based on current location
		int currentRow = numberOfRows-player.getY()/player.getPlayerHeight();
		// loops backwards from the current row until finding a safe row and resets the ylocation of the player
		for (int i = currentRow; i>=0; i--){
			if(safeRows.get(i)){
				player.setY((numberOfRows-i)*player.getPlayerHeight());
				break;
			}
		}
	
	}
    
    /**
     Checks each enemy for collisions with the player. Checks the location of each enemy against the current location of the player at that tick.
     
     @author Erin Hitchner
     @return true if there is a collision
     */
    public boolean collisionCheck() {
        	Iterator enemyAttIterator = theOneAndOnlyShark.getEnemyAtt().iterator();
		while (enemyAttIterator.hasNext()){
            		int[] currentEnemy = (int[]) enemyAttIterator.next();
			if(player.getX()+player.getPlayerWidth()-collisionBuffer > currentEnemy[0] && player.getX()+collisionBuffer < currentEnemy[0] + currentEnemy[3] && player.getY() == currentEnemy[1]){
				return true;
			}
        }
        return false;
    }
    
    /**
     Checks the location of the player to see if player reaches finish.
     
     @author Tom Comiskey
     @return true if the player is at the finish
     */
    public boolean playerAtFinish(){

    	return player.getY() <= player.getPlayerHeight()/2;

    }
    
    public Bonus generateBonus(){
        int r = (int)(Math.random()*6)+2;
        return Bonus.makeBonus(boardWidth, player.getY()-player.getPlayerHeight()*r, scalingFactor);
    }
    
    public void removeBonus(){
        bonus.setX(-1*bonus.getWidth());
        bonus.setY(-1*bonus.getHeight());
    }
    
    
    public boolean bonusCollisionCheck(){
        if(player.getX()+player.getPlayerWidth() > bonus.getX() && player.getX() < bonus.getX() + bonus.getWidth() && player.getY() == bonus.getY()){
            return true;
        }
        return false;
    }
    
    public boolean timeLeft(){		//returns true if there is still time to play
        long curTime = System.currentTimeMillis();
        return curTime-startTime < levelTime;
    }
    
    public void resetStartTime(long pausedTime){
        startTime += pausedTime;
    }
    
    public void updateRemainingTime(){
        long elapsedTime = System.currentTimeMillis()-startTime;
        remainingTime = levelTime-elapsedTime;
    }
    
    //setters
    public void setBoardWidth(int boardWidth){
		this.boardWidth = boardWidth;
	}
	public void setBoardHeight(int boardHeight){
		this.boardHeight = boardHeight;
	}
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	public void setWin(boolean win){
		this.win = win;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	public void setIsOcean(boolean isOcean){
		this.isOcean = isOcean;
	}
	
	public void setBonus(Bonus bonus){
		this.bonus = bonus;
	}
	
	//getters
	public int getBoardWidth(){
		return boardWidth;
	}
	public int getBoardHeight(){
		return boardHeight;
	}
	public int getDifficulty(){
		return difficulty;
	}
	public boolean getWin(){
		return win;
	}
	public Player getPlayer(){
		return player;
	}
	public boolean getIsOcean(){
		return isOcean;
	}
	public Shark getTheOneAndOnlyShark(){
		return theOneAndOnlyShark;
	}
	public Trash getTheOneAndOnlyTrash(){
		return theOneAndOnlyTrash;
	}
    public Human getTheOneAndOnlyHuman(){
        return theOneAndOnlyHuman;
    }
    public Bird getTheOneAndOnlyBird(){
        return theOneAndOnlyBird;
    }
    public Bonus getBonus(){
        return bonus;
    }

    public long getRemainingTime(){
    	return remainingTime;
    }
    
    public long getStartTime(){
    	return startTime;
    }
    
    public String toString(){
        String returnString = "";
        //for (GameCharacter character: enemies){
        //   returnString += character.toString() + "\n";
        //}
        return returnString;
	}
/*    
    public static final int BUTTON1 = 1;
    public static final int MOUSE_CLICKED = 500;
   public static void main(String args[]) {
        Board b = new Board(1);
        MouseEvent me1 = new MouseEvent(b, MOUSE_CLICKED, 100, 0, 100, b.getPlayer().getY(), 1, false, BUTTON1);
        MouseEvent me2 = new MouseEvent(b, MOUSE_CLICKED, 100, 0, 600, b.getPlayer().getY(), 1, false, BUTTON1);
        MouseEvent me3 = new MouseEvent(b, MOUSE_CLICKED, 100, 0, 400, 400, 1, false, BUTTON1);
        System.out.println(b);
        System.out.println(player);
        b.moveCharacter();
        player.move(me1);
        player.move(me2);
        player.move(me3);
        int lost = 0;
        for(int i = 0; i < 100; i++){
            b.moveCharacter();
            System.out.println(b);
            for() {
                if (b.collisionCheck(gc)) {
                    System.out.println("COLLISION:");
                    Message message = gc.hit();
                    System.out.println(message.getQuestion());
                    System.out.println("a. "+ message.getRightAnswer());
                    System.out.println("b. "+ message.getWrongAnswer1());
                    System.out.println("c. "+ message.getWrongAnswer2());
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Enter answer:");
                    String s = "";
                    try {
                        s = br.readLine();
                    }
                    catch(IOException ex){
                        System.err.println("Invalid Format!");
                    }
                    if (s.equals("a")) {
                        System.out.println("RIGHT");
                        b.resetPlayer();
                        System.out.println("Player Reset");
                    }
                    else {
                        System.out.println("YOU LOSE");
                        lost = 1;
                        break;
                    }//else
                    
                }//if
                
            }//for
            if(lost == 1){
            	break;
            }
        }
    }
   */ 
}
