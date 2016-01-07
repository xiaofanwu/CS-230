/* final project 
 * Xiaofan Wu & Yuanzhen Pan
 * AboutFrame
 * Xiaofan is primarily responsible for this file
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
import javax.swing.Timer;


// This class sets up the about page. It has three buttons, about us, how to play and home
public class AboutFrame extends JFrame{
  private JButton home,howTo,aboutButton;
  private ImageIcon about,howInfo;
  private JLabel info;
  private JFrame homeP;
  private BufferedImage aboutI,homeB,howToPlay,aboutUsI,howImage;
  
  public AboutFrame(String title){
    super(title);
    
    try{
      
      homeB=ImageIO.read(new File("image/homeB.png"));
      howToPlay=ImageIO.read(new File("image/howToPlay.png"));
      aboutUsI=ImageIO.read(new File("image/aboutUs.png"));
      aboutI=ImageIO.read(new File("image/about.png"));
      howImage=ImageIO.read(new File("image/instruction.png"));
    } 
    
    catch (IOException e){
      System.out.println(e);
    }
    
    
    ButtonListener buttonListener=new ButtonListener();
    
    //set up the about frame
    setPreferredSize(new Dimension(800, 800));
    setLayout(new BorderLayout());
    setBackground(Color.white);   
    
    //set up the top menu button panel
    JPanel menu = new JPanel();
    menu.setPreferredSize(new Dimension(40,70));
    menu.setLayout(new GridLayout(1,5));
    
    //set up home button
    home=new JButton(new ImageIcon(homeB));
    home.addActionListener(buttonListener);
    menu.add(home);
    
    //set up side menu button 
    howTo=new JButton(new ImageIcon(howToPlay));
    howTo.addActionListener(buttonListener);
    
    //set up about button on the right
    aboutButton=new JButton(new ImageIcon(aboutUsI));
    aboutButton.addActionListener(buttonListener);
    
    //set up the information session in the middle to be displayed
    about=new ImageIcon(aboutI);
    howInfo=new ImageIcon(howImage);
    
    //set up the middle label
    info=new JLabel(about);
    
    //add everything
    add(menu,BorderLayout.PAGE_START);
    add(info,BorderLayout.CENTER);
    add(howTo,BorderLayout.LINE_START);
    add(aboutButton,BorderLayout.LINE_END);
    pack();
    setVisible(true);
    
  }
  
  
//  For testing purposes  
//  public static void main(String[] args) {
//    JFrame.setDefaultLookAndFeelDecorated(true);
//    //only need the line below for testing purpose.
//    new AboutFrame("About Us");
//  }
  
  private class ButtonListener implements ActionListener {    
    
    public void actionPerformed (ActionEvent event) {
      //if the user clicks on home, we want to just change the panel to home panel
      if (event.getSource()==home){
        
        homeP=new HomePageFrame("Home Page");
        
        setVisible(false);
        homeP.setVisible(true); 
        invalidate();
        validate();
        repaint();
        //return, so that way if the home button is clicked, the program won't keep going, since we just want 
        //to change the panel.
        return;
      }
      
      if (event.getSource()==howTo){
        //if the how to button is clicked, change the information in the JPanel
        info.setIcon(howInfo);
        return;
      }
      
      if (event.getSource()==aboutButton){
        info.setIcon(about);
        return;
        
      }  
    }
  }
}






