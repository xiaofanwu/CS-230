/* CorrectChange.Java
 CS230 PS 1 
 Written by: Xiaofan Wu
 Modified by:Xiaofan Wu
 Modified date:09/05/2015
*/
import java.util.*;
 
public class CorrectChange {
  //create a method
  public static void main(String[] args) {
    //ask the user for the money they want to exchange. Make sure it scans for a double, because the user can type in
    //non-integers with decimals
    Scanner scan = new Scanner(System.in);
    System.out.println("How much money do you want to make change for?");
    Double dollar = scan.nextDouble();
    //multiply the dollar by 100 to convert it to cents. Convert the integers to double after multiplication.
    int dollarInCents= (int)(dollar * 100.0);
     //call the helper method to figure out the amount of each denomination. Since the helper method returns the 
    //left over amount after counting one denomination, I just need to get the left over total from previous 
    //step
    int twenty = makeChangeWithOneDenomination(dollarInCents, "$20 bills" , 2000);
    int five = makeChangeWithOneDenomination(twenty, "$5 bills" , 500);
    int one = makeChangeWithOneDenomination(five, "$1 bills" , 100);
    int quarters = makeChangeWithOneDenomination(one, "quarters" , 25);
    int dimes = makeChangeWithOneDenomination(quarters, "dimes" , 10);
    int nickels = makeChangeWithOneDenomination(dimes, "nickels" , 5);
    int pennies = makeChangeWithOneDenomination(nickels, "pennies" , 1);
  }
  //create a helper method
  public static int makeChangeWithOneDenomination(int total, String denominationName, int denomination) {
     //get the quantity of each denomination by using division
      int quantityOfMoney=total/denomination;
      //print out a sentence that tells the user how many certain denomination they can exchange
      System.out.println(quantityOfMoney + " " + denominationName);
      //figure out the remainder to use for next round of division
      return total%denomination;
  }
}

    
