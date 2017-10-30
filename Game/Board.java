import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class Board {
	private int boardWidth;
	private int boardHeight;
	private int difficulty; //1 = easy, 2 = medium, 3 = hard
	private int numberOfRows; // number of rows in the game board
	private boolean win; // parameter used for stopping game
	private boolean isOcean; // false for land, true for ocean to change background and enemy types
	private Player player; // the controllable player
	private ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>(50); // the list of enemies
	private ArrayList<Boolean> safeRows = new ArrayList<Boolean>(); // a list of all of the rows that do not contain enemies used for checkpoints
	
	
	/* Board Constructor sets up the model by creating the player and 
	filling an ArrayList of enemies that will represent every obstacle in 
	the game. Also sets the size of the game board and the difficulty is used
	to scale the number of enemies in the game. 
	*/
	
	public Board(int difficulty){
		// set the initial values for the board attributes
		boardWidth = 800;
		boardHeight = 800;
		this.difficulty = difficulty;
		win = true;
		isOcean = false;	
		player = new Player(boardWidth, boardHeight);
		numberOfRows = boardHeight/player.getPlayerHeight()-1;
		
		// xcoord and ycoord are variables to hold the values of the enemy coordinates
		int xcoord = 0;
		int ycoord = boardHeight-(3*player.getPlayerHeight());
		// row is a counter for what row the enemies are in
		int row = 0;
		// set all rows to safe until they get populated with an enemy
		for (int i = 0; i<1000; i++){
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
		safeRows.set(1, new Boolean(false));
		// looping until total number of enemies needed for game difficulty are instantiated
		for(int i = 1; i < difficulty * numEnemiesConstant/*multiplier to get number of enemies required for the difficulty*/; i++){
		    if (row%2 == 0){ // even rows will be sharks
		        enemies.add(new Shark(boardWidth, xcoord, ycoord, currentDirection));
		        xcoord += enemies.get(i).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
		    }
		    else{ // odd rows will be trash
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
		            safeRows.set(row+1, new Boolean(false));
		        }
		        else{
		            ycoord -= 2*player.getPlayerHeight();
		            row+=2;
		            //sets the next row to be used as unsafe
		            safeRows.set(row+1, new Boolean(false));
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
				player.setY((numberOfRows-i+1)*player.getPlayerHeight());
				break;
			}
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
        String returnString = "This game board is " + boardHeight + " by " + boardWidth + ". The difficulty is " + difficulty + ". The win and loss booleans are: " + win + " ";
        for (GameCharacter character: enemies){
            returnString += character.toString() + "\n";
        }
        return returnString;
	}
}
