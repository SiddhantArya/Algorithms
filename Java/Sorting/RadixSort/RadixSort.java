// Radix Sort is to do digit by digit sort starting from least significant digit
// to most significant digit.

// It is used in place of Comparison based sorting algorithms like Merge Sort,
// Heap Sort, Quick-Sort etc whose lower bound is O(nLogn) i.e.,
// they cannot do better than nLogn.

// Radix sort uses counting sort as a subroutine to sort which is a linear time
// sorting algorithm that sort in O(n+k) time where k is the range of elements.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O((n+b) * log_b(k))
//                  where, k is the maximum possible value,
//                         d (digits in input integers) => log_b (k)
//                         b is the base representing numbers (10 for decimal)
//                     as. Radix Sort takes O((n+b)*d)

// Comparison with O(n log(n)) sorting algorithms:
//                  On average, Radix Sort doesn't do better than them.
//                  However, if k <= n^c where c is a constant.
//                           the complexity becomes O(nLog_b(n)).
//                           still doesn’t beat comparison based sorting algos.
//                  Further, If b = n, we get the time complexity as O(n).
//                           Linear Time if base is equal to number of inputs or
//                  In other words, we can sort an array of integers with range
//                  from 1 to n^c if the numbers are represented in base n
//                  (or every digit takes log2(n) bits).

import java.util.Arrays;

public class RadixSort
{

  // Method to get max value from array
  public int max(int[] arr)
  {
    if(arr.length <= 0)
    {
      System.out.println("Invalid Arguments specified");
      return -1;
    }

    int max = arr[0];
    for (int i=1; i<arr.length; i++)
    {
      if (arr[i] > max)
       max = arr[i];
    }
    return max;
  }

  // Radix sort uses counting sort for sorting based on digits
  public void count_sort(int[] arr, int radix)
  {
    if(arr.length <= 0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }

    int count[] = new int[10];
    int output[] = new int[arr.length];

    // Finding count of occurence based on remainder
    for (int i=0; i<arr.length; i++)
      count[( (arr[i]/radix) % 10 )]++;

    // Forming cumulative count array
    for (int i=1; i<10; i++)
      count[i]+=count[i-1];

    // Sorting into output array
    for (int i=arr.length-1; i>=0; i--)
    {
      output[ count[(arr[i]/radix)%10]-1 ] = arr[i];
      count[(arr[i]/radix)%10]--;
    }

    // Copying output array into original array
    for(int i =0; i<arr.length; i++)
      arr[i] = output[i];
  }

  // Radix sort
  public void radix_sort(int[] arr)
  {
    if(arr.length <= 0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }

    int max = max(arr);
    for (int radix = 10; max/radix > 0; radix*=10)
      count_sort(arr, radix);
  }

  // Driver Program
  public static void main(String[] args)
  {
    int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
    System.out.println("Given Array:  " + Arrays.toString(arr));
    RadixSort obj = new RadixSort();
    obj.radix_sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }

}
