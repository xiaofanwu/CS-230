/* SavingsAccount.java
 * CS230 Assignment #3 Task2
 * Written by: Shiping Xu & Xiaofan Wu
 * September 30th, 2015
 */

import java.text.DecimalFormat; // used to format output

public class SavingsAccount extends Account {
  // instance variables
  private double annualInterestRate;
  
  // constructor
  public SavingsAccount(double deposit, double rate) {
    super(deposit); // calls constructor in the Account class, pass deposit parameter
    this.annualInterestRate = rate;    
  }
  
  
  //withdraw method
  public void withdraw(double amount) {
    if (accountBalance >= amount) // if there's enough balance left in the account
      accountBalance = accountBalance - amount;
    else  // if account balance is less than withdraw amount, withdraw is not allowed
      System.out.println("\nNot able to withdraw because there is not enough balance in your account.");
  } 
  
  //add monthly interest
  public void calculateBalance() {
    // divide annual interest rate by 12 and add the monthly interest to its balance
    accountBalance = accountBalance + (accountBalance*((annualInterestRate/100.)/12.));
  }
  
  // toString() displays eveything Account displays plus the interest rate
  public String toString() {
    DecimalFormat fmt = new DecimalFormat("0.###");
    String s = super.toString(); // calls toString() from Account class
    s += "\nAnnual Interest rate: " + fmt.format(annualInterestRate); // display the annual interest rate
    return s;
  }
  
}