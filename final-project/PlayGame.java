/*Final Project
 *Xiaofan Wu & Yuanzhen Pan
 *PlayGame
 *Xiaofan is primarily responsible for this file
 */

//import everyhing explicitly, so that other programmer knows what it is in this class.  
import java.util.*;
import java.io.*;
import java.lang.Object;

// PlayGame grabs two files and adds the languages into a hashtable
// It also has additional methods that puts languages into a linkedlist which will be used later to 
// set up cards in the actual game 

public class PlayGame{
  
  private String nativeL;
  private String foreignL;
  private Hashtable dictionary;
  
  
  //constructor takes in two files, one is native language, one is foreign language to learn
  public PlayGame(String nativeL, String foreignL){
    
    this.nativeL=nativeL;
    this.foreignL=foreignL;
    dictionary= new Hashtable<String, String>();
    setHashtable();
  }
  
  
  
  //method that set the content of the hashtable dictionary
  public void setHashtable(){
    
    try{ // must catch exception in the case that the file given does not exist
      
      Scanner scanN = new Scanner(new File(nativeL));
      Scanner scanF = new Scanner(new File(foreignL));
      
      while(scanN.hasNext() && scanF.hasNext()){
        String wordN =scanN.next();
        String wordF=scanF.next();
        
        // two words in different languages will be added twice
        // with one as the key and the other value 
        dictionary.put(wordN,wordF);
        dictionary.put(wordF,wordN);
      }
      
      // close Scanner scan 
      scanN.close();
      scanF.close();
    }
    catch(IOException ex){
      System.out.println("File does not exist."); 
    }
  }
  
  
  // getter for dictionary
  public Hashtable<String,String> getHashtable(){
    return dictionary;
  }
  
  
  //method that imports files and return a LinkedList 
  //this method will be used later to create buttons in PlayGameFrame
  public LinkedList<LanguagePair> fromFile(){
    LinkedList<LanguagePair> deck=new LinkedList<LanguagePair>();
    try{ // must catch exception in the case that the file given does not exist
      Scanner scanN = new Scanner(new File(nativeL));
      Scanner scanF = new Scanner(new File(foreignL));
      
      //add in all the words in to the hashtable. native language as key,foreign language as value
      while(scanN.hasNext() && scanF.hasNext()){
        String wordN = scanN.next();
        String wordF=scanF.next();
        
        deck.add(new LanguagePair(wordN,wordF));
      } 
      // close Scanner scan 
      scanN.close();
      scanF.close();
    }
    //catch
    catch(IOException ex){
      System.out.println("File does not exist."); 
    }
    
    return deck;
  }
  
  
  // method that randomize the elements in the LinkedList
  // this will help us to randomize words on different cards
  public LinkedList<String> random(){
    
    LinkedList<LanguagePair> deck=this.fromFile();
    LinkedList<String> temp=new LinkedList<String>();
    LinkedList<Integer> r1=new LinkedList<Integer>();
    LinkedList<Integer> r2=new LinkedList<Integer>();
    
    //create a list of number from 0 to the size of the deck
    for (int i=0;i<deck.size();i++){
      r1.add(i);
    }
    
    //shuffle the numbers randomly
    Collections.shuffle(r1);
    
    for (int i=0;i<deck.size();i++){
      r2.add(i);
    }
    //shuffle the numbers randomly
    Collections.shuffle(r2);
    
    //randomly generate a list of number from 0 to size of deck
    while (!r2.isEmpty() || !r1.isEmpty()){
      //random generate a number 0 or 1, so we can either choose the native or foreign language.
      Random random = new Random();
      int num = random.nextInt(2);
      
      //either randomly get a native language or foreign language.
      if (num==1 && !r1.isEmpty()){
        temp.add(deck.get(r1.get(0)).getN());
        r1.remove(0);
      }
      
      else{
        if (!r2.isEmpty()){
          temp.add(deck.get(r2.get(0)).getF());
          r2.remove(0);
        }
      }
    }     
    return temp;
  }
  
  // method that checkMatch if the meaning of the two input Strings 
  // this helps us to check later if the meaning of the two cards are the same
  public boolean checkMatch(String input1, String input2){
    //check if input1 and input 2 match 
    if(dictionary.get(input1).equals(input2)){
      return true;
    }
    return false; 
  }
  
// This is for testing purposes.    
//  public static void main(String args[]){
//    PlayGame card=new PlayGame("english.txt","spanish.txt");
//        System.out.println(card.fromFile());
//        System.out.println(card.random());    
//    
//    PlayGame hCard=new PlayGame("EnglishHard.txt","SpanishHard.txt");      
//    hCard.setHashtable();
//    System.out.println(hCard.getHashtable());
//    System.out.println(hCard.getHashtable());
//    System.out.println(hCard.checkMatch("Uno","One"));
//  }
}
