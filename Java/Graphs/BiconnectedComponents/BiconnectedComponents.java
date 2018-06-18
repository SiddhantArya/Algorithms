// Find Biconnected Components of a Graph, Cut-Points and Bridges
// Tarjan's Strongly Connected Components Algorithm

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
// Likewise, any edge whose removal increases the number of connected components
// violates the property of biconnectedness is called a Bridge or Cut-Edge.

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

// This algorithm is a little better than Kosaraju's SCC Algorithm even though
// the time complexity is the same. This is because it uses only 1 pass of DFS
// while Kosaraju's algorithm uses 2 Passes.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Iterator;
import pkg.graphs.Graph;
import pkg.graphs.Edge;

public class BiconnectedComponents
{
  Graph g;
  int time;
  ArrayList<LinkedList<Edge>> components;
  Stack<Edge> stack;
  ArrayList<Edge> bridges;
  boolean[] cutPoints;

  public BiconnectedComponents()
  {
    g = new Graph(12);
    time = 0;
    cutPoints = new boolean[12];
    components = new ArrayList<LinkedList<Edge>>();
    stack = new Stack<Edge>();
    bridges = new ArrayList<Edge>();
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(2,4);
    g.addEdge(3,4);
    g.addEdge(1,5);
    g.addEdge(0,6);
    g.addEdge(5,6);
    g.addEdge(5,7);
    g.addEdge(5,8);
    g.addEdge(7,8);
    g.addEdge(8,9);
    g.addEdge(10,11);
  }

  // Recursive utility for Biconnected Component Compute
  public void bcc(int i,
                boolean[] visited,
                int[] low,
                int[] depth,
                int[] parent)
  {
    visited[i] = true;
    int noChildren = 0;

    depth[i] = low[i] = ++time;

    LinkedList<Integer> children = g.getAdjList().get(i);
    for (Integer child : children)
    {
      if (!visited[child])
      {
        if (!checkEdge(i, child))
          stack.push(new Edge(i, child));
        parent[child] = i;
        noChildren++;

        bcc(child, visited, low, depth, parent);

        low[i] = Math.min(low[i], low[child]);

        // Articulation Point found
        // New Component: Pop all edges from stack until Edge U->V
        if (parent[i] != -1 && low[child] >= depth[i]
          || parent[i] == -1 && noChildren > 1)
        {
          cutPoints[i] = true;
          addToComponents(i, child);
        }
        if (low[child] > depth[i])
          bridges.add(new Edge (i, child));
      }
      else if (child != parent[i])
      {
        if (!checkEdge(i, child))
          stack.push(new Edge(i, child));
        low[i] = Math.min(low[i], depth[child]);
      }
    }
  }

  public void addToComponents(int i, int child)
  {
    LinkedList<Edge> component = new LinkedList<Edge>() ;
    while (!stack.empty())
    {
      Edge e = stack.pop();
      if (checkAdded(e))
        component.add(e);
      if (e.getSource() == i && e.getDestination()==child
        || e.getSource() == child && e.getDestination()==i)
        break;
    }
    components.add(component);
  }

  // This is to ensure that an edge from previous component is not added to new
  // component
  public boolean checkAdded(Edge e)
  {
    int src = e.getSource();
    int dest = e.getDestination();
    for (LinkedList<Edge> component : components)
    {
      for (Edge i : component)
      {
        if (i.getSource() == src && i.getDestination() == dest)
          return false;
        else if (i.getDestination() == src && i.getSource() == dest)
          return false;
      }
    }
    return true;
  }

  // This is required for Undirected Graph
  // Checks if I-J or J-I is already added onto the stack
  public boolean checkEdge(int i, int j)
  {
    Iterator<Edge> it = stack.iterator();
    boolean isPresent = false;
    while (it.hasNext())
    {
      Edge e = (Edge) it.next();
      int src  = e.getSource();
      int dest = e.getDestination();
      boolean checkIJ = (src  == i) && (dest == j);
      boolean checkJI = (dest == i) && (src  == j);

      isPresent = checkIJ || checkJI;
      if (isPresent)
        return true;
    }
    return false;
  }

  // Biconnected Component Compute Algorithm
  public void bcc()
  {
    int V = g.getVertices();
    boolean[] visited = new boolean[V];
    int[] low = new int[V];
    int[] depth = new int[V];
    int[] parent = new int[V];

    Arrays.fill(low, -1);
    Arrays.fill(depth, -1);
    Arrays.fill(parent, -1);

    for (int i=0; i<V; i++)
    {
      if (!visited[i])
        bcc(i, visited, low, depth, parent);

      LinkedList<Edge> component = new LinkedList<Edge>() ;
      boolean isEmpty = true;
      while (!stack.empty())
      {
        isEmpty = false;
        Edge e = stack.pop();
        if (checkAdded(e))
          component.add(e);
      }
      if (!isEmpty)
        components.add(component);
    }
  }


  // Driver Method
  public static void main(String[] args)
  {
    BiconnectedComponents obj = new BiconnectedComponents();
    System.out.println("Given Graph: " + obj.g);

    obj.bcc();

    System.out.println("Cut Points: ");
    for (int i=0; i<obj.cutPoints.length; i++)
      if (obj.cutPoints[i] == true)
        System.out.print(i + " ");
    System.out.println();
    System.out.println("Bridges: ");
    for (Edge e : obj.bridges)
      System.out.print(e + " ");
    System.out.println();
    System.out.println("Biconnected Components: ");
    for (int i=0; i<obj.components.size(); i++)
    {
      System.out.println("Component " + (i+1) + ": ");
      for (Edge ed : obj.components.get(i))
        System.out.print(ed + " ");
      System.out.println();
    }
  }
}
