// Given an array of integers. Find a peak element in it.
// An array element is peak if it is NOT smaller than its neighbors.

// Uses a modification of Binary Search

// Time  Complexity: O(log n)
// Space Complexity: O(log n) as we are using recursive
public class FindPeak {

  public int find (int[] arr, int low, int high)
  {
    int n = arr.length-1;
    if (low<=high)
    {
      int mid = low + (high-low)/2;
      if (mid == 0 || arr[mid-1]<=arr[mid]
          && mid == n || arr[mid+1]<=arr[mid] )
        return mid;
      else if (mid!=0 && arr[mid-1]>arr[mid])
        return find(arr, low, mid-1);
      else
        return find(arr, mid, high);
    }
    return -1;
  }

  // Driver Method
  public static void main(String[] args)
  {
       int arr[] = {1, 3, 20, 4, 1, 0};
       int n = arr.length-1;
       FindPeak obj = new FindPeak();
       System.out.println("Index of a peak point is " +
                           obj.find(arr, 0, n));
   }
}
