import java.util.*;

/*Xiaofan Wu and Wenxi Zhang 
 * Point.java
 * modified 9/18/15
 * 
 */
//create class for Point
public class Point{
  //object variable 
  private double x;
  private double y;
  
 //create getter methods 
  public double getX(){
    return x;
  }
  
  public double getY(){
    return y;
  }
  
  // this. sth : referring to the variable in the top 
  //constructor does the initial setting 
  
  public Point(double x, double y){
    this.x=x;
    this.y=y;
  }
  //create setters 
  public void setX(double newX){
    x=newX;
  }
  
// will be better that for every variable we have a getter and setter method
  public void setY(double newY){
    y=newY;
  }
  

  //create the find distance method 
  public double findDistance(Point a){
    
    double distance = Math.sqrt(Math.pow(a.getX()-x,2)+Math.pow(a.getY()-y,2));
    return distance;
  }
  
  // print thing related to the object
  public String toString(){
    return "("+x+","+y+")";
  }
  
 
 //find the difference in distance between the two points, then find if they are equidistant
    
  public boolean areEquidistant(Point one, Point two){
    final double TOLERANCE= 0.01;
    
    double diffOne = findDistance (one);
    double diffTwo = findDistance(two);

    if (Math.abs(diffOne-diffTwo)<TOLERANCE){
      
      return true;
    }
    
    return false;

  }
  //create a method that read from user input 
  
  public static Point readPoint(){
    Scanner input= new Scanner(System.in); // system.in means read sth from the keyboard
    System.out.print("Please enter the x value of first point:");
    double xKey=input.nextDouble();
    System.out.print("Please enter the y value of first point:");
    double yKey=input.nextDouble();
    //using the user input to create a new object by using the point class
    Point newPoint= new Point(xKey,yKey);
    return newPoint;
    
  }
    
    
  // in the static main method, if you are calling another method directly, the method should also be static 
  // try to enter x,y, and use nextLine() to take it as a string, and use string class to split it 
  public static void main(String[] args){
    //create three new points by asking the user to input them 
    Point firstP=readPoint();
    Point secondP=readPoint();
    Point thirdP=readPoint();
//check if they are equidistant 
    if (thirdP.areEquidistant(firstP,secondP)||
        secondP.areEquidistant(firstP,thirdP)||
        firstP.areEquidistant(thirdP,secondP)){
      
      System.out.println("This point is equidistant to the other two.");
      
    }
    
    else{
      
      System.out.println("These three points are not equidistant to each other.");
      
    }

  }
}
    
  
  