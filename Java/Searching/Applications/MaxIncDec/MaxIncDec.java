// Find the maximum element in an array that is increasing at first
// and decreasing later
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time complexity: O(log n)
// Space complexity: O(log n) <- Recursive


import java.util.Arrays;

public class MaxIncDec
{
  public int findMax(int[] arr, int low, int high)
  {
    // To prevent overflow error in unsigned intergers
    if (low < high)
    {
      // Single Element
      if (low==high)
        return low;

      // Only two elements
      if (low == high-1)
      {
        if (arr[low] < arr[high])
          return high;
        else
          return low;
      }

      int mid = low + (high-low)/2;

      // Checking boundary conditions and checking midpoint for max
      if ( (mid == 0 || arr[mid] >= arr[mid-1]) &&
          (mid == high || arr[mid] >= arr[mid+1]) )
        return mid;

      // Recursing appropriately
      if (arr[mid] <= arr[mid+1] && arr[mid-1] <= arr[mid] )
        return findMax(arr, mid, high);
      else
        return findMax(arr, low, mid);
    }
    return -1;
  }

  public static void main(String[] args)
  {
    // Test cases borrowed from Geek for Geeks
    int[] arr1 = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
    int n1 = arr1.length;
    int[] arr2 = {1, 3, 50, 10, 9, 7, 6};
    int n2 = arr2.length;
    int[] arr3 = {10, 20, 30, 40, 50};
    int n3 = arr3.length;
    int[] arr4 = {120, 100, 80, 20, 0};
    int n4 = arr4.length;

    MaxIncDec obj = new MaxIncDec();

    // Array according to the brief
    System.out.println("Arr1: " + Arrays.toString(arr1));
    System.out.println("Max1: " + arr1[obj.findMax(arr1, 0, n1-1)] );
    // Another attempt
    System.out.println("Arr2: " + Arrays.toString(arr2));
    System.out.println("Max2: " + arr2[obj.findMax(arr2, 0, n2-1)] );
    // Strictly increasing
    System.out.println("Arr3: " + Arrays.toString(arr3));
    System.out.println("Max3: " + arr3[obj.findMax(arr3, 0, n3-1)]);
    // Strictly decreasing
    System.out.println("Arr4: " + Arrays.toString(arr4));
    System.out.println("Max4: " + arr4[obj.findMax(arr4, 0, n4-1)]);

  }

}
