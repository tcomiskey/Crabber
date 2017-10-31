import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Controller{
	// controller attributes
	private Board b;
	private View v;
	// makes a timer attribute that can be used to start and stop the game by calling tick
	private Timer timer = new Timer(100, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			tick();
		}
	}
	);
	
	/* Controller constructor that is called from the view.
	The view is made first because the board need to recieve user input to determine
	the difficulty to use to make the board. The controller constructor is not called
	until after the user has selected a difficulty in the view. */
	
	public Controller(View v){
		this.v = v;
		// makes the board
		b = new Board(v.getDifficulty());
		// Lets the view know where the player is and what image to use for the player
		v.setPlayerX(b.getPlayer().getX());
		v.setPlayerY(b.getPlayer().getY());
		v.makePlayerLabel(b.getPlayer().getImage());
		// starts the timer and starts calling tick
		timer.start();

	}
	
	// tick method used to update the enemy locations and player location
	public void tick(){
		Player player = b.getPlayer();
		v.setPlayerX(player.getX());
		v.setPlayerY(player.getY());
		ArrayList<GameCharacter> boardEnemies = b.getEnemies();
		ArrayList<GameCharacter> viewEnemies = v.getEnemies();
		Message message;

		for(int i=0; i<boardEnemies.size(); i++){
			//Current enemy in the board and view arraylists
			GameCharacter curBoardEnemy = boardEnemies.get(i);
			GameCharacter curViewEnemy = viewEnemies.get(i);
			//Move enemies
			curBoardEnemy.move();
			//Update enemy locations in view
			curViewEnemy.setX(curBoardEnemy.getX());
			curViewEnemy.setY(curBoardEnemy.getY());
			//Determine if there is a collision
            if (b.collisionCheck(curBoardEnemy)) {
                message = curBoardEnemy.hit();
                //System.out.println(message);
                timer.stop();
                v.throwQuestion(message);
            }

            if(b.playerAtFinish()){
            	v.startWinWindow();
            }
			/*if(player.getY() == curBoardEnemy.getY() && player.getX()+player.getPlayerWidth() > curBoardEnemy.getX() && player.getX() < curBoardEnemy.getX()+curBoardEnemy.getImgWidth()){
				message = curBoardEnemy.hit();
				System.out.println(message);
				// stop the game and show the question or bonus message
				timer.stop();
                		v.throwQuestion(message);
			}*/
		}//for
		v.updateLocations();
		
	}//tick

	public void getMouseClick(MouseEvent e){
		b.getPlayer().move(e);
		v.setPlayerX(b.getPlayer().getX());
		v.setPlayerY(b.getPlayer().getY());
	}
	
	// gets called if question answered correctly
	// calls the resetPlayer method in board if the question 
    	public void resetPlayer(){
    		b.resetPlayer();
    		// updates the players location in the view
    		v.setPlayerX(b.getPlayer().getX());
		v.setPlayerY(b.getPlayer().getY());
		// restarts the timer
		timer.start();
    	}
    
    	public Board getBoard(){
    		return b;
    	}

    	public View getView(){
    		return v;
    	}
    	
   	public String toString() {
        	return "Controller!";
    	}

    	

}

	
