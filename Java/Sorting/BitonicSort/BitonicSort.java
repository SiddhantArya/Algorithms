// A bitonic sequence is a sequence with
// x_0 <= ... <= x_k >= ... >= x_n-1
// for some k, such that 0 <=  k <= n,
// or a circular shift of such a sequence.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Bitonic Sort is a classic parallel algorithm for sorting.
// We always compare elements in predefined sequence and the sequence of
// comparison doesn’t depend on data.
// Although, this algorithm only works if length of input is a power of 2

// Time Complexity: O(n . log(n) . log(n)) or O(n.log^2(n))
// Space Complexity: O(log(n) . log(n)) or O(log^2(n))
// Stable: No
// In-Place: Yes

import java.util.Arrays;

public class BitonicSort
{

  public void swapOnCompare(int[] arr, int i, int j, boolean dir)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments Specified");
      return;
    }
    if (dir == true && arr[i] > arr[j] ||
        dir == false && arr[i] < arr[j])
    {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }

  public void bitonicMerge(int[] arr, int low, int count, boolean dir)
  {
    if (count > 1)
    {
      int mid = count / 2;
      for (int i = 0; i<mid; i++)
      {
        swapOnCompare(arr, low+i,low+i+mid, dir);
      }

      bitonicMerge (arr, low, mid, dir);
      bitonicMerge (arr, low+mid, mid, dir);
    }
  }

  public void bitonicSort(int[] arr, int low, int count, boolean dir)
  {
    if (count > 1)
    {
      int mid = count / 2;
      // Recursively Sort individual parts
      bitonicSort (arr, low, mid, true);
      bitonicSort (arr, low+mid, mid, false);

      // Merge two sorted arrays (complete)
      bitonicMerge (arr, low, count, dir);
    }
  }

  public void sort(int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments Specified");
      return;
    }
    bitonicSort(arr, 0, arr.length, true);
  }

  public static void main(String[] args)
  {
    int arr[] = {3, 7, 4, 8, 6, 2, 1, 5};
    BitonicSort ob = new BitonicSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    ob.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }

}
