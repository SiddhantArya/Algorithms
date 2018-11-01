// A generic implementation of a Binary Tree
// A tree whose elements have at most 2 children is called a binary tree.
// Since each element in a binary tree can have only 2 children, we typically
// name them the left and right child.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Trees are heirarchical data structures
// Trees (with some ordering e.g., BST) provide moderate access/search
//  (quicker than Linked List and slower than arrays).
// Trees provide moderate insertion/deletion
//  (quicker than Arrays and slower than Unordered Linked Lists).
// Like Linked Lists and unlike Arrays, Trees don’t have an upper limit on
//  number of nodes as nodes are linked using pointers.


import pkg.trees.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

// Generic Binary Tree Class
public class BinaryTree<T extends Comparable<T>>
{
  private TreeNode<T> root;

  public BinaryTree(T x)
  {
    super();
    root = new TreeNode<T>(x);
  }

  public TreeNode<T> getRoot()
  {
    return this.root;
  }

  public void setRoot(TreeNode<T> r)
  {
    this.root = r;
  }

  // Insert an element into the tree.
  // Because it is not an ordered tree we insert into the first position we find
  // We keep traversing the tree until we find a node whose either left or right
  // is empty.
  // Time Complexity: O(n) as we have to traverse all nodes in the tree.
  public void add(T elem)
  {
    TreeNode<T> temp = new TreeNode<T>(elem);
    Queue<TreeNode<T>> levelOrderQ = new LinkedList<TreeNode<T>>();
    TreeNode<T> parent = null;
    if (root == null)
      root = temp;
    else
      levelOrderQ.add(root);

    while(!levelOrderQ.isEmpty())
    {
      TreeNode<T> cur = levelOrderQ.remove();
      // Check if there exists a left child
      // If yes, then add it to the level order queue
      //   Else, make the new element as the left child
      if(cur.getLeft() == null)
      {
        cur.setLeft(temp);
        parent = cur;
        break;
      }
      else
        levelOrderQ.add(cur.getLeft());

      // Check if there exists a right child
      // If yes, then add it to the level order queue
      //   Else, make the new element as the right child
      if (cur.getRight() == null)
      {
        cur.setRight(temp);
        parent = cur;
        break;
      }
      else
        levelOrderQ.add(cur.getRight());
    }
    temp.setParent(parent);
  }

  // Right Most Leaf Node
  public TreeNode<T> getDeepest(TreeNode<T> head)
  {
    if (head == null)
      return null;

    Queue<TreeNode<T>> levelOrderQ = new LinkedList<TreeNode<T>>();
    levelOrderQ.add(head);
    TreeNode<T> temp = null;
    while(!levelOrderQ.isEmpty())
    {
      temp = levelOrderQ.remove();
      if (temp.getRight() != null)
        levelOrderQ.add(temp.getRight());
      else if (temp.getLeft() != null)
        levelOrderQ.add(temp.getLeft());
      else
        break;
    }
    return temp;
  }

  // Method to check if a node is the Root Node
  // Time Complexity: O(1)
  public boolean isRoot(T key)
  {
    TreeNode<T> node = find(key);
    if (node == null)
      return false;
    if (node.getParent() == null && node == root)
      return true;
    return false;
  }

  // Method to check if a node is the Leaf Node
  // Time Complexity: O(1)
  public boolean isLeaf(T key)
  {
    TreeNode<T> node = find(key);
    if (node == null)
      return false;

    if (node.getLeft() == null && node.getRight() == null)
      return true;

    return false;
  }

  // Method to find a key in the Tree rooted at root
  public TreeNode<T> find(T key)
  {
    return find(key, root);
  }

  // Method to find a key in the Tree rooted at subTree
  // Time Complexity: O(n) as we have to traverse entire tree in the worst case
  public TreeNode<T> find(T key, TreeNode<T> subTree)
  {
    if (subTree==null)
      return null;

    Queue<TreeNode<T>> levelOrderQ = new LinkedList<TreeNode<T>>();
    levelOrderQ.add(subTree);

    TreeNode<T> cur = null;
    while (!levelOrderQ.isEmpty())
    {
      cur = levelOrderQ.remove();
      if (cur.getKey().equals(key))
        return cur;
      if (cur.getLeft() != null)
        levelOrderQ.add(cur.getLeft());
      if (cur.getRight() != null)
        levelOrderQ.add(cur.getRight());
    }
    return null;
  }

  // Method to delete a key from the tree
  // As a binary tree is not ordered, we need to find the deepest element and
  // replace it with the item to be deleted.
  // Time Complexity: O(n) as we have to traverse entire tree in the worst case
  public boolean delete(T key)
  {
    if (root == null)
      return false;

    TreeNode<T> toReplace = getDeepest(root);

    // Deepest not found
    if (toReplace==null)
      return false;

    TreeNode<T> toDelete = find(key);

    // Key not found
    if (toDelete == null)
      return false;

    // If key = root.getKey(), then simply mark root as null
    if (toDelete.equals(root))
    {
      root = null;
      return true;
    }

    TreeNode<T> dParent = toDelete.getParent();
    // Calculates whether the node to be deleted is the left or the right child
    int pos = dParent.getLeft() == toReplace ? -1 : 1;

    // If Deepest element is the same as the one to be deleted
    if (toDelete.equals(toReplace))
    {
      if (pos < 0)
        dParent.setLeft(null);
      else
        dParent.setRight(null);
      return true;
    }

    // Updating reference for the node to be deleted with the one to be replaced
    toDelete.setKey(toReplace.getKey());
    // Left child of the deleted node can be the deepest node or any other node
    toReplace.setLeft(toDelete.getLeft() == toReplace ? null : toDelete.getLeft()) ;
    // Right child of the deleted node can be the deepest node or any other node
    toReplace.setRight(toDelete.getRight() == toReplace ? null : toDelete.getRight());
    if (pos < 0)
      dParent.setLeft(toReplace);
    else
      dParent.setRight(toReplace);

    // Updating reference information for the replaced node
    pos = (toReplace.getParent().getLeft() == toReplace ? -1 : 1);
    if (pos < 0)
      toReplace.getParent().setLeft(null);
    else
      toReplace.getParent().setRight(null);
    toReplace=null;
    return true;
  }

  // Level Order Traversal
  // Time Complexity: O(n)
  public String toString()
  {
    Queue<TreeNode<T>> levelOrderQ = new LinkedList<TreeNode<T>>();
    if (root == null)
      return "";
    StringBuilder result = new StringBuilder("Level Order Traversal: \n");
    levelOrderQ.add(root);
    int cur_level = levelOrderQ.size();
    int sub_level = 0;
    while (!levelOrderQ.isEmpty())
    {
      TreeNode<T> cur = levelOrderQ.remove();
      cur_level--;
      result.append(cur.getKey() + " ");

      if (cur.getLeft() != null)
      {
        levelOrderQ.add(cur.getLeft());
        sub_level++;
      }
      if (cur.getRight() != null)
      {
        levelOrderQ.add(cur.getRight());
        sub_level++;
      }

      if (cur_level==0)
      {
        result.append("\n");
        cur_level = sub_level;
        sub_level = 0;
      }
    }
    return result.toString();
  }

  // Recursive Inorder Traversal driver method
  public String inorder_rec()
  {
    if(root == null)
    {
      return "";
    }
    StringBuilder out = new StringBuilder("");
    inorder_rec(root, out);
    return out.toString();
  }

  // Recursive Inorder Traversal utility method
  public void inorder_rec(TreeNode<T> node, StringBuilder out)
  {
    if (node != null)
    {
      inorder_rec(node.getLeft(), out);
      out.append(node.getKey() + " ");
      inorder_rec(node.getRight(), out);
    }
  }

  // Iterative Inorder Traversal method
  public String inorder_iter()
  {
    LinkedList<TreeNode<T>> inorderStack = new LinkedList<TreeNode<T>>();
    TreeNode<T> cur = root;
    StringBuilder out = new StringBuilder();
    while (cur != null || !inorderStack.isEmpty())
    {
      while (cur != null)
      {
        inorderStack.push(cur);
        cur = cur.getLeft();
      }
      cur = inorderStack.pop();
      out.append(cur.getKey() + " ");
      cur = cur.getRight();
    }
    return out.toString();
  }

  // Recursive Preorder Traversal driver method
  public String preorder_rec()
  {
    if(root == null)
    {
      return "";
    }
    StringBuilder out = new StringBuilder("");
    preorder_rec(root, out);
    return out.toString();
  }

  // Recursive Preorder Traversal utility method
  public void preorder_rec(TreeNode<T> node, StringBuilder out)
  {
    if (node != null)
    {
      out.append(node.getKey() + " ");
      preorder_rec(node.getLeft(), out);
      preorder_rec(node.getRight(), out);
    }
  }

  // Iterative Preorder Traversal method
  public String preorder_iter()
  {
    LinkedList<TreeNode<T>> preorderStack = new LinkedList<TreeNode<T>>();
    TreeNode<T> cur = root;
    StringBuilder out = new StringBuilder();
    while (cur != null || !preorderStack.isEmpty())
    {
      while (cur != null)
      {
        out.append(cur.getKey() + " ");
        preorderStack.push(cur);
        cur = cur.getLeft();
      }
      cur = preorderStack.pop();
      cur = cur.getRight();
    }
    return out.toString();
  }

  // Recursive Postorder Traversal driver method
  public String postorder_rec()
  {
    if(root == null)
    {
      return "";
    }
    StringBuilder out = new StringBuilder("");
    postorder_rec(root, out);
    return out.toString();
  }

  // Recursive Postorder Traversal utility method
  public void postorder_rec(TreeNode<T> node, StringBuilder out)
  {
    if (node != null)
    {
      postorder_rec(node.getLeft(), out);
      postorder_rec(node.getRight(), out);
      out.append(node.getKey() + " ");
    }
  }

  // Iterative Postorder Traversal utility method
  public String postorder_iter()
  {
    LinkedList<TreeNode<T>> postorderStack = new LinkedList<TreeNode<T>>();
    TreeNode<T> cur = root;
    StringBuilder out = new StringBuilder();
    while (cur != null || !postorderStack.isEmpty())
    {
      while (cur != null)
      {
        if (cur.getRight() != null)
          postorderStack.push(cur.getRight());
        postorderStack.push(cur);
        cur = cur.getLeft();
      }
      cur = postorderStack.pop();
      if (cur.getRight()!=null && cur.getRight() == postorderStack.peek())
      {
        TreeNode<T> next = postorderStack.pop();
        postorderStack.push(cur);
        cur = next;
      }
      else
      {
        out.append(cur.getKey() + " ");
        cur = null;
      }
    }
    return out.toString();
  }

  // The height of a tree would be the height of its root node,
  // or equivalently, the depth of its deepest node.
  // An empty tree will have a height of -1.
  // Time Complexity: O(n)
  public int heightTree()
  {
    return heightNode(root);
  }

  // The height of a node is the number of edges on the longest path from the
  // node to a leaf.
  // A leaf node will have a height of 0.
  // Time Complexity: O(n)
  public int heightNode(TreeNode<T> node)
  {
    if (node != null)
    {
      int left  = heightNode(node.getLeft());
      int right = heightNode(node.getRight());
      return (Math.max(left, right) + 1);
    }
    // This is the case when when node is empty
    return -1;
  }

  // The depth of a node is the number of edges from the node to the root node.
  // A root node will have a depth of 0.
  // Time Complexity: O(n)
  public int depthNode(TreeNode<T> node)
  {
    if (node == null || root == null)
      return 0;
    if (node == root)
      return 0;
    LinkedList<SimpleEntry<TreeNode<T>, Integer>> levelOrderQ =
                      new LinkedList<SimpleEntry<TreeNode<T>, Integer>>();
    levelOrderQ.add(new SimpleEntry<TreeNode<T>, Integer>(root, 0));
    while (!levelOrderQ.isEmpty())
    {
      SimpleEntry<TreeNode<T>, Integer> entry = levelOrderQ.remove();
      TreeNode<T> cur = entry.getKey();
      int depth = entry.getValue();
      if (cur.getKey().equals(node.getKey()))
        return depth;
      if (cur.getLeft() != null)
        levelOrderQ.add(new SimpleEntry<TreeNode<T>, Integer>(cur.getLeft(), depth+1));
      if (cur.getRight() != null)
        levelOrderQ.add(new SimpleEntry<TreeNode<T>, Integer>(cur.getRight(), depth+1));
    }
    // If we reach here, it means the node doesn't exist in the tree
    return -1;
  }

  // The diameter (or width) of a tree is the number of edges on the longest path
  // between any two leaf nodes. This path may or may not include the root node
  // Here longest path means edges
  // The diameter of a tree T is the largest of the following quantities:
  // the diameter of T’s left subtree
  // the diameter of T’s right subtree
  // the longest path between leaves that goes through the root of T
  // (this can be computed from the heights of the subtrees of T)
  public int diameter(TreeNode<T> node)
  {
    if (node!=null)
    {
      int hLeft  = heightNode(node.getLeft());
      int hRight = heightNode(node.getRight());

      int dLeft  = diameter(node.getLeft());
      int dRight = diameter(node.getRight());
      // On including the root node, 2 more edges are added
      return Math.max((hLeft + hRight + 2), Math.max(dLeft, dRight));
    }
    return 0;
  }

  public int diameter()
  {
    return diameter(root);
  }

  // Prints nodes at K-Distance in the same subtree as node
  // Time Complexity: O(n)
  public void printKdNode(TreeNode<T> node, int k)
  {
    if (node == null || k<0)
      return;
    if (k==0)
    {
      System.out.println(node.getKey());
    }
    // k!=0, recur for left and right subtrees
    printKdNode(node.getLeft(), k-1);
    printKdNode(node.getRight(), k-1);
  }

  // Prints nodes at K distance in different subtree
  // Returns distance of target from root.
  // Time Complexity: O(n)
  public int printKdNode(TreeNode<T> node, TreeNode<T> target, int k)
  {
    if (node == null || k<0 || target == null)
      return -1;
    if (target == node)
    {
      printKdNode(node, k);
      return 0;
    }

    // Distance of root's left child from target
    // If target is in the left subtree, dLeft will not be -1
    int dLeft = printKdNode(node.getLeft(), target, k);
    if (dLeft != -1)
    {
      // If the distance of target node is at k-1 distance from root
      if (k-dLeft-1 == 0)
        System.out.println(node.getKey());
      // If not, then we should find the node in the right subtree
      else
        printKdNode(node.getRight(), k-dLeft-2);
      return dLeft+1;
    }

    // Distance of root's right child from target
    // If target is in the right subtree, dRight will not be -1
    int dRight = printKdNode(node.getRight(), target, k);
    if (dRight != -1)
    {
      // If the distance of target node is at k-1 distance from root
      if (k-dRight-1 == 0)
        System.out.println(node.getKey());
        // If not, then we should find the node in the left subtree
      else
        printKdNode(node.getLeft(), k-dRight-2);
      return dRight+1;
    }
    // If dLeft == -1 && dRight == -1 i.e. target not found in the tree
    return -1;
  }

  // Prints ancestors of a given key in the tree
  // Time Complexity: O(n)
  public void printAncestors(TreeNode<T> node, T key)
  {
    if (node == null)
      return;
    if (node.getKey().equals(key))
    {
      System.out.println();
      return;
    }
    TreeNode<T> target = find(key, node);
    if (target != null)
    {
      System.out.print(node.getKey() + " ");
      if (find(key, node.getLeft())!=null)
        printAncestors(node.getLeft(), key);
      else
        printAncestors(node.getRight(), key);
    }
  }


  // Driver Method
  public static void main(String[] args)
  {
    BinaryTree<Integer> obj = new BinaryTree<Integer>(3);
    obj.add(new Integer(10));
    obj.add(new Integer(11));
    obj.add(new Integer( 7));
    obj.add(new Integer( 9));
    obj.add(new Integer(15));
    obj.add(new Integer( 8));
    System.out.println(obj);
    System.out.println("Deepest: " + obj.getDeepest(obj.getRoot()));
    System.out.println("Finding   9 in Tree: " + obj.find(9));
    System.out.println("Finding  -1 in Tree: " + obj.find(-1));
    System.out.println("Deleting 11 in Tree: " + obj.delete(11));
    System.out.println("Updated Tree " + obj);
    System.out.println("Is  3 Root: " + obj.isRoot(3));
    System.out.println("Is 10 Root: " + obj.isRoot(10));
    System.out.println("Is  7 Leaf: " + obj.isLeaf(7));
    System.out.println("Is 10 Leaf: " + obj.isLeaf(10));
    System.out.println("Height of Tree: " + obj.heightTree());
    System.out.println("Diameter of Tree: " + obj.diameter());
    System.out.println("\nHeight of Root: " + obj.heightNode(obj.getRoot()));
    System.out.println("Height of Internal Node (8): " +
                          obj.heightNode(obj.getRoot().getRight()));
    System.out.println("Height of Leaf (7): " +
                          obj.heightNode(obj.getRoot().getLeft().getLeft()));
    System.out.println("Depth of Root: " + obj.depthNode(obj.getRoot()));
    System.out.println("Depth of Internal Node (8): " +
                          obj.depthNode(obj.getRoot().getRight()));
    System.out.println("Depth of Leaf (7): " +
                          obj.depthNode(obj.getRoot().getLeft().getLeft()));
    System.out.println("Nodes at K(1)-Distance from node with key " +
                          obj.getRoot().getLeft().getKey() +
                          ": ");
    obj.printKdNode(obj.getRoot(), obj.getRoot().getLeft(),1);
    System.out.println("Nodes at K(2)-Distance from root: ");
    obj.printKdNode(obj.getRoot(), obj.getRoot(), 2);
    System.out.println("Ancestors of Node with Key 9:");
    obj.printAncestors(obj.getRoot(), 9);
    System.out.println("Ancestors of Node with Key 11:");
    obj.printAncestors(obj.getRoot(), 11);
    System.out.println("\nTraversals:");
    System.out.println("Inorder  (Rec): " + obj.inorder_rec());
    System.out.println("Inorder (Iter): " + obj.inorder_iter());
    System.out.println("Preorder  (Rec): " + obj.preorder_rec());
    System.out.println("Preorder (Iter): " + obj.preorder_iter());
    System.out.println("Postorder  (Rec): " + obj.postorder_rec());
    System.out.println("Postorder (Iter): " + obj.postorder_iter());
  }
}
