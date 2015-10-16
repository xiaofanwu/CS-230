/* Account.java
 * CS230 Assignment #3 Task 2: Create your own Bank
 * Written by: Shiping Xu & Xiaofan Wu
 * September 30, 2015
 */

import java.text.DecimalFormat;  //used to format output
import java.util.*; // required for us to be able to use the Random class

public abstract class Account {
  // instance variables
  protected String accountNum;
  protected double accountBalance;
  
  // constructor
  public Account(double deposit) {
    Random rad = new Random(); // create a new random object
    // generate a new account number between 00000 and 99999
    this.accountNum = String.valueOf((rad.nextInt(100000)));
    if (accountNum.length() == 1)  // if randomly generated a 1 digit-number, append 4 0's in the front
      accountNum = "0000" + accountNum;
    else if (accountNum.length() == 2) // if randomly generated a 2 digit-number, append 3 0's in the front
      accountNum = "000" + accountNum;
    else if (accountNum.length() == 3) // if randomly generated a 3 digit-number, append 2 0's in the front
      accountNum = "00" + accountNum;
    else if (accountNum.length() == 4) // if randomly generated a 4 digit-number, append 1 0's in the front
      accountNum = "0" + accountNum;                                      
    this.accountBalance = deposit; // set initial balance to be the initial deposit    
  }
 
  
  // deposit method
  final public void deposit(double deposit) {
    accountBalance = accountBalance + deposit;
  }
  
  //toString() displays the account number and the balance
  // in a nicely formatted way
  public String toString() {
    DecimalFormat fmt = new DecimalFormat("0.###");
    String s = "\n*********\nAccount Number: " + this.accountNum;
    s += "\nAccount Balance: " + fmt.format(this.accountBalance);
    return s;
  }
  
  // abstract withdraw method, to be implemented in inheriting class
  abstract public void withdraw(double amount);   
  
  
  } // close Account class




