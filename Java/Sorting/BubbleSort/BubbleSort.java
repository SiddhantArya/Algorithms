// Implement Bubble Sort algorithm
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: Worst Case: O(n^2) occurs when array is reverse sorted.
//                : O(n) occurs when array is already sorted

// Space Complexity: O(1)

// Sorting In Place: Yes
// Stable: Yes

import java.util.Arrays;

public class BubbleSort
{
  // Swap method
  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[j];
    arr[j] = arr[i];
    arr[i] = temp;
  }

  // Bubble Sort
  public void sort(int[] arr)
  {
    // Swapped condition is an optimization over regular BubbleSort
    // It stops the sorting algorithm for unnecessary execution
    boolean swapped = true;
    for (int i = 0; i < arr.length-1 && swapped==true; i++)
    {
      swapped=false;
      for(int j=arr.length-1; j>i; j--)
      {
        if (arr[i] > arr[j])
        {
          swapped = true;
          swap(arr, i, j);
        }
      }
    }
  }

  // Driver Method
  public static void main(String[] args)
  {
    // Base case borrowed from GeekForGeeks
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    BubbleSort obj = new BubbleSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
