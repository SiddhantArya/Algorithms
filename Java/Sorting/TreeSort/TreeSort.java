// Tree sort is a sorting algorithm that is based on Binary Search Tree data
// structure. It first creates a binary search tree from the elements of the
// input list or array and then performs an in-order traversal on the created
// binary search tree to get the elements in sorted order.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: O(n log n) as
//                  Adding one item to a Binary Search tree takes O(log n) time.
// Space Complexity: O(n)
// In-Place: No

import java.util.Arrays;

class Node
{
  int key;
  Node left, right;

  public Node (int k)
  {
    this.key = k;
    this.left = null;
    this.right = null;
  }

  public Node (int k, Node l, Node r)
  {
    this.key = k;
    this.left = l;
    this.right = r;
  }

  public int getKey()
  {
    return this.key;
  }

  public Node getLeft()
  {
    return this.left;
  }

  public Node getRight()
  {
    return this.right;
  }

  public void setKey(int k)
  {
    this.key = k;
  }

  public void setLeft(Node l)
  {
    this.left = l;
  }

  public void setRight(Node r)
  {
    this.right = r;
  }
}

public class TreeSort
{
  int index;

  // Insert the element into the Tree
  public Node insert(Node root, int key)
  {
    if (root == null)
      return new Node(key);
    // Recursively insert into left
    else if (key < root.getKey())
      root.setLeft(insert(root.getLeft(), key));
    // Recursively insert into right
    else
      root.setRight(insert(root.getRight(), key));
    return root;
  }

  // In-Order traversal of the Tree
  public void inorderArray(Node root, int[] arr)
  {
    if (root!=null)
    {
      inorderArray(root.getLeft(), arr);
      arr[index++] = root.getKey();
      inorderArray(root.getRight(), arr);
    }
  }

  public void sort (int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }

    // Make first element root
    Node root = new Node(arr[0]);
    // Insert remaining items into the tree
    for (int i=1; i<arr.length; i++)
      insert(root, arr[i]);

    this.index = 0;
    // Inorder traversal returns the sorted array
    inorderArray(root, arr);
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    TreeSort obj = new TreeSort();
    System.out.println("Given Array:  " + Arrays.toString(arr));
    obj.sort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
