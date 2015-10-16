/* Transactions.java
 * CS230 Assignment #3 Task2
 * Written by: Shiping Xu & Xiaofan Wu
 */

// Transactions --- driver class that instantiates and tests each of the account types
public class Transactions {
  
  public static void main (String[] args) {
    // instantiates cAccount1 and sAccount1
    CheckingAccount cAccount1 = new CheckingAccount(30);
    System.out.println(cAccount1);
    SavingsAccount sAccount1 = new SavingsAccount(30, 5.5);    
    System.out.println(sAccount1);
    
    // withdraw 100 from cAccount1
    cAccount1.withdraw(100);
    System.out.println(cAccount1);
    
    // withdraw 300 from sAccount1
    sAccount1.withdraw(300);
    System.out.println(sAccount1);
    
    sAccount1.deposit(100);
    
    // add monthly interest to sAccount1
    sAccount1.calculateBalance();
    System.out.println(sAccount1);  
    
  }
}