import java.io.*;
import java.util.*

public class Enemy extends Character{
	private Question[] questions;
	
	public Enemy(int x, int y){
		super(x, y);
		File qAndAFile = new File("QuestionAndAnswerList.txt");
		if myFile.exists(){
			Scanner fileScanner = new Scanner(qAndAFile);
			while(fileScanner.hasNext()){
				ArrayList <String> questionSpecifics = new ArrayList<String>[4];
				for (int newLineCount = 0, newLineCount<4, newLineCount++){
					questionSpecifics[newLineCount] = fileScanner.next();
				}
				questions[i] = new Question(questionSpecifics);
			}
		}
	}
	public Question hit(){
		return 
	}
	
}
