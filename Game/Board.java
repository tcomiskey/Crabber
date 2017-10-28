import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class Board extends JPanel implements MouseListener {
	private int boardWidth;
	private int boardHeight;
	private int difficulty; //1 = easy, 2 = medium, 3 = hard
	private boolean win;
	private boolean loss;
	private Player player;
	private ArrayList<GameCharacter> enemies;
	private boolean isOcean; //false for land, true for ocean
	
	
	
	//hella getters and setters
	
	public Board(int difficulty){
		boardWidth = 300;
		boardHeight = 300;
		this.difficulty = difficulty;
		win = false;
		loss = false;
		addMouseListener(this);
		isOcean = false;
        int xcoord = 0;
        int ycoord = boardHeight-(2*player.getPlayerHeight());
        int row = 0;
        int numEnemiesConstant = 20;
        int numPerRowConstant = 5;
        
        // constant subject to change
        enemies.add(new Shark(boardWidth, xcoord, ycoord, "QuestionAndAnswerList.txt"));
        xcoord += enemies.get(0).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
        for(int i = 1; i < difficulty * numEnemiesConstant; i++){
            if (row%2 == 0){
                enemies.add(new Shark(boardWidth, xcoord, ycoord));
                xcoord += enemies.get(i).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
            }
            else{
                enemies.add(new Trash(boardWidth, xcoord, ycoord));
                xcoord += enemies.get(i).getImgWidth()+ (5-difficulty)*player.getPlayerWidth();
            }
            if (i > numPerRowConstant){
                double randomRowSpacing = Math.random();
                if (randomRowSpacing < 0.8){
                    ycoord += player.getPlayerHeight();
                    row++;
                }
                else{
                    ycoord += 2*player.getPlayerHeight();
                    row+=2;
                }
                xcoord = 0;
                
            }
            
        }
		
	}
	public static void main(String[] args){
		Board b = new Board(1);
	}
	public void moveCharacter(){
		GameCharacter c;
		Iterator<GameCharacter> iter = enemies.iterator();
		while (iter.hasNext()){
			c = iter.next();
			c.move();
		}
	}
	public void mouseClicked(MouseEvent e){
		System.out.println("Mouse Clicked");
		win = true;
	}
	public void mouseEntered(MouseEvent e){
	
	}
	public void mouseExited(MouseEvent e){
	
	}
	public void mousePressed(MouseEvent e){
	
	}
	public void mouseReleased(MouseEvent e){
	
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
	public void setLoss(boolean loss){
		this.loss = loss;
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
	public boolean getLoss(){
		return loss;
	}
	public Player getPlayer(){
		return player;
	}
	public boolean getIsOcean(){
		return isOcean;
	}
	public String toString(){
		return "This game board is " + boardHeight + " by " + boardWidth + ". The difficulty is " + difficulty + ". The win and loss booleans are: " + win + " " + loss + ".";
	}
}
