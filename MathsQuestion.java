import java.io.*;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Class for generating mathematical questions
 *
 * @author Group H
 * @version 29/04/2018
 */
public class MathsQuestion
{
    // declaring variables
    private int firstNumber;
    private int secondNumber;
    private int randomAction;
    private int correctAnswer;
    private String exerciseString;
    private Random rand;
    
    /**
     * MathsQuestions constructor to initialise variables
     */
    public MathsQuestion()
    {
        //initialising variables
        rand = new Random();
        firstNumber = rand.nextInt(9);
        secondNumber = rand.nextInt(9);
        randomAction = rand.nextInt(3);
    }

    /**
     * Method to create a random maths question
     *
     * @return    Random maths question in string
     */
    public String randomMathsQuestion()
    {
        //creating a randomised maths question and making it a string type
        
        if (randomAction == 0){
            exerciseString = (String.valueOf(firstNumber+1) + " + " + String.valueOf(secondNumber+1));
        }else if (randomAction == 1){
            exerciseString = (String.valueOf(firstNumber+1) + " - " + String.valueOf(secondNumber+1));
        }else {
            exerciseString = (String.valueOf(firstNumber+1) + " * " + String.valueOf(secondNumber+1));
        }
        
        //returning a question in string
        return (exerciseString);
            
        
    }
    
    /**
     * Method to calculate the answer for previously generated question
     *
     * @return    Correct answer in string
     */
    public int answerMaths()
    {
        //calculating the right answer to the question
        
        if (randomAction == 0){
            correctAnswer = (firstNumber+1) + (secondNumber+1);
        }else if (randomAction == 1){
            correctAnswer = (firstNumber+1) - (secondNumber+1);
        }else {
            correctAnswer = (firstNumber+1) * (secondNumber+1);
        }
        
        //returning the integer of the right answer
        return (correctAnswer);
        
    }
    
    
}
