import java.util.*;
/*Xiaofan Wu and Wenxi Zhang 
 * Flight.java
 * modified 9/18/15
 * 
 */
//declare all the variables in the class
public class Flight{
  private String name;
  private String flightNo; //should it be int or string
  private String origin;
  private String destination;
  //create a contructor. the constructor takes in four variable and assigns them.
  public Flight(String name, String flightNo, String origin, String destination){
    this.name=name;
    this.flightNo=flightNo;
    this.origin=origin;
    this.destination=destination;
  }
  //create getters to get each variable
  public String getName(){
    return name;
  }
  
  public String getNo(){
    return flightNo;
  }
  
  public String getOri(){
    return origin;
  }
  
  public String getDes(){
    return destination;
  }
  //create setters to set all the variables
  
  public void setName(String newName){
    name=newName;
  }
  
  public void setNo(String newNo){
    flightNo=newNo;
  }
  
  public void setOri(String newOri){
    origin=newOri;
  }
  
  public void setDes(String newDes){
    destination=newDes;
  }
  
    
  // I stands for input
  public static Flight readFlight(){
    //ask the user to put in all the variable
    Scanner input= new Scanner(System.in); // system.in means read sth from the keyboard
    System.out.print("Please enter the flight name:");
    String nameI=input.nextLine();
    System.out.print("Please enter the flight number:");
    String numberI= input.nextLine();
    
    System.out.print("Please enter the flight origin:");
    String originI=input.nextLine();
    
    System.out.print("Please enter the flight destination:");
    String destinationI=input.nextLine();
    //create a Flight obeject using Flight class
    Flight newflight= new Flight(nameI,numberI,originI,destinationI);
    return newflight;
  }
    
  // == ask for the address of string, so is always false 
  public static boolean stopOver(Flight one, Flight two){
    //check if there is layover betwwen two flights
    if ((one.getOri()).equals(two.getDes()) || (one.getDes()).equals(two.getOri())){
      return true;
    }
    return false;
  }
  
  //create toString method to nicely print of the origin and destination
  public String toString(){
    return (name +flightNo+" Origin: "+origin+" destination: "+destination);
  }
  // when calling in main, the method should be static, or be called by the object 
  public static void main(String[] args){
    //use the readFlight method to get the user to input two flights info
    Flight first=readFlight();
    Flight second=readFlight();
    System.out.println(first.toString());
    //to string
    System.out.println(second);
    //if there is a shared city, print out the corresponding message if not print out that there is no stop city. 
    
    if (stopOver(first,second)){
      System.out.println("These two flight share a stop city.");
    }else{
      System.out.println("These two flight do not share a stop city.");
    }
  }
}

      
    
    