/*Final Project
 *Xiaofan Wu & Yuanzhen Pan
 *FlashCardGame
 *Yuanzhen is primarily responsible for this file
 */

//import everyhing explicitly, so that other programmer knows what it is in this class.
import java.util.*;
import java.io.*;
import javafoundations.ArrayQueue;

//class that sets up the flashcards
public class FlashCardGame{
  
  private String nativeL;
  private String foreignL;
  // variable that holds the previous card
  FlashCard previousCard;
  ArrayQueue<FlashCard> allVocabQ;
  LinkedList<FlashCard> hardVocabL;
  
  
  //constructor takes in two files, one is native language, one is foreign language to learn
  public FlashCardGame(String nativeL, String foreignL){
    
    previousCard= new FlashCard("","");
    this.nativeL=nativeL;
    this.foreignL=foreignL;
    allVocabQ= new ArrayQueue<FlashCard>();
    hardVocabL= new LinkedList<FlashCard>();
  }
  
  
  //method that imports files
  public void fromFile(){
    
    try{ // must catch exception in the case that the file given does not exist
      
      Scanner scanN = new Scanner(new File(nativeL));
      Scanner scanF = new Scanner(new File(foreignL));
      
      //add in all the words in to the hashtable. native language as key,foreign language as value
      while(scanN.hasNext() && scanF.hasNext()){
        System.out.println(scanN.hasNext());
        String wordN = scanN.next();
        String wordF=scanF.next();
        
        System.out.println(wordF);
        System.out.println(wordN);
        
        FlashCard card =new FlashCard(wordN,wordF);
        System.out.println(card);
        allVocabQ.enqueue(card);
        System.out.println(card);
      } 
      // close Scanner scan 
      scanN.close();
      scanF.close(); 
    }
    
    //catch
    catch(IOException ex){
      System.out.println("File does not exist."); 
    }
  }
  
  
  // view the current card
  public FlashCard viewCard(){
    return allVocabQ.first();
  }
  
  
  //dequeue the current card and put it at the back of the queue
  public void pass(){
    previousCard= allVocabQ.dequeue();
    allVocabQ.enqueue(previousCard);
    
// This is for testing purposes
//    System.out.println("previous Card is updated to:"+ previousCard);
//    System.out.println("The Queue: "+ allVocabQ);
  }
  
  
  public FlashCard previous(){
    return previousCard;
  }
  
  
  //check if this particular vocab already exist in the hardVocabList
  //the top flashcard of originalQ and enqueue into the hardVocabQ 
  public void addToHard(){
    if(!hardVocabL.contains(allVocabQ.first())){
      hardVocabL.add(allVocabQ.first());
    }
  }
  
  //getter for hardVocab
  public LinkedList getHard(){
    return hardVocabL;
  }
  
  public String toString(){
    return allVocabQ.toString();
  }
  
  // invoke toString of SeeHardVocab
  public String seeHardVocab(){
    return hardVocabL.toString();
  }
  
// This was for testing purposes 
//  public static void main(String[] args){
//    FlashCard cardT= new FlashCard("Two","Dos");
//    System.out.println(cardT);
//    FlashCardGame test= new FlashCardGame("english.txt","spanish.txt");
//    test.fromFile();
//    System.out.println(test);
//    System.out.println(test.viewCard());
//    test.pass();
//    System.out.println(test);
//    test.addToHard();
//    System.out.println("Hard:"+test.seeHardVocab());
//    System.out.println("AllDeck: "+test);
//    
//    test.pass();
//    System.out.println("previous: "+test.previous());
//}
}

