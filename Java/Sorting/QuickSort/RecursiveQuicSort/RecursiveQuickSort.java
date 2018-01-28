// Implement Quick Sort algorithm.
// Divide and Conquer algorithm.
// Finds a pivot elemnt and implements such that the elements left
// to the pivot are all lower than the pivot and the elements right
// to the pivot are all higher.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// QuickSort is preferred over MergeSort for Arrays
// QuickSort is cache friendly (locality of reference)
// Allocating and de-allocating the extra space used for merge sort O(N)
// increases the running time of the algorithm.
// This is reversed in case of LinkedLists as Linked Lists by design don't
// support randomized access to elements so continuous memory access increases
// the overhead for QuickSort.

// Time Complexity: Average Case:
//                : T(n) = 2T(n/2) + c
//                :      = O(n . log n)
//                : Worst Case: The worst case occurs when the partition
//                  process always picks greatest or smallest element as pivot.
//                : T(n) = T(n-1) + theta(n)
//                :      = T(n^2)

// Space Complexity: O(1) <- for the two subarrays
//                 : O(log N) of stack space is required for recursion
// Sorting In Place: Yes
// Stable: Yes

import java.util.Arrays;

public class RecursiveQuickSort
{

  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // This version of Quick Sort chooses the middle element as the pivot 
  public void sort(int[] arr, int low, int high)
  {
    int mid = low + (high-low)/2;
    int pivot = arr[mid];
    int i = low;
    int j= high;

    while(i<=j)
    {
      // Find the index to the left which is higher than pivot
      while(arr[i] < pivot)
        i++;
      // Find the index to the right which is lower than pivot
      while(arr[j] > pivot)
        j--;

      if(i<=j)
      {
        swap(arr, i, j);
        i++;
        j--;
      }

      // Call QuickSort recursively
      if(low<j)
        sort(arr, low, j);
      if(high>i)
        sort(arr, i, high);
    }
  }

  public static void main(String[] args)
  {
    int[] arr = {10, 7, 8, 9, 1, 5};
    RecursiveQuickSort obj = new RecursiveQuickSort();
    System.out.println("Given Array: " + Arrays.toString(arr));
    obj.sort(arr, 0, arr.length-1);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
