// Modification of Insertion Sort

// In normal insertion, sort it takes O(i) (at ith iteration) in worst case.
// We can reduce it to O(log i) by using binary search.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O(n^2), however, if cost of comparisons is more than swaps
//                  This algorithm is preferred over traditional insertion sort
// Space Complexity: O(1)
// Stable: Yes
// In-Place: Yes

import java.util.Arrays;

public class BinaryInsertionSort
{

  public int binarySearch(int[] arr, int low, int high, int key)
  {
    if (low >= high)
    {
      if (arr[low] > key)
        return low;
      else
        return low+1;
    }

    int mid = low +(high-low)/2;

    if (arr[mid] == key)
      return mid+1;
    else if (arr[mid] > key)
      return binarySearch (arr, low, mid-1, key);
    return binarySearch (arr, mid+1, high, key);

  }

  public void swap (int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void insertionSort (int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments Specified");
      return;
    }

    for (int i=1; i<arr.length; i++)
    {
      int key = arr[i];
      int j = i-1;
      int loc = binarySearch (arr, 0, j, arr[i]);

      while (j >= loc)
      {
        swap (arr, j+1, j);
        j--;
      }
      arr[j+1] = key;
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    BinaryInsertionSort obj = new BinaryInsertionSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.insertionSort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
