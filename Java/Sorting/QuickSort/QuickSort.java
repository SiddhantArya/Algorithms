// Implement Quick Sort algorithm.
// Divide and Conquer algorithm.
// Finds a pivot elemnt and implements such that the elements left
// to the pivot are all lower than the pivot and the elements right
// to the pivot are all higher.
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: T(n) = 2T(n/2) + c
//                : O(n . log n)

// Space Complexity: O(1) <- for the two subarrays
// Sorting In Place: Yes
// Stable: Yes

import java.util.Arrays;

public class QuickSort
{

  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

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
    QuickSort obj = new QuickSort();
    System.out.println("Given Array: " + Arrays.toString(arr));
    obj.sort(arr, 0, arr.length-1);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
