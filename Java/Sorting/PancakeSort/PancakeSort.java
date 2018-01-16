// Given an an unsorted array, sort the given array. You are allowed to do only
// following operation on array.
// flip(arr, i): Reverse array from 0 to i
// Unlike a traditional sorting algorithm, which attempts to sort with the
// FEWEST comparisons possible, the goal is to sort the sequence in as few
// reversals as possible.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Pancake Sort is similar to Selection Sort. We repeatedly place the
// maximum element at the end and reduce the size of current array by one.

// Time Complexity: O(n^2)
// Space Complexity: O(1)
// In Place: Yes
import java.util.Arrays;

public class PancakeSort
{

  // Returns the index of the maximum element
  public int indexMax (int[] arr, int index)
  {
    int imax = 0;
    for (int i=0; i<=index; i++)
      if (arr[imax] > arr[i])
        imax = i;
    return imax;
  }

  // This function is same as a reverse of an array
  public void flip (int[] arr, int index)
  {
    for (int i=0; i<index; i++)
    {
      int temp = arr[index];
      arr[index] = arr[i];
      arr[i] = temp;
      index--;
    }
  }

  // Pancake Sort
  public void sort (int[] arr)
  {
    for (int size = arr.length-1; size>0; size--)
    {
      int imax = indexMax(arr, size);

      if (imax != size)
      {
        // Bring max element to front
        flip (arr, imax);
        // Flip entire array to get max element to back
        flip (arr, size);
      }
    }
    // Final Flip
    flip(arr, arr.length-1);
  }

  // Driver Method
  public static void main(String[] args)
  {
    int arr[] = {23, 10, 20, 11, 12, 6, 7, 15};
    System.out.println("Given Array:  " + Arrays.toString(arr));
    PancakeSort obj = new PancakeSort();
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }

}
