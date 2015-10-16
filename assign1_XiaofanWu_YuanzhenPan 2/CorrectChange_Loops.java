/* CorrectChange_Loops.Java
 CS230 PS 1 
 Written by: Xiaofan Wu
 Modified by:Xiaofan Wu
 Modified date:09/05/2015
*/
import java.util.*;

public class CorrectChange_Loops {
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
 
  //create a method that helps count the changes with the given input total amount of money, the name of 
  //of the denomination, and the denomination integer.
  public static int makeChangeWithOneDenomination(int total, String denominationName, int denomination) {
    //count each time that the total is bigger than the denomination
    int count=0;
    // while the total is bigger than denominaiton, each time it is bigger, I want to add a count.
    while (total>=denomination){
      count++;
      // take out the denomination amount each time
      total=total-denomination;
    }
    //after the while loop is done, print out the total amount of that denomination
    System.out.println(count + " " + denominationName);
    //also return the left over after this time, so that the next denomination can use this amount to start with.
    return total;
    

  }
}

