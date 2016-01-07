/* PalindromeChecker.java
 * CS230 Assignment #5 Task 2
 * Written by: Shiping Xu & Xiaofan Wu
 * 10/25/2015
 */

import javafoundations.LinkedStack;

public class PalindromeChecker {  
  String string; // the string to check for palindrome
  LinkedStack<Character> stack; // the stack to be used in the process
  
  // constructor
  public PalindromeChecker() {    
    stack = new LinkedStack<Character>();
    string = ""; 
  }
  
  // empty the stack
  private void cleanStack() {
    while (!stack.isEmpty()) stack.pop(); 
  }
  
  // returns true if the string is a palindrome
  public boolean checkPalindrome() {
    cleanStack();  // first make sure the stack is empty
    char expression[] = string.toCharArray(); // convert the input string in an array of characters
    for (int i=0; i<string.length(); i++) { // beginning of a for loop
      stack.push(new Character(expression[i])); // push every character in the array into the stack
    }
   // for every element in the string, compare it with the top element of the stack     
    for (int i=0; i<string.length();i++) {
      if (!(string.charAt(i) == stack.pop()))
        return false; // if they are not the same character, return false
    }
    return true; // return true if all 
  }
  
  // setter -- to set the string
  public void setString(String s1) {
    string = s1;
  }
  
  // getter -- return the string
  public String getString() {
    return string;
  }
  
 // toString method to display the string
  public String toString() {
    String s = "Word '" + string + "'";
    return s;
  }
  
  
  // main method
  public static void main (String[] args) {
    PalindromeChecker p1 = new PalindromeChecker();
    // if the use enter a linear collection of alphanumeric characters
    if (args.length !=0) { 
      String tempString =""; // creates an empty tempString
      for (int i=0; i<args.length; i++) {
        tempString += args[i]; // put all elements in args array into tempString
      } // convert the the string to lower case and remove all spaces and commas
      p1.setString((tempString.replaceAll("( )|(,)", "")).toLowerCase()); 
      if (p1.checkPalindrome()) // check if the string is a palindrome
        System.out.println(p1 + " is a palindrome.");
      else 
        System.out.println(p1 + " isn't a palindrome.");
    } else { // if the user enters nothing
      System.out.println("Please enter one word that you would like to test:");
    }   
  }  
}