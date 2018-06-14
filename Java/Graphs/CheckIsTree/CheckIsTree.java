// Check if a given graph is a tree or not

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// An undirected graph is tree if it has following properties.
// a) There is no cycle.
// b) The graph is connected.

// Time Complexity: O(V+E) because of DFS, graph represented as Adjascency List
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import pkg.graphs.Graph;

public class CheckIsTree
{
  Graph g;

  CheckIsTree()
  {
    g = new Graph(5);
    constructGraph1();
  }

  CheckIsTree(int x)
  {
    g = new Graph(5);
    constructGraph2();
  }

  public void constructGraph1()
  {
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(0, 3);
    g.addEdge(3, 4);
  }

  public void constructGraph2()
  {
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 1);
    g.addEdge(0, 3);
    g.addEdge(3, 4);
  }

  public boolean isCyclic(int v, boolean[] visited, int parent)
  {
    visited[v] = true;

    LinkedList<Integer> children = g.getAdjList().get(v);
    for (Integer child : children)
    {
      if (visited[child])
      {
        if (child != parent)
          return true;
      }
      else
      {
        if (isCyclic(child, visited, v))
          return true;
      }
    }
    return false;

  }

  public boolean isTree()
  {
    boolean[] visited = new boolean[g.getVertices()];
    Arrays.fill(visited, false);

    // If there is a cycle, then the given graph is not a tree
    if (isCyclic(0, visited, -1))
      return false;

      // If a node is not visited/reachable i.e. dfs produces a forest,
      // then the given graph is not a tree
    for (int i=0; i<g.getVertices(); i++)
      if (visited[i] == false)
        return false;

    return true;
  }

  public static void main(String[] args)
  {
    CheckIsTree obj1 = new CheckIsTree();
    System.out.println("Given Graph: " + obj1.g);
    System.out.println("Is Given Graph a Tree: " + obj1.isTree() + "\n");

    CheckIsTree obj2 = new CheckIsTree(1);
    System.out.println("Given Graph: " + obj2.g);
    System.out.println("Is Given Graph a Tree: " + obj2.isTree());
  }
}
