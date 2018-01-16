// Cycle sort is a comparison sort that is theoretically optimal in terms of
// the total number of writes to the original array.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// It is based on the idea that array to be sorted can be divided into cycles.

// Minimizes the no of writes to the sort as each element is written at max once

// It is used when the writes to the memory are expensive.

// Stable: No
// In-Place: Yes
// Time Complexity: O(n^2)
// Space Complexity: O(1)

import java.util.Arrays;

public class CycleSort
{

  public static void sort (int arr[])
  {
    int n = arr.length;
    for (int cycle=0; cycle<n-1; cycle++)
    {
        int item = arr[cycle];
        int pos = cycle;

        for (int i = cycle+1; i<n; i++)
            if (arr[i] < item)
                pos++;

        if (pos == cycle)
            continue;

        while (item == arr[pos])
            pos += 1;

        if (pos != cycle)
        {
            int temp = item;
            item = arr[pos];
            arr[pos] = temp;
        }

        while (pos != cycle)
        {
            pos = cycle;

            for (int i = cycle+1; i<n; i++)
                if (arr[i] < item)
                    pos += 1;

            while (item == arr[pos])
                pos += 1;

            if (item != arr[pos])
            {
                int temp = item;
                item = arr[pos];
                arr[pos] = temp;
            }
        }
    }
  }

  public static void main(String[] args)
  {
    int arr[] = {1, 8, 3, 9, 10, 10, 2, 4 };
    System.out.println("Given Array:  " + Arrays.toString(arr));
    CycleSort obj = new CycleSort();
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }

}
