import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;

/**
 * Class for generating picture questions
 *
 * @author Group H
 * @version 29/04/2018
 */
public class PictureQuestion
{
    //declaring variables
    private String[][] picAns;
    private int[] randomizeQuest;
    
    

    /**
     * Constructor of PictureQuestion to initialise the variables
     */
    public PictureQuestion()
    {
        //initialising variables
        
        picAns = new String[5][3];
        
        randomizeQuest = new int[5];
        for (int i= 0; i< 5; i++){
            randomizeQuest[i] = i;
        }
        
        randomizeQuest = randomSequence(randomizeQuest);
    }

    /**
     * Randomly sorts pictures and returns an array of pictures
     *
     * @return   Randomly sorted images
     */
    public BufferedImage[] readImages()
    {
        
        // reading all the images and storing them to a BufferedImage type array
        BufferedImage[] img = new BufferedImage[5];
        try {
            img[0] = ImageIO.read(new File("zero.jpg"));
        } catch (IOException e) {
        }
        
        try {
            img[1] = ImageIO.read(new File("one.jpg"));
        } catch (IOException e) {
        }
        
        try {
            img[2] = ImageIO.read(new File("two.jpg"));
        } catch (IOException e) {
        }
        
        try {
            img[3] = ImageIO.read(new File("three.jpg"));
        } catch (IOException e) {
        }
        
        try {
            img[4] = ImageIO.read(new File("four.jpg"));
        } catch (IOException e) {
        }
        
        //Putting images in pre-written random sequence
        
        BufferedImage first = null;
        BufferedImage second = null;
        BufferedImage temp = null;
        
        for (int i=0; i<5; i++){
            temp = img[i];
            img[i] = img[randomizeQuest[i]];
            img[randomizeQuest[i]] = temp;
        }
        
        //returning the array of randomly sorted images
        return (img);
        
    }
    
    /**
     * Assigns possible answers to randomly sorted images
     *
     * @return   Two dimmensional array of possible image answers
     */
    public String[][] possibleAnswers()
    {
        //putting all possible answers into a two dimensional matrix
        picAns[0][0] = ("grayson perry");
        picAns[0][1] = ("perry");
        picAns[0][2] = ("grayson");
        
        picAns[1][0] = ("macaulay culkin");
        picAns[1][1] = ("culkin");
        picAns[1][2] = ("macaulay");
        
        picAns[2][0] = ("judi dench");
        picAns[2][1] = ("dench");
        picAns[2][2] = ("judi");
        
        picAns[3][0] = ("jimi hendrix");
        picAns[3][1] = ("hendrix");
        picAns[3][2] = ("jimi");
        
        picAns[4][0] = ("winston churchill");
        picAns[4][1] = ("churchill");
        picAns[4][2] = ("winston");
        
        //Adjusting the answers in the same way the pictures were adjusted
        //so the pictures and answers are under the same numbers
        
        String[] first = new String[3];
        String[] second = new String[3];
        String[] temp = new String[3];
        
        for (int i=0; i<5; i++){
            temp = picAns[i];
            picAns[i] = picAns[randomizeQuest[i]];
            picAns[randomizeQuest[i]] = temp;
        }
        
        //returning the 2 dimensional matrix with the possible answers
        return (picAns);
        
    }
    
    /**
     * Creates a random sequence for picture re-arangement
     *
     * @param   Sequence of numbers from 0 to 4
     * @return   Random sequence of numbers from 0 to 5
     */
    public int[] randomSequence(int[] sequence)
    {
        Random rand = new Random();
        int first = 0;
        int second = 0;
        int temp = 0;
            
        //simple algorithm to randomize the sequence
        for (int i=0; i<10; i++){
            first = rand.nextInt(5);
            second = rand.nextInt(5);
            temp = sequence[first];
            sequence[first] = sequence[second];
            sequence[second] = temp;
        }
        
        //returning random sequence of numbers from 0 to 4
        return (sequence);
        
    }
    
}
