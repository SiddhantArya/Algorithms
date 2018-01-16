// Gnome Sort also called Stupid sort is based on the concept of a Garden Gnome
// sorting his flower pots.

// A garden gnome sorts the flower pots by the following method-
// He looks at the flower pot next to him and the previous one;
// if they are in the right order he steps one pot forward,
// otherwise he swaps them and steps one pot backwards.

//If there is no previous pot (he is at the starting of the pot line),
// he steps forwards; if there is no pot next to him (he is at the end of the
// pot line), he is done.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O(n^2)
// Space Complexity: O(1)
// Stable: Yes
// In-Place: Yes

import java.util.Arrays;

public class GnomeSort
{

  public void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sort(int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }

    int i = 0;
    while (i < arr.length)
    {
      if (i == 0 || arr[i - 1] <= arr[i])
        i++;
      else
      {
        swap(arr, i, i - 1);
        i--;
      }
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    GnomeSort obj = new GnomeSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
