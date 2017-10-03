// Jump Search
// @author Siddhant Arya
// @email siddhant.arya18@gmail.com

// Time Complexity: In the worst case, we have to do n/m jumps, hence
//                  No of comparisons:     (n/m) + m-1
//                  No of Steps to be min: m = sqrt(n) <Time Complexity>
//                : O( sqrt(n) )

// Space Complexity: O(1)

import java.util.Arrays;

public class JumpSearch
{

  public int search(int[] arr, int x)
  {
    int n = arr.length-1;

    if (n<0)
    {
      System.out.println("Incorrect Array entered");
      return -2;
    }

    // Calculating the jump amount
    int jmp = (int) Math.floor(Math.sqrt(n));
    int step = jmp;
    int prev = 0;
    // Updating prev and step corresponding to the bucket containing the element
    while ( prev<n && arr[(int) Math.min(step, n)]<x)
    {
      prev = step;
      step += jmp;
    }

    // Linear traversal within the bucket
    while ( arr[prev]<x && prev<(int)Math.min(step, n) )
      prev++;

    if ( arr[prev]==x )
      return prev;

    return -1;
  }

  public static void main(String[] args)
  {
    int sorted_arr[] = {0, 1, 1, 2, 3, 5, 8, 13, 21,
                      34, 55, 89, 144, 233, 377, 610};
    JumpSearch obj = new JumpSearch();
    System.out.println("Given array: " + Arrays.toString(sorted_arr));
    System.out.println("Negative Results mean <Not Found>");
    System.out.println("Result <1:NF>: " + obj.search(sorted_arr, 610));
  }
}
