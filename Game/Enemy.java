import java.io.*;
import java.util.*;
import java.lang.Math;
public class Enemy extends GameCharacter{
	
	private Scanner fileScanner;
	// made static so that all enemies will have the same question index
	private static int questionIndex;
	private static ArrayList<Question> questions;	
	
	//this constructor only called once to set the static variables for all other enemies
	public Enemy(int x, int y, int randomIndex){
		//super(x, y);
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
	public Enemy(int x, int y){
		//super(x,y);
	}
	
	public Question hit(){
        questionIndex++;
		return questions.get(questionIndex);
	}
	
}
