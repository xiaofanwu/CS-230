/* CheckingAccount.java
 * CS230 Assignment #3 Task2
 * Written by: Shiping Xu & Xiaofan Wu
 * September 30th, 2015
 */

import java.text.DecimalFormat; // used to format output

// a child class of Account
public class CheckingAccount extends Account {
  // instance variables
  private double minimumBalance;
  private double overdraftFee;
  
  // constructor
  public CheckingAccount(double deposit) {
    super(deposit); // calls constructor in the Account class; pass deposit as the parameter
    this.minimumBalance = 10; 
    this.overdraftFee = 35;
  }
  
  // withdraw method 
  public void withdraw(double amount) {
      accountBalance = accountBalance - amount;// overdrafts is allowed in CheckingAccount
      // if balance drops below minimum balance, an overdraft fee is incurred
      if (accountBalance < minimumBalance) {
        System.out.println("\nYour account balance is below the minimum balance." + 
                                      "Therefore, an overdraft fee of $35 is incurred.");
        accountBalance = accountBalance-overdraftFee;
      }
  }
                         
  // toString() displays everything Account displays plus minimum balance                                              
  public String toString() {
    DecimalFormat fmt = new DecimalFormat("0.###");
    String s = super.toString(); //calls toString() from Account class plus the minimum balance
    s += "\nMinimum balance: " + fmt.format(this.minimumBalance);
    return s;
  }    

} 