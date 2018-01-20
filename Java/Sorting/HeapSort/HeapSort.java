// Heap just guarantees that elements on higher levels are greater(for max-heap)
// or smaller (for min-heap) than elements on lower levels,
// whereas BST guarantees order (from "left" to "right").
// If you want sorted elements, go with BST.by Dante is not a geek

// Heap is better at findMin/findMax (O(1)), while BST is good at all finds
// (O(logN)). Insert is O(logN) for both structures. If you only care about
// findMin/findMax (e.g. priority-related), go with heap.
// If you want everything sorted, go with BST.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O(nLogn)
// Space Complexity: O(1)
// Stable: No
// In-Place: Yes
import java.util.Arrays;

public class HeapSort {

  public void swap (int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sort(int[] arr)
  {
    int n = arr.length;

    // Build a heap starting from (rooted at i)
    for (int i = n/2 - 1; i>= 0; i--)
      heapify(arr, n, i);

    // Bottom-up Heap Sort
    for (int i = n-1; i >= 0; i--)
    {
      // Swap the last element of heap with root
      swap(arr, i, 0);
      heapify(arr, i, 0);
    }
  }

  // Builds Min/Max Heap
  public void heapify(int[] arr, int n, int root)
  {
    int i_max = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;

    if ( left < n && arr[left] > arr[i_max])
    {
      i_max = left;
    }
    if ( right < n && arr[right] > arr[i_max])
    {
      i_max = right;
    }

    if(i_max != root)
    {
      swap(arr, i_max, root);
      // Recursively heapify the affected sub-tree
      heapify(arr, n, i_max);
    }
  }


  public static void main(String[] args) {
    int[] arr = {2, 9, 1, 4, 3, 7, 8, 6, 5};
    System.out.println("Given Array:  " + Arrays.toString(arr));
    HeapSort obj = new HeapSort();
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));
  }

}
