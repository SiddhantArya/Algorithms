// Cycle in a Directed Graph using Colors (as illustrated in CLRS book)

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// DFS can be used to detect a cycle in a directed graph, if a back edge is
// detected while traversal, there exists a cycle.

// While doing traversal, assign one of the below three colors to every vertex.
// WHITE : Vertex is not processed yet.  Initially
//         all vertices are WHITE.
//
// GRAY : Vertex is being processed (DFS for this
//        vertex has started, but not finished which means
//        that all descendants (ind DFS tree) of this vertex
//        are not processed yet (or this vertex is in function
//        call stack)
//
// BLACK : Vertex and all its descendants are
//         processed.
//
// While doing DFS, if we encounter an edge from current
// vertex to a GRAY vertex, then this edge is back edge
// and hence there is a cycle.

// Time Complexity: O(V+E) as it uses DFS
//                         and Graph is represented using Adjascency List
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import pkg.graphs.Graph;

public class CycleDirectedColor
{
  Graph g;

  enum Color {WHITE, GRAY, BLACK};

  CycleDirectedColor()
  {
    g = new Graph(4, true);
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);
  }

  public boolean isCyclic(int i, Color[] status)
  {
    status[i] = Color.GRAY;

    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (status[child].equals(Color.GRAY))
        return true;
      if (status[child].equals(Color.WHITE))
        if (isCyclic(child, status))
          return true;
    }
    status[i] = Color.BLACK;
    return false;
  }

  public boolean isCyclic()
  {
    Color[] status = new Color[g.getVertices()];
    Arrays.fill(status, Color.WHITE);

    for (int i=0; i<g.getVertices(); i++)
    {
      if (status[i] == Color.WHITE)
        if (isCyclic(i, status))
          return true;
    }
    return false;
  }

  public static void main(String[] args)
  {
    CycleDirectedColor obj = new CycleDirectedColor();
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Contains Cycle: " + obj.isCyclic());
  }
}
