// Program to implement Binary Search
// @author Siddhant Arya
// @email siddhant.arya18@gmail.com

// It is an algorithm so named because of the way it searches an element.
// It used exponential increments to find the range of the element.
// After finding the range of the element it uses Binary Search to find it.

// Exponential Binary Search is particularly useful for
// unbounded searches, where size of array is infinite.
// Better than Binary Search for bounded arrays also when
// the element to be searched is closer to the first element.

// Time Complexity:   O(log n)
// Space Complexity:  O(log n) for recursive binary search
//                    O(1) for iterative binary search
import java.util.Arrays;

public class ExponentialSearch
{

  // Regular Binary Search Algorithm
  public int bin_search(int[] arr, int low, int high, int x)
  {
    if(low<=high)
    {
      int mid = low + (high-low)/2;
      if (arr[mid]==x)
        return mid;
      else if (arr[mid]<x)
        return bin_search(arr, mid+1, high, x);
      return bin_search(arr, low, mid, x);
    }
    return -1;
  }

  // Interpolation Search
  public int search(int[] arr, int x)
  {
    if (arr.length <=0)
    {
      System.out.println("Array unitialized");
      return -2;
    }

    if (arr[0] == x)
      return 0;

    int i=1, n=arr.length-1;
    if (arr[0] >x || arr[n] < x)
      return -1;

    // Range of the element
    while (i<=n && arr[i]<=x)
      i*=2;

    return bin_search(arr, i/2, Math.min(i, n), x);

  }


  public static void main(String[] args)
  {
    // Statically coded arrays and x, can be dynamically taken
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8 ,9};
    int x = 8;
    ExponentialSearch obj = new ExponentialSearch();
    System.out.println("Given array: " + Arrays.toString(arr));
    System.out.println("Negative Results mean <Not Found>");
    System.out.println("Search for " + x + " @ Index (-1: NF): "
                        + obj.search(arr, x));
  }
}
