/* Final Project
 * Xiaofan Wu & Yuanzhen Pan
 * HomePageFrame
 * Xiaofan primarily responsible for this file
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

//Home page has an image and two buttons, start and about
public class HomePageFrame extends JFrame {
  
  // sets up this panel with name of the creators and instructions and an image 
  private JButton start,about;
  private JFrame aboutFrame,startFrame;
  private BufferedImage home,startF,aboutF;
  
  public HomePageFrame(String title) {
    super(title);
    
    //take in all the image files
    
    try{
      home=ImageIO.read(new File("image/LingoTree.png"));
      startF=ImageIO.read(new File("image/startB.png"));
      aboutF=ImageIO.read(new File("image/aboutBM.png"));
    }
    
    catch (IOException e){
      System.out.println(e);
    }
    
    //set the settings for home page
    setPreferredSize(new Dimension(800, 800));
    setLayout(new BorderLayout());
    setBackground (Color.white); //set background color to be green
    
    //set up the home page picture and button holder 
    JLabel homepage= new JLabel (new ImageIcon(home));
    JPanel buttonsL=new JPanel();
    
    //set up button listener for buttons
    ButtonListener buttonListener=new ButtonListener();
    
    //set up the buttons
    start=new JButton(new ImageIcon(startF));
    start.addActionListener(buttonListener);
    
    about=new JButton(new ImageIcon(aboutF));
    about.addActionListener(buttonListener);
    
    //set up the button label holder 
    buttonsL.setPreferredSize(new Dimension(40,70));
    buttonsL.setLayout(new GridLayout(1,2));
    buttonsL.setBackground(Color.white);
    
    //add buttons to button holder
    buttonsL.add(start);
    buttonsL.add(about);
    
    //add homepage and button label
    add(homepage,BorderLayout.CENTER);
    add(buttonsL,BorderLayout.PAGE_END);
    
    pack();
    setVisible(true);   
  }
  
  public static void main(String[] args) {
    //start the home page when user run this file
    JFrame.setDefaultLookAndFeelDecorated(true);
    new HomePageFrame("Home");
  }

  
//implement button listener 
  private class ButtonListener implements ActionListener {    
    
    public void actionPerformed (ActionEvent event) {
      //if the button clicked is about button, make the home page invisible and make the about page visible.
      //the same thing goes for start page.
      
      if (event.getSource()==about){
        aboutFrame=new AboutFrame("About Page");
        setVisible(false);
        aboutFrame.setVisible(true);
      }
      
      else if (event.getSource()==start){  
        startFrame=new StartFrame("startFrame");
        setVisible(false);
        startFrame.setVisible(true);
        
      }
    }
  }
}

