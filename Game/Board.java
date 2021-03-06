import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.util.concurrent.*;
import java.io.*;

public class Board extends JFrame{
	private int boardWidth;
	private int boardHeight;
	private int difficulty; //1 = easy, 2 = medium, 3 = hard
	private int numberOfRows; // number of rows in the game board
	private boolean win; // parameter used for stopping game
	private boolean isOcean; // false for land, true for ocean to change background and enemy types
	private static Player player; // the controllable player
	private ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>(50); // the list of enemies
	private ArrayList<Boolean> safeRows = new ArrayList<Boolean>(); // a list of all of the rows that do not contain enemies used for checkpoints
	private Bonus bonus;
	
	/* Board Constructor sets up the model by creating the player and 
	filling an ArrayList of enemies that will represent every obstacle in 
	the game. Also sets the size of the game board and the difficulty is used
	to scale the number of enemies in the game. 
	*/
	
	public Board(int difficulty){
		// set the initial values for the board attributes
		boardWidth = 800;
		boardHeight = 700;
		this.difficulty = difficulty;
		win = true;
		isOcean = false;	
		player = new Player(boardWidth, boardHeight);
		numberOfRows = boardHeight/player.getPlayerHeight()-1;
		
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
		int numPerRowConstant = 3*difficulty;
		// picks left as the current direction of movement;
        	int currentDirection = -1;
		// first enemy will use complex constuctor to instantiate static array of questions
		enemies.add(new Shark(boardWidth, xcoord, ycoord, currentDirection, "QuestionAndAnswerList.txt"));
		// counter for how many enemies have been placed in the current row
		int enemiesInRow = 1;
		// xcoord will be incremented based on the game difficulty*width of the player
		xcoord += enemies.get(0).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
		// since an enemy was added to this row the safeRow value is set to false
		safeRows.set(row, new Boolean(false));
		// looping until total number of enemies needed for game difficulty are instantiated
		for(int i = 1; row < numberOfRows/*multiplier to get number of enemies required for the difficulty*/; i++){
		    if (row%2 == 1){ // odd rows will be sharks
		        enemies.add(new Shark(boardWidth, xcoord, ycoord, currentDirection));
		        xcoord += enemies.get(i).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
		    }
		    else{ // even rows will be trash
		        enemies.add(new Trash(boardWidth, xcoord, ycoord, currentDirection));
		        xcoord += enemies.get(i).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
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
    	//move method that is called to move all of the enemy characters
	public void moveCharacter(){
		Iterator<GameCharacter> enemyIterator = enemies.iterator();
		while (enemyIterator.hasNext()){
			enemyIterator.next().move();
		}
	}
	// method called when placing the player back to the last visited safe row
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
    
    public boolean collisionCheck(GameCharacter collisionCheckCharacter) {
        if(player.getY() == collisionCheckCharacter.getY() && player.getX()+player.getPlayerWidth() > collisionCheckCharacter.getX() && player.getX() < collisionCheckCharacter.getX()+collisionCheckCharacter.getImgWidth()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean playerAtFinish(){
    	return player.getY() == 0;
    }

	public void generateBonus(){
    		double r = Math.random();
            	if(r < .25/difficulty){
            		bonus = Bonus.makeBonus(bonus, boardWidth, player.getY()-player.getPlayerHeight()*2);
            	}
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
	public ArrayList<GameCharacter> getEnemies(){
		return enemies;
	}
	public String toString(){
        String returnString = "";
        for (GameCharacter character: enemies){
            returnString += character.toString() + "\n";
        }
        return returnString;
	}
    
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
            for(GameCharacter gc : b.enemies) {
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
}
