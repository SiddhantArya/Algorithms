// Transitive Closure Matrix of a given Graph.

// Given a directed graph, find out if a vertex v is reachable from another
// vertex u for all vertex pairs (u, v) in the given graph.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Reachable means that there is a path from vertex u to v.
// The reach-ability matrix is called TRANSITIVE CLOSURE a graph.

// This is done by modifying DFS algorithm where we Call DFS for every node of
// graph to mark reachable vertices in tc[][] and not calling DFS for an
// adjacent vertex if it is already marked as reachable

// We can do the same thing with Floyd Warshall algorithm in O(V^3) complexity.

// Time Complexity: O(V^2) -- Better than Floyd Warshall

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import pkg.graphs.Graph;

public class TransitiveClosureMatrix
{
  Graph g;

  TransitiveClosureMatrix()
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

  public void dfs(int i, int j, int[][] arr)
  {
    arr[i][j] = 1;

    // Get children of destination vertex
    LinkedList<Integer> children = g.getAdjList().get(j);
    for (Integer child : children)
    {
      // Check or update transitive matrix of Souce with Destination's children
      if (arr[i][child] == 0)
        dfs(i, child, arr);
    }
  }

  public int[][] getTransitiveClosure()
  {
    int V = g.getVertices();
    int[][] arr = new int[V][V];
    for (int i=0; i<V; i++)
    {
      arr[i] = new int[V];
      Arrays.fill(arr[i], 0);
    }

    for (int i=0; i<V; i++)
      dfs(i, i,  arr);

    return arr;
  }

  public static void main(String[] args)
  {
    TransitiveClosureMatrix obj = new TransitiveClosureMatrix();
    int[][] result = obj.getTransitiveClosure();
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Tansitive Closure Matrix: ");
    for (int i=0; i<obj.g.getVertices(); i++)
      System.out.println(Arrays.toString(result[i]));
  }
}
