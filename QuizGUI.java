import javax.swing.*;          
import java.awt.*;           
import java.util.*;
import javax.swing.border.*;
import java.awt.event.*;    
import java.awt.image.BufferedImage;

/**
 * QuizGUI class to start the gui and call different parts of the game
 *
 * @author Group H
 * @version 29/04/2018
 */
public class QuizGUI extends JFrame
{
    //declaring variables
    private boolean showBorders;
    private QuestionsMC newMC;
    private MathsQuestion newMaths;
    private Scoreboard newScoreboard;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private String score;
    private int point = 0;
    private int submit = 1;
    String input = new String();
    private int inputmaths;
    private int clickedButton = 0;
    private JTextField tf1 = new JTextField();
    private JTextField tf2 = new JTextField();
    private JButton b11 = new JButton();
    private JButton b12 = new JButton();
    private String mathsQuestion = new String();
    private String username = new String();
    private long time = 0;
     
    /**
     * QuizGUI constructor to execute different parts of the game
     */
    public QuizGUI(){
        //Setting title of the window 
        super("PCSD QUIZ");
        
        //setting the gui size
        setSize(600,300);
        
        //making the gui visible
        showBorders = true;
                
        //starting the timer
        startTimer();
        
        //launches the first game
        mcGUI();
    }
    
    /**
     * Method to execute the multiple choice questions
     */
    public void mcGUI(){   
        // //preparing the gui for multiple choice questions
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        
        newMC = new QuestionsMC();
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        score = new String();  
        
        point = 0;
        
        //loop for ten randomised sequence multiple choice questions
        for( int i = 0; i<10 ; i++){ 
            //initialising variables
            JPanel p11 = new JPanel();
            JPanel p12 = new JPanel();
            JPanel p13 = new JPanel();
            String[] answers = new String[5];
            submit = 1;
            clickedButton = 0;
            newMC.createQuestion(i);
            answers = newMC.createAnswers(newMC.createQuestion(i));
            
            //adding title
            p11.add(addBorder(new JLabel("Quiz Game")));
            contentPane.add(p11);
            
            //adding scorebar
            JLabel j1 = new JLabel("Score: " + point);
            p12.add(addBorder(j1));
            contentPane.add(p12);
            JPanel p15 = new JPanel();
            
            //adding the question to screen
            p13.add(addBorder(new JLabel((i+1) + ". " + newMC.createQuestion(i))));
            contentPane.add(p13);
            
            //setting layout of buttons
            JPanel p14 = new JPanel(new GridLayout(2,2));
            
            //naming the buttons with possible answers
            b1 = new JButton(answers[0]);
            b2 = new JButton(answers[1]);
            b3 = new JButton(answers[2]);
            b4 = new JButton(answers[3]);
            
            p14.add(addBorder(b1)); // First Answer
            p14.add(addBorder(b2)); // Second Answer
            p14.add(addBorder(b3)); // Third Answer
            p14.add(addBorder(b4)); // Fourth Answer
            contentPane.add(p14);
            
            //making everything visible
            setVisible(true);
            
            //while loop to wait for the press of the button
            while (submit == 1){
                
                //checking if first answer was selected
                b1.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b1){
                            clickedButton = 1;
                            submit = 0;
                        }
                        
                        }
                    });
                
                //checking if second answer was selected
                b2.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b2){
                            clickedButton = 2;
                            submit = 0;
                        }
                        
                        }
                    });
                    
                //checking if third answer was selected
                b3.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b3){
                            clickedButton = 3;
                            submit = 0;
                        }
                        
                        }
                    });
                    
                //checking if fourth answer was selected
                b4.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b4){
                            clickedButton = 4;
                            submit = 0;
                        }
                        
                        }
                    });
                
                }
            
            //giving the point if the answer was right
            if (Integer.valueOf(answers[4]) == clickedButton-1){
                point++;
            }
            
            //removing all gui elements
            p11.removeAll();
            p12.removeAll();
            p13.removeAll();
            p14.removeAll();
            p15.removeAll();
            contentPane.removeAll();
            
        }
        
        photoGUI();
        
    }   
    
    /**
     * Method to execute photo questions
     */
    public void photoGUI(){
        //preparing the gui for multiple choice questions
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        
        //Initialising variables
        submit = 1;
        PictureQuestion picQ = new PictureQuestion();
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        BufferedImage[] bufPics = new BufferedImage[5];
        bufPics = picQ.readImages();
        String[][] allAns = new String[5][3];
        allAns = picQ.possibleAnswers();
        
        //for loop to cycle through photo questions
        for (int i=0 ; i<5 ; i++){
           //initialising variables
           submit = 1;
           JPanel p1 = new JPanel();
           JPanel p4 = new JPanel();
           JPanel j1 = new JPanel();
           JPanel p3 = new JPanel();
           
           
           //adding quiz name to screen
           p1.add(addBorder(new JLabel("Photos Quiz")));
           contentPane.add(p1);
           
           //adding score to screen
           j1.add(addBorder(new JLabel("Score: " + point)));
           contentPane.add(j1);
           
           //adding image to screen
           contentPane.add(new JLabel(new ImageIcon(bufPics[i])));
           
           //making the layout of the button next and text input
           JPanel p2 = new JPanel(new GridLayout(1,2));
           
           //Adding next button to screen
           b11 = new JButton("Next");
           p2.add(addBorder(b11));
           contentPane.add((p2));
           
           //adding the text field to screen
           tf1 = new JTextField("Who is this?");
           p3.add(addBorder(tf1));
           contentPane.add(addBorder(p3));
           
           //saving possible anwers
           String firstAnswer  = allAns[i][0];
           String secondAnswer = allAns[i][1];
           String thirdAnswer  = allAns[i][2];
           
           //calling the button listener which checks if the right
           //answer was inputted
           listener(firstAnswer, secondAnswer, thirdAnswer);
           
           //fitting everything to gui
           pack();
           
           //making everything visible
           setVisible(true);
           
           //while loop to check when the next button is pressed
           while (submit == 1) {
                
                b11.addActionListener(new ActionListener(){ 
                     public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b11){
                            submit = 0;
                        }
                    }
                    });
                    
           }    
                          
             
           
           //removing all elements from screen
           p1.removeAll();
           p2.removeAll();
           p3.removeAll();
           contentPane.removeAll();
           
        }
        
        mathsGUI();
        
    }    
    
    /**
     * Method to check whether photo answer was correct
     * 
     * @param Strings of first, second and third possible answers 
     */
    public void listener(String firstAnswer, String secondAnswer, String thirdAnswer){
        
        //checking if next button was pressed
        b11.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    //grabbing the user input from the text box
                    input = tf1.getText().toLowerCase();
                    
                    //if user input is the same as one of the possible answer
                    //give user a point
                    if(input.equals(firstAnswer) || input.equals(secondAnswer) || input.equals(thirdAnswer)){
                        point++;
                    } 
                    
                }
            });
        
             
        }
    
    /**
     * Method to check whether the maths answer was correct
     * 
     * @param integer of user inputted answer 
     */
    public void listenerMaths(int answer){
        //checking when next button is pressed
        b12.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    //grabbing user input and converting it to integer
                    inputmaths = Integer.valueOf(tf2.getText());
                    
                    //if answer is correct giving user a point
                    if(answer == inputmaths){
                        point++;
                    } 
                    
                }
            });
        
        }    
    
    /**
     * Method to execute the maths questions
     */
    public void mathsGUI(){
        
        //preparing the window for maths questions
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        
        //initialising the variables
            newMaths = new MathsQuestion();
            mathsQuestion = newMaths.randomMathsQuestion();
            int answer = newMaths.answerMaths();
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();
            JPanel p4 = new JPanel();
            JPanel p5 = new JPanel();
            
        //for loop to cycle through the random maths questions
        for (int i = 0 ; i<5 ; i++){
            //assigning values to variables
            JLabel l1 = new JLabel("Maths Quiz");
            JLabel j1 = new JLabel("Score: " + point);
            JLabel l2 = new JLabel(mathsQuestion);
            tf2 = new JTextField("Answer?");
            b12 = new JButton("Next");
            submit = 1;

            //adding borders to elements
            p1.add(addBorder(l1));
            p5.add(addBorder(j1));
            p2.add(addBorder(l2));
            p3.add(addBorder(tf2));
            p4.add(addBorder(b12));
            
            //adding all elements to the gui
            contentPane.add(p1);
            contentPane.add(p5);
            contentPane.add(p2);
            contentPane.add(p3);
            contentPane.add(p4);
            
            //calling maths listener to check whether the answer was correct
            listenerMaths(answer);
            
            //fitting all elements to the gui
            pack();
            
            //making all elements visible
            setVisible(true);
            
            //Stopping the for loop until the button isn not pressed
            while(submit == 1){
                b12.addActionListener(new ActionListener(){ 
                     public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b12){
                            submit = 0;
                        }
                    }
                    });
            }
            
            //removing all elements from screen
            p1.removeAll();
            p2.removeAll();
            p3.removeAll();
            p4.removeAll();
            contentPane.removeAll();
        }
        
        //ending the timer
        endTimer();
        
        //letting user input their name
        askName();
        
        //showing the new scoreboard
        scoreBoardGUI();
    }
    
    /**
     * Method to add borders around JComponent
     * Credit:
     * Aidan Slingsby
     * a.slingsby@city.ac.uk
     * Last modified: Tuesday, 3 April 2018, 12:10PM
     * https://moodle.city.ac.uk/mod/page/view.php?id=977210
     */
    public JComponent addBorder(JComponent component)
    {       
        // Create blank border of 4 pixels along the top, left, bottom and right.
        Border spacer = BorderFactory.createEmptyBorder(1,1,1,1);
         
        // Either combine the spacer with a grey border, or leave blank.
        if (showBorders == true)
        {
            component.setBorder(BorderFactory.createCompoundBorder(
                                 spacer, BorderFactory.createLineBorder(Color.GRAY)));
                 
        }
        else
        {
            component.setBorder(spacer);
        }
         
        return component;
    }
    
    /**
     * Method to create and show the scoreboard
     */
    public void scoreBoardGUI(){
        //preparing the gui for scoreboard
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        
        //initialising variables
        String[][] stringScoreboard;
        Scoreboard newScoreboard = new Scoreboard();
        b1 = new JButton("Menu");
        
        //making the new scoreboard by using methods from scoreboard class
        stringScoreboard = newScoreboard.dividedLinesScore(newScoreboard.overwriteAndOutputSc(username, point, time));
        
    
        //assingning values to the scoreboard labels
        JLabel j0 = new JLabel("-----LEADERBOARD-----");
        
        JLabel j60 = new JLabel("Position");
        JLabel j61 = new JLabel("Player");
        JLabel j62 = new JLabel("Score");
        JLabel j63 = new JLabel("Time");
        
        JLabel j10 = new JLabel(stringScoreboard[0][0]);
        JLabel j11 = new JLabel(stringScoreboard[0][1]);
        JLabel j12 = new JLabel(stringScoreboard[0][2]);
        JLabel j13 = new JLabel(stringScoreboard[0][3]);
        
        JLabel j20 = new JLabel(stringScoreboard[1][0]);
        JLabel j21 = new JLabel(stringScoreboard[1][1]);
        JLabel j22 = new JLabel(stringScoreboard[1][2]);
        JLabel j23 = new JLabel(stringScoreboard[1][3]);
        
        JLabel j30 = new JLabel(stringScoreboard[2][0]);
        JLabel j31 = new JLabel(stringScoreboard[2][1]);
        JLabel j32 = new JLabel(stringScoreboard[2][2]);
        JLabel j33 = new JLabel(stringScoreboard[2][3]);
        
        JLabel j40 = new JLabel(stringScoreboard[3][0]);
        JLabel j41 = new JLabel(stringScoreboard[3][1]);
        JLabel j42 = new JLabel(stringScoreboard[3][2]);
        JLabel j43 = new JLabel(stringScoreboard[3][3]);
        
        JLabel j50 = new JLabel(stringScoreboard[4][0]);
        JLabel j51 = new JLabel(stringScoreboard[4][1]);
        JLabel j52 = new JLabel(stringScoreboard[4][2]);
        JLabel j53 = new JLabel(stringScoreboard[4][3]);
        
        //creating a layout
        JPanel p0 = new JPanel();
        
        JPanel p1 = new JPanel(new GridLayout(1,4));
        JPanel p2 = new JPanel(new GridLayout(1,4));
        JPanel p3 = new JPanel(new GridLayout(1,4));
        JPanel p4 = new JPanel(new GridLayout(1,4));
        JPanel p5 = new JPanel(new GridLayout(1,4));
        JPanel p6 = new JPanel(new GridLayout(1,4));
        
        JPanel p7 = new JPanel(new GridLayout(1,1));
        
        
        //adding borders to all elements
        p0.add(addBorder(j0));
        
        p1.add(addBorder(j10));
        p1.add(addBorder(j11));
        p1.add(addBorder(j12));
        p1.add(addBorder(j13));
        
        p2.add(addBorder(j20));
        p2.add(addBorder(j21));
        p2.add(addBorder(j22));
        p2.add(addBorder(j23));
        
        p3.add(addBorder(j30));
        p3.add(addBorder(j31));
        p3.add(addBorder(j32));
        p3.add(addBorder(j33));
        
        p4.add(addBorder(j40));
        p4.add(addBorder(j41));
        p4.add(addBorder(j42));
        p4.add(addBorder(j43));
        
        p5.add(addBorder(j50));
        p5.add(addBorder(j51));
        p5.add(addBorder(j52));
        p5.add(addBorder(j53));
        
        p6.add(addBorder(j60));
        p6.add(addBorder(j61));
        p6.add(addBorder(j62));
        p6.add(addBorder(j63));
        
        p7.add(addBorder(b1));
        
        //adding elements to the gui
        contentPane.add(addBorder(p0));
        contentPane.add(addBorder(p6));
        contentPane.add(addBorder(p1));
        contentPane.add(addBorder(p2));
        contentPane.add(addBorder(p3));
        contentPane.add(addBorder(p4));
        contentPane.add(addBorder(p5));
        contentPane.add(addBorder(p7));
        
        //fitting all elements to screen
        pack();
        setVisible(true);
        
        //button to return to menu
        b1.addActionListener(new ActionListener()
                    { 
                     public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b1){
                            contentPane.removeAll();
                            menuGUI();
                        }
                    }
                    });
    }
    
    /**
     * Method to create and show menu, however menu is not working and
     * can be accessed only after the game is finished by clicking menu button
     * on the scoreboard.
     */
    public void menuGUI(){
        //preparing gui for menu
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        
        //assigning values
        JLabel j1 = new JLabel("Menu");
        JLabel j2 = new JLabel("Choose an option:");
        JButton b1 = new JButton("Start Quiz");
        JButton b2 = new JButton("Show Scoreboard");
        JButton b3 = new JButton("Instructions");
        JButton b4 = new JButton("Quit");
        
        //initialising variables
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        
        //adding borders to elements
        p1.add(j1);
        p2.add(j2);
        p3.add(addBorder(b1));
        p4.add(addBorder(b2));
        p5.add(addBorder(b3));
        p6.add(addBorder(b4));
        
        p7.add(addBorder(p1));
        p7.add(addBorder(p2));
        p7.add(addBorder(p3));
        p7.add(addBorder(p4));
        p7.add(addBorder(p5));
        
        //adding elements to screen
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(p4);
        contentPane.add(p5);
        contentPane.add(p6);
        contentPane.add(p7);
        
        //fitting elements to screen
        pack();
        
        //making everything visible
        setVisible(true);
        
        //checking if start button was pressed
        b1.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == b1){
                    mcGUI();
                }  
            }
        });
        
        //checking if scoreboard button was pressed
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == b2){
                    //delete all elements
                    contentPane.removeAll();
                    //display the scoreboard
                    scoreBoardGUI();
                }
            }
        });
        
        //checking if instructions button was pressed
        b3.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == b3){
                    JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                    // show a joptionpane dialog using showMessageDialog
                    JOptionPane.showMessageDialog(frame,
                    "<html><br>After starting the game, click on an answer to move to the next question in the multiple choice round.<br>In the photo round, enter the celebrity's first name, surname or both names and press Next.<br>In the maths round, answer the questions as quick as you can, then press Next.</html>" ,
                    "Instructions",
                    JOptionPane.PLAIN_MESSAGE);
                }    
            }
        });
        
        //checking if quit button was pressed
        b4.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == b4){
                    System.exit(0);
                }    
            }
        });
    }
    
    /**
     * Method to ask for users name
     */
    public void askName(){
        //prepare the screen for the name input
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        
        //initialising variables
        submit=1;
        JPanel p1 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        
        //assigning values to variables
        JLabel l1 = new JLabel("What is your name?");
        tf2 = new JTextField("Name");
        b12 = new JButton("Next");

        //adding the borders to elements
        p1.add(addBorder(l1));
        p3.add(addBorder(tf2));
        p4.add(addBorder(b12));
        
        //adding elements to gui
        contentPane.add(p1);
        contentPane.add(p3);
        contentPane.add(p4);
        
        //calling the listener to check what input was inputted
        listenerUsername();
        
        //making all elements visible
        setVisible(true);
        
        //while loop to wait for user input
        while(submit == 1){
                b12.addActionListener(new ActionListener(){ 
                     public void actionPerformed(ActionEvent e){
                        if (e.getSource() == b12){
                            submit = 0;
                        }
                    }
                    });
            }
        
        //removing all elements from screen
        contentPane.removeAll();
        
    }
    
    /**
     * Method to check what user inputted
     */
    public void listenerUsername(){
        
        b12.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    //getting users input and assigning it to variable
                    //called username
                    username = tf2.getText();
                }
            });
        }
    
    /**
     * Method to check the time when the game was started
     */
    public void startTimer(){
        time = System.currentTimeMillis();
    }
    
    /**
     * Method to check the time when the game was finished, calculate
     * how long did the game took and convert it to seconds
     */
    public void endTimer(){
        time = (System.currentTimeMillis()-time)/1000;
    }
}
    
 