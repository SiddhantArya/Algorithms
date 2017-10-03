// Program to implement TernarySearch (better suited for unimodal search)
// @author Siddhant Arya
// @email siddhant.arya18@gmail.com

// Time Complexity : T(n) = T(2n/3) + c i.e. Theta(Log n) / O(Log n)

// Space Complexity: O(1) in case of iterative implementation.
// In case of recursive implementation, O(Log n) recursion call stack space.

// Algorithmic Paradigm: Divide and Conquer

// Notes: For midpoint calculations, we used (low + (high-low)/3) form because,
// As a signed 32-bit integer, it overflows and flips negative.
// Therefore (high + low)/3 is wrong because (high + low) could be negative.
// For both positive integers, (high+low)/3 is fine.

import java.util.Arrays;

class TernarySearch {

  // Performing IllegalArgument check
  public int search(int op, int[] arr, int x)
  {
    if ( !(op==1 || op==2) || arr.length < 0 )
    {
      System.out.println("Illegal arguments specified");
      return -2;
    }

    if (op == 1)
      return search_rec(arr, 0, arr.length-1, x);
    else
      return search_iter(arr, x);
  }

  // Recursive implementation
  public int search_rec(int[] arr, int low, int high, int x)
  {
    if ( low<=high )
    {
      int m1 = low + (high-low)/3;
      int m2 = m1 + (high-low)/3;

      // No. not in array coz greater than highest or smaller than lowest
      if (arr[high] < x || arr[low] > x)
        return -1;

      if ( arr[m1]==x )
        return m1;
      else if ( arr[m2]==x )
        return m2;
      // Third Trident (m2 to high)
      else if ( arr[m2]<=x && x<=arr[high] )
        return search_rec(arr, m2+1, high, x);
      // Second Trident (m1 to m2)
      else if ( arr[m2]>=x && x>=arr[m1] )
        return search_rec(arr, m1+1, m2-1, x);
      // First Trident (low to m1)
      else if ( arr[m1]>=x && x>=arr[low] )
        return search_rec(arr, low, m1-1, x);
      // Not Found
      else
        return -1;
    }
      return -1;
  }

  // Iterative implementation
  public int search_iter(int[] arr, int x)
  {
    // Slightly different implementation from recursive offered (both are fine)
    // Has less number of if comparisons
    int low = 0;
    int high = arr.length-1;
    while (low <= high)
    {
      int m1 = low + (high-low)/3;
      int m2 = low + (high-low)*2/3;

      // Not in array
      if ( arr[low]>x || arr[high]<x )
        return -1;

      if ( arr[m1]==x )
        return m1;
      if ( arr[m2]==x )
        return m2;
      // First Trident (low to m1)
      else if ( arr[m1]>x )
        high = m1-1;
      // Second Trident (m1 to m2)
      else if ( arr[m2]>x )
      {
        low = m1+1;
        high = m2-1;
      }
      // Third Trident (m2 to high)
      else
        low = m2+1;
    }
    return -1;
  }

  // Driver Method
  public static void main(String[] args)
  {
    // Statically coded arrays and x, can be dynamically taken
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int x = 8;
    TernarySearch obj = new TernarySearch();
    System.out.println("Given array: " + Arrays.toString(arr));
    System.out.println("Negative Results mean <Not Found>");
    System.out.println("Recusrive Search for " + x + " at Index (-1: NF): " + obj.search(1, arr, x));
    System.out.println("Iterative Search for " + x + " at Index (-1: NF): " + obj.search(2, arr, x));


  }
}
