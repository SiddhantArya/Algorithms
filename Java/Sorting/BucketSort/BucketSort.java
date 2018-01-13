// Bucket sort, also known as bin sort, is a distribution sort that works by
// arranging elements into several ‘buckets’ which are then sorted using another
// sort, typically insertion sort, and merged into a sorted list.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Bucket sort can be exceptionally fast because of the way elements are
// assigned to buckets, typically using an array where the index is the value.

// This means that more auxiliary memory is required for the buckets at the
// cost of running time than more comparison sorts.

// Time Complexity:
//                 Worst Case: O(n^2) when all elements allocated to same bucket
//                 Average Case: O(n+k) time in the average case
//                 where n is the number of elements to be sorted
//                       k is the number of buckets.

// Space Complexity: Worst case: O(n*k)

// Counting Sort is a special case of Bucket Sort,
// where each bucket contains 1 item, just with a more complex implementation.

// Following example is used to sort float values in the range of [0, 1)
import java.util.*;
import java.util.Arrays;

public class BucketSort
{

  // Insertion sort implicitly applied on insertion into Bucket
  public void insertIntoBucket(List<Float> bucket, float item)
  {
    if (bucket.isEmpty())
      bucket.add(item);
    else
    {
      int i=0;
      // This loop implicitly sorts individual buckets
      for(Float f:bucket)
      {
        if(Float.compare(f.floatValue(), item) <= 0)
          i++;
        else
          break;
      }
      bucket.add(i, new Float(item));
    }
  }

  // Method to perform Bucket Sort
  public void sort(float[] arr)
  {
    if (arr.length <=0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }

    // Initialize Buckets into dynamic lists
    List<List<Float>> buckets = new ArrayList<List<Float>>(10);
    for (int i = 0; i < 10; i++)
      buckets.add(new ArrayList<Float>());

    // Sorting based on hashing i.e. putting elements into different buckets
    for(int i=0; i<arr.length; i++)
    {
      // Position based on calculated hash corresponding to [0, 1)
      int bucket = (int) (arr[i]*10)%10;
      insertIntoBucket(buckets.get(bucket), arr[i]);
    }

    // Copying elements into original array
    int i=0;
    Iterator outer = buckets.iterator();
    while (outer.hasNext())
    {
      List ol = (List) outer.next();
      Iterator inner = ol.iterator();
      while (inner.hasNext())
        arr[i++] = ((Float)inner.next()).floatValue();
    }
  }

  // Driver Program
  public static void main(String[] args)
  {
    float arr[] = {0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f, 0.242f,
                   0.4f, 0.92f, 0.612f, 0.532f, 0.012f, 0.333f, 0.512f, 0.41f};
    BucketSort obj = new BucketSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted Array: " + Arrays.toString(arr));

  }

}
