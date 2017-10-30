// Implement Insertion Sort by repeatedly storing the next element from the
// rest of the array into its correct position in the sorted subarray

// Insertion sort is used when number of elements is small.
// It can also be useful when input array is almost sorted,
// only few elements are misplaced in complete big array.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: Worst/Average Case: O(n^2)
// Space Complexity: O(1)

// Sorting In Place: Yes
// Stable: Yes
import java.util.Arrays;

public class InsertionSort
{
  // Swap Method
  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // Insertion Sort
  public void sort(int[] arr)
  {
    for (int i=1; i<arr.length-1; i++)
    {
      int key = arr[i];
      int j=i-1;

      // Transfering each greater element to next position
      // An optimization can be performed here to find the
      // correct location of the element using binary search
      // if the size of the array is too large
      // This would reduce the no of comparisons.
      // However, the overall time complexity remains the same.
      while (j>=0 && arr[j+1] < arr[j])
      {
        swap(arr, j+1, j);
        j--;
      }
      arr[j+1] = key;
    }
  }

  // Driver Method
  public static void main(String[] args)
  {
    // Base case borrowed from GeekForGeeks
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    InsertionSort obj = new InsertionSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
