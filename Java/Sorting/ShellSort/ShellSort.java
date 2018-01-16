// Shell Sort

// In insertion sort, we move elements only one position ahead. When an element
// has to be moved far ahead, many movements are involved. Shell sort is an
// alternate algorithm based on Insertion sort where array elements are
// gap-sorted i.e. for a large value of h, we make the array h-sorted.
// The value of h is reduced until it reaches 1 and the array is said to be
// sorted if every h-th element is sorted.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Shell Sort is a variation of insertion sort.
// Any mechanism for selecting gaps can be selected as long as the last element
// in the gap sequence is 1.

// In Place: Yes


// Stable: No, Instability is to be expected because the increment-based sorts
//            move elements distances without examining of elements in between.

// Time Complexity: Depends on the gaps selected
//                  For this algorithm, complexity is O(n^2)
import java.util.Arrays;

public class ShellSort
{
  public void sort(int[] arr)
  {
    int n = arr.length;
    for (int gap = n/2; gap > 0; gap /= 2)
    {
        // First ensure the entire array is gap-Sorted
        // i.e. elements that are gap-separated must be sorted

        for (int i = gap; i < n; i += 1)
        {
            // add a[i] to the elements that have been gap
            // sorted save a[i] in temp and make a hole at
            // position i
            int temp = arr[i];

            // shift earlier gap-sorted elements up until
            // the correct location for a[i] is found
            int j;
            for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
              arr[j] = arr[j - gap];

            // put temp (the original a[i]) in its correct
            // location
            arr[j] = temp;
        }
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    ShellSort obj = new ShellSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
