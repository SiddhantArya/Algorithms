// Djikstras Algorithm for Single Source Shortest Path

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// We generate a SPT (shortest path tree) with given source as root.
// This algorithm can only be applied to Graphs which have No Negative Edges
// This constraint ensures than once a node is visited, it's optimal distance
// can't be improved.

// Dijkstra’s algorithm doesn’t work for graphs with negative weight edges.
// For graphs with negative weight edges, Bellman–Ford algorithm can be used,

// The idea is to traverse all vertices of graph using BFS and
// use a Min Heap to store the vertices not yet included in SPT

//  Min Heap is used as a priority queue to get the minimum distance vertex
// from set of not yet included vertices.

// The Greedy approach always selects the most promising node such that
// For every adjacent vertex v, if sum of distance value of u (from source)
// and weight of edge u-v, is less than the distance value of v,
// then update the distance value of v.

// Time Complexity:  O(E log V)
//                  The time complexity of the above code/algorithm looks O(V^2)
//                  as there are two nested while loops. If we take a closer
//                  look, we can observe that the statements in inner loop are
//                  executed O(V+E) times (similar to BFS). The inner loop has
//                  add() operation which takes O(LogV) time and outer loop has
//                  poll() operation which takes O(logV) time.
//                  So overall time complexity is O(E+V)*O(LogV) which is
//                  O((E+V)*LogV) = O(ELogV)
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import pkg.graphs.Graph;
import java.util.Collections;

public class DijkstrasSSSP
{
  Graph g;
  public static int INF = Integer.MAX_VALUE;

  class NodeTuple implements Comparable<NodeTuple>
  {
    int node, distance;

    NodeTuple(int n, int d)
    {
      node = n;
      distance = d;
    }

    @Override
    public int compareTo(NodeTuple x)
    {
      if (x.distance < this.distance)
        return 1;
      else if (x.distance > this.distance)
        return -1;
      return 0;
    }
  }

  public DijkstrasSSSP()
  {
    g = new Graph(9, false, true);
    constructGraph();
  }

  public int[] dijkstras(int src, int dest)
  {
    int V = g.getVertices();
    int[] distance = new int[V];
    int[] pred = new int[V];
    boolean[] visited = new boolean[V];

    Arrays.fill(distance, INF);
    Arrays.fill(pred, -1);

    distance[src] = 0;

    PriorityQueue<NodeTuple> pq = new PriorityQueue<NodeTuple>(V);

    pq.add(new NodeTuple(src, 0));

    while (!pq.isEmpty())
    {
      NodeTuple top = pq.poll();
      visited[top.node] = true;

      if (top.node == dest)
        break;

      // This operation is to ignore the decrease-Key operations
      // by ignoring the element whose min distance has already been found.
      if (distance[top.node] < top.distance)
        continue;

      LinkedList<Integer> children = g.getAdjList().get(top.node);
      HashMap<Integer, Integer> edgeWeight = g.getWtList().get(top.node);
      for (Integer child : children)
      {
        if (visited[child])
          continue;
        int nDist = distance[top.node] + edgeWeight.get(child);
        if (nDist < distance[child])
        {
          distance[child] = nDist;
          pred[child] = top.node;
          pq.add(new NodeTuple(child, distance[child]));
        }
      }
    }
    ArrayList<Integer> path = new ArrayList<Integer>();

    int last_i = dest;
    while (pred[last_i] != -1)
    {
      path.add(last_i);
      last_i = pred[last_i];
    }
    path.add(last_i);

    Collections.reverse(path);
    System.out.println("Path from " +  src + " to " + dest + " : "
                        + Arrays.toString(path.toArray()));
    return distance;
  }

  public int maxAt(int[] arr)
  {
    if (arr.length <= 0)
      return -1;
    int max_i=0;
    for(int i = 1; i<arr.length; i++)
    {
      if (arr[i] > arr[max_i])
        max_i = i;
    }
    return max_i;
  }

  public void constructGraph()
  {
    g.addEdge(0, 1, 4);
    g.addEdge(0, 7, 8);
    g.addEdge(1, 2, 8);
    g.addEdge(1, 7, 11);
    g.addEdge(2, 3, 7);
    g.addEdge(2, 5, 4);
    g.addEdge(2, 8, 2);
    g.addEdge(3, 4, 9);
    g.addEdge(3, 5, 14);
    g.addEdge(4, 5, 10);
    g.addEdge(5, 6, 2);
    g.addEdge(6, 7, 1);
    g.addEdge(6, 8, 6);
    g.addEdge(7, 8, 7);
  }

  public static void main(String[] args)
  {
    DijkstrasSSSP obj = new DijkstrasSSSP();
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Distance Array: " + Arrays.toString(obj.dijkstras(0, 4)));
  }
}
