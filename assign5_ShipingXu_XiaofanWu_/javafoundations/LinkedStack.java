//********************************************************************
//  LinkedStack.java       Java Foundations
//  Written by: Shiping Xu & Xiaofan Wu
//  10/25/2015
//  Represents a linked implementation of a stack.
//********************************************************************

package javafoundations;
import javafoundations.exceptions.*;

public class LinkedStack<T> implements Stack<T>
{
   private int count;
   private LinearNode<T> top;

   //-----------------------------------------------------------------
   //  Creates an empty stack using the default capacity.
   //-----------------------------------------------------------------
   public LinkedStack()
   {
      count = 0;
      top = null;
   }

   //-----------------------------------------------------------------
   //  Removes the element at the top of this stack and returns a
   //  reference to it. Throws an EmptyCollectionException if the
   //  stack contains no elements.
   //-----------------------------------------------------------------
   public T pop() throws EmptyCollectionException {
      if (count == 0)
         throw new EmptyCollectionException ("Pop operation failed. "
            + "The stack is empty.");
      T result = top.getElement();
      top = top.getNext();
      count--;

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this stack.
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "<top of stack>\n";
      LinearNode current = top;

      while (current != null)
      {
         result += current.getElement() + "\n";
         current = current.getNext();
      }

      return result + "<bottom of stack>";
   }

   //-----------------------------------------------------------------
   //  The following methods are left as Programming Projects.
   //-----------------------------------------------------------------
   // Add the specified element to the top of this stack
    public void push (T element) {
      LinearNode<T> node = new LinearNode<T>(element);  // create a new node with the specified element  
      if (count!=0) 
        node.setNext(top);       
      top = node; 
      count++; // increment count by 1
    }
    
    // Returns top without retrieving
    public T peek () throws EmptyCollectionException {
      if(count == 0) // if there's no element in the stack
        throw new EmptyCollectionException("Peek operation failed. Stack is empty.");
      return top.getElement();
    }
    
    // checks if stack is empty
    public boolean isEmpty() {
      return count == 0;
    }
    
    // returns the number of elements in the stack
    public int size() {
      return count;
    }
}
