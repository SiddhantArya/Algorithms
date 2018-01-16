// Given an unsorted array of integers, sort the array into a wave like array.
// WaveForm array ‘arr[0..n-1]’ is arr[0] >= arr[1] <= arr[2] >= arr[3] <=  …..

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O(n)
import java.util.Arrays;
public class WaveSort
{

  public void swap (int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sort(int[] arr)
  {
    int n = arr.length-1;
    for (int i = 0; i < n; i+=2)
    {
      if (i>0 && arr[i] < arr[i-1])
        swap(arr, i, i-1);
      if (i<n && arr[i+1] > arr[i])
        swap(arr, i+1, i);
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {10, 90, 49, 2, 1, 5, 23};
    WaveSort obj = new WaveSort();
    System.out.println("Given Array:      " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("WaveSorted Array: " + Arrays.toString(arr));

  }

}
