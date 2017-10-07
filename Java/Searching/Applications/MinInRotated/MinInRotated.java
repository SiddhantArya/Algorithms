// Find the minimum element in a sorted array which has been rotated
// @name  Siddhant Arya
// @email siddhant.arya18@gmail.com

// Simplest implementation will use linear search to check the first time an
// element is smaller than the previous one.

// Efficient implementation is the one which uses a modified version of the
// Binary Search algorithm.

// Time complexity: O(log n)
// Space complexity: O(log n) <- Recursive

// No of Rotations = index of the min element

public class MinInRotated
{
  public int searchMin(int[] arr, int low, int high)
  {
    if(low<high)
    {
      int mid = low + (high-low)/2;

      // As is common in Binary Search, comparisons made with mid
      if (mid < high && arr[mid] > arr[mid+1])
        return arr[mid+1];
      if (low < mid  && arr[mid-1] > arr[mid])
        return arr[mid];

      // Continuing search if the conditions were not met
      if (arr[mid] > arr[high])
        return searchMin(arr, mid+1, high);
      else
        return searchMin(arr, low, mid-1);
    }
    else
      return arr[low];
  }

  public static void main(String[] args)
  {
    MinInRotated obj = new MinInRotated();
    // Base cases borrowd from Geek for Geeks
    int arr1[] =  {5, 6, 1, 2, 3, 4};
    int n1 = arr1.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr1, 0, n1-1));

    int arr2[] =  {1, 2, 3, 4};
    int n2 = arr2.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr2, 0, n2-1));

    int arr3[] =  {1};
    int n3 = arr3.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr3, 0, n3-1));

    int arr4[] =  {1, 2};
    int n4 = arr4.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr4, 0, n4-1));

    int arr5[] =  {2, 1};
    int n5 = arr5.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr5, 0, n5-1));

    int arr6[] =  {5, 6, 7, 1, 2, 3, 4};
    int n6 = arr6.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr6, 0, n1-1));

    int arr7[] =  {1, 2, 3, 4, 5, 6, 7};
    int n7 = arr7.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr7, 0, n7-1));

    int arr8[] =  {2, 3, 4, 5, 6, 7, 8, 1};
    int n8 = arr8.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr8, 0, n8-1));

    int arr9[] =  {3, 4, 5, 1, 2};
    int n9 = arr9.length;
    System.out.println("The minimum element is "+ obj.searchMin(arr9, 0, n9-1));
  }
}
