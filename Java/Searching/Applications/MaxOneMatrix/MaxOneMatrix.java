// Row with Max Ones in a Matrix
// @name  Siddhant Arya
// @email siddhant.arya18@gmail.com

// Given a binary matrix (containing only 0 and 1) of order n*n. All rows are
// sorted already, We need to find the row number with maximum number of 1s.
// Also find number of 1s in that row.

// Basic Approach : Traverse whole of matrix and for each row find the number of
// 1 and among all that keep updating the row number with maximum number of 1.
// This approach will result in O(n^2) time complexity.

// Better Approach : We can perform a better if we try to apply binary search
// for finding position of first 1 in each row and as per that we can find the
// number of 1 from each row as each row is in sorted order.
// This will result in O(nlogn) time complexity.

// Best Approach: Utilizes the fact that the rows are all sorted. So, all 1s
// will be present at the end of each row. Starting from end of first row, we
// can proceed to the first occurence of 1 in that row and henceforth move down
// to the next row. We can further decrement iff the element is a 1, otherwise
// move down and continue till we reach the last row.
// This will result in O(n) time complexity.

public class MaxOneMatrix
{
  public void calculateMaxOnes(int[][] m)
  {
    int n_rows = m.length;
    int n_cols = m[0].length;
    int i,j;
    int row=0;
    for (i=0, j=n_cols-1; i<n_rows;i++)
    {
      while(m[i][j] == 1 && j>=0)
      {
        row = i;
        j--;
      }
    }
    System.out.println("Row: " + (row+1) );
    System.out.println("Ones: " + (int)(n_cols-j-1));
  }

  public static void main(String[] args)
  {
    int[][] matrix = new int[][] {{0, 0, 0, 1},
                                  {0, 0, 0, 1},
                                  {0, 0, 0, 0},
                                  {0, 1, 1, 1}};
    MaxOneMatrix obj = new MaxOneMatrix();
    obj.calculateMaxOnes(matrix);
  }
}
