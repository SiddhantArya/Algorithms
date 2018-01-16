// Cocktail Sort is an improvement of Bubble sort and is implemented in 2 stages
// Forward phase:  left to right, just like the Bubble Sort. During the loop,
//                 adjacent items are compared and if value on the left is
//                 greater than the value on the right, then values are swapped.
// Backward phase: right to left with similar operation to forward phase

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O(n^2)
// Space Complexity: O(1)
// Stable: Yes
// In-Place: Yes

import java.util.Arrays;

public class CocktailSort
{
  public void swap (int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sort (int[] arr)
  {
    int start = 0;
    int end = arr.length-1;
    boolean swapped = true;
    while (swapped==true)
    {
      swapped = false;
      for (int i=start; i<end; i++)
      {
        if (arr[i] > arr[i+1])
        {
          swap(arr, i, i+1);
          swapped = true;
        }
      }

      if (swapped == false)
        break;
      swapped = false;
      end--;

      for(int i=end; i>start; i--)
      {
        if (arr[i-1] > arr[i])
        {
          swap(arr, i, i-1);
          swapped = true;
        }
      }
      start++;
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    CocktailSort obj = new CocktailSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
