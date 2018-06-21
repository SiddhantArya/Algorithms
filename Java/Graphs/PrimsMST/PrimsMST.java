// Prim's Minimum Spanning Tree
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
// 1. Add neighbors of the source vertices to a PriorityQueue (PQ).
// 2. Pick the smallest edge in the PW (this will have min edge weight).
//    If the destination is visited, discard it.
//    Else add the edge to MST and add its neighbors to the PQ
// 3. Repeat step#2 until there are elements in the queue.

// The algorithm is a Greedy Algorithm with the Greedy Choice being picking the
// smallest weight edge that does not cause a cycle in the MST so far.

// Time Complexity: O(ElogV).
//                  Inner loop runs O(E+V) times
//                  Each inner loop involves a remove() operation (O(logV))
//                  So overall complexity is O(ELogE + ELogV) time.
//                  ~ O(ElogV)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import pkg.graphs.Graph;
import pkg.graphs.Edge;

public class PrimsMST
{
  Graph g;

  public PrimsMST()
  {
    g= new Graph(9, false, true);
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(0, 1,  4);
    g.addEdge(0, 7,  8);
    g.addEdge(1, 2,  8);
    g.addEdge(1, 7, 11);
    g.addEdge(2, 3,  7);
    g.addEdge(2, 8,  2);
    g.addEdge(2, 5,  4);
    g.addEdge(3, 4,  9);
    g.addEdge(3, 5, 14);
    g.addEdge(4, 5, 10);
    g.addEdge(5, 6,  2);
    g.addEdge(6, 7,  1);
    g.addEdge(6, 8,  6);
    g.addEdge(7, 8,  7);
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

  public ArrayList<Edge> spanningTree()
  {
    int V = g.getVertices();
    boolean[] visited  = new boolean[V];
    ArrayList<Edge> result = new ArrayList<Edge>();

    EdgeComparator eComparator = new EdgeComparator();
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>(V, eComparator);

    visited[0] = true;
    addNeighbors(pq, 0);

    ArrayList<Edge> eList = g.getEdgeList();

    while (!pq.isEmpty())
    {
      Edge e = pq.poll();

      if (visited[e.getDestination()])
        continue;

      visited[e.getDestination()] = true;
      result.add(e);
      addNeighbors(pq, e.getDestination());
    }
    return result;
  }

  public void addNeighbors(PriorityQueue<Edge> pq, int src)
  {
    LinkedList<Integer> children = g.getAdjList().get(src);
    HashMap<Integer, Integer> wtList = g.getWtList().get(src);
    for (Integer child : children)
    {
      pq.add(new Edge(src, child, wtList.get(child)));
    }
  }

  public static void main(String[] args)
  {
    PrimsMST obj = new PrimsMST();
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Spanning Tree: "
                        + Arrays.toString(obj.spanningTree().toArray()));
  }
}
