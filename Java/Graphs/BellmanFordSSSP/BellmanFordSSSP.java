// Bellman-Ford Single Source Shortest Path Algorithm

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Like Dijkstras Algorithm, this algorithm is used to find the distance of a
// source vertex from all other vertices of the graph.

// However, one of the biggest drawback of the Dijkstras algorithm is the fact
// that it can't be used when there are negative edges in the graph.

// Bellman Ford can easily detect negative cycles.
// Bellman-Ford is also simpler than Dijkstra and suites well for distributed
// systems as unlike Dijksra’s where we need to find minimum value of all
// vertices, in Bellman-Ford, edges are considered one by one, therefore
// increasing Spacial locality.

// This uses Dynamic Programming with optimal substructure inherent within
// the graph.

// Algorithm:
// 1. Set every entry in distance matrix to INF.
// 2. Set D[source] to be 0.
// 3. Relax each edge V-1 times.

// Time Complexity: O(V.E) as relaxation happens v-1 times for each edge.
//                  i.e. greater than Dijkstras but can work with -ve edges.

import java.util.*;
import pkg.graphs.Graph;
import pkg.graphs.Edge;

public class BellmanFordSSSP
{

  Graph g;
  int[] dist;
  int[] prev;
  final int INF = Integer.MAX_VALUE/2;

  BellmanFordSSSP()
  {
    g = new Graph(5, true, true);
    dist = new int[5];
    prev = new int[5];
    constructGraph();
  }

  public void constructGraph()
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

  public boolean bellmanFord(int src)
  {
    int V = g.getVertices();
    Arrays.fill(dist, INF);
    Arrays.fill(prev, -1);

    dist[src] = 0;
    for(int i=0; i<V; i++)
    {
      ArrayList<Edge> edgesList = g.getEdgeList();
      for (Edge e : edgesList)
      {
        if (dist[e.getSource()] == INF)
        continue;

        if(dist[e.getDestination()] > dist[e.getSource()] + e.getWeight())
        {
          dist[e.getDestination()] = dist[e.getSource()] + e.getWeight();
          prev[e.getDestination()] = e.getSource();
        }
      }
    }

    // Run the algorithm a second time to detect any negative cycle.
    // A negative cycle is encountered if we find a better path than the optimal
    // solution found after V-1 iterations.
    // This loops propagates the effect of the -ve cycle throughout the graph.
    boolean hasNegativeCycle = false;
    for(int i=0; i<V; i++)
    {
      for(Edge e : g.getEdgeList())
      {
        if(dist[e.getDestination()] > dist[e.getSource()] + e.getWeight())
        {
          dist[e.getDestination()] = -INF;
          hasNegativeCycle = true;
        }
      }
    }
    return hasNegativeCycle;
  }

  public ArrayList<Integer> shortestPath(int src, int dest)
  {
    ArrayList<Integer> path = new ArrayList<>();
    path.add(dest);
    int cur = dest;
    while (prev[cur] != src)
    {
      path.add(prev[cur]);
      cur = prev[cur];
    }
    path.add(src);
    Collections.reverse(path);
    return path;
  }

  public static void main(String[] args)
  {
    BellmanFordSSSP obj = new BellmanFordSSSP();
    System.out.println("Given Graph: " + obj.g);
    boolean hasNegativeCycle = obj.bellmanFord(0);
    System.out.println("Has Negative Cycle: " + hasNegativeCycle);
    System.out.println("Distance from source: " + Arrays.toString(obj.dist));

    ArrayList<Integer> path1 = obj.shortestPath(0, 2);
    System.out.println("Shortest Path from 0 to 2: "
                        + Arrays.toString(path1.toArray()));
    ArrayList<Integer> path2 = obj.shortestPath(0, 3);
    System.out.println("Shortest Path from 0 to 3: "
                        + Arrays.toString(path2.toArray()));
    ArrayList<Integer> path3 = obj.shortestPath(0, 4);
    System.out.println("Shortest Path from 0 to 4: "
                        + Arrays.toString(path3.toArray()));
  }

}
