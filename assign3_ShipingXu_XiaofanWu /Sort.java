/* Sort.java
 * CS230 Assignment#3 Task3-subtask1
 */

/* sortArray() uses a nested for loop to find the the maximum number in an array 
 * and switch it with the last element in the array. The first loop starts from the 
 * last index of the loop and set the current maxmimum index as 0 and the maximum 
 * number as the first character in string. Then the second loop goes over the array 
 * and checks if there's any number that is greater than the current maxmium. If there 
 * is, then set it as the maxmium number and its index as the maximum index. When we reach
 * the end of the second loop, we go into swap() method and create a temporary integer and set
 * it to the maxmimum value in the array. We then switch the two numbers in the two indexes. 
 * We then decrease j by 1 (the second last element in the array), and do the same thing over again
 * until we reach the first letter of the first letter of the array.
 */


public class Sort {  
  
  /*
  * sorts the integers in the input array in increasing order
  */
  public static void sortArray (int[] arrayA) {
    int maxNum;    // maximum integer so far
    int maxIndex;  // index of maximum integer
    int i, j;
    for (j = arrayA.length - 1; j > 0; j--) {
      maxIndex = 0;
      maxNum = arrayA[0];
      for (i = 1; i <= j; i++) 
        if (arrayA[i] > maxNum) {
          maxNum = arrayA[i];
          maxIndex = i;
        }
      swap(arrayA, maxIndex, j);
    }
  }
  
/** 
  * exchanges the contents of locations i and j in the input array
  */
  private static void swap (int[] arrayA, int i, int j) {
    int temp = arrayA[i];
    arrayA[i] = arrayA[j];
    arrayA[j] = temp;
  }
  

}