// Detect Cycle in an Undirected Graph

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Like directed graphs, we can use DFS to detect cycle in an undirected graph
// For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is
// already visited and u is not parent of v, then there is a cycle in graph.

// If we don’t find such an adjacent for any vertex, then there is no cycle.

// Time Complexity: O(V+E)
import java.util.LinkedList;
import java.util.ArrayList;
import pkg.graphs.Graph;

public class CycleUndirected
{

  Graph g;

  CycleUndirected()
  {
    g = new Graph(5);
    constructGraph1();
  }

  public void constructGraph1()
  {
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 0);
    g.addEdge(0, 3);
    g.addEdge(3, 4);
  }

  CycleUndirected(int x)
  {
    g = new Graph(3);
    constructGraph2();
  }

  public void constructGraph2()
  {
    g.addEdge(0, 1);
    g.addEdge(1, 2);
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

  public boolean isCyclic()
  {
    boolean[] visited = new boolean[g.getVertices()];
    for (int i =0; i<g.getVertices(); i++)
    {
      if (!visited[i])
        if (isCyclic(i, visited, -1))
          return true;
    }
    return false;
  }

  public static void main(String[] args)
  {
    CycleUndirected obj1 =new CycleUndirected();
    System.out.println("Given Graph 1 -> " + obj1.g);
    System.out.println("Cyclic: " + obj1.isCyclic());
    System.out.println();
    CycleUndirected obj2 =new CycleUndirected(1);
    System.out.println("Given Graph 1 -> " + obj2.g);
    System.out.println("Cyclic: " + obj2.isCyclic());

  }
}
