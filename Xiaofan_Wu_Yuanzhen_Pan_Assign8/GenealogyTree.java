/* Assignment 8
 * Task 2
 * Xiaofan Wu, Yuanzhen Pan
 */

import java.util.Iterator; // need to put the javafoundation folder in
import javafoundations.*;
import javafoundations.exceptions.*;

public class GenealogyTree<T> implements AncestryTree<T>, Iterable<T>{
  T[] array;
  int count;
  
  // Create a 1-argument constructor that sets the root of a new tree
  public GenealogyTree(T elt){
    array= (T[]) (new Object[5]); 
    array[0]=elt;
    count=1;
  }

  //  Returns the element stored in the root (aka CoTU) of the tree.
  public T getCoTU(){ 
    return array[0]; // CoTU is the elt at index 0 of the array
  }
  
  // Define a helper method that takes in an element 
  // and returns the index of that element in the array
  // We didn't throw an exception in this method because whenever this method is invoked
  // we always checked if the target appears in the array first, therefore, not necessary to throw exception here
  private int findIndex(T target){
    int index=-1; // initialize the index to be -1, if the target doesn't exist, the index returned is -1
    for (int i=0;i<array.length;i++){
      //make sure that the element is not null and it equals to our target
      if(array[i]!=null && array[i].equals(target))
        index=i;
    }
    return index;
  }
  
  //  Returns the member that is the offspring of target.
  //  Returns null as the offspring of the root.
  public T getOffspring(T target){ 
    //make sure that the element actually appears and that the element is not the root.
    if(!appears(target))
      throw new ElementNotFoundException("Sorry, the target is not found in the tree.");
    
    if(target.equals(array[0]))
      return null;
    
    int index=findIndex(target);
    return array[(index-1)/2];
    }
  
    //  Returns the member that is the left child of target.
    //  Returns null if the left child does not exist.
  public T getPater(T target){
    if(!appears(target))
      throw new ElementNotFoundException("Sorry, the target is not found in the tree.");
    
    int index=findIndex(target);
    
    //check if the index is out of bound
    
    if (index*2+1>=array.length){
      return null;
    }
    
    return array[(index*2+1)];
    
  }
  
  //  Sets the left child of the tree target to lchild.
  //  It throws an exception if target is not already in the tree
  //  If the lchild already exist for target, we overwrite it
  public void setPater(T target, T lchild){
    if(!appears(target))
      throw new ElementNotFoundException("Sorry, the target is not found in the tree.");
    
    int index=findIndex(target);
    
    if(index*2+1>=array.length) // check if we need to expand the array
      expand();
    
    array[index*2+1]=lchild; 
    count++;
    }
  
  //  Returns the element that is the right child of target.
  //  Returns null if the right child does not exist
  public T getMater(T target){ // Maternal ancestors are on the right
    
    if(!appears(target)){
      throw new ElementNotFoundException("Sorry, the target is not found in the tree.");
    }
    int index=findIndex(target);
    //check if the index is out of bound
    if (index*2+2>=array.length)
      return null;
    
    return array[index*2+2];

  }
  
  //  Sets the right child of target to rchild.
  //  It throws an exception if target is not already in the tree
  //  If the rchild already exist for target, we overwrite it
  public void setMater(T target, T rchild){
    if(!appears(target)){
      throw new ElementNotFoundException("Sorry, the target is not found in the tree.");
    }
    int index=findIndex(target);
    if(index*2+2>=array.length)// check if we need to expand the array
      expand();
    
    array[index*2+2]=rchild;
    count++;
  }
  
  //  Returns true if the tree contains an element that
  //  matches the specified target element and false otherwise.
  public boolean appears (T target){
    //loop thourgh the entire array, if the element is not empty, check if that equals to 
    //our target. If we can't find anything in the end, will return false.
    for (int i=0;i<array.length;i++){
      if (array[i]!=null){ 
        if (array[i].equals(target))
          return true;
      }      
    }
    return false;
  }
  
  // Returns true if the two members share a grandchild,
  // and false if they are not or if a shared grandchild does not exist
  // Two grandparents that share a grandchild are "inLaws"
  public boolean inLaws(T gp1, T gp2){
    //first get the child of the two grandparents
    //check if the child of two grandparents have the same child
    if ((!appears(gp1)) || (!appears(gp2)))
       throw new ElementNotFoundException("Sorry, the target is not found in the tree.");
      
  
    T c1=getOffspring(gp1);
    T c2=getOffspring(gp2);
  
    return (getOffspring(c1).equals(getOffspring(c2)));
   
 

  }
  
  //  Returns the number of members in this ancestral tree.
  public int size(){
    return count;
  }
  
  //  Returns the string representation of the binary tree,
  //  one line per level
  public String toString(){
    int level=0;
    String result="My genealogy contains "+ size()+" members: ";
    for (int i=0;i<array.length;i++){
      if(array[i]!=null){
        //check if we reach the end the level yet by using 2^n-1
        if (Math.pow(2,level)-1==i){
          result+="\n" + array[i];
          level++;
        }else{
          result+=" "+array[i];
        }
      }
    }
     return result; 
    }
 
  // helper method to expand array, create a temp array, add everything to the temp array
  //then point to the temp array.
  private void expand(){
    T[] temp=(T[]) (new Object[array.length*2]);
    for (int i=0;i<array.length;i++)
      temp[i]=array[i];
    array=temp;
  }
  //create a method that go through the tree in level order and add everything that is not null.
  public Iterator<T> byGenerations(){
    ArrayIterator<T> iter=new ArrayIterator<T>();
    for (int i=0;i<array.length;i++){
      if (array[i]!=null)
        iter.add(array[i]);
    }
    return iter;
  }
  
  public Iterator<T> iterator(){
    return byGenerations();
  }
  
  
  public static void main(String[]args){
   // We can assume the class works for a full tree as a tutor said 
   GenealogyTree<String> test = new GenealogyTree<String>("D");// note how to write syntax
   test.setPater("D","E");
   test.setMater("D","B");
   System.out.println(test);
   test.setPater("E","I");
   test.setMater("E","A");
   test.setPater("B","F");
   test.setMater("B","K");
  
   //In the test case, we add more elements than the array can hold. This is to test method expand();
   System.out.println(test);
   System.out.println("The CoTu of the test case is(D): "+ test.getCoTU());
   System.out.println("The index of E in the array is (1): "+ test.findIndex("E"));
   System.out.println("The index of Z in the array is (-1): "+ test.findIndex("Z"));
   System.out.println("The offspring of E is(D): "+ test.getOffspring("E"));
   System.out.println("Is M a member of the test genealogy tree? (false) "+ test.appears("M"));
   System.out.println("Are A and K in laws? (true): "+test.inLaws("A","K")); 
   System.out.println("The size of this test genealogy tree is (7): "+ test.size());
   System.out.println("the mom of D is B: "+ test.getMater("D"));
   System.out.println("the father of D is E: "+ test.getPater("D"));
   System.out.println("the father of K should be null "+ test.getPater("K"));
   System.out.println("the mother of K should be null "+ test.getMater("K"));
   
   
   //test method iterator and byGenerations
   //iterator invokes byGenerations
   Iterator<String> a=test.iterator();
      while (a.hasNext()){
   System.out.println(a.next());
      }
   // test cases for exceptions
   //test.getOffspring("M");
   //test.getPater("M");
   //test.getMater("M");
   //test.setPater("M","Z");
   //test.setMater("M","Z");
   //test.inLaws("s","f");

   
  }
}