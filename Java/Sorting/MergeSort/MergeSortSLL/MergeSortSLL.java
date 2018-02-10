// Implement Merge Sort algorithm on a Singly Linked List.
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
  Node next;

  public Node(int d)
  {
    this.data = d;
    this.next = null;
  }

  public int getData()
  {
    return this.data;
  }

  public Node getNext()
  {
    return this.next;
  }

  public void setData(int x)
  {
    this.data = x;
  }

  public void setNext(Node n)
  {
    this.next = n;
  }

  public String toString()
  {
    return this.data + " " + this.next;
  }

  public Node(Node x)
  {
    this.data = x.data;
    this.next = x.next;
  }
}

public class MergeSortSLL
{
  Node root;

  public void insert(int x)
  {
    Node temp = new Node(x);
    Node cur = root;
    if (cur != null)
    {
      while (cur.getNext() != null)
        cur = cur.getNext();
      cur.setNext(temp);
    }
    else
      root = temp;
  }

  public void insert(int d, Node x)
  {
    Node temp = new Node(d);
    Node cur = x;
    if (cur != null)
    {
      while (cur.getNext() != null)
        cur = cur.getNext();
      cur.setNext(temp);
    }
    else
      x = temp;
  }

  public MergeSortSLL(int[] arr)
  {
    if (arr.length <= 0)
    {
      System.out.println("Invalid Arguments Specified");
      root = null;
      return;
    }
    for (int i = 0; i<arr.length; i++)
      insert(arr[i]);
  }

  public Node getMid(Node start)
  {
    Node slow = start;
    Node fast = start.getNext();
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
    if (left.getData() < right.getData())
    {
      res = left;
      res.setNext(merge(left.getNext(), right));
    }
    else
    {
      res = right;
      res.setNext(merge(left, right.getNext()));
    }
    return res;
  }

  public Node sort(Node h)
  {
    if (h == null || h.next == null)
        return h;

    Node middle = getMid(h);
    Node right = sort(middle.getNext());

    middle.setNext(null);
    Node left = sort(h);

    Node res = merge(left, right);
    return res;
  }

  public String toString()
  {
    StringBuilder out = new StringBuilder("[");
    for (Node cur = this.root; cur != null; cur = cur.getNext())
    {
      out.append(cur.getData());
      if (cur.getNext() != null)
        out.append(", ");
    }
    out.append("]");
    return out.toString();
  }

  public void sort()
  {
    this.root = sort(this.root);
  }

  public static void main(String[] args)
  {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    MergeSortSLL obj = new MergeSortSLL(arr);
    System.out.println("Given Array:  " + obj);
    obj.sort();
    System.out.println("Sorted array: " + obj);

  }
}
