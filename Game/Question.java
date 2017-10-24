public class Question {
    String question;
    String rightAnswer;
    String wrongAnswer1;
    String wronganswer2;
    
    public Question(ArrayList<String> questionSpecifics) {
        question = questionSpecifics[1]
        rightAnswer = questionSpecifics[2]
        wrongAnswer1 = questionSpecifics[3]
        wronganswer2 = questionSpecifics[4]
    }
    
    public String toString() {
        return "Question: " + question + "\n" + rightAnswer + "\n" + wrongAnswer1 + "\n" + wronganswer2
    }
    
}