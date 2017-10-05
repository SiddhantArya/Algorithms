// Interpolation Search

// Preferable to Binary search when elements are UNIFORMLY DISTRIBUTED.

// This algorithm is yet another modification of Binary Search but it tries to
// speed up the algorithm when the array elements are UNIFORMLY DISTRIBUTED
// by adjusting the mid such that it is closer to the position rather than
// strictly in the middle.

// You can think of array arr as a function f that acts on index and return a
// value, which is monotone (because array is sorted). So you have your initial
// data f(low) = m and f(high) = M. Now you can interpolate your function f with
// a straight line, which is quite reasonable to do because your f is monotone
// and you have only 2 points.
// So your interpolation should be line (linear function) that pass throw points
// (low, m) and (high, M).
// Formula is like 2-point Cartesian formula for 2-D line

// This program will have to be modified a little in case all the elements are
// same. Left for exercise

// T(N) <= T( sqrt(n) ) + C
// Time Complexity:   O(n) if not UNIFORMLY DISTRIBUTED (Worst Case)
//                    O(log log n) if UNIFORMLY DISTRIBUTED (average case)
// Space Complexity:  Iterative - O(1)
//                    Recursive - O()

import java.util.Arrays;

public class InterpolationSearch
{
  public int search(int op, int[] arr, int x)
  {
    if ( !(op==1 || op==2) || arr.length<=0)
    {
      System.out.println("Illegal arguments specified");
      return -2;
    }
    if (op==1)
      return search_rec(arr, 0, arr.length-1, x);
    return search_iter(arr, x);
  }

  // Recursive Method
  // Slight modification of recursive Binary Search
  public int search_rec(int[] arr, int low, int high, int x)
  {
    if(low<=high)
    {
      if(arr[low] > x || arr[high] < x)
        return -1;

      // Midpoint calculations like cartesian formula
      int pos = low + (int) ((x-arr[low])*(high-low))/(arr[high]-arr[low]);
      if(arr[pos] == x)
        return pos;
      else if (arr[pos]>x)
        return search_rec(arr, low, pos-1, x);
      else
        return search_rec(arr, pos+1, high, x);
    }
    return -1;
  }

  // Iterative Method
  // Slight modification of iterative Binary Search
  public int search_iter(int[] arr, int x)
  {
    int low = 0;
    int high = arr.length-1;
    while(low<=high)
    {
      if(arr[low] > x || arr[high] < x)
        return -1;

      // Midpoint calculations like cartesian formula
      int pos = low + (int) ((x-arr[low])*(high-low))/(arr[high]-arr[low]);
      if(arr[pos] == x)
        return pos;
      else if (arr[pos]>x)
        high = pos-1;
      else
        low =  pos+1;
    }
    return -1;
  }

  // Driver Method
  public static void main(String[] args)
  {
    int sorted_arr[] = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42};
    int x = 18;
    InterpolationSearch obj = new InterpolationSearch();
    System.out.println("Given Array: " + Arrays.toString(sorted_arr));
    System.out.println("Negative Results mean <Not Found>");
    System.out.println("Recusrive Search Index (-1: NF): " +
                        obj.search(1, sorted_arr, x));
    System.out.println("Iterative Search Index (-1: NF): " +
                        obj.search(2, sorted_arr, x));
  }
}
