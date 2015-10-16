/* Xiaofan Wu& Yuanzhen Pan
 * Pset4 Task 1: Vigenere
 * October 7th
 */
import javax.swing.JOptionPane;

//create a class called Vigenere that implements Encryptable
public class Vigenere implements Encryptable{
  // define instance variables
  private String message;
  private boolean isEncrypted;
  private String password;
  // build a constructor
  public Vigenere(String message,String password){
    this.message=message;
    this.password=password;
  }
  //formatMessage method delete the space and change everything to upper in the input message
  private void formatMessage(){
    message=message.replaceAll("\\s","").toUpperCase();
  }
  //arrayOfShift method takes in a password
  //find the alphabetical order of the characer in the password
  //through finding and and manipulating the ACSII index of each character of the password
  //and put them in an array
  private int[] arrayOfShift(String password){
    password=password.toUpperCase();
    int[] list= new int[password.length()] ;
    for (int i=0;i<password.length();i++){
      list[i]=password.charAt(i)-65;
    }  
  return list;
  }
    
  //encrypt method encrypts the message 
  //by increasing the ACSII of the characters according to the alphabetical order of the characters in password
  public void encrypt(){
    // create a new empty string called encrypted that temporarily will store the encrypted message
    String encrypted="";
    // get the alphabetical shift by invoking arrayOfShift method
    int[] results=arrayOfShift(password);
    //create a for loop that loops through each character of the message
    for (int i=0;i<message.length();i++){
      //numb is the new ACSII after the shift
      //loop through the shift in results, and use that shift to get numb
      int numb= message.charAt(i)+(results[i%password.length()]);
      //if the numb exceeds the ACSII  of the caplitalized letters
      //it returns to the character "A" and continue the shift, such that we get the correct character
      if (numb>90){
      encrypted=encrypted + (char)(numb-26);
      }else{
        // if numb doesn't exceed the range, it gets the correct character and add the character to the end of encrypted
        encrypted=encrypted + (char)(numb);
      }
    }
    //set message to the encrypted
    message=encrypted;
    isEncrypted=true;
  }                   
                           
                        
  
  public String decrypt(){
    //check if the message is encrypted
    if (isEncrypted)
    {
    //create a new empty string that stores the characters of decrypted message
    String unmasked="";
    int[] results=arrayOfShift(password);
    //create a for loop that loops through the character of the message
    for (int i=0;i<message.length();i++){
      //numb is the new ACSII  after the shift
      //loop through the shift in results, and use that shift to get numb
      int numb= message.charAt(i)-(results[i%password.length()]);
      //if numb is smaller than the ACSII range of capitalized letters
      //add 26 to get the correct character 
      if (numb<65){
      unmasked=unmasked + (char)(numb+26);
      //else numb is in the right ACSII range, use numb to get the correct character
      }else{
        unmasked=unmasked + (char)(numb);
      }
    }
    //set message back to the decrypted message
    message=unmasked;
    isEncrypted=false;
  }
    return message;
  }
  
  //toString method
  public String toString(){
    return message;
  }
  
  // set up the dialog box in the main method
  public static void main(String[] args){
    String messageinput, passwordinput;
    int decryptedY;
    
    // prompt the user for message and password and save the input
    messageinput=JOptionPane.showInputDialog ("What is the message? ");
    passwordinput=JOptionPane.showInputDialog ("What is the password?");
    // create a new message object with user inputs 
    Vigenere message= new Vigenere(messageinput, passwordinput);
    //format the message
    message.formatMessage();
    //encrypt the message
    message.encrypt();
    
    //return the encrypted message to the user
    JOptionPane.showMessageDialog (null, message);
    //prompt the user and ask if he wants ti decrypt the message
    decryptedY = JOptionPane.showConfirmDialog(null, "Do you want it decrypted?");
    
    // if the user wants to decrypt the message
    // invoke the decrypt method and return it to the user in the dialog box
    if (decryptedY == JOptionPane.YES_OPTION){
      String decrypted=message.decrypt();
      JOptionPane.showMessageDialog (null,decrypted);
    }
    
    // if the user doesn't want to decrypt,
    // return a thank you message in the dialog box
    else{
      String thankyou="Thank you for using this tool!";
      JOptionPane.showMessageDialog (null, thankyou);
    }
  }
}
