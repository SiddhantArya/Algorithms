// Implement Selection Sort by repeatedly selecting the minimum element from the
// rest of the array and putting it in the front
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: Worst/Average Case: O(n^2)
// Space Complexity: O(1)

// Sorting In Place: Yes
// Stable: Yes

import java.util.Arrays;

public class SelectionSort
{
  // Swap Method
  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // Selection Sort
  public void sort(int[] arr)
  {
    for (int i=-1; i<arr.length-1; i++)
    {
      int min = i+1;
      for(int j=i+1; j<arr.length; j++)
      {
        if(arr[j] < arr[min])
        {
          min = j;
        }
      }
      swap(arr, i+1, min);
    }
  }

  // Driver Method
  public static void main(String[] args)
  {
    // Base case borrowed from GeekForGeeks
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    SelectionSort obj = new SelectionSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));

  }

}
