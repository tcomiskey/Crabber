import java.util.*;

/**
Contains a question that must be answered when an enemy is hit and three answer choices 

@author Erin Hitchner
*/
public class Question extends Message{
    String question;
    String rightAnswer;
    String wrongAnswer1;
    String wronganswer2;

    /**
    Constructs a quesion object with the given question and answers

    @param questionSpecifics first String is the quesion, second string is the right answer, and the other strings are wrong answers
    */
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
