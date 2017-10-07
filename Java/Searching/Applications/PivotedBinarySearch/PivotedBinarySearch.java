// Search an element in a sorted and rotated array
// @author Siddhant Arya
// @email siddhant.arya18@gmail.com

// Time complexity: O(log n)
// Space complexity: O(log n) <- Recursive
public class PivotedBinarySearch
{
  public int findPivot(int[] arr, int low, int high)
  {
    if(low<high)
    {
      int mid = low + (high-low)/2;

      // As is common in Binary Search, comparisons made with mid
      if (mid < high && arr[mid] > arr[mid+1])
        return mid+1;
      if (low < mid  && arr[mid-1] > arr[mid])
        return mid;

      // Continuing search if the conditions were not met
      if (arr[mid] > arr[high])
        return findPivot(arr, mid+1, high);
      else
        return findPivot(arr, low, mid-1);
    }
    else
      return low;
  }

  public int pivotedBinarySearch(int[] arr, int x)
  {
    if (arr.length <= 0)
      return -1;

    int pivot = findPivot(arr, 0, arr.length-1);

    if (pivot == 0)
      return binarySearch(arr, 0, arr.length-1, x);

    if (x == arr[pivot])
      return pivot;
    else if (x>arr[0])
      return binarySearch(arr, 0, pivot-1, x);
    else
      return binarySearch(arr, pivot+1, arr.length-1, x);


  }

  public int binarySearch(int[] arr, int low, int high, int x)
  {
    int mid = low + (high - low)/2;
    if (low <= high)
    {
      if (arr[mid] == x)
        return mid;
      // First Bucket (low to mid-1)
      else if (arr[mid] > x)
        return binarySearch(arr, low, mid-1, x);
      // Second Bucket (mid+1 to high)
      else
        return binarySearch(arr, mid+1, high, x);
    }
    return -1;
  }

  public static void main(String[] args)
  {
    PivotedBinarySearch obj = new PivotedBinarySearch();
    // Base cases borrowd from Geek for Geeks
    int arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
    int key = 3;
    System.out.println("Index of the " + key +" <-1:NF>: "
                   + obj.pivotedBinarySearch(arr, key));
  }
}
