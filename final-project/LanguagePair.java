/* Final Project 
 * Xiaofan Wu, Yuanzhen Pan
 * Language Pair
 * Yuanzhen is primarily responsible for this file
 */

//create a class to link two vocab of different languages together using an array
public class LanguagePair{
  
  String[] wordPair;
  
  public LanguagePair(String nL, String fL){
    wordPair= new String[2];
    wordPair[0]=nL;
    wordPair[1]=fL;
  }
  
  //getter for native language
  public String getN(){
    return wordPair[0];
  }
  
  // getter for foreign language
  public String getF(){
    return wordPair[1];
  }
  
  public String toString(){
    return (wordPair[0] + " " + wordPair[1]) ;
  }
  
  public static void main(String[] args){
    LanguagePair nn= new LanguagePair("one","uno");
    System.out.println(nn.getN()+ " "+nn.getF());
  }
}
