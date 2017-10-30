// Given two almost identical arrays which differ by an element,
// write a function that returns the extra element
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Implemented Using binary search

// Time complexity: O(log n)
// Space complexity: O(log n) <- Recursive

import java.util.Arrays;

public class FindExtra
{
  public int findExtra(int arr1[], int arr2[], int left, int right, int index)
  {
    if (left <= right)
    {
      int mid = left + (right-left)/2;
      if (mid == arr1.length-1)
        return mid;
      if (arr1[mid] == arr2[mid])
        return findExtra(arr1, arr2, mid+1, right, index);
      else
        return findExtra(arr1, arr2, left, mid-1, mid);
    }
    return index;
  }

  // Driver method
  public static void main(String[] args)
  {
    int arr1[] = {2, 3, 4, 6, 8, 10, 12};
    int arr2[] = {2, 4, 6, 8, 10, 12};
    int arr3[] = {1, 2, 3, 4, 6, 8, 10};
    int arr4[] = {2, 3, 4, 6, 8, 10};
    int n = arr1.length;

    System.out.println("Array1: " + Arrays.toString(arr1));
    System.out.println("Array2: " + Arrays.toString(arr2));
    System.out.print("Extra Element is at index: ");
    System.out.println(new FindExtra().findExtra(arr1, arr2, 0, n-1, -1));

    System.out.println("Array1: " + Arrays.toString(arr1));
    System.out.println("Array3: " + Arrays.toString(arr3));
    System.out.print("Extra Element is at index: ");
    System.out.println(new FindExtra().findExtra(arr1, arr3, 0, n-1, -1));

    System.out.println("Array1: " + Arrays.toString(arr1));
    System.out.println("Array4: " + Arrays.toString(arr4));
    System.out.print("Extra Element is at index: ");
    System.out.println(new FindExtra().findExtra(arr1, arr4, 0, n-1, -1));
  }
}
