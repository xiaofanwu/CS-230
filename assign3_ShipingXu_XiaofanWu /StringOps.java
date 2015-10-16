/* StringOps.java
 * Implement two new methods that operate on Strings
 * CS230 Assignment3 Task1: Fun with Strings
 * Written by: Shiping Xu and Xiaofan Wu
 */

public class StringOps {  
  // returns a new string constructer from the input string  
  public static String removeChar (String S, char ch) {
    if(S.charAt(0) == ch) { // if the first letter in S is ch, return everything after first letter
      return S.substring(1); 
  // if there's only one letter left and letter is not the character, return the letter
    } else if (S.length() == 1 && S.charAt(0) != ch) {
      return S; 
 // recursive case, recursively call removeChar and append the first letter
    } else { 
      return S.charAt(0) + removeChar(S.substring(1), ch);
    }
  }  
  
// check if two words are anagrams  
  public static void testAnagram (String word1, String word2) { 
    // if two words have exactly same letter, but in a different order
    if (helper(word1, word2) && !(word1.equals(word2))) {
      System.out.println(word1 + " and " + word2 + " are anagrams");     
    } else { // if two words are not anagrams
      System.out.println(word1 + " and " + word2 + " are not anagrams");  
    }   
  }    

  // help method for testAnagram()
  private static boolean helper(String word1, String word2) {
    // if at the end of recursion, all letters in word1 are removed but letters in word2 are not
    if (word1.length() == 0 && word2.length() != 0) {
      return false; //the two words are not anagrams
// if at the end of recursion, all letters in word1 and word2 are removed
    } else if (word1.length() ==0 && word2.length() == 0) {
      return true; // the two words are anagrams
    } else { //recursive case, recursively remove letters in word1 and word2
      String word2new = removeChar(word2, word1.charAt(0));
      String word1new = removeChar(word1, word1.charAt(0));
      return helper(word1new, word2new);
    }
  }
    
// main method, test other two methods   
  public static void main (String[] args) {
    System.out.println(removeChar("java", 'a'));
    System.out.println(removeChar("banana", 'n'));
    testAnagram("melon","lemon");
    testAnagram("hello", "hello");
  }
  
}