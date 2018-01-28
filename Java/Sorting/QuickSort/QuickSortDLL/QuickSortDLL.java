// Implement Quick Sort algorithm for Doubly Linked Lists.
// Divide and Conquer algorithm.
// Finds a pivot elemnt and implements such that the elements left
// to the pivot are all lower than the pivot and the elements right
// to the pivot are all higher.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// QuickSort is preferred over MergeSort for Arrays
// QuickSort is cache friendly (locality of reference)
// Allocating and de-allocating the extra space used for merge sort O(N)
// increases the running time of the algorithm.
// This is reversed in case of LinkedLists as Linked Lists by design don't
// support randomized access to elements so continuous memory access increases
// the overhead for QuickSort.

// Time Complexity: Average Case:
//                : T(n) = 2T(n/2) + c
//                :      = O(n . log n)
//                : Worst Case: The worst case occurs when the partition
//                  process always picks greatest or smallest element as pivot.
//                : T(n) = T(n-1) + theta(n)
//                :      = T(n^2)

// Space Complexity: O(1) <- for the two subarrays
//                 : O(log N) of stack space is required for recursion
// Sorting In Place: Yes
// Stable: Yes

import java.util.Arrays;

class Node
{
  int data;
  Node next, prev;

  public Node(int d)
  {
    this.data = d;
    this.next = null;
    this.prev = null;
  }

  public int getData()
  {
    return this.data;
  }

  public Node getNext()
  {
    return this.next;
  }

  public Node getPrev()
  {
    return this.prev;
  }

  public void setData(int d)
  {
    this.data = d;
  }

  public void setNext(Node x)
  {
    this.next = x;
  }

  public void setPrev(Node x)
  {
    this.prev = x;
  }

  public String toString()
  {
    return this.getData()+ " " + this.getNext();
  }

}

public class QuickSortDLL
{
  // Head node for the SLL
  Node root;

  public QuickSortDLL (int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments specified");
      return;
    }

    // Creating List
    for (int i=0; i < arr.length; i++)
      insert(arr[i]);
  }

  public QuickSortDLL (int x)
  {
    this.root = new Node(x);
  }

  // Insert into the Single Linked List
  public void insert(int x)
  {
    Node temp = new Node(x);
    Node cur = this.root;
    if (cur != null)
    {
      while (cur.getNext() != null)
        cur = cur.getNext();
      temp.setPrev(cur);
      cur.setNext(temp);
    }
    else
    {
      this.root = temp;
    }
  }

  // The display Method
  public String toString()
  {
    StringBuilder out = new StringBuilder("[");
    Node cur = this.root;
    while (cur != null)
    {
      if (cur.getNext() != null)
        out.append(cur.getData() + ", ");
      else
        out.append(cur.getData());
      cur = cur.getNext();
    }
    out.append("]");
    return out.toString();
  }

  public String toStringReverse()
  {
    StringBuilder out = new StringBuilder("[");
    Node cur = getLast(this.root);
    while (cur != null)
    {
      if (cur.getPrev() != null)
        out.append(cur.getData() + ", ");
      else
        out.append(cur.getData());
      cur = cur.getPrev();
    }
    out.append("]");
    return out.toString();
  }

  public Node getLast(Node x)
  {
    Node t = x;
    while (t.getNext() !=null)
      t = t.getNext();
    return t;
  }

  public QuickSortDLL sort(Node head)
  {
    int pivot = head.getData();
    Node cur = head.getNext();

    QuickSortDLL less=null, more=null, eq;
    eq = new QuickSortDLL(head.getData());
    if (head == null)
      return null;
    while (cur!=null)
    {
      if (cur.getData() < pivot)
      {
        if (less == null)
          less = new QuickSortDLL(cur.getData());
        else
          less.insert(cur.getData());
      }
      else if (cur.getData() == pivot)
        eq.insert (cur.getData());
      else
      {
        if (more == null)
          more = new QuickSortDLL(cur.getData());
        else
          more.insert(cur.getData());
      }
      cur = cur.getNext();
    }

    if (less != null)
      less = sort(less.root);
    if (more != null)
      more = sort(more.root);

    // Merge the three lists
    int flag = 0;
    if (more != null)
    {
      more.root.setPrev(getLast(eq.root));
      getLast(eq.root).setNext(more.root);
    }

    if (less != null)
    {
      eq.root.setPrev(getLast(less.root));
      getLast(less.root).setNext(eq.root);
      flag = 1;
    }
    return (flag==1 ? less: eq);

  }

  public void sort()
  {
    this.root = sort(this.root).root;
  }

  public static void main(String[] args)
  {
    int[] arr = {4, 3, 9, 1, 8, 6, 7, 2, 5, 5, 4};
    QuickSortDLL obj = new QuickSortDLL(arr);
    System.out.println("Given Array:  " + obj);
    obj.sort();
    System.out.println("Sorted Array: " + obj);
    System.out.println("Sorted Array Reverse: " + obj.toStringReverse());

  }

}
