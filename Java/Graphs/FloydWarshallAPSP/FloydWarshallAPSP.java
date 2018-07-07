// Floyd-Warshall's Algorithm for All Pairs Shortest Path Algorithm.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// This algorithm is used to find shortest path between all pairs of vertices in
// and edge weighted directed graph.
// It is used in place of Dijkstra's Algorithm which return shortest path from
// a single source to all the other vertices.

// Floyd Warshall Algorithm uses the adjascency matrix representation.

// It is a DP problem with an optimal substructure as follows:

// The idea is to one by one pick all vertices and updates all shortest paths
// which include the picked vertex as an intermediate vertex in the shortest
// path.
// When we pick vertex number k as an intermediate vertex, we already have
// considered vertices {0, 1, 2, .. k-1} as intermediate vertices.
// For every pair (i, j) of the source and destination vertices respectively,
// there are two possible cases.
// 1) k is not an intermediate vertex in shortest path from i to j.
//    We keep the value of dist[i][j] as it is.
// 2) k is an intermediate vertex in shortest path from i to j.
//    We update the value of dist[i][j] as dist[i][k] + dist[k][j].

// Inductive Hypothesis:
// Suppose that prior to the kth iteration it holds that for i, j for every V,
// D[i][j] contains the length of the shortest path Q from i to j in G
// containing only vertices in the set {1, 2, ..., k − 1},
// and Predecessors[i][j] contains the immediate predecessor of j on path Q.

// Time Complexity : O(V^3)
// Space Complexity: O(V^2) as we are computing value of k-th stage using k-1-th
//                   stage value essentially in place.
//                   If it is not done in place, we can do it in
//                   O(V^3) as well.

// Comparison with Dijkstra's Algorithm

// Firstly, it is better at detecting negative edge cycles than Dijkstra's.
// An optimal path between two nodes can easily be affected by negative cycles.

// Secondly, it can be argued that we can run Dijkstra's Algorithm n times to
// achieve a similar result. However, Floyd Warshall is preferred not only
// because it is both temporally and spacially efficient when compared to
// Dijkstra's, but also when we compare the time complexities, Dijkstra's
// algorithms has larger constant values needed for every distance calcuation.

// Let us assume v to be the number of vertices. For a sparse graph (few edges)
// the number of edges e = O(v). For a dense graph (many edges) e = O(v^2).

// Now the best asymptotic implementation of the shortest path problem from a
// single source takes O(e + vlogv) amortized time. This implementation of the
// Dijkstra's algorithm uses Fibonacci Heaps, which are not very practical
// because of the high constant values involved.

// For instance, apart from the parent and children, every vertex in the heap
// is also connected to its sibling node using a double linked list. This leads
// to each node storing a lot of pointers.
// Apart from the heap, even the adjacency list needs to be accessed for every
// vertex i.e. O(n.e).

// If we assume the worst case scenario where our graph turns out to be a dense
// graph, e = O(v^2), Dijkstra's algorithm will take O(v^2 + vlogv) = O(v^2).

// Now how would you find the shortest path between all pairs of vertices?

// Option 1:
// You could go with Dijkstra's algorithm and apply it for every single vertex.
// Running Dijkstra n times:    [assuming binary heap used for Dijkstra]
// time = number of nodes × O((n + e)lgn)
//      = n × O((n + e)lgn)
//      = O(n(n + e)lgn)
//  v * O(v^2) = O(v^3). However, the constants involved would make the
// practical cost higher. For every vertex you'll have to build the heap (once),
// check the adjacency list,
// decrease-key
// and extract the minimum(while maintaining the min-heap)

// Option 2:
// The Floyd-Warshall algorithm basically works on a v * v adjacency matrix.
// It considers every vertex and decides what would be the shorter route if
// could you go via that vertex. This is a constant time comparison and an
// insert-operation (into a 2D array) carried out for all v^2 elements of the
// matrix.

// This needs to be performed for every vertex. Therefore the time complexity
// comes out to be O(v^3) but with a very small constant value, making it
// extremely viable during implementation.

// So all you need is the graph in the format of an adjacency matrix, one more
// adjacency matrix to store the new values and 3 nested for loops that run for
// a total of v * v * v times.

import java.util.*;

public class FloydWarshallAPSP
{
  int V, E;
  int[][] adjMatrix;

  // Shorter MAX_VALUE used to prevent integer overflow
  final int INF = Integer.MAX_VALUE/2;
  FloydWarshallAPSP(int v)
  {
    V = v;
    adjMatrix = new int[v][];
    for (int i = 0; i<V; i++)
      adjMatrix[i] = new int[V];

    constructGraph();
  }

  public void constructGraph()
  {
    /* Let us create the following weighted graph
        10
   (0)------->(3)
    |         /|\
  5 |          |
    |          | 1
   \|/         |
   (1)------->(2)
        3

    Here shortest path between 0 and 3 is through 1 and 2.
        */
    if (adjMatrix!=null)
    {
      adjMatrix[0] = new int[] {0,   5,  INF, 10};
      adjMatrix[1] = new int[] {INF, 0,   3, INF};
      adjMatrix[2] = new int[] {INF, INF, 0,   1};
      adjMatrix[3] = new int[] {INF, INF, INF, 0};
    }
  }

  public int[][] floydWarshall()
  {
    int[][] pred = new int[V][V];
    int[][] distance = new int[V][V];
    for (int i = 0; i<V; i++)
    {
      pred[i] = new int[V];
      distance[i] = new int[V];
      for (int j = 0; j<V; j++)
      {
        distance[i][j] = adjMatrix[i][j];
        pred[i][j] = (i != j && adjMatrix[i][j] != INF) ? i : -1;
      }
    }

    // The core of Floyd Warshall
    // For calculation <i,j>, <i,k> + <k,j> is considered as
    // it calculates if a better path through k is possible.
    for (int k=0; k<V; k++)
    {
      for(int i=0; i<V; i++)
      {
        if (distance[i][k] == INF)
          continue;
        for(int j=0; j<V; j++)
        {
          if (distance[k][j] == INF)
            continue;
          if (distance[i][j] > distance[i][k] + distance[k][j])
          {
            distance[i][j] = distance[i][k] + distance[k][j];
            // To check overflow
            distance[i][j] = Math.max(distance[i][j], -INF);
            pred[i][j] = pred[k][j];
          }
        }
      }
    }

    System.out.println("All Pair Shortest Distances (-: INF): ");
    for (int i=0; i<V; i++)
    {
      for (int j=0; j<V; j++)
        System.out.print((distance[i][j] == INF ? "-" : distance[i][j]) + " ");
      System.out.println();
    }

    // If distance of any verex from itself
    // becomes negative, then there is a negative
    // weight cycle.
    for (int i = 0; i < V; i++)
      if (distance[i][i] < 0)
        return null;
    return pred;
  }

  public ArrayList<Integer> shortestPath(int src, int dest, int[][] pred)
  {
    ArrayList<Integer> path = new ArrayList<Integer>();
    path.add(dest);
    int cur = dest;
    while (pred[src][cur]!=src)
    {
      path.add(pred[src][cur]);
      cur = pred[src][cur];
    }
    path.add(src);
    Collections.reverse(path);
    return path;
  }

  public static void main(String[] args)
  {
    FloydWarshallAPSP obj = new FloydWarshallAPSP(4);
    int[][] pred = obj.floydWarshall();
    if (pred == null)
    {
      System.out.println("The Given Graph contains a negative cycle");
      return;
    }
    System.out.println("Predecessors (No Neg cycle): ");
    for (int i=0; i<4; i++)
      System.out.println(Arrays.toString(pred[i]));
    System.out.println("Shortest Path from 0 to 3: " +
                        obj.shortestPath(0, 3, pred));
  }
}
