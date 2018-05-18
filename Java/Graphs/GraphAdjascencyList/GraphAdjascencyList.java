// <!> This is the same graph implementation as is maintained in the java package
// <!> pkg.graphs.Graph
// <!> It is provided here redundantly just for exposition sake
// <!> This doesn't include a main method. Hence, should not be run.

// Graph is a data structure that consists of following two components:
// 1. A finite set of vertices also called as nodes.
// 2. A finite set of ordered pair of the form (u, v) called as edge.
// The pair is ordered because (u, v) is not same as (v, u) in case of
// directed graph(di-graph). The pair of form (u, v) indicates that there is an
// edge from vertex u to vertex v. The edges may contain weight/value/cost.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// There are two most commonly used representations of a Graph
// 1. Adjacency Matrix
// 2. Adjacency List
// There are other representations also like, Incidence Matrix, Incidence List.
// The choice of the graph representation is situation specific.
// It totally depends on the type of operations to be performed and ease of use.

// Space Complexity: O(|V|+|E|) where V is no of vertices and E is no of edges
//                 : O(V^2) in the worst case
// Queries like whether there is an edge from vertex u to vertex v are not
// efficient and can be done O(V).

import java.util.LinkedList;
import java.util.ArrayList;
// public class pkg.graphs.Graph
public class GraphAdjascencyList
{
  int vertices;
  ArrayList<LinkedList<Integer>> adjList;

  public GraphAdjascencyList()
  {
    this(5);
  }

  public GraphAdjascencyList(int n)
  {
    vertices = n;
    adjList = new ArrayList<LinkedList<Integer>>(n);
    for (int i = 0; i<vertices; i++)
      adjList.add(new LinkedList<Integer>());
  }

  public void addEdge(int src, int dest)
  {
    adjList.get(src).add(dest);
    adjList.get(dest).add(src);
  }

  public int getVertices()
  {
    return vertices;
  }

  public ArrayList<LinkedList<Integer>> getAdjList()
  {
    return adjList;
  }

  public String toString()
  {
    StringBuilder res = new StringBuilder("Vertices: ");
    for (int i=0; i<vertices; i++)
      res.append(i + " ");
    res.append("\n");
    for (int i=0; i<vertices; i++)
    {
      res.append("Adjascency List of vertex " +  i + " : " + i);
      for (Integer j: adjList.get(i))
        res.append(" -> " + j);
      res.append("\n");
    }
    return res.toString();
  }
}
