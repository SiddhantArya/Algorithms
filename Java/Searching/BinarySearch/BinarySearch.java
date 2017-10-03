// Program to implement Binary Search
// @author Siddhant Arya
// @email siddhant.arya18@gmail.com

// Time Complexity : T(n) = T(n/2) + c i.e. Theta(Log n) / O(Log n)

// Space Complexity: O(1) in case of iterative implementation.
// In case of recursive implementation, O(Log n) recursion call stack space.

// Algorithmic Paradigm: Divide and Conquer

// Notes: For midpoint calculations, we used (low + (high-low)/2) form because,
// As a signed 32-bit integer, it overflows and flips negative.
// Therefore (high + low)/2 is wrong because (high + low) could be negative.
// For both positive integers, (high+low)/2 is fine.

import java.util.Arrays;

class BinarySearch
{

  // Performing IllegalArgument check
  public int search(int op, int[] arr, int x)
  {
    if (arr.length < 0 || !(op==1 || op==2))
    {
      System.out.println("Invalid Array/option provided");
      return -2;
    }

    if (op == 1)
      return search_rec(arr, 0, arr.length-1, x);
    else
      return search_iter(arr, x);

  }

  // Recursive implementation
  // Space Complexity: (Log n) for call stack space
  private int search_rec(int[] arr, int low, int high, int x)
  {
    int mid = low + (high - low)/2;
    if (low <= high)
    {
      if (arr[mid] == x)
        return mid;
      // First Bucket (low to mid-1)
      else if (arr[mid] > x)
        return search_rec(arr, low, mid-1, x);
      // Second Bucket (mid+1 to high)
      else
        return search_rec(arr, mid+1, high, x);
    }
    return -1;
  }

  // Iterative implementation
  // Space Complexity: O(1)
  private int search_iter(int[] arr, int x)
  {
    int low = 0;
    int high = arr.length-1;
    while (low <= high)
    {
      int mid = low + (high-low)/2;
      if (arr[mid] == x)
        return mid;
      // Second Bucket (mid+1 to high)
      else if (arr[mid] < x)
        low = mid+1;
      // First Bucket (low to mid-1)
      else
        high = mid-1;
    }
    return -1;
  }

  // Driver Method
  public static void main(String[] args)
  {
    // Statically coded arrays and x, can be dynamically taken
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8 ,9};
    int x = 8;
    BinarySearch obj = new BinarySearch();
    System.out.println("Given array: " + Arrays.toString(arr));
    System.out.println("Negative Results mean <Not Found>");
    System.out.println("Recusrive Search Index (-1: NF): " + obj.search(1, arr, x));
    System.out.println("Iterative Search Index (-1: NF): " + obj.search(2, arr, x));

  }
}
