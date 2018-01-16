// CombSort is an improvement of the Bubble Sort algorithm.
// The gap starts with a large value and shrinks by a factor of 1.3 in every
// iteration until it reaches the value 1.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Comb Sort uses a gap based approach to reduce the number of inversion counts
// Inversion counts determine how far the array is to being sorted
// It is said to exist if an element to right is smaller i < j and a[i] > a[j]

// Time Complexity = Worst/Average Case: O(n^2), on average faster than Bubble
//                   Best Case: O(n)
// Space Complexity = O(1)
// In-Place: Yes
// Stable: No

import java.util.Arrays;

public class CombSort
{
  public int nextGap(int gap)
  {
    gap = (int) gap * 10 / 13;
    if (gap<1)
      gap = 1;
    return gap;
  }

  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sort(int[] arr)
  {
    if (arr.length < 0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }
    int gap = arr.length;
    boolean swapped = false;
    while (gap !=1 || swapped==true)
    {
      gap = nextGap(gap);
      swapped = false;

      for (int i=0; i+gap<arr.length; i++)
      {
        if (arr[i] > arr[i+gap])
        {
          swapped = true;
          swap(arr, i, i+gap);
        }
      }
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {1, 4, 1, 2, 7, 5, 2};
    System.out.println("Given Array:  " + Arrays.toString(arr));
    CombSort obj = new CombSort();
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
