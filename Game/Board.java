import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements MouseListener {
	private int boardWidth;
	private int boardHeight;
	private int difficulty;
	private boolean win;
	private Player myDude;
	private Character[] things;
	
	
	
	//hella getters and setters
	
	public Board(int difficulty){
		boardWidth = 300;
		boardHeight = 300;
		this.difficulty = difficulty;
		win = false;
		//make and fill character array in here based on difficulty
		addMouseListener(this);
		
		
		
	}
	public void moveCharacter(){
		for (Character c: things){
		
		}
	}
	public void mouseClicked(MouseEvent e){
		System.out.println("Mouse Clicked");
	}
	public void mouseEntered(MouseEvent e){
	
	}
	public void mouseExited(MouseEvent e){
	
	}
	public void mousePressed(MouseEvent e){
	
	}
	public void mouseReleased(MouseEvent e){
	
	}
	
	
}
