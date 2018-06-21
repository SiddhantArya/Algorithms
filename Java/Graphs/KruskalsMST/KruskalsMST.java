// Kruskal's Minimum Spanning Tree
// Given connected graph G with positive edge weights,
// find a min weight set of edges that connects all of the vertices.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Given a CONNECTED and UNDIRECTED graph, a spanning tree of that graph is a
// subgraph that is a tree and connects all the vertices together.
// A single graph can have many different spanning trees.
// A minimum spanning tree (MST) or minimum weight spanning tree for a weighted,
// connected and undirected graph is a spanning tree with weight less than or
// equal to the weight of every other spanning tree.

// The weight of a spanning tree is the sum of weights of each edge in the tree.

// The algorithm is as follows:
// 1. Sort all the edges in non-decreasing order of their weight.
// 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree
//    formed so far. If cycle is not formed, include this edge.
//    Else, discard it.
// 3. Repeat step#2 until there are (V-1) edges in the spanning tree.

// We detect a cycle in Step 2 using the Union Find Algorithm.

// The algorithm is a Greedy Algorithm with the Greedy Choice being picking the
// smallest weight edge that does not cause a cycle in the MST so far.

// Time Complexity: O(ElogE) or O(ElogV).
//                  Sorting of edges takes O(ELogE) time.
//                  After sorting, we iterate through all edges and apply
//                  Union-Find algorithm. The find and union operations can take
//                  atmost O(LogV) time.
//                  So overall complexity is O(ELogE + ELogV) time.
//                  The value of E can be atmost O(V2), so O(LogV) = O(LogE).
//                  Therefore, overall time complexity is O(ElogE) or O(ElogV)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import pkg.graphs.Graph;
import pkg.graphs.Edge;

public class KruskalsMST
{
  Graph g;

  KruskalsMST()
  {
    g = new Graph(4, false, true);
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(0, 1, 10);
    g.addEdge(0, 2,  6);
    g.addEdge(0, 3,  5);
    g.addEdge(1, 3, 15);
    g.addEdge(2, 3,  4);
  }

  public int find (int[] arr, int x)
  {
    if (arr[x] == x)
      return x;
    return find (arr, arr[x]);
  }

  public void union(int[] arr, int s, int d)
  {
    int set1 = find(arr, s);
    int set2 = find(arr, d);

    arr[set1] = set2;
  }

  public ArrayList<Edge> spanningTree()
  {
    ArrayList<Edge> result = new ArrayList<Edge>();
    int[] edgeSets = new int[g.getVertices()];
    for (int i=0; i < edgeSets.length; i++)
      edgeSets[i] = i;

    ArrayList<Edge> eList = g.getEdgeList();
    EdgeComparator eComparator = new EdgeComparator();
    Collections.sort(eList, eComparator);

    for (Edge e : eList)
    {
      int srcSet  = find(edgeSets, e.getSource());
      int destSet = find(edgeSets, e.getDestination());

      if (srcSet != destSet)
      {
        // edgeSets[srcSet] = destSet;
        union(edgeSets, e.getSource(), e.getDestination());
        result.add(e);
      }
    }

    return result;
  }

  public class EdgeComparator implements Comparator<Edge>
  {
    @Override
    public int compare(Edge e1, Edge e2)
    {
      if (e1.getWeight() < e2.getWeight())
        return -1;
      else if (e1.getWeight() > e2.getWeight())
        return 1;
      return 0;
    }
  }

  public static void main(String[] args)
  {
    KruskalsMST obj = new KruskalsMST();
    System.out.println("Given Graph: " + obj.g);
    ArrayList<Edge> eList = obj.spanningTree();
    System.out.println("Spanning Tree: " + Arrays.toString(eList.toArray()));
  }
}
