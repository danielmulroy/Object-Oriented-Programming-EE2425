import java.io.*;
import java.util.*;

/**
 * QuestionsMC class to read the multiple choice questions and their answers.
 *
 * @author Group H
 * @date 29/04/2018
 */
public class QuestionsMC
{
    //declaring variables
    private boolean shuffle;
    private int[] sequence;
    private File questionsMultipleC;
    String[] questionLines;

    /**
     * QuestionsMC constructor to initialise the variables.
     */
    public QuestionsMC()
    {
        //initialising variables
        shuffle = true;
        sequence = new int[10];
        questionsMultipleC= new File("questions.txt");
        questionLines = new String[50];
        
    }
    

    /**
     * scanningQuestions method to scan the text file and put all lines on the array of strings
     *
     * @param  lineNumber line number of the question
     * @return    string array questionLines[lineNumber] desired line number
     */
    public String scanningQuestions(int lineNumber)
    {
        
        
        //reading the file
        try {
            File questions = new File("questions.txt");
            //reads the characters of the text file
            FileReader fileReader = new FileReader(questions);
            
            //buffered reader makes the read of the file more effic
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            int textLine = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                questionLines[textLine] = line.toString();//making files string type
                textLine++;
                
            }
            fileReader.close();
  
        }
        catch (IOException e) {
            //catching the "file not found" exception
            e.printStackTrace();
            
        }
        
        //returns the desired line number
        return questionLines[lineNumber];
    
    }
    
    
    /**
     * Method for selecting random question
     *
     * @param  questionNumber desired question number
     * @return    question in string
     */
    public String createQuestion(int questionNumber)
    {
        //If statement to check whether the random sequence of questions
        //has already been created
        if (shuffle == true){
            //creating a sequence from 0 to 9
            for (int i=0; i<10; i++){
                sequence[i]=i;
            }
            
            //preparing variables for sequence shuffle
            Random rand = new Random();
            int first = 0;
            int second = 0;
            int temp = 0;
            
            //simple algorithm to randomize the sequence
            for (int i=0; i<10; i++){
                first = rand.nextInt(10);
                second = rand.nextInt(10);
                temp = sequence[first];
                sequence[first] = sequence[second];
                sequence[second] = temp;
            }
        }
        //overwriting the shuffle variable in order to avoid unnecesary shuffling
        this.shuffle = false;
        
        //returning the question in string
        return (scanningQuestions(sequence[questionNumber]*5));
        
        
    }
    
    /**
     * method for giving the possible answers to the question and right answer
     *
     * @param  question string
     * @return    string array of possible answers and correct answer number in string
     */
    public String[] createAnswers(String question)
    {
        //creating variables
        String[] answers = new String[5];
        int rightQuestionNum = 0;
        int rightAnswerNum = 0;
        
        //checking which question was selected from the list
        for(int i=0; i<10; i++){
            if (question==questionLines[i*5]){
                rightQuestionNum = i;
                break;
            }
        }
        
        //finding the number of the right answer and removing the plus
        //from the right answer
        for(int i=0; i<4; i++){
            answers[i] = questionLines[rightQuestionNum*5+i+1];
            if(answers[i].charAt(answers[i].length() - 1)=='+'){
                rightAnswerNum = i;
                //last place of answers array is the number of right answer
                answers[4] = Integer.toString(rightAnswerNum);
                answers[i] = answers[i].substring(0, answers[i].length() - 1);
            }
        }
        
        //returning the array of possible answers and the number of
        //the right answer
        return (answers);
        
    }
    
}
