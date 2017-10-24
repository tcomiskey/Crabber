import java.util.*;

public class Board implements MouseListener extends JPanel{
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
	public moveCharacter(){
		for (Character c: things){
		
		}
	}
	public void mouseClicked(MouseEvent e){
		System.out.println("Mouse Clicked");
	}
	
	
}
