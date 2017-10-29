import java.util.*;

public class Question extends Message{
    String question;
    String rightAnswer;
    String wrongAnswer1;
    String wronganswer2;
    
    public Question(ArrayList<String> questionSpecifics) {
        question = questionSpecifics.get(0);
        rightAnswer = questionSpecifics.get(1);
        wrongAnswer1 = questionSpecifics.get(2);
        wronganswer2 = questionSpecifics.get(3);
    }
    
    public String toString() {
        return question + "\n" + rightAnswer + "\n" + wrongAnswer1 + "\n" + wronganswer2 + "\n";
    }
    
    public String getQuestion() {
        return question;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }
    public String getWrongAnswer2() {
        return wronganswer2;
    }
    
}
