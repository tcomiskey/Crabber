import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements MouseListener {
	private int boardWidth;
	private int boardHeight;
	private int difficulty;
	private boolean win;
	private boolean loss;
	private Player player;
	private ArrayList<GameCharacter> enemies;
	private boolean landOrOcean; //0 for land, 1 for ocean
	
	
	
	//hella getters and setters
	
	public Board(int difficulty){
		boardWidth = 300;
		boardHeight = 300;
		this.difficulty = difficulty;
		win = false;
		loss = false;
		addMouseListener(this);
		landOrOcean = false;
		
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
	public void setLandOrOcean(boolean landOrOcean){
		this.landOrOcean = landOrOcean;
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
	public boolean getLandOrOcean(){
		return landOrOcean;
	}
	public String toString(){
		return "This game board is " + boardHeight + " by " + boardWidth + ". The difficulty is " + difficulty + ". The win and loss booleans are: " + win + " " + loss + ".";
	}
}
