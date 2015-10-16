/* AreaTriangle.Java
 CS230 PS 1 
 Written by: Xiaofan Wu and Yuanzhen Pan
 Modified by:Xiaofan Wu and Yuanzhen Pan
 Modified date:09/05/2015
*/
import java.util.*;
 
public class AreaTriangle{
  //create a method 
  public static void main(String[] args) {
    //prompt the user for the first side of the triangle
    Scanner scan1 = new Scanner(System.in);
    System.out.println("Please enter the length of the first side of a triangle:");
    Double sideOne = scan1.nextDouble();
    //prompt the user for the second side of the triangle
    Scanner scan2 = new Scanner(System.in);
    System.out.println("Please enter the length of the second side of a triangle:");
    Double sideTwo = scan2.nextDouble();
    //prompt the user for the second side of the triangle
    Scanner scan3 = new Scanner(System.in);
    System.out.println("Please enter the length of the third side of a triangle:");
    Double sideThree = scan3.nextDouble();
    //call the helper method to find if the triangle is a isosceles
    isosceles(sideOne,sideTwo,sideThree);
    //call the helper method to figure out the area
    area(sideOne,sideTwo,sideThree);
    
  }
  //create a isosceles helper method
  public static void isosceles(double side1,double side2,double side3) {
    //if anyone of the two sides of the triangle are equal
    if (side1==side2 || side2==side3 || side1==side3){
      //print out the statement that the triangle is isosceles
       System.out.println("Triangle is isosceles.");
    
    }else {
      //otherwise, the triangle is not isosceles
       System.out.println("Triangle is not isosceles");
    }
  
  }
  //create another method that only has print statement
  public static void area(double side1,double side2,double side3) {
    //calculate the area of the triangle base on the equation given
    double s = (side1+side2+side3)/2;
    double area= Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
    //print out the area of the triangle
    System.out.println("The area of the triangle is "+ area);
 
  }
  
}

    
