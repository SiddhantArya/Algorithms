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
//                 : O(N) for auxiliary array for stack
// Sorting In Place: Yes
// Stable: Yes

import java.util.Arrays;

public class IterativeQuickSort
{

  // Swap Method
  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // This version of Quick Sort chooses the last element as the pivot
  public int partition(int[] arr, int low, int high)
  {
    int i = low-1;
    int pivot = arr[high];

    for (int j = low; j <= high-1; j++)
    {
      if (arr[j] <= pivot)
      {
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr, i+1, high);
    return i+1;
  }

  // Iterative Quick Sort uses auxiliary stack to keep track of low, high values
  // Mimicking the call stack during the recursion (albeit storing less info)
  public void sort(int[] arr, int low, int high)
  {
    int top = -1;
    int[] stack = new int[high - low + 1];

    stack[++top] = low;
    stack[++top] = high;


    while (top >= 0)
    {
      int h = stack[top--];
      int l = stack[top--];

      int pivot = partition (arr, l, h);

      if (pivot-l > 1)
      {
        stack[++top] = l;
        stack[++top] = pivot-1;
      }

      if (h-pivot > 1)
      {
        stack[++top] = pivot+1;
        stack[++top] = high;
      }
    }
  }

  public static void main(String[] args)
  {
    int[] arr = {10, 7, 8, 9, 1, 5};
    IterativeQuickSort obj = new IterativeQuickSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr, 0, arr.length-1);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
