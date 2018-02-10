// Implement Merge Sort algorithm in a Doubly Linked List.
// Divide and Conquer algorithm.
// It divides input array in two halves,
// calls itself for the two halves and
// then merges the two sorted halves.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Time Complexity: T(n) = 2T(n/2) + c
//                : O(n . log n)

// Space Complexity: O(n) <- for the two subarrays
// Sorting In Place: Not in a typical implementation (array),
//                   but in place in linked lists
// Stable: Yes

// Not good for arrays of large sizes as a lot of auxilliary space required
// But, it is preferred for sorting a linked list as the slow random-access
// performance of a linked list other algorithms like quicksort perform poorly,
// and others (such as heapsort) completely impossible.


class Node
{
  int data;
  Node prev, next;

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

  public Node getPrev()
  {
    return this.prev;
  }

  public Node getNext()
  {
    return this.next;
  }

  public void setData(int x)
  {
    this.data = x;
  }

  public void setPrev(Node n)
  {
    this.prev = n;
  }

  public void setNext(Node n)
  {
    this.next = n;
  }
}

public class MergeSortDLL
{
  Node root;

  public MergeSortDLL(int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments Specified");
      return;
    }

    for (int i = 0; i<arr.length; i++)
      insert(arr[i]);
  }

  public void insert(int x)
  {
    Node temp = new Node(x);
    Node cur = this.root;
    if (cur!=null)
    {
      while(cur.getNext() != null)
        cur = cur.getNext();
      temp.setPrev(cur);
      cur.setNext(temp);
    }
    else
    {
      this.root = temp;
    }
  }

  public Node getMid(Node h)
  {
    Node slow = h;
    Node fast = slow.getNext();

    while (fast != null)
    {
      fast = fast.getNext();
      if (fast != null)
      {
        slow = slow.getNext();
        fast = fast.getNext();
      }
    }
    return slow;
  }

  public Node merge(Node left, Node right)
  {
    if (left == null)
      return right;
    if (right == null)
      return left;

    Node res = null;
    Node temp = null;
    if (left.getData() <= right.getData())
    {
      res = left;
      temp = merge(left.getNext(), right);
      temp.setPrev(res);
      res.setNext(temp);
    }
    else
    {
      res = right;
      temp = merge(left, right.getNext());
      temp.setPrev(res);
      res.setNext(temp);
    }
    return res;
  }

  public Node sort(Node h)
  {
    if (h == null || h.getNext() == null)
      return h;

    Node mid = getMid(h);
    Node right = mid.getNext();
    right.setPrev(null);
    mid.setNext(null);
    right = sort(right);
    Node left = sort(h);
    Node res = merge(left, right);
    return res;
  }

  public void sort()
  {
    this.root = sort(this.root);
  }

  public String toString()
  {
    StringBuilder out = new StringBuilder("[");
    Node cur = this.root;
    while (cur != null)
    {
      out.append(cur.getData());
      if (cur.getNext() != null)
        out.append(", ");
      cur = cur.getNext();
    }
    out.append("]");
    return out.toString();
  }

  public String toReverse()
  {
    StringBuilder out = new StringBuilder("[");
    Node cur = this.root;
    while (cur.getNext() != null)
      cur = cur.getNext();

    while (cur != null)
    {
      out.append(cur.getData());
      if (cur.getPrev() != null)
        out.append(", ");
      cur = cur.getPrev();
    }
    out.append("]");
    return out.toString();
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    MergeSortDLL obj = new MergeSortDLL(arr);
    System.out.println("Given Array:  " + obj);
    obj.sort();
    System.out.println("Sorted Array: " + obj);
    System.out.println("Reverse Sorted Array: " + obj.toReverse());

  }
}
