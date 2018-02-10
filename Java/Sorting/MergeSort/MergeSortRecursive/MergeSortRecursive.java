// Implement Recursive Merge Sort algorithm.
// Divide and Conquer algorithm.
// It divides input array in two halves,
// calls itself for the two halves and
// then merges the two sorted halves.
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: T(n) = 2T(n/2) + c
//                : O(n . log n)

// Space Complexity: O(n) <- for the two subarrays
// Sorting In Place: Not in a typical implementation (array),
//                   but in place in linked lists
// Stable: Yes

// Not good for arrays of large sizes as a lot of auxilliary space required
import java.util.Arrays;

public class MergeSortRecursive
{
    // Swap Method
  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // Merge Method
  public void merge(int[] arr, int low, int mid, int high)
  {
    // Auxillary space for two subarrays
    int n1 = mid-low+1;
    int n2 = high-mid;
    int[] arr1 = new int[n1];
    int[] arr2 = new int[n2];

    // Filling up array1 using LOW, MID, HIGH terminology
    for (int i=0; i<n1; i++)
      arr1[i] = arr[low + i];

    // Filling up array2 using LOW, MID, HIGH terminology
    for(int i=0; i<n2; i++)
      arr2[i] = arr[mid + 1 + i];

    // Setting initial positions of two subarrays
    int i = 0;
    int j = 0;

    // Setting initial position of final array to LOW
    int k = low;

    while (i<n1 && j<n2)
    {
      if(arr1[i] <= arr2[j])
        arr[k++] = arr1[i++];
      else
        arr[k++] = arr2[j++];
    }

    // Filling elements from arr1 if any left
    while(i<n1)
      arr[k++] = arr1[i++];

    // Filling elements from arr2 if any left
    while(j<n2)
      arr[k++] = arr2[j++];
  }

  // Merge Sort
  public void sort(int[] arr, int low, int high)
  {
    if (low<high)
    {
      int mid = low +(high-low)/2;
      sort(arr, low, mid);
      sort(arr, mid+1, high);
      merge(arr, low, mid, high);
    }
  }

  public static void main(String[] args)
  {
    // Base case borrowed from GeekForGeeks
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    MergeSortRecursive obj = new MergeSortRecursive();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr, 0, arr.length-1);
    System.out.println("Sorted array: " + Arrays.toString(arr));

  }
}
