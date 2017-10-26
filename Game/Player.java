public class Player{
	
	private int xLocation;
	private int yLocation;
	static final int PLAYER_WIDTH = 160; //Not accurate values just place holders
	static final int PLAYER_HEIGHT = 160;
	static final int BOARD_LEFT_BOUNDARY = 0;
	static final int BOARD_RIGHT_BOUNDARY = 500;

	private void moveLeft(){
		if(xLocation >= BOARD_LEFT_BOUNDARY ){
			xLocation -= PLAYER_WIDTH/4; // Increment by a quarter of player width
			System.out.println("Player moved left");	
			System.out.println(this);	
		}
	}
	
	private void moveRight(){
		if(xLocation <= BOARD_RIGHT_BOUNDARY - PLAYER_WIDTH){
			xLocation += PLAYER_WIDTH/4;
			System.out.println("Player moved right");	
			System.out.println(this);
		}
	}

	private void moveForward(){
		yLocation += PLAYER_HEIGHT/4;
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
}

