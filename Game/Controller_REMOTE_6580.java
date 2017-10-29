import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class Controller{

	Board b;
	View v;
	private Timer timer = new Timer(1000, new TimerListener());

	public Controller(View v){
		this.v = v;
		b = new Board(v.getDifficulty());
		System.out.println(b);
		timer.start();
        	runTick();

	}

	public void tick(){
		Player player = b.getPlayer();
		v.setPlayerX(player.getX());
		v.setPlayerY(player.getY());
		ArrayList<GameCharacter> boardEnemies = b.getEnemies();
		ArrayList<GameCharacter> viewEnemies = v.getEnemies();
		Message message;
        	System.out.println("tick");
		for(int i=0; i<boardEnemies.size(); i++){ //maybe switch to iterator
			//Current enemy in the board and view arraylists
			GameCharacter curBoardEnemy = boardEnemies.get(i);
			GameCharacter curViewEnemy = viewEnemies.get(i);
			//Move enemies
			curBoardEnemy.move();
			//Update enemy locations in view
			curViewEnemy.setX(curBoardEnemy.getX());
			curViewEnemy.setY(curBoardEnemy.getY());
			//Determine if there is a collision
			if(player.getY() == curBoardEnemy.getY() && player.getX()+player.getPlayerWidth() > curBoardEnemy.getX() && player.getX() < curBoardEnemy.getX()+curBoardEnemy.getImgWidth()){
				message = curBoardEnemy.hit();
			}
		}//for
	}//tick

    public void runTick() {
        for(int i = 0; i < 10; i++) {
            tick();
        }
    }
    
    public String toString() {
        return "Controller!!!";
    }

    public Board getBoard(){
    	return b;
    }

    public View getView(){
    	return v;
    }
}

	