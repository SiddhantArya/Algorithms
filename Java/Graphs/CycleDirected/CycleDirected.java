// Detect a Cycle in a Directed Graph

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// There is a cycle in a graph only if there is a back edge present in the graph.
// A back edge is an edge that is from a node to itself(selfloop) or one of its
// ancestor in the tree produced by DFS.

// To detect a back edge, we can keep track of vertices currently in recursion
// stack of function for DFS traversal.
// If we reach a vertex that is already in the recursion stack,
// then there is a cycle in the tree.

// Time Complexity: O (V + E)
// Space Complexity: O (V + E)

import java.util.ArrayList;
import java.util.LinkedList;
import pkg.graphs.Graph;

public class CycleDirected
{
  Graph g;

  // Testing for a graph with a cycle
  CycleDirected()
  {
    g = new Graph(4, true);
    constructGraph1();
  }

  // Testing for a graph without a cycle
  CycleDirected(int x)
  {
    g = new Graph(3, true);
    constructGraph2();
  }

  // Recursive utility method for isCyclic
  // i denotes the node from which cycle detection is to be done
  public boolean isCyclic(int i, ArrayList<Integer> recList)
  {
    if (recList.contains(i))
      return true;

    recList.add(i);

    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (isCyclic(child, recList))
        return true;
    }
    recList.remove((Integer) i);
    return false;
  }

  // Driver method for isCyclic
  public boolean isCyclic()
  {
    ArrayList<Integer> recList = new ArrayList<Integer>();
    // Do a DFS traversal beginning with all the vertices.
    for (int i=0; i<g.getVertices(); i++)
    {
      if (isCyclic(i, recList))
        return true;
    }
    return false;
  }

  public void constructGraph1()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);
  }

  public void constructGraph2()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
  }

  // Driver method
  public static void main(String[] args)
  {
    CycleDirected obj1 = new CycleDirected();
    System.out.println("Given Graph 1 -> " + obj1.g);
    System.out.println("Cyclic: " + obj1.isCyclic());

    System.out.println();

    CycleDirected obj2 = new CycleDirected(1);
    System.out.println("Given Graph 2 -> " + obj2.g);
    System.out.println("Cyclic: " + obj2.isCyclic());
  }
}
