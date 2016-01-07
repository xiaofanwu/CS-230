/* Final Project
 * Xiaofan Wu & Yuanzhen Pan
 * PlayGameFrame
 * Xiaofan is primarily responsible for this file
 */

//import everyhing explicitly, so that other programmer knows what it is in this class.
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


//PlayGameFrame uses PlayGame class to get the cards and display them. Additionally playGameFrame has buttons
//to go to other pages.
public class PlayGameFrame extends JFrame{
  
  //declare all the variables that we might need in the listener
  private JButton home,newGame,learn,about;
  private JButton[] buttons;
  private LinkedList<String> cards;
  private PlayGame deck;
  private ImageIcon cardIm;
  private JPanel display;
  private LinkedList<Integer>cardIndex;
  private int c;
  private Integer scores;
  private JLabel score, winningM;
  private JFrame newGameF,homeFrame,aboutF,newFlashF;
  private ActionListener listener;
  private Timer timer;
  private boolean disable;
  private String nFile,lFile;
  
  
  //take in the title of the frame, and two files (native language and foreign language. Will be invoked in other frame.
  public PlayGameFrame(String title,String nFile,String lFile){
    
    super(title);
    
    //set file names
    this.nFile=nFile;
    this.lFile=lFile;
    //LinkedList to put in index of cards that are clicked. 
    cardIndex=new LinkedList<Integer>();
    c=0; //count for how many click the user clicked
    scores=0;  //keep score.
    
    try{
      
      //create the deck and randomnize it. 
      deck=new PlayGame(nFile,lFile);
      cards=deck.random();
      
      //create the layout of this window
      setLayout(new BorderLayout());
      setPreferredSize(new Dimension(800, 800));
      setBackground (Color.white);//set background color to be white
      
      //create the cards as buttons for the user to click.
      buttons=new JButton[cards.size()];
      System.out.println(cards.size());
      
      //initialize the display panel to put the button cards in, set its layout.
      display = new JPanel();
      display.setLayout(new GridLayout(4,4));
      
      ButtonListener cardListener=new ButtonListener();
      
      //create the cards in a loop, since won't know the exact number of cards.
      BufferedImage cardI=ImageIO.read(new File("image/card.png"));
      cardIm=new ImageIcon(cardI);
      for (int i=0;i<cards.size();i++){
        //initially set every card with the background picture and add action listener 
        buttons[i]=new JButton(cardIm);
        display.add(buttons[i]);
        buttons[i].addActionListener(cardListener);
      }
      
      //setting up listener for waiting time after user clicks on two card, after waiting, check the cards.
      listener = new ActionListener(){
        public void actionPerformed(ActionEvent event){
          checkCard();
        }
      };
      
      //create top menu panel and set layout for the top menu buttons.
      JPanel menu = new JPanel();
      menu.setPreferredSize(new Dimension(40,70));
      menu.setLayout(new GridLayout(1,5));
      menu.setBackground(Color.white);
      
      //take in images of the menu buttons 
      BufferedImage homeB=ImageIO.read(new File("image/homeB.png"));
      BufferedImage newGameB=ImageIO.read(new File("image/newGameB.png"));
      BufferedImage learnB=ImageIO.read(new File("image/learnB.png"));
      BufferedImage aboutB=ImageIO.read(new File("image/aboutB.png"));
      
      //create the top menu buttons
      home=new JButton(new ImageIcon(homeB));
      home.addActionListener(cardListener);
      menu.add(home);
      
      newGame=new JButton(new ImageIcon(newGameB));
      newGame.addActionListener(cardListener);
      menu.add(newGame);
      
      learn=new JButton(new ImageIcon(learnB));
      learn.addActionListener(cardListener);
      menu.add(learn);
      
      about=new JButton(new ImageIcon(aboutB));
      about.addActionListener(cardListener);
      menu.add(about);
      
      score=new JLabel();
      score.setText("\t Your Score: " + Integer.toString(scores));
      menu.add(score);
      
      //display for winning message after the game is done
      winningM=new JLabel();
      
      //add everything and set the frame to be true.
      add(menu,BorderLayout.PAGE_START);
      add(display,BorderLayout.CENTER);
      pack();
      setVisible(true);  
    }
    
    //catch exception if that the file names don't exist.
    catch (IOException e){
      System.out.println(e);
    }
  }
  
  
  //check if two cards match, if they do set the text field to be none and disable the buttons, other wise flip them back by setting the 
  //button image to be the background picture again.
  public void checkCard(){
    //if the two cards are the same,disable buttons and clear everyhing in the button
    if (deck.checkMatch(cards.get((cardIndex.get(0))),cards.get((cardIndex.get(1))))){
      
      buttons[(cardIndex.get(0))].setIcon(null);
      buttons[(cardIndex.get(1))].setIcon(null);
      buttons[(cardIndex.get(0))].setText("");
      buttons[(cardIndex.get(1))].setText("");
      buttons[(cardIndex.get(0))].setEnabled(false);
      buttons[(cardIndex.get(1))].setEnabled(false);
      
      //add 10 points to score if two cards match
      scores+=10;
      score.setText("\t  Your Score: " + Integer.toString(scores));
    }
    
    //if the two cards don't match, set text to be blank and flip the cards back by resetting the background picture.
    else{
      
      buttons[cardIndex.get(0)].setIcon(cardIm);
      buttons[(cardIndex.get(1))].setIcon(cardIm);
      buttons[(cardIndex.get(0))].setText("");
      buttons[(cardIndex.get(1))].setText("");
      
      //take five points off if the the cards didn't match
      scores-=5;
      score.setText("\t  Your Score: " + Integer.toString(scores));
    }
    
    //after done counting, reset the counter that counts how many cards are fliped. Reset boolean disable to make the buttons clickable again.
    c=0;
    disable=false;
    
    //each time, check if the game is over. If it is, remove everything and display the winning message.
    if (gameOver()){
      display.removeAll();
      display.updateUI();
      winningM.setText("  Congratulations! You won the game with a score of " + Integer.toString(scores)+"!");
      winningM.setFont(winningM.getFont().deriveFont(Font.BOLD, 20));
      winningM.setForeground(Color.ORANGE);
      display.add(winningM);
    }
  }
  
  
  //helper method to check check if the game is over by checking if every button in the buttons list is disabled.
  private boolean gameOver(){
    for (int i=0;i<cards.size();i++){
      if (buttons[i].isEnabled())
        return false;
    }
    return true;
  }
  
  
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    new PlayGameFrame("Game Page","english.txt","spanish.txt");
  }
  
  
  //button listeners
  private class ButtonListener implements ActionListener {    
    
    public void actionPerformed (ActionEvent event) {
      //if the current state is disabled (when the cards got flipped), the user can't click anything 
      if (disable){
        return;
      }
      
      //if the user clicks on home, we want to just change the panel to home panel
      if (event.getSource()==home){
        homeFrame=new HomePageFrame("Home Page");
        setVisible(false);
        homeFrame.setVisible(true);
        invalidate();
        validate();
        repaint();
      }
      
      //if the button is clicked as about, go to about page.
      else if (event.getSource()==about){
        aboutF=new AboutFrame("about page");
        setVisible(false);
        about.setVisible(true);
        invalidate();
        validate();
        repaint();
        return;
      }
      
      //if the buttons clicked is new game, create the game frame again with the files
      else if (event.getSource()==newGame){
        newGameF=new PlayGameFrame("Game Page",nFile,lFile);
        setVisible(false);
        newGameF.setVisible(true);
      }
      
      //if the button clicked is learn, create the learn frame with the same files
      else if (event.getSource()==learn){
        newFlashF=new FlashCardFrame("learn page",nFile,lFile);
        setVisible(false);
        newFlashF.setVisible(true); 
      }
      
      //previously, all the statements had to have return instead of writing if and else statement to 
      //make sure that if the menu buttons is clicked, the following won't keep gong.
      //check which button is clicked. 
      for (int i=0;i<cards.size();i++){   
        
        //if that button is clicked, add that button index in the cardIndex and find that card information and display that information
        if(event.getSource() == buttons[i]){
          cardIndex.addFirst(i);          
          String input=cards.get(i);
          buttons[i].setIcon(null);
          buttons[i].setText(input);
          
          //increase button clicked count.
          c++;
          
          //refresh the frame
          invalidate();
          validate();
          repaint();
        }
      }
      
      //if the button clicked is two times,disable any other click, wait for two seconds, then check if current two cards are the same.
      if (c==2){
        
        //set disable buttons to be true, so that the user can no longer click any buttons
        disable=true;
        timer = new Timer(2000, listener);
        timer.setRepeats(false);
        timer.start();    
      }    
    }
  }
}


