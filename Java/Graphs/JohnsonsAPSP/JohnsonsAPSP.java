// Johnsons All Pair Shortest Path Algorithm

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Johnson’s algorithm is a way to find the shortest paths between all pairs of
// vertices in a sparse, edge weighted, directed graph.
// It allows some of the edge weights to be negative numbers, but no
// negative-weight cycles may exist.

// It works by using the Bellman–Ford algorithm to compute a transformation of
// the input graph that removes all negative weights,
// allowing Dijkstra’s algorithm to be used on the transformed graph.

// Algorithm:
// 1) Let the given graph be G. Add a new vertex s to the graph,
//    add edges from new vertex to all vertices of G.
//    Let the modified graph be G’.
//
// 2) Run Bellman-Ford algorithm on G’ with s as source.
// Let the distances calculated by Bellman-Ford be h[0], h[1], .. h[V-1].
// If we find a negative weight cycle, then return.
// Note that the negative weight cycle cannot be created by new vertex s as
// there is no edge to s. All edges are from s.
//
// 3) Reweight the edges of original graph. For each edge (u, v),
// assign the new weight as “original weight + h[u] – h[v]”.
//
// 4) Remove the added vertex s and run Dijkstra’s algorithm for every vertex.

// Time Complexity: O(V^2log V + VE)
//                  The main steps in algorithm are Bellman Ford Algorithm
//                  called once and Dijkstra called V times. Time complexity of
//                  Bellman Ford is O(VE)
//                  Dijkstra is O(VLogV).
//                  So overall time complexity is O(V2log V + VE).

// The time complexity of Johnson's algorithm becomes same as Floyd Warshell
// when the graphs is complete (For a complete graph E = O(V2). But for sparse
// graphs, the algorithm performs much better than Floyd Warshall.
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;
import pkg.graphs.Graph;
import pkg.graphs.Edge;

public class JohnsonsAPSP
{
  Graph g;

  public static final int INF = Integer.MAX_VALUE/2;

  JohnsonsAPSP(int x)
  {
    if (x==5)
    {
      g = new Graph(5, true, true);
      constructGraph1();
    }
    if (x==4)
    {
      g = new Graph(4, true, true);
      constructGraph2();
    }
  }

  public void constructGraph1()
  {
    g.addEdge(0, 1, -1);
    g.addEdge(0, 2,  4);
    g.addEdge(1, 2,  3);
    g.addEdge(1, 3,  2);
    g.addEdge(1, 4,  2);
    g.addEdge(3, 2,  5);
    g.addEdge(3, 1,  1);
    g.addEdge(4, 3, -3);
  }

  public void constructGraph2()
  {
    g.addEdge(0, 2,  3);
    g.addEdge(1, 0,  2);
    g.addEdge(2, 1,  7);
    g.addEdge(2, 3,  1);
    g.addEdge(3, 0,  6);
  }

  public boolean johnsonsAlgorithm(int[][] distance)
  {
    if (g == null)
    {
      System.out.println("Graph can't be empty");
      return false;
    }

    int V = g.getVertices();

    // Add a new vertex to the graph which will act like the source for the
    // augmented graph used for Bellman Ford Algorithm.
    Graph augmentedGraph = new Graph(V+1, true, true);
    // Add an edge from New Vertex to each vertex in the original graph
    for (int i=0; i<V; i++)
      augmentedGraph.addEdge(V, i, 1);
    // Add edges from original ggraph to augmented graph
    for (Edge e: g.getEdgeList())
      augmentedGraph.addEdge(e.getSource(), e.getDestination(), e.getWeight());

    System.out.println("Augmented Graph: " + augmentedGraph);
    int[] dist = new int[V+1];
    Arrays.fill(dist, INF);
    // Run Bellman Ford Algorithm with the new vertex as Source
    boolean noNegativeCycle = bellmanFord(augmentedGraph, V, dist);

    // If the augmented graph contains a negative cycle, then return
    // This is true as any Negative Cycles can't contain the newly added vertex
    // as there is no edge coming into the new Vertex.
    if (noNegativeCycle == false)
      return false;


    // Re-weigting of original graps is done based on the distances found by
    // Bellman Ford

    ArrayList<HashMap<Integer, Integer>> wtList = g.getWtList();
    // *or* Graph wtGraph = new Graph(V, g.getIsDirected(), g.getIsWeighted());
    for (Edge e : g.getEdgeList())
    {
      int src  = e.getSource();
      int dest = e.getDestination();
      int wt   = e.getWeight();
      wt = wt + dist[src] - dist[dest];
      // *or* wtGraph.addEdge(src, dest, wt);
      wtList.get(src).put(dest, wt);
    }
    // *or* g = wtGraph;

    // Arrays.fill(distance, INF);

    System.out.println("Re-weighted Graph: " + g);

    dijkstras(distance);

    return true;
  }

  public boolean bellmanFord(Graph g, int s, int[] dist)
  {
    Arrays.fill(dist, INF);
    dist[s] = 0;

    for (int i=0; i<g.getVertices(); i++)
    {
      for(Edge e : g.getEdgeList())
      {
        int src  = e.getSource();
        int dest = e.getDestination();
        int wt   = e.getWeight();

        if (dist[src] == INF)
          continue;
        if (dist[dest] > dist[src] + wt)
          dist[dest] = dist[src] + wt;
      }
    }
    // Bellman Ford Complete

    // Now we need to relax the edges one more time to make sure there are no
    // neggative edges
    boolean noNegativeCycle = true;
    for(Edge e : g.getEdgeList())
    {
      int src  = e.getSource();
      int dest = e.getDestination();
      int wt   = e.getWeight();

      if (dist[dest] > dist[src] + wt)
      {
        dist[dest] = INF;
        noNegativeCycle = false;
      }
    }
    return noNegativeCycle;
  }

  class NodeTuple implements Comparable<NodeTuple>
  {
    int node, distance;

    NodeTuple(int n, int d)
    {
      node = n;
      distance = d;
    }

    @Override
    public int compareTo(NodeTuple t)
    {
      if (this.distance < t.distance)
        return -1;
      else if (this.distance > t.distance)
        return 1;
      return 0;
    }
  }

  public void dijkstras(int[][] distance)
  {
    int V = g.getVertices();
    boolean[] visited = new boolean[V];
    // Arrays.fill(distance, INF);

    PriorityQueue<NodeTuple> q = new PriorityQueue<>();

    for (int i=0; i<distance.length; i++)
    {
      Arrays.fill(visited, false);
      distance[i][i]=0;
      q.add(new NodeTuple(i, 0));

      while (!q.isEmpty())
      {
        NodeTuple top = q.poll();
        visited[top.node] = true;

        if (distance[i][top.node] < top.distance)
          continue;

        LinkedList<Integer> children = g.getAdjList().get(top.node);
        HashMap<Integer, Integer> edgeWeight = g.getWtList().get(top.node);
        for (Integer child : children)
        {
          if (!visited[child])
          {
            int nDist = distance[i][top.node] + edgeWeight.get(child);
            if (nDist < distance[i][child])
            {
              distance[i][child] = nDist;
              q.add(new NodeTuple(child, nDist));
            }
          }
        }
      }
    }
    for(int i=0; i<distance.length; i++)
    System.out.println("Distance[" +i+ "]: " + Arrays.toString(distance[i]));
  }



  public static void main(String[] args)
  {
    for (int x=4; x<6; x++)
    {
      System.out.println("*************************************");
      JohnsonsAPSP obj = new JohnsonsAPSP(x);
      System.out.println("Given Graph: " + obj.g);
      int[][] distance = new int[obj.g.getVertices()][];
      for (int i=0; i<obj.g.getVertices(); i++)
      {
        distance[i] = new int[obj.g.getVertices()];
        Arrays.fill(distance[i], INF);
      }
      obj.johnsonsAlgorithm(distance);
      System.out.println();
    }

  }
}
