/*
 * This class represents a quiz question
 */
public class Question {

    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    char correctAnswer;

    public Question(String q, String a, String b, String c, String d, char correct) {
        question = q;
        optionA = a;
        optionB = b;
        optionC = c;
        optionD = d;
        correctAnswer = correct;
    }
}
