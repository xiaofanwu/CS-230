/* Final project 
 * Xiaofan Wu & Yuanzhen Pan
 * FlashCardFrame
 * Yuanzhen is primarily responsible for this file
 */

//import everyhing explicitly, so that other programmer knows what it is in this class.
import java.util.*;
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


// This class sets up the FlashCard page. 
// It has a main flashcard, a small card showcase the content of the previous card
// There are also several buttons
// It primarily invokes method from FlashCard class
public class FlashCardFrame extends JFrame{
  
  //declare instance variable so that the two file names can be used later to invoke the PlayGameFrame
  FlashCardGame cards;
  JLabel cardText, smallCardText,cardBackground,plainText,smallCard;
  JPanel centerPanel,southPanel,westPanel,northPanel;
  JButton buttonHome,buttonNext,buttonBefore,buttonAddToH,buttonHard,buttonAll,buttonPlayGame;
  BufferedImage next, addToHard, hardVocab,allVocab, playGame,home;
  JFrame homePageF, playGameF;
  String nativeFile,foreignFile; 
  
  
  // constructor that takes in the title of the page, native language file and foreign language file
  public FlashCardFrame(String title,String nativeFile,String foreignFile){
    super(title);
    
    this.nativeFile=nativeFile;
    this.foreignFile=foreignFile;
   
    cards=new FlashCardGame(nativeFile,foreignFile);
    cards.fromFile();
    setLayout(new BorderLayout());
    
    cardText= new JLabel(cards.viewCard().toString());
    smallCardText= new JLabel(cards.viewCard().toString());
    plainText= new JLabel("<html>"+"The previous card is: "+ "<br>");
    
    cardBackground = new JLabel(new ImageIcon("image/blankCard.jpg"));
    cardBackground.setLayout(new BorderLayout());
    centerPanel= new JPanel();
    centerPanel.add(cardBackground);
    
    smallCard = new JLabel(new ImageIcon("image/smallCard.jpg"));
    smallCard.setLayout(new BorderLayout());
    
    cardText.setFont(cardText.getFont().deriveFont(Font.BOLD, 40));
    cardText.setForeground(Color.BLUE);
    cardText.setHorizontalAlignment(JLabel.CENTER);
    cardBackground.add(cardText);
    
    plainText.setFont(cardText.getFont().deriveFont(Font.BOLD, 20));
    plainText.setForeground(Color.BLACK);
    plainText.setHorizontalAlignment(JLabel.CENTER);
    smallCard.add(plainText);
    
    smallCardText.setFont(cardText.getFont().deriveFont(Font.BOLD, 25));
    smallCardText.setForeground(Color.ORANGE);
    smallCardText.setHorizontalAlignment(JLabel.CENTER);
    smallCard.add(smallCardText);
    
    //import images 
    try{  
      home= ImageIO.read(new File("image/homeB.png"));
      next=ImageIO.read(new File("image/nextB.jpg"));
      addToHard=ImageIO.read(new File("image/addToHardB.png"));
      hardVocab =ImageIO.read(new File("image/hardVocabB.png"));
      allVocab=ImageIO.read(new File("image/allVocabB.png"));
      playGame=ImageIO.read(new File("image/playNowB.png"));
      
    } catch(IOException e){
      System.out.println("The imported image does not exist");  
    }
    
    //set up buttons
    buttonHome = new JButton(new ImageIcon(home));
    buttonNext = new JButton(new ImageIcon(next));
    buttonAddToH = new JButton(new ImageIcon(addToHard));
    buttonHard = new JButton(new ImageIcon(hardVocab));
    buttonAll = new JButton(new ImageIcon(allVocab));
    buttonPlayGame = new JButton(new ImageIcon(playGame));
    
    ButtonListener listener= new ButtonListener();
    buttonHome.addActionListener(listener);
    buttonNext.addActionListener(listener);
    buttonAddToH.addActionListener(listener);
    buttonHard.addActionListener(listener);
    buttonAll.addActionListener(listener);
    buttonPlayGame.addActionListener(listener);
    
    // this was for testing purposes
    //System.out.println(cards);
    
    southPanel= new JPanel();
    southPanel.add(buttonHard);
    southPanel.add(buttonAll);
    southPanel.add(buttonAddToH);
    southPanel.add(buttonHard);
    southPanel.add(buttonPlayGame);
    
    westPanel= new JPanel();
    westPanel.setLayout (new BorderLayout());
    westPanel.add(plainText,BorderLayout.NORTH);
    westPanel.add(smallCard,BorderLayout.CENTER);
    
    northPanel=new JPanel();
    northPanel.setLayout (new GridLayout(1,3));
    northPanel.setPreferredSize(new Dimension(40,70));
    northPanel.add(buttonHome);  
    
    add(centerPanel);
    add(southPanel);
    add(northPanel,BorderLayout.NORTH);
    add(centerPanel,BorderLayout.CENTER);
    add(buttonNext,BorderLayout.EAST);
    add(westPanel,BorderLayout.WEST);
    add(southPanel,BorderLayout.SOUTH);  
    
    pack();
    setVisible(true);
  }
  
// This is for testing purpose
//   public static void main(String[] args) {
//    JFrame.setDefaultLookAndFeelDecorated(true);
//    new FlashCardFrame("FlashCard","text/English_hard_number.txt","text/Spanish_hard_number.txt");
//  }
  
  private class ButtonListener implements ActionListener{
    
    public void actionPerformed(ActionEvent event)
    {
      if (event.getSource()==buttonNext){
        // make sure that the smallCard is shown when the user clicks Next button after viewing the hardVocab
        westPanel.add(smallCard,BorderLayout.CENTER);
        westPanel.add(plainText,BorderLayout.NORTH);
        
        // make sure that the big card is set up consistently everytime when buttonNext is Clicked
        cardText.setFont(cardText.getFont().deriveFont(Font.BOLD, 40));
        cardText.setForeground(Color.BLUE);
        cards.pass();
        cardText.setText(cards.viewCard().toString()); 
        smallCardText.setText(cards.previous().toString());  
        
      }else if(event.getSource()==buttonAddToH){
        cards.addToHard();
           
      }else if(event.getSource()==buttonHard){
        //remove the smallcard
        westPanel.remove(smallCard);
        westPanel.remove(plainText);
        
        //LinkedList to hold all hardCard
        LinkedList<FlashCard> temp= new LinkedList<FlashCard>(cards.getHard());
        String s= "<html>";
        for(int i=0; i<temp.size();i++){
          s+=temp.get(i).toString()+"<br>";
        }
        cardText.setText(s+"<html>");
        cardText.setFont(cardText.getFont().deriveFont(Font.BOLD, 20));
        cardText.setForeground(Color.RED); 
 
      //return to the queue of flash cards  
      }else if(event.getSource()==buttonAll){
        cardText.setText(cards.viewCard().toString());
        cardText.setFont(cardText.getFont().deriveFont(Font.BOLD, 40));
        cardText.setForeground(Color.BLUE);
        westPanel.add(smallCard,BorderLayout.CENTER);
        westPanel.add(plainText,BorderLayout.NORTH);
        
      // proceed to the flip card game page  
      }else if(event.getSource()==buttonPlayGame){
        playGameF=new PlayGameFrame("Game Page",nativeFile,foreignFile);
        setVisible(false);
        playGameF.setVisible(true);
        
       //This was for testing purposes
       // System.out.println("clicked PlayGame");   
        
      }else if(event.getSource()==buttonHome){
         setVisible(false);
         homePageF= new HomePageFrame("Home Page");
         homePageF.setVisible(true);     
      }
    }
  }
}






