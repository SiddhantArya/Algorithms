// Check if the given graph is a Biconnected Graph

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Biconnected Graph is one which is an INSEPARABLE Graph, meaning that if any
// one vertex were to be removed, the graph will still remain connected.
// On removal, the number of components remain the same in a biconnected graph

// Any vertex whose removal increases the number of connected components
// violates the property of biconnectedness.
// All such vertices are called Articulation Vertices or Cut-Vertices.
// i.e. A cutpoint, cut vertex, or articulation point of a graph G is a
// vertex that is shared by two or more connected blocks.

// For this to be true, there should be two vertex-disjoint paths between any two vertices.
// In a Biconnected Graph, there is a simple cycle through any two vertices.

// A connected graph is Biconnected if it is connected and doesn’t have any
// Articulation Point. We mainly need to check two things in a graph.
// a) The graph is connected.
// b) There is not articulation point in graph.

// The idea is to run a DSF while maintaining the following information:
// a) the depth of each vertex in the DFS tree (once it gets visited), and
// b) for each vertex v, the lowest depth of neighbors of all descendants of v
//    (including v itself) in the DFS tree, called the lowpoint.

// The lowpoint of v can be computed after visiting all descendants of v
// (i.e., just before v gets popped off the DFS stack) as the minimum of the
// b.1 depth of v,
// b.2 depth of all neighbors of v (other than the parent of v in the DFS tree)
// b.3 lowpoint of all children of v in the depth-first-search tree.

// Applications: This property is especially useful in maintaining a graph with
//               a two-fold redundancy, to prevent disconnection upon the
//               removal of a single edge (or connection). Hence, it usefull in
//               the field of networking.

// Time Complexity: O(V+E) because of DFS, graph represented by adjascency lists

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import pkg.graphs.Graph;

public class CheckBiconnectedGraph
{
  Graph g;
  int time;

  CheckBiconnectedGraph(int x)
  {
    int v = 0;
    switch(x)
    {
      case 1:
        v = 2;
        break;
      case 2:
      case 4:
        v = 5;
        break;
      case 3:
      case 5:
        v = 3;
        break;
      default:
        v = 0;
    }

    g = new Graph(v);
    time = 0;
    constructGraph(x);
  }

  public void constructGraph(int x)
  {
    switch(x)
    {
      case 1:
        g.addEdge(0, 1);
        break;
      case 2:
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        break;
      case 3:
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        break;
      case 4:
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        break;
      case 5:
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        break;
      default:
    }

  }

  public boolean checkCutPoint(int i,
                              boolean[] visited,
                              int[] low,
                              int[] depth,
                              int[] parent)
  {
    visited[i] = true;
    // We calculate the no of children mainly to find out if a root vertex has
    // more than one outgoing edges or not.
    int noChildren = 0;

    // Initialize low and depth of vertex
    time+=1;
    depth[i] = low[i] = time;

    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (!visited[child])
      {
        // Set parent of child to i
        parent[child] = i;
        noChildren++;

        // Recursively check if child subgraph has an articulation point
        if (checkCutPoint(child, visited, low, depth, parent))
          return true;

        // Check if child sub-graph has a connection to any ancestors of i
        // This will be updated during the case where the link value of the
        // node that we are going to (child) will be less than
        // the low link value of the node that we are coming from (i)
        // b.3 as mentioned above

        low[i]  = Math.min(low[i], low[child]);

        // i is an articulation point in following cases

        // (1) i is root of DFS tree and has two or more children.
        // This condition checks for nodes who are part of a Strongly connected
        // component or a cycle
        // If these nodes have more than one outgoing nodes, that means that
        // can connect to more than one strongly connected components and hance
        // become candidates for articulation nodes.
        // This check is only applied to root nodes as when there is at most
        // one (singleton or 1) out-edge then it is the node that
        // triggers the start of the cycle and having more than one edge would
        // it an articulation point.
        if (parent[i] == -1 && noChildren > 1)
          return true;

        // (2) If i is not root and low value of one of its
        //  children is more than depth (discovery time) of i.
        // This implies that if the low value of the node that you are coming
        // from is more than the low value that you are going to, after having
        // updated all the low values during the dfs pass, then that means that
        // you are at an artuculation point

        // This condition checks for articulation points due to bridges
        // or Cut-Edges
        if (parent[i] != -1 && low[child] > depth[i])
          return true;

        // This condition checks for articulation points due to starting point of
        // cycle i.e. there is a cycle back to the node when the callback is
        // encountered
        // Cycle corresponds to a strongly connected component and removing the
        // node that started the cycle would essentially increase the number of
        // of connected components by 1 (sever the graph in two).
        // This check is only applied to non root nodes as when there is at most
        // one (singleton or 1) out-edge then it is part of the cycle and it
        // didn't trigger the start of the cycle.
        if (parent[i] != -1 && low[child] == depth[i])
          return true;
      }
      // If the child is already visited and is not the parent
      // Then update the low array
      else
      {
        // b.2 point as mentioned above
        // The callback will trigger each time this condition holds and the node
        // is popped from the DFS Stack
        if (child != parent[i])
          low[i]  = Math.min(low[i], depth[child]);
      }
    }
    return false;
  }

  public boolean isBiconnected()
  {
    int noV = g.getVertices();
    boolean[] visited = new boolean[noV];
    int[] parent = new int[noV];
    int[] low = new int[noV];
    int[] depth = new int[noV];

    Arrays.fill(visited, false);
    Arrays.fill(parent, -1);
    Arrays.fill(low, 0);
    Arrays.fill(depth, 0);

    // Checking for articulation point recursively
    if (checkCutPoint(0, visited, low, depth, parent))
      return false;

    System.out.println(Arrays.toString(visited));

    // Checking if Graph is connected (reachable from source)
    for (int i=0; i<noV; i++)
      if(visited[i]==false)
        return false;

    return true;
  }

  public static void main(String[] args)
  {
    for (int i=1; i<6; i++)
    {
      CheckBiconnectedGraph obj = new CheckBiconnectedGraph(i);
      System.out.println("Given Graph " + i + ": " + obj.g);
      System.out.println("Is Biconnected: " + obj.isBiconnected() + "\n");
    }
  }
}
