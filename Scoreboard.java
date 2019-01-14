import java.io.*;
import java.util.*;

/**
 * Scoreboard class to keep track of players scores.
 *
 * @author Group H
 * @date 29/04/2018
 */
public class Scoreboard
{
    // creating an array of string lines to store the scoreboard
    private String[] scoreboardLines;
    private String[][] segmentScoreboardLines;
    
    /**
     * Constructor of Scoreboard to initialise the variables
     */
    public Scoreboard(){
        //initialising variables
        scoreboardLines = new String[5];
        segmentScoreboardLines = new String[5][4];
    }
    
    /**
     * Method to scan the scoreboard
     *
     * @return  Array of scoreboard lines as appears in the text file
     */
    public String[] scanScoreboard()
    {
        try {
            File scoreboard = new File("scoreboard.txt");
            //reads the characters of the text file
            FileReader fileReader = new FileReader(scoreboard);
            
            //buffered reader makes the read of the file more efficient
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            int textLine = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                scoreboardLines[textLine] = line.toString();//making lines string type
                textLine++;
                
            }
            fileReader.close();
  
        }
        catch (IOException e) {
            //catching the "file not found" exception
            e.printStackTrace();
            
        }
        
        //returning the array of lines as appears in the questions.txt
        return (scoreboardLines);
        
        
    }
    
    /**
     * Method to divide lines of scoreboards to segments
     *
     * @param Array of scoreboard lines
     * @return  Two dimentional array of scoreboard line segments
     */
    public String[][] dividedLinesScore(String[] scoreboardLines)
    {
        //Dividing scoreboard lines into separate segments
        for(int i=0; i<5; i++){
            segmentScoreboardLines[i]=scoreboardLines[i].split(" ");
            
        } 
        
        //returning two dimentional array of scoreboard line segments
        return(segmentScoreboardLines);
    }
    
    /**
     * Method to overwrite the scoreboard
     * 
     * @param String with the players name, int with score and long with time
     * @return New array of scoreboard lines with the new player score
     */
    public String[] overwriteAndOutputSc(String name, int newScore, long newTime)
    {
        //creating variables
        String[] scoreboard = scanScoreboard();
        int counter = 1;
        int placeTime = 0;
        int placeScore = 0;
        String[] segments = new String[4];
        
        
        //for loop to check if the player had better score than any of the
        //leaders
        for(int i=0; i<5; i++){
            //grabbing the score of player from the line of string
            //and turning it to integer
            segments = scoreboard[i].split(" ");
            placeScore = Integer.valueOf(segments[2]);
            placeTime = Integer.valueOf(segments[3]);
            
            //if statement to check whether the new player score is higher
            if (newScore>placeScore){
                
                //if new player is better than 5th place just overwrite
                //the fifth place
                if (i==4){
                    scoreboard[4] = (5 + " " + name + " " + newScore + " " + newTime);
                } else{
                    //if not then rearange the scoreboard and input the player in the right place according to score
                    for(int j=0; j<5-i; j++){
                        if(3-j == -1){
                            break;
                        }
                        scoreboard[4-j] = ((5-j) + scoreboard[3-j].substring(1));
                    }
                    scoreboard[i] = ((i+1) + " " + name + " " + newScore + " " + newTime);
                    break;
                }
                
            } else if (newScore==placeScore && newTime<=placeTime) {
                
                //if new player has same score as fifth place but better time
                //overwrite the fifth place
                if (i==4){
                    scoreboard[4] = (5 + " " + name + " " + newScore + " " + newTime);
                } else{
                    //if not then rearange the scoreboard and input the 
                    //player in the right place according to score and time
                    for(int j=0; j<5-i; j++){
                        if(3-j == -1){
                            break;
                        }
                        scoreboard[4-j] = ((5-j) + scoreboard[3-j].substring(1));
                    }
                    scoreboard[i] = ((i+1) + " " + name + " " + newScore + " " + newTime);
                    break;
                }
                
            }
            
        }
        
        
        //overwriting the scoreboard.txt file with the new scoreboard
        try {
            PrintWriter writer = new PrintWriter("scoreboard.txt", "UTF-8");
            
            for (int i=0; i<5; i++){
                writer.println(scoreboard[i]);
            }
            writer.close();
        }
        catch (Exception e) {
            //catching the "file not found" exception
            e.printStackTrace();
            
        }
        
        //returns a new scoreboard array of string lines with the 5 new best players
        return (scoreboard);
        
    }
    
}