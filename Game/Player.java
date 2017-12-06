import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;

public class Player {

	
	private int xLocation;	//Locations are top left corner
	private int yLocation;
	// actual image dimensions
	private int playerWidth = 50;
	private int playerHeight = 50;
	static final int BOARD_LEFT_BOUNDARY = 0;
	static int BOARD_RIGHT_BOUNDARY;
	static int BOARD_HEIGHT;
	private Image image;

	public Player(int boardWidth, int boardHeight, double scalingFactor){
        	BOARD_RIGHT_BOUNDARY = boardWidth;
        	BOARD_HEIGHT = boardHeight;
        	playerWidth = (int)(50*scalingFactor);
		playerHeight = (int)(50*scalingFactor);
        	try {
            		image = ImageIO.read(new File("images/brown rectangle.png"));
			image = image.getScaledInstance(playerWidth, playerHeight, Image.SCALE_SMOOTH);
        	} catch (IOException e) {
            		e.printStackTrace();
        	}
        	
		xLocation = (BOARD_RIGHT_BOUNDARY-BOARD_LEFT_BOUNDARY)/2 - playerWidth/2;
		
		yLocation = BOARD_HEIGHT - playerHeight;
		System.out.println("Player: " + playerHeight);
		System.out.println(yLocation);
		
	}

	//Chooses a direction to move
	public void move(MouseEvent e){
		if(e.getY() < yLocation-playerHeight/2){
			moveForward();
		}
		else if(e.getX() > xLocation + playerWidth){
			moveRight();
		}
		else if(e.getX() < xLocation){
			moveLeft();
		}
	}
	
	private void moveLeft(){
		if(xLocation >= BOARD_LEFT_BOUNDARY + playerWidth){	//makes sure player is on screen and has room to move
			xLocation -= playerWidth; 
			System.out.println("Player moved left");	
			System.out.println(this);	
		}
	}
	
	private void moveRight(){
		if(xLocation <= BOARD_RIGHT_BOUNDARY - playerWidth){
			xLocation += playerWidth;
			System.out.println("Player moved right");	
			System.out.println(this);
		}
	}

	private void moveForward(){
		yLocation -= playerHeight;
		System.out.println("Player moved forward");
		System.out.println(this);	
	}
	
	public String toString(){
		return "Player's Location: "+"("+xLocation+","+yLocation+")";
	}	

	public void sendPlayerToStart(){
		xLocation = (BOARD_RIGHT_BOUNDARY-BOARD_LEFT_BOUNDARY)/2 - playerWidth/2;
		yLocation = BOARD_HEIGHT - playerHeight;
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
	public void setSize(double scalingFactor){
		playerWidth = (int)(50*scalingFactor);
		playerHeight = (int)(50*scalingFactor);
		image = image.getScaledInstance(playerWidth, playerHeight, Image.SCALE_SMOOTH);
		System.out.println("Player: " + playerHeight);
	}
	public int getPlayerHeight(){
		return playerHeight;
	}
	public int getPlayerWidth(){
		return playerWidth;
	}
	public Image getImage(){
		return image;
	}
}
