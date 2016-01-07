/* Final Project
 * Xiaofan Wu & Yuanzhen Pan
 * Flashcard
 * Yuanzhen is primarily responsible for this file
 */

//import everyhing explicitly, so that other programmer knows what it is in this class.  
import java.util.*;
import java.io.*;

// class that create a flashcard object with two words of different languages 
// used in FlashCardGame
public class FlashCard{
  
  private String nativeL;
  private String foreignL;
  
  public FlashCard(String nativeL,String foreignL ){
    this.nativeL=nativeL;
    this.foreignL=foreignL;
  }
  
  //getter to get native language
  public String getN(){
    return nativeL;
  }
  //getter to get foreign language
  public String getF(){
    return foreignL;
  }
  public String toString(){
    return nativeL+ ":\n"+ foreignL ;
  }
  
//This was for testing purposes
//  public static void main(String[] args){
//    FlashCard nn= new FlashCard("One","Uno");
//    System.out.println(nn);
//   }
}