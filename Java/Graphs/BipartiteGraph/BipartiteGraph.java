// Bipartite Graph

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// A Bipartite Graph is a graph whose vertices can be divided into two
// independent sets, U and V such that every edge (u, v) either connects a
// vertex from U to V or a vertex from V to U.

// In other words, for every edge (u, v), either u belongs to U and v to V,
// or u belongs to V and v to U.

// We can also say that there is no edge that connects vertices of same set.

// It can be noted that a Bipartite Graph is a 2-color graph (m-Coloring) i.e.
// such that vertices in a set are colored with the same color, then opposite
// ends of the edges should be present in the other set (different color)

// BFS Algorithm for the same :
// 1. Assign RED color to the source vertex (putting into set U).
// 2. Color all the neighbors with BLUE color (putting into set V).
// 3. Color all neighbor’s neighbor with RED color (putting into set U).
// 4. This way, assign color to all vertices such that it satisfies all the
//    constraints of m way coloring problem where m = 2.
// 5. While assigning colors, if we find a neighbor which is colored with same
//    color as current vertex, then the graph cannot be colored with 2 vertices
//    (or graph is not Bipartite)

// Time Complexity: O(V+E) as graph is represented using adjascency lists.
//                  Same as DFS or BFS

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import pkg.graphs.Graph;

public class BipartiteGraph
{
  Graph g;
  BipartiteGraph()
  {
    g = new Graph(4);
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
  }

  enum Color
  {
    RED, BLACK;
  }

  public Color flipColor(Color parent)
  {
    if (parent == Color.RED)
      return Color.BLACK;
    return Color.RED;
  }

  public boolean isBipartiteBFS(int i, Color[] result)
  {
    // Same as Color.RED
    result[i] = Color.values()[0];

    LinkedList<Integer> fifoq = new LinkedList<>();
    fifoq.add(i);

    while (!fifoq.isEmpty())
    {
      int u = fifoq.remove();

      LinkedList<Integer> children = g.getAdjList().get(u);
      for (Integer child : children)
      {
        if (result[child] == null)
        {
          result[child] = flipColor(result[u]);
          fifoq.add(child);
        }
        else if (result[child] == result[u])
          return false;
      }
    }
    return true;
  }

  public boolean isBipartiteBFS()
  {
    Color[] result = new Color[g.getVertices()];
    // Doing this to check for disconnected graphs
    // if it is known tha the graph is connected,
    // then we need to only run the utility function once (i.e. for source).
    for (int i=0; i<g.getVertices(); i++)
    {
      if (result[i] == null)
      if (isBipartiteBFS(i, result) == false)
      return true;
    }
    return true;
  }

  public boolean isBipartiteDFS(int i, Color[] result)
  {
    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (result[child] == null)
      {
        result[child] = flipColor(result[i]);
        if (isBipartiteDFS(child, result) == false)
          return false;
      }
      else if (result[child] == result[i])
        return false;
    }
    return true;
  }

  public boolean isBipartiteDFS()
  {
    Color[] result = new Color[g.getVertices()];
    result[0] = Color.RED;
    // Doing this to check for disconnected graphs
    // if it is known tha the graph is connected,
    // then we need to only run the utility function once (i.e. for source).
    for (int i=0; i<g.getVertices(); i++)
    {
      if (result[i] == null)
        if (isBipartiteDFS(i, result) == false)
          return true;
    }
    return true;
  }

  // Driver Method
  public static void main(String[] args)
  {
    BipartiteGraph obj = new BipartiteGraph();
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Is Bipartite (BFS): " + obj.isBipartiteBFS());
    System.out.println("Is Bipartite (DFS): " + obj.isBipartiteDFS());
  }
}
