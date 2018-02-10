// Implement Three Way Merge Sort algorithm.
// Divide and Conquer algorithm.
// It divides input array in three halves,
// calls itself for the three halves and
// then merges the three sorted halves.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: T(n) = 3T(n/3) + O(n)
//                : O(n . log_3 n)
// Although time complexity looks less compared to 2 way merge sort,
// the time taken actually may become higher because number of comparisons in
// merge function go higher.

// Space Complexity: O(n) <- for the two subarrays
// Sorting In Place: Not in a typical implementation (array),
//                   but in place in linked lists
// Stable: Yes

// Not good for arrays of large sizes as a lot of auxilliary space required

import java.util.Arrays;

public class MergeSortThreeWay
{
  public int[] merge(int[] arr, int l, int m1, int m2, int r)
  {

    int n1 = m1 - l + 1;
    int n2 = m2 - m1;
    int n3 = r - m2;

    int left[]  = new int[n1];
    int mid[]   = new int[n2];
    int right[] = new int[n3];

    for (int i=0; i<n1; i++)
      left[i] = arr[l+i];

    for (int i=0; i<n2; i++)
      mid[i] = arr[m1 + i + 1];

    for (int i=0; i<n3; i++)
      right[i] = arr[m2 + i + 1];

    int i = 0;
    int j = 0;
    int k = 0;
    int x = l;

    while (i<n1 && j<n2 && k<n3)
    {
      if (left[i] <= mid[j])
      {
        if (left[i] <= right[k])
          arr[x++] = left[i++];
        else
          arr[x++] = right[k++];
      }
      else
      {
        if (mid[j] <= right[k])
          arr[x++] = mid[j++];
        else
          arr[x++] = right[k++];
      }
    }

    while (i<n1 && j<n2)
    {
      if (left[i] <= mid[j])
        arr[x++] = left[i++];
      else
        arr[x++] = mid[j++];
    }

    while (i<n1 && k<n3)
    {
      if (left[i] <= right[k])
        arr[x++] = left[i++];
      else
        arr[x++] = right[k++];
    }

    while (j<n2 && k<n3)
    {
      if (mid[j] <= right[k])
        arr[x++] = mid[j++];
      else
        arr[x++] = right[k++];
    }

    while (i<n1)
      arr[x++] = left[i++];

    while (j<n2)
      arr[x++] = mid[j++];

    while (k<n3)
      arr[x++] = right[k++];

    return arr;
  }

  public void sort(int[] arr, int l, int h)
  {
    if (h - l >= 1)
    {
      int m1 = l +    (h-l)/3;
      int m2 = l + (2*(h-l)/3);

      sort(arr, l, m1);
      sort(arr, m1+1, m2);
      sort(arr, m2+1, h);

      merge(arr, l, m1, m2, h);

    }
  }

  public void sort(int[] arr)
  {
    if (arr.length<=0)
    {
      System.out.println("Invalid Arguments Specified");
      return;
    }
    sort(arr, 0, arr.length-1);
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90, 100, 1, 19};
    MergeSortThreeWay obj = new MergeSortThreeWay();
    System.out.println("Given Array : " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
