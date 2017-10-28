import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.swing.*;

public class Enemy extends GameCharacter{
    private ImageIcon image;
	
	private Scanner fileScanner;
	// made static so that all enemies will have the same question index
	private static int questionIndex;
	private static ArrayList<Question> questions;	
	
	//this constructor only called once to set the static variables for all other enemies
	public Enemy(int boardWidth, int randomIndex){
		super(boardWidth);
		questions = new ArrayList<Question>();
		File qAndAFile = new File("QuestionAndAnswerList.txt");
		if (qAndAFile.exists()){
			try{
			fileScanner = new Scanner(qAndAFile);
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			
			while(fileScanner.hasNext()){
				ArrayList <String> questionSpecifics = new ArrayList<String>(4);
				for (int newLineCount = 0; newLineCount<4; newLineCount++){
					questionSpecifics.add(fileScanner.nextLine());
				}
				questions.add(new Question(questionSpecifics));
			}
		}
		questionIndex = (int) Math.floor(Math.random()*questions.size());
	}
	public Enemy(int boardWidth){
		super(boardWidth);
	}
	
	public Message hit(){
        	questionIndex++;
		return questions.get(questionIndex);
	}
	public int getX(){
		return xLoc;
	}//getX
	
	public int getY(){
		return yLoc;
	}//getY
	
	public void setX(int x){
		xLoc = x;
	}//setX
	
	public void setY(int y){
		yLoc = y;
	}//setY
	
	public void setDir(int dir){
		this.dir = dir;
	}//setDir
	
	public ImageIcon getImage(){
		return image;
	}//getImage

}
