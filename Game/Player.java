import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;
public class Player{

	
	private int xLocation;	//Locations are top left corner
	private int yLocation;
	// actual image dimensions
	static final int PLAYER_WIDTH = 50;
	static final int PLAYER_HEIGHT = 50;
	static final int BOARD_LEFT_BOUNDARY = 0;
	static int BOARD_RIGHT_BOUNDARY;
	static int BOARD_HEIGHT;
	private ImageIcon image;

	public Player(int boardWidth, int boardHeight){
        	BOARD_RIGHT_BOUNDARY = boardWidth;
        	BOARD_HEIGHT = boardWidth;
		xLocation = (BOARD_RIGHT_BOUNDARY-BOARD_LEFT_BOUNDARY)/2 - PLAYER_WIDTH/2;
		yLocation = BOARD_HEIGHT - PLAYER_HEIGHT*2;
		image = new ImageIcon("images/brown rectangle.png");
	}

	//Chooses a direction to move
	public void move(MouseEvent e){
		if(e.getY() < yLocation-PLAYER_HEIGHT){
			moveForward();
		}
		else if(e.getX() > xLocation + PLAYER_WIDTH){
			moveRight();
		}
		else if(e.getX() < xLocation){
			moveLeft();
		}
	}
	
	private void moveLeft(){
		if(xLocation >= BOARD_LEFT_BOUNDARY + PLAYER_WIDTH){	//makes sure player is on screen and has room to move
			xLocation -= PLAYER_WIDTH; // Increment by a quarter of player width
			System.out.println("Player moved left");	
			System.out.println(this);	
		}
	}
	
	private void moveRight(){
		if(xLocation <= BOARD_RIGHT_BOUNDARY - PLAYER_WIDTH){
			xLocation += PLAYER_WIDTH;
			System.out.println("Player moved right");	
			System.out.println(this);
		}
	}

	private void moveForward(){
		yLocation -= PLAYER_HEIGHT;
		System.out.println("Player moved forward");
		System.out.println(this);	
	}
	
	public String toString(){
		return "Player's Location: "+"("+xLocation+","+yLocation+")";
	}	
	public int getX(){
		return xLocation;
	}
	public int getY(){
		return yLocation;
	}
	public void setX(int xCoord){
		xLocation = xCoord;
	}
	public void setY(int yCoord){
		yLocation = yCoord;
	} 	
	public int getPlayerHeight(){
		return PLAYER_HEIGHT;
	}
	public int getPlayerWidth(){
		return PLAYER_WIDTH;
	}
	public ImageIcon getImage(){
		return image;
	}
}
