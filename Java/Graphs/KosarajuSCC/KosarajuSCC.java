// Strongly Connected graph

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// This algorithm is based on the Kosaraju's Algorithm for Strongly Connected
// Components

// It makes use of the fact that the transpose graph (the same graph with the
// direction of every edge reversed) has exactly the same strongly connected
// components as the original graph.

// A directed graph is strongly connected if there is a path between
// all pairs of vertices.
// We make use of a key observation that if we do a DFS of graph and store
// vertices according to their finish times, we make sure that the finish time
// of a vertex that connects to other SCCs (other that its own SCC),
// will always be greater than finish time of vertices in the other SCC

// DFS of a graph produces a single tree if all vertices are reachable from DFS
// starting point. Otherwise DFS produces a forest.

// Algorithm is as follows:
// 1. Call DFS(G) to compute finishing times f[u] for all u.
// 2. Compute G_Transpose
// 3. Call DFS(G_Transpose), but in the main loop, consider vertices in order of
//    decreasing f[u] (as computed in first DFS)
// 4. Output the vertices in each tree of the depth-first forest formed in
//    second DFS as a separate SCC.

// Time Complexity: O(V+E)
// The above algorithm is asymptotically best algorithm but it uses 2-Pass DFS
// There are other algorithms like Tarjan’s algorithm and Path-based strong
// component algorithm which have same time complexity but find SCCs using
// single DFS.

import pkg.graphs.Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Stack;

public class KosarajuSCC
{
  Graph g;

  KosarajuSCC()
  {
    g = new Graph(5, true);
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 1);
    g.addEdge(0, 3);
    g.addEdge(3, 4);
  }

  // Algorithm for Topoligical Sort based on DFS
  public void tSort(int i, boolean[] visited, Stack<Integer> stack)
  {
    visited[i] = true;

    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (visited[child] == false)
        tSort(child, visited, stack);
    }

    stack.push(i);
  }

  // Algorithm for DFS
  public void dfs(Graph g, int i, boolean[] visited)
  {
    System.out.print(i + " ");
    visited[i] = true;

    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (visited[child] == false)
        dfs(g, child, visited);
    }
  }

  // Transpose of a Graph
  public Graph getTranspose()
  {
    Graph transpose = new Graph(g.getVertices(),
                                g.getIsDirected(),
                                g.getIsWeighted());
    for (int i=0; i<g.getVertices(); i++)
    {
      LinkedList<Integer> children = g.getAdjList().get(i);
      for (Integer child : children)
      {
        transpose.addEdge(child, i);
      }
    }

    return transpose;
  }

  // Kosaraju's Algorithm
  public void printSCC()
  {
    boolean[] visited = new boolean[g.getVertices()];
    Arrays.fill(visited, false);

    // DFS Pass 1
    // Build up the finish time of all nodes represented by a Stack
    // Apply the Topoligical Sort to the Graph
    Stack<Integer> stack = new Stack<Integer>();
    for (int i=0; i<g.getVertices(); i++)
    {
      if (visited[i] == false)
        tSort(i, visited, stack);
    }

    // Get Transpose of the Graph
    Graph transpose = getTranspose();
    // Reinitialize the visted array
    Arrays.fill(visited, false);

    // DFS Pass 2
    // Print reachable vertices in components arranged in reverse order of
    // finish time
    System.out.println("Kosaraju's Strongly Connected Components: ");
    int count=0;
    while (!stack.isEmpty())
    {
      int node = (int) stack.pop();
      count++;
      if (visited[node] == false)
      {
        System.out.print("Component " + count + ": ");
        dfs(transpose, node, visited);
        System.out.println();
      }
    }
  }

  // Driver Method
  public static void main(String[] args)
  {
    KosarajuSCC obj = new KosarajuSCC();
    System.out.println("Given Graph: " + obj.g);
    obj.printSCC();
  }
}
