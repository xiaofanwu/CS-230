/*Final Project
 *Xiaofan Wu & Yuanzhen Pan
 *StartFrame
 *Xiaofan is primarily responsible for this file
 */

//This class sets up the start frame. The settings page where users 
//choose their languages,theme and level of difficulty
//Based on the user's input, it grabs corresponding text file
//and sets up flashcards and learn page by invoking FlashCardGame and PlayGameFrame
//when the user clicks on the correct buttons.

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

public class StartFrame extends JFrame{
  
  private JButton learnB,playB,homeB;
  private JComboBox nativeL,learnL,level,theme;
  private ImageIcon about,howInfo;
  private JLabel info;
  private JFrame homeF,newGameF,flashCardF;
  private String nFileName,fFileName;
  
  public StartFrame(String title){
    
    super(title);
    
    try{
      
      EventListener eventL=new EventListener();
      setPreferredSize(new Dimension(800, 800));
      setLayout(new BorderLayout());
      
      //set the buttom menu 
      JPanel menu = new JPanel();
      menu.setPreferredSize(new Dimension(40,70));
      menu.setLayout(new GridLayout(1,2));
      
      //set the top menu for home button 
      JPanel topMenu = new JPanel();
      topMenu.setPreferredSize(new Dimension(40,70));
      topMenu.setLayout(new GridLayout(1,8));
      
      //set up home button
      BufferedImage homeF=ImageIO.read(new File("image/homeB.png"));
      homeB=new JButton(new ImageIcon(homeF));
      homeB.addActionListener(eventL);
      topMenu.add(homeB);
      
      //set up the middle setting panel
      JPanel settings=new JPanel();
      settings.setLayout(new GridLayout(4,1));
      
      //set up the setting dropdown menus
      JLabel chooseN=new JLabel("  Choose your native language.");
      settings.add(chooseN);
      
      nativeL=new JComboBox();
      nativeL.addItem("English");
      nativeL.addItem("Spanish");
      nativeL.addItem("Malay");
      nativeL.addActionListener(eventL);
      settings.add(nativeL);
      
      JLabel chooseL=new JLabel("  What language do you want to learn?");
      settings.add(chooseL);
      
      learnL=new JComboBox();
      learnL.addItem("English");
      learnL.addItem("Spanish");
      learnL.addItem("Malay");
      learnL.addActionListener(eventL);
      settings.add(learnL);
      
      JLabel chooseLevel=new JLabel("  Choose a level of difficulty.");
      settings.add(chooseLevel);
      
      level=new JComboBox();
      level.addItem("easy");
      level.addItem("hard");
      level.addActionListener(eventL);
      settings.add(level);
      
      JLabel chooseTheme=new JLabel("  Choose a theme.");
      settings.add(chooseTheme);
      
      theme=new JComboBox();
      theme.addItem("number");
      theme.addItem("fruit");
      theme.addActionListener(eventL);
      settings.add(theme);
      
      //set up the learn button 
      BufferedImage learnF=ImageIO.read(new File("image/learnBStart.png"));
      learnB=new JButton(new ImageIcon(learnF));
      learnB.addActionListener(eventL);
      menu.add(learnB);
      
      //set up the Play button
      BufferedImage playF=ImageIO.read(new File("image/playB.png"));
      playB=new JButton(new ImageIcon(playF));
      playB.addActionListener(eventL);
      menu.add(playB);
      
      setBackground(Color.white);
      
      add(menu,BorderLayout.PAGE_END);
      add(topMenu,BorderLayout.PAGE_START);
      add(settings,BorderLayout.CENTER);  
      
      pack();
      setVisible(true);
    }
    
    catch (IOException e){
      System.out.println(e);
    }
  }
  
//  public static void main(String[] args) {
//    JFrame.setDefaultLookAndFeelDecorated(true);
//    //new StartFrame("Start Page");
//  }
  
  private class EventListener implements ActionListener { 
    
    public void actionPerformed (ActionEvent event) {
      
      //get the item selected
      String foreignLanguage=(String)learnL.getSelectedItem();
      String nativeLanguage=(String)nativeL.getSelectedItem();
      String levelOfD=(String)level.getSelectedItem();
      String themeOfV=(String)theme.getSelectedItem();
      
      //For testing purposes
//      System.out.println("fL"+foreignLanguage);
//      System.out.println("nL"+nativeLanguage);
//      System.out.println("Level"+levelOfD);
//      System.out.println("theme"+themeOfV);
      
      //if the user clicks on home, we want to just change the panel to home panel
      if (event.getSource()==homeB){
        homeF=new HomePageFrame("Home Page");
        setVisible(false); 
        homeF.setVisible(true);   
        invalidate();
        validate();
        repaint();
        
        // error message to remind the user to make sure that two input languages are different
      }else if (foreignLanguage.equals(nativeLanguage)){    
        JOptionPane.showMessageDialog(null, "Please make sure your native language is not the same as your target language", "alert", JOptionPane.ERROR_MESSAGE);
        
      }else if (event.getSource()==learnB){
        
        nFileName=("text/"+nativeLanguage + "_" + levelOfD + "_" + themeOfV+".txt");
        fFileName=("text/"+foreignLanguage + "_" + levelOfD + "_" + themeOfV+".txt");
        
        System.out.println("The nFileName"+nFileName);
        System.out.println("The fFName"+fFileName);
        
        flashCardF=new FlashCardFrame("Learning FlashCard",nFileName,fFileName);
        setVisible(false);
        flashCardF.setVisible(true);
        System.out.println("clicked FlashCard Learn");
        
        //if the how to button is clicked, change the information in the JPanel
      }else if (event.getSource()==playB){
        
        nFileName=("text/"+nativeLanguage + "_" + levelOfD + "_" + themeOfV+".txt");
        fFileName=("text/"+foreignLanguage + "_" + levelOfD + "_" + themeOfV+".txt");
        System.out.println(nFileName);
        
        newGameF=new PlayGameFrame("Game Page",nFileName,fFileName);
        setVisible(false);
        
        newGameF.setVisible(true);
        System.out.println("clicked newGame");     
      }  
    }
  }
}






