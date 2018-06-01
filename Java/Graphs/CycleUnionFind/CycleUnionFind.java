// Detect Cycle in an Undirected Graph using Union-Find Algorithm.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Union-Find Algorithm can be used to check whether an undirected graph
// contains cycle or not.

// Find: Determine which subset a particular element is in. This can be used for
// determining if two elements are in the same subset.
// Union: Join two subsets into a single subset.

// Time Complexity: O(n)
//                : It can be improved to O(Logn) using Union by Rank or Height 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import pkg.graphs.Graph;
import pkg.graphs.Edge;

public class CycleUnionFind
{
  Graph g;

  CycleUnionFind()
  {
    g = new Graph(3);
    constructGraph();
  }

  void constructGraph()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
  }

  public int find (int v, int[] parent)
  {
    int i = v;
    while (parent[i] != -1)
      i = parent[i];
    if (parent[i] == -1)
      return i;
    return -1;
  }

  public void union (int src, int dest, int[] parent)
  {
    int x = find (src, parent);
    int y = find (dest, parent);
    parent[x] = y;
  }

  public boolean isCyclic()
  {
    int[] parent = new int[g.getVertices()];
    Arrays.fill(parent, -1);

    ArrayList<Edge> edgeList = g.getEdgeList();
    for (Edge e : edgeList)
    {
      int src = e.getSource();
      int dest = e.getDestination();
      int x = find (src, parent);
      int y = find (dest, parent);

      if (x != y)
        union(src, dest, parent);
      else
        return true;
    }
    return false;
  }

  public static void main(String[] args)
  {
    CycleUnionFind obj = new CycleUnionFind();
    System.out.println("Given Graph: " + obj.g);
    // System.out.println("Edge List: " +   obj.g.getEdgeList());
    if (obj.isCyclic())
      System.out.println("Cycle Found");
    else
      System.out.println("No Cycle found");
  }
}
