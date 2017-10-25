// Given a sorted array and a number x,
// write a function that counts the occurrences of x
// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Using binary search functions for floor and ciel to find the first and last
// occurences of x respectively and finding the frequency by subtracting indeces

// Time complexity: O(log n)
// Space complexity: O(log n) <- Recursive

import java.util.Arrays;

public class FindFreq
{
  // Using floor function to find the first occurence of x
  public int floor(int[] arr, int low, int high, int x)
  {
    if (low<=high)
    {
      int mid = low + (high-low)/2;
      if (arr[mid]==x && (mid==0 || arr[mid-1]<x) )
        return mid;
      if (arr[mid]<x)
        return floor(arr, mid+1, high, x);
      else
        return floor(arr, low, mid-1, x);
    }
    return -1;
  }

  // Using modified ciel function to find the last occurence of x
  public int ciel(int[] arr, int low, int high, int x)
  {
    if (low<=high)
    {
      int mid = low + (high-low)/2;
      if (arr[mid]==x && (mid==arr.length-1 || arr[mid+1]>x) )
        return mid;
      if (arr[mid]>x)
        return ciel(arr, low, mid-1, x);
      else
        return ciel(arr, mid+1, high, x);
    }
    return -1;
  }

  // Calculates the frequency by subtracting indeces
  public int find(int[] arr, int x)
  {
    int n = arr.length-1;
    if (n<=0)
    {
      System.out.println("Illegal Arguments provided");
      return -1;
    }

    int low = floor(arr, 0, n, x);
    if (low == -1)
      return -1;

    int high = ciel(arr, 0, n, x);

    return (high-low+1);
  }

  // Driver method
  public static void main(String[] args)
  {
    int[] arr = {1, 1, 2, 2, 2, 2, 3};
    int x1 = 1;
    int x2 = 2;
    int x3 = 3;
    int x4 = 4;

    FindFreq obj = new FindFreq();

    System.out.println("Given Array: " + Arrays.toString(arr));
    System.out.print("No1: " + x1);
    System.out.println(", Freq: " + obj.find(arr, x1));

    System.out.print("No2: " + x2);
    System.out.println(", Freq: " + obj.find(arr, x2));

    System.out.print("No3: " + x3);
    System.out.println(", Freq: " + obj.find(arr, x3));
  }
}
