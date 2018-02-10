// Implement Iterative Merge Sort algorithm.
// Divide and Conquer algorithm.
// It divides input array in two halves,
// calls itself for the two halves and
// then merges the two sorted halves.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: T(n) = 2T(n/2) + c
//                : O(n . log n)

// Space Complexity: O(n) <- for the two subarrays
// Sorting In Place: Not in a typical implementation (array),
//                   but in place in linked lists
// Stable: Yes

// Not good for arrays of large sizes as a lot of auxilliary space required


import java.util.Arrays;
public class MergeSortIterative
{

  public int[] merge(int[] arr, int l, int m, int r)
  {
    int n1 = m - l + 1;
    int n2 = r - m;
    int left[] = new int[n1];
    int right[] = new int[n2];


    for (int i = 0; i<n1; i++)
      left[i] = arr[l+i];

    for (int j = 0; j<n2; j++)
      right[j] = arr[m+j+1];

    int i=0;
    int j=0;
    int k=l;

    while (i<n1 && j<n2)
    {
      if (left[i] <= right[j])
        arr[k++] = left[i++];
      else
        arr[k++] = right[j++];
    }

    while (i<n1)
      arr[k++] = left[i++];

    while (j<n2)
      arr[k++] = right[j++];

    return arr;
  }

  public void sort(int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments Specified");
      return;
    }

    for (int cur_size = 1; cur_size<arr.length; cur_size*=2)
    {
      for (int left = 0; left<arr.length-1; left += 2*cur_size)
      {
        int mid   = left + cur_size - 1;
        int right = Math.min(left + cur_size*2 - 1, arr.length-1);
        arr = merge(arr, left, mid, right);
      }

    }

  }

  public static void main(String[] args)
  {
    // Base case borrowed from GeekForGeeks
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    MergeSortIterative obj = new MergeSortIterative();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
