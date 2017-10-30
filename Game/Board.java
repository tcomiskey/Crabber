import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.util.concurrent.*;
//import jline.*;

public class Board extends JFrame{
	private int boardWidth;
	private int boardHeight;
	private int difficulty; //1 = easy, 2 = medium, 3 = hard
	private boolean win;
	private static Player player;
	private ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>(50);
	private boolean isOcean; //false for land, true for ocean
	private ArrayList<Boolean> safeRows = new ArrayList<Boolean>();
	private int numberOfRows;
	
	
	//hella getters and setters
	
	public Board(int difficulty){
		boardWidth = 800;
		boardHeight = 800;
		this.difficulty = difficulty;
		win = true;
		isOcean = false;
      	
			
		player = new Player(boardWidth, boardHeight);
		
		int xcoord = 0;
		int ycoord = boardHeight-(3*player.getPlayerHeight());
		int row = 0;
		numberOfRows = boardHeight/player.getPlayerHeight()-1;
		for (int i = 0; i<1000; i++){
			safeRows.add(new Boolean(true));
		}
		int numEnemiesConstant = 100*difficulty;
		int numPerRowConstant = 3*difficulty;
		int enemiesInRow = 1;
        	int currentDirection = -1;
		
		// first enemy will use complex constuctor to instantiate static array of questions
		enemies.add(new Shark(boardWidth, xcoord, ycoord, currentDirection, "QuestionAndAnswerList.txt"));
		// xcoord will be incremented based on the game difficulty*width of the player
		xcoord += enemies.get(0).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
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
		    // if you max out the number of enemies for a row, pick next row to fill (1 or 2 rows up), reset xcoord to 0
		    if (enemiesInRow == numPerRowConstant){
		        double randomRowSpacing = Math.random();
		        if (randomRowSpacing < 0.4){
		            ycoord -= player.getPlayerHeight();
		            row++;
		            safeRows.set(row+1, new Boolean(false));
		        }
		        else{
		            ycoord -= 2*player.getPlayerHeight();
		            row+=2;
		            safeRows.set(row+1, new Boolean(false));
		        }
                xcoord = (int) Math.floor(Math.random() * 4 * player.getPlayerWidth());
                enemiesInRow = 0;
                double randomDir = Math.random();
                if (randomDir < 0.5){
                    currentDirection = -1;
                }
                else{
                    currentDirection = 1;
                }
		    }
		    
		    
        }
		
	} //Board constructor
    
    
    //move method
	public void moveCharacter(){
		GameCharacter c;
		Iterator<GameCharacter> iter = enemies.iterator();
		while (iter.hasNext()){
			c = iter.next();
			c.move();
		}
	}
	public void resetPlayer(){
		int currentRow = numberOfRows-player.getY()/player.getPlayerHeight();
        System.out.println("row " + currentRow);
		for (int i = currentRow; i>=0; i--){
			if(safeRows.get(i)){
				player.setY((numberOfRows-i+1)*player.getPlayerHeight());
				break;
			}
		}
		System.out.println("safe rows" + safeRows);
        System.out.println("row " + currentRow);
	
	}
    
    public boolean collisionCheck(GameCharacter collisionCheckCharacter) {
        if(player.getY() == collisionCheckCharacter.getY() && player.getX()+player.getPlayerWidth() > collisionCheckCharacter.getX() && player.getX() < collisionCheckCharacter.getX()+collisionCheckCharacter.getImgWidth()){
            return true;
        }
        else {
            return false;
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
    
    public static final int BUTTON1 = 1;
    public static final int MOUSE_CLICKED = 500;
    public static void main(String args[]) {
        Board b = new Board(1);
        MouseEvent me = new MouseEvent(b, MOUSE_CLICKED, 100, 0, 400, 400, 1, false, BUTTON1);
        System.out.println(b);
        System.out.println(player);
        b.moveCharacter();
        player.move(me);
        //System.out.println(b);
        System.out.println(player);
        for(int i = 0; i < 100; i++){
            b.moveCharacter();
            /*for(GameCharacter gc : b.enemies) {
                if (b.collisionCheck(gc)) {
                    Message message = gc.hit();
                    System.out.println(message.getQuestion());
                    System.out.println("a. "+ message.getRightAnswer());
                    System.out.println("b. "+ message.getWrongAnswer1());
                    System.out.println("c. "+ message.getWrongAnswer2());
                    ConsoleReader starter = new ConsoleReader(System.in, new PrintWriter(System.out));
                    char[] play = {'a', 'b', 'c'};
                    int j  = starter.readCharacter(play);
                    if(j == (int)'a'){
                        System.out.println("RIGHT");
                    }
                    else{
                        Sytem.out.println("WRONG");
                    }
                }
                
            }*/
        }
    }
}
