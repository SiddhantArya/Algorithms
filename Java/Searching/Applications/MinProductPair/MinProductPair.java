// Minimum Product of a Pair in an array.
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Given an array of positive integers. We are required to write a program to
// print the minimum product of any two numbers of the given array.

// Simple approach can be to sort the array and return the product o first two
// elements of the sorted array. However, sorting takes O(log n) time.

// A better solution is to to modify linear search algorithm and keeping track
// of the two minimum elements in the same iteration. This takes O(n) time.

// Time Complexity:  O(n)
// Space Complexity: O(1)

import java.util.Arrays;

public class MinProductPair
{
  public int calcMinProduct(int[] arr)
  {
    if (arr.length<=0)
    {
      System.out.println("Array empty");
      return -1;
    }
    else if (arr.length-1==1)
    {
      return arr[0]*arr[0];
    }
    int min1 = Math.min(arr[0], arr[1]);
    int min2 = Math.max(arr[0], arr[1]);

    for (int i=2; i<arr.length-1; i++)
    {
      if (arr[i] < min1)
      {
        min2=min1;
        min1=arr[i];
      }
      else if (arr[i] <min2)
      {
        min2=arr[i];
      }
    }
    return min1*min2;
  }
  public static void main(String[] args)
  {
      int arr[] = { 11, 8 , 5 , 7 , 5 , 100 };
      MinProductPair obj = new MinProductPair();
      System.out.println("Given Array: " + Arrays.toString(arr));
      System.out.println("Min Product Pair: " + obj.calcMinProduct(arr));
  }
}
