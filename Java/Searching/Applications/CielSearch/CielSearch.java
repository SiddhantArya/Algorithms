// Ciel Search
// @author Siddhant Arya
// @email siddhant.arya18@gmail.com

// Ciel Search can be used to find the element either equal to or just less
// than the required element. It can further be modified to return the i-th
// greater element than the given element by just making a small change.

// This can be implemented using Linear Search also, but using a slight
// modification of Binary Search algorithm makes it much more efficient.

// Time Complexity              : O(log n) <- Similar to Binary Search
// Space Complexity (Recursive) : O(log N) <- Similar to Binary Search
// Space Complexity (Iterative) : O(1)     <- Similar to Binary Search

// Notes: For midpoint calculations, we used (low + (high-low)/2) form because,
// As a signed 32-bit integer, it overflows and flips negative.
// Therefore (high + low)/2 is wrong because (high + low) could be negative.
// For both positive integers, (high+low)/2 is fine.

import java.util.Arrays;

public class CielSearch
{
  // Common utility method for argument checking
  public int search(int op, int[] arr, int x)
  {
    if( arr.length <= 0 || !(op==1 || op==2))
    {
      System.out.println("Invalid arguments provided");
      return -2;
    }
    if(op == 1)
      return search_ciel_rec(arr, 0, arr.length-1, x);
    return search_ciel_iter(arr, x);
  }

  // Recursive method
  public int search_ciel_rec(int[] arr, int low, int high, int x)
  {
    // Binary Search condition modified
    // If 1 is changed to i, then it will return i-th greater element than given
    if(high - low > 1)
    {
      // Better than mid = (high - low)/2
      int mid = low + (high-low)/2;
      if (arr[mid] > x)
        return search_ciel_rec(arr, low, mid, x);
      else
        return search_ciel_rec(arr, mid, high, x);
    }
    return high;
  }

  public int search_ciel_iter(int[] arr, int x)
  {
    int low = 0;
    int high = arr.length-1;

    // Binary Search condition modified
    // If 1 is changed to i, then it will return i-th greater element than given
    while (high - low > 1)
    {
      int mid = low + (high-low)/2;
      if (arr[mid] <= x)
        low = mid;
      else
        high = mid;
    }
    return high;
  }

  // Driver Method
  public static void main(String[] args)
  {
    int sorted_arr[] = {1, 2, 3, 4, 5, 6, 7, 9, 10};
    int x=8;
    System.out.println("Given Array: " + Arrays.toString(sorted_arr));
    CielSearch obj = new CielSearch();
    int res_rec = obj.search(1, sorted_arr, x);
    System.out.println("<Recursive> Ciel value for " + x + " :" +
                        sorted_arr[res_rec]);
    int res_iter = obj.search(2, sorted_arr, x);
    System.out.println("<Iterative> Ciel value for " + x + " :" +
                        sorted_arr[res_iter]);
  }

}
