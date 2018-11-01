package pkg.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode<T extends Comparable<T>>
{
  T key;
  TreeNode<T> left, right, parent;

  public TreeNode()
  {
    key  = null;
    left = null;
    right = null;
    parent = null;
  }

  public TreeNode(T x)
  {
    key = x;
    left  = null;
    right = null;
    parent = null;
  }

  public TreeNode(T x, TreeNode<T> l, TreeNode<T> r, TreeNode<T> p)
  {
    key = x;
    left = l;
    right = r;
    parent = p;
  }

  public T getKey()
  {
    return this.key;
  }

  public TreeNode<T> getLeft()
  {
    return this.left;
  }

  public TreeNode<T> getRight()
  {
    return this.right;
  }

  public TreeNode<T> getParent()
  {
    return this.parent;
  }

  public void setKey(T x)
  {
    this.key = x;
  }

  public void setLeft(TreeNode<T> l)
  {
    this.left = l;
  }

  public void setRight(TreeNode<T> r)
  {
    this.right = r;
  }

  public void setParent(TreeNode<T> p)
  {
    this.parent = p;
  }

  public String toString()
  {
    StringBuilder result = new StringBuilder(
                  this.key +
                  "<" +
                  (this.parent==null ? "null": this.parent.getKey()) +
                  "> (" + this.left +
                  ", " + this.right +")");
    return result.toString();
  }

}
