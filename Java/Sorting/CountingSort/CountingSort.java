 // Implement Counting Sort by counting the number of objects having distinct
 // key values (kind of hashing). Then doing some arithmetic to calculate the
 // position of each object in the output sequence.

// Special case of Radix Sort

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity:  Worst/Average Case: O(n+k) where k is the range of data
// Space Complexity: O(n+k) as Count array is dependent on the range of data

// Sorting In Place: No
// Stable: Yes. Each element in the count array stores the no of elements
//         less than or equal to the index value. If we start outputting the
//         input array in the reverse direction the elements in the output array
//         will appear in the order they were present in the input array
//         therefore, making it a Stable Sorting algorithm.

// Counting sort is efficient if the range of input data is not
// significantly greater than the number of objects to be sorted.

// One significant disadvantage to this kind of sort is the need to maintain a
// contiguous array holding all of the possible values in the input.

import java.util.Arrays;

public class CountingSort
{

  // Utility Method to find minimum in the array
  public int min(int[] arr)
  {
    if (arr.length <= 0)
      return -1;

    int min = arr[0];
    for(int i=1; i<arr.length; i++)
    {
      if(arr[i] < min)
        min = arr[i];
    }

    return min;
  }

  // Utility Method to find maximum in the array
  public int max(int[] arr)
  {
    if (arr.length <= 0)
      return -1;

    int max = arr[0];
    for(int i=1; i<arr.length; i++)
    {
      if(arr[i] > max)
        max = arr[i];
    }

    return max;
  }

  // This will work for arrays with both positive and negative numbers.
  public void sort(int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Illegal Arguments Specified");
      return;
    }

    int min = min(arr);
    int max = max(arr);

    int count[] = new int[ max - min + 1 ];
    int output[] = new int[arr.length];

    // Updating the counts for each element
    for(int i=0; i<arr.length; i++)
      count[arr[i] - min]++;

    // Change the count array to contain cumulative counts of elements
    for(int i=1; i<count.length; i++)
      count[i]+=count[i-1];

    // Forming the output array
    for(int i=arr.length-1; i>=0; i--)
    {
      output[ count[arr[i] - min] - 1 ] = arr[i];
      count[arr[i] - min]--;
    }

    // Storing the sorted output array into original array
    for(int i=0; i<output.length; i++)
      arr[i] = output[i];

  }

  // Driver Method
  public static void main(String[] args)
  {
    int arr[] = {1, 4, 1, 2, 7, 5, 2};
    System.out.println("Given Array:  " + Arrays.toString(arr));
    CountingSort obj = new CountingSort();
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }
}
