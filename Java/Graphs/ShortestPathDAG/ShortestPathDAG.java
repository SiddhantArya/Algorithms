// Shortest Path in a DAG
// Given a Weighted Directed Acyclic Graph and a source vertex in the graph,
// find the shortest paths from given source to all other vertices.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Following is complete algorithm for finding shortest distances.
// 1) Initialize dist[]={INF,INF, ….} and dist[s]=0 where s is the source vertex
// 2) Create a toplogical order of all vertices.
// 3) Do following for every vertex u in topological order.
// ………..Do following for every adjacent vertex v of u
// ………………if (dist[v] > dist[u] + weight(u, v))
// ………………………dist[v] = dist[u] + weight(u, v)

// Time Complexity: O(V+E) because it uses Topological Sort

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;
import pkg.graphs.Graph;

public class ShortestPathDAG
{
  Graph g;
  static final int INF = Integer.MAX_VALUE;

  ShortestPathDAG()
  {
    g = new Graph(6, true, true);
    constructGraph();
  }

  public void tSort(int v, boolean[] visited, Stack<Integer> stack)
  {
    visited[v] = true;

    LinkedList<Integer> children = g.getAdjList().get(v);
    for(Integer child : children)
    {
      if (!visited[child])
        tSort(child, visited, stack);
    }
    stack.push(v);
  }

  public int[] shortestPath(int src)
  {
    int V = g.getVertices();
    boolean[] visited = new boolean[V];
    int[] distance = new int[V];
    Stack<Integer> stack = new Stack<Integer>();

    Arrays.fill(visited, false);
    Arrays.fill(distance, INF);
    distance[src] = 0;

    for (int i=0; i<V; i++)
    {
      if (!visited[i])
        tSort(i, visited, stack);
    }

    while (!stack.isEmpty())
    {
      int i = (int)stack.pop();
      LinkedList<Integer> children = g.getAdjList().get(i);
      HashMap<Integer, Integer> edgeWeight = g.getWtList().get(i);
      for (Integer child : children)
      {
        if (distance[i] != INF && distance[child] > distance[i] + edgeWeight.get(child))
          distance[child] = distance[i] + edgeWeight.get(child);
      }
    }
    return distance;
  }

  public void constructGraph()
  {
    g.addEdge(0, 1, 5);
    g.addEdge(0, 2, 3);
    g.addEdge(1, 3, 6);
    g.addEdge(1, 2, 2);
    g.addEdge(2, 4, 4);
    g.addEdge(2, 5, 2);
    g.addEdge(2, 3, 7);
    g.addEdge(3, 4, -1);
    g.addEdge(4, 5, -2);
  }

  public static void main(String[] args)
  {
    ShortestPathDAG obj = new ShortestPathDAG();
    int[] d = obj.shortestPath(1);
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Shortest Distances from <1>(Integer.MAX_VALUE => INF): "
                        + Arrays.toString(d));
  }
}
