/** 
 * Xiaofan Wu and Wenxin (Sharon) Zhang
 * DicePlayer.java
 * modified on 9/18/15
 * Creates the "hand" of a player by creating sn object of 5 random dice
  * @author   Takis Metaxas, Stella Kakavouli
  * @version     %I%, %G%
  */

///* class DicePlayer. First declare all the variable needed.
public class DicePlayer {
  
  private String name;
  final private int handSize = 5;
  private Die[] diceArray;
  
  /** 
   * Constructor: Creates a player's hand by creating and rolling the dice. 
   * @param: String The name of this Player
    */   
  public DicePlayer(String name) {
    this.name=name;
    //use the die class that we created an object that has the player's hand 
    diceArray=new Die[handSize];
      for (int i=0;i<handSize;i++){
        // put values into each slot of the array
      diceArray[i]=new Die();
      diceArray[i].roll();
    }
     

  }
  
  public String getName()  {
    return name;
  }
  
  /** 
   * returns the contents of the hand that a player holds. 
    */
  public String toString()  {
    String s = name + "::";
//return individual slot in the diceArray
    for (int i=0;i<handSize;i++){
      s=s+diceArray[i]+" ";
    }
    return  s;
  }
  
  /** Returns an array with the contents of the hand of this player. 
    * @return integer array of the hand of this player
    */
  public int[] getValues() {
    //roll dice first

    for (int i = 0; i < handSize; i++) {
      diceArray[i].roll();
    }
    //individual assign each element in the values array to a value of the diceArray.
    int [] values = new int[handSize];
    for (int i=0; i<handSize;i++) {
      values[i]=diceArray[i].getFaceValue();


  
  }
        return values;
  }
  
  /** Testing method. 
    */
  public static void main (String args[]) {
    
    DicePlayer p1 = new DicePlayer("HAL");
    System.out.println(p1);
    DicePlayer p2 = new DicePlayer("Stella");
    System.out.println(p2);
    
    //System.out.println("the array of values of the dice " + hal.toString());
  }
}
