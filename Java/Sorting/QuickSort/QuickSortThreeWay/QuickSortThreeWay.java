// Implement Quick Sort algorithm.
// Divide and Conquer algorithm.
// Finds a pivot elemnt and implements such that the elements left
// to the pivot are all lower than the pivot and the elements right
// to the pivot are all higher. The Three Way also arranges elements in mid.

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

public class QuickSortThreeWay
{
  // Swap Method
  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public int[] partition(int[] arr, int low, int high)
  {
    int pivot = arr[high];
    int mid = low;

    while(mid<=high)
    {
      if(arr[mid]<pivot)
      {
        swap(arr, low, mid);
        low++;
        mid++;
      }
      else if (arr[mid]==pivot)
      {
        mid++;
      }
      else
      {
        swap(arr, mid, high);
        high--;
      }
    }
    return new int[]{low-1, high};
  }

  public void sort(int[] arr, int low, int high)
  {
    if (low>=high)
      return;

    int[] p = partition(arr, low, high);
    sort(arr, low, p[0]);
    sort(arr, p[1], high);
  }

  public static void main(String[] args)
  {
    int[] arr = {4, 3, 9, 1, 8, 6, 7, 2, 5, 5, 4};
    QuickSortThreeWay obj = new QuickSortThreeWay();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr, 0, arr.length-1);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
