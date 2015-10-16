/** 
 * Xiaofan Wu and Wenxin (Sharon) Zhang
 * DiceGame.java
 * modified on 9/18/15
 * 
 * Simulates a Dice Poker game played between the computer and user. 
  * This class definition contains a main() method that assumes 
  * that the user enters a name and an integer in the command line, for example: 
  * java PlayDice Wendy 7
  * If no arguments are passed, it will call: java Dave 5
  * @author   Takis Metaxas, Stella Kakavouli
  * @version     %I%, %G%
  */


  /** Creates a game object that contains the variables associated with a game.
    */
public class DiceGame {
  private DicePlayer humanP; 
  private DicePlayer computerP;
  private int numRounds = 3; // the default
  
  /** 
   * first constructor 
   * Creates the two players. The computer player is named HAL
   */
  public DiceGame(String name) {
    humanP=new DicePlayer(name);
    computerP=new DicePlayer("HAL");
  }

  /** 
   * 2-argument constructor 
   * @param String The name of the human Player
   * Again, the computer player is named HAL
   * @param int The number of rounds to be played
  */  
  public DiceGame(String name, int n) {

    humanP=new DicePlayer(name);
    computerP=new DicePlayer("HAL");
    numRounds=n;
  }
  
  /** 
   * Counts how many distinct values (ons, twos, threes, etc) appear in the input array
    * and stores each count onto the diceResults array.
    * PRE-CONDITION: diceResults[] should have enough length 
    * to accomodate the values found in the input array.
    *
    * @param input   the input array
    * @param diceResults  holds the multiplicity of values found in input.
    * Note that this is actually the result of this method.
    */
  private void accumulateValues(int[] input, int[] diceResults) {
    //go through the loop multiple times base on the length of the input which should be an array of five numbers
  
      for (int i=0; i<input.length;i++){
        //get the value in that particular slot and add one to that number's counter. We did minus one to offset the 
        //fact that array counting starts at 0 instead of one.
        diceResults[input[i]-1]++;
      }
    }

  
      
  
  /** 
   * Given an input array storing five dice values, 
    * determines the rank of the set of values 
    * @param input  the array with the five dice values
    * @return    the rank: an integer between 0 and 6
    */

  public int getMax(int[] input){
    int max=input[0];
    for (int i=0;i<input.length;i++){
      if (max<input[i]){
        max=input[i];
      }
    }
    return max;
  }
  

  
  public int getNonzeroNum(int[] input){
    int nonzero=0;
    for (int i=0; i<input.length;i++){
      if (input[i]!=0){
        nonzero++;
      }
    }
    return nonzero;
  }
  
    
  
  
  public int getRank(int[] input){
    int[] results= new int[6];
    accumulateValues(input, results);
    int max=getMax(results);
    int nonzero=getNonzeroNum(results);
    if (max==5){
      System.out.print("Five of a Kind ");
      return 6;
    }else{
      if (max==4){
        System.out.print("Four of a Kind ");
        return 5;
      }else{
        if (max==3){
          if (nonzero==2){
            System.out.print("Full House ");
            return 4;
          }else{
            System.out.print("Three of a Kind ");
            return 3;
          }
        }else{
          if (max==2){
            if(nonzero==3){
              System.out.print("Two Pairs ");
              return 2;
            }else{
              System.out.print("One Pair ");
              return 1;
              
            }
          }else{
            System.out.print("Nothing ");
            return 0; 
            
          }
        }
      }
    }
  }
                     
   
  /** 
   * Plays one round of the game: First the computer's (HAL) turn, 
    * then the player's turn
    * @return   0 if computer wins the round, 1 if player wins, 2 if a tie
    */
  public int playOneRound() {
//print out who is playing first by invoking the method getValues
    System.out.print("Hal is playing: ");
    int[] computer=computerP.getValues();
    int computerR=getRank(computer);
    System.out.println("("+computerR+")");
    System.out.print(humanP.getName()+" is playing: ");
    int[] human=humanP.getValues();
    int humanR=getRank(human);
    System.out.println("("+humanR+")");
    
    System.out.println("   ");
    //check who has a higher rank 
    if (computerR> humanR){
    return 0; 
    } else {
      if(computerR< humanR){
        return 1;
      }
      else{
        return 2;
      }
    }
  }
  
  /**  
   * Simulates the playing of numRounds of the Dice Poker game between 
    * HAL and player name, and prints the winner at the end
    * @param name   the player's name
    * @param numRounds  the number of rounds to play
    *****************************************************
    */
  public void playDiceGame () {
    int cwin = 0; //rounds won by the computer
    int pwin = 0; //rounds won by human player
    
    System.out.print("Good evening, " + humanP.getName());
    System.out.println(" I'm completely operational and " + 
                       "all my circuits are functioning perfectly.");
    System.out.println("How are you?");
    System.out.println("Would you like to play a game of Dice Poker? I play very well.");
    //go through the loop base on the number of rounds and invoke playOneRound eachtime we go through the loop
    for (int i=1;i<=numRounds;i++){
      System.out.println("             ");
      System.out.println("             ");
      System.out.println("*** ROUND: "+i+" ***");
      int roundResult= playOneRound(); // everytime when we write it, the function is called
      //find out who wins the game and add one to the score counter 
      if (roundResult==1){
        pwin++;
      }else{
        if (roundResult==0){
          cwin++;
        }
       
      
    }
    }
    
    
    
      
          
        
    
    // After all rounds played, determine the final winner of the game and print the results
    String results = "\nThe game was won by ";
    if (pwin > cwin) 
      results = results + humanP.getName() + " with a score of " + pwin + " to " + cwin;
    else if (cwin>pwin) 
      results = results + "HAL with a score of " + cwin + " to " + pwin;
    else 
      results = "The game was tied with a score of " + cwin + " to " + pwin;
    
    results = results + " in " + numRounds + " rounds.";
    System.out.println(results + "Thank you for a very enjoyable game!"); 
  }
  
  
  
  /** 
   * Start the homework by reading this method. 
    */
  public static void main (String args[]) {
    DiceGame game;
    
    if (args.length == 1) {
      String name = args[0];
      game = new DiceGame(name);
    } 
    else if (args.length == 2) {
      String name = args[0];
      int rounds = Integer.parseInt(args[1]);
      game = new DiceGame(name, rounds);
    }
    else  { //wrong num of args
      System.out.println("Usage: \n>java DiceGame player's_name number_of_rounds\n" +
                        " or \n>java DiceGame player's_name");
      return; //done. no reason to try to play
    }
    
    game.playDiceGame();

  }
}
