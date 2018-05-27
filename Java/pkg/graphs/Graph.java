// Detect Cycle in an Undirected Graph using Union-Find Algorithm.

// Union-Find Algorithm can be used to check whether an undirected graph
// contains cycle or not.
package pkg.graphs;

import java.util.LinkedList;
import java.util.ArrayList;

public class Graph
{
  int vertices, edges;
  boolean isDirected;
  // Adjeascency List for complete Graph
  ArrayList<LinkedList<Integer>> adjList;
  // Indegree and Outdegree for all vertices of Graph
  ArrayList<ArrayList<Integer>> inList, outList;

  public Graph()
  {
    this(5, false);
  }

  public Graph(boolean x)
  {
    this(5, x);
  }

  public Graph(int n)
  {
    this(n, false);
  }

  public Graph(int n, boolean dir)
  {
    vertices = n;
    isDirected = dir;
    edges = 0;
    adjList = new ArrayList<LinkedList<Integer>>(n);
    // In a Directed Graph, the max number of edges can be wither
    //                      V(V-1) if there exists no self edge
    //                   or V^2    if there are self edges
    // In an Undirected Graph, the max number of edges is V(V-1)/2
    if (isDirected == true)
    {
      // Assuming Self Edges are allowed
      inList = new ArrayList<ArrayList<Integer>>(vertices * vertices);
      outList = new ArrayList<ArrayList<Integer>>(vertices * vertices);
    }
    else
    {
      inList = new ArrayList<ArrayList<Integer>>((vertices * (vertices-1))/2);
      outList = new ArrayList<ArrayList<Integer>>((vertices * (vertices-1))/2);
    }
    for (int i = 0; i<vertices; i++)
    {
      adjList.add(new LinkedList<Integer>());
      inList.add(new ArrayList<Integer>());
      outList.add(new ArrayList<Integer>());
    }
  }

  public ArrayList<ArrayList<Integer>> getInList()
  {
    return inList;
  }

  public ArrayList<ArrayList<Integer>> getOutList()
  {
    return outList;
  }

  public void addEdge(int src, int dest)
  {
    if (isDirected == false)
    {
      // For Adjascency Lists
      adjList.get(src).add(dest);
      adjList.get(dest).add(src);
      // For Edges
      outList.get(dest).add(src);
      outList.get(src).add(dest);
      inList.get(dest).add(src);
      inList.get(src).add(dest);
    }
    else
    {
      // For Adjascency List
      adjList.get(src).add(dest);
      // For Edges
      outList.get(src).add(dest);
      inList.get(dest).add(src);
    }
    edges++;
  }

  public int getVertices()
  {
    return vertices;
  }

  public int getEdges()
  {
    return edges;
  }

  public ArrayList<LinkedList<Integer>> getAdjList()
  {
    return adjList;
  }

  public ArrayList<Edge> getEdgeList()
  {
    ArrayList<Edge> edgesList = new ArrayList<Edge>(edges);
    for (int i = 0; i < vertices; i++)
    {
      for (Integer j : adjList.get(i))
      {
        if (isDirected)
          edgesList.add(new Edge(i, j));
        else
        {
          if (i < j)
            edgesList.add(new Edge(i, j));
        }
      }
    }
    return edgesList;
  }

  public String toString()
  {
    StringBuilder res = new StringBuilder("Vertices: ");
    for (int i=0; i<vertices; i++)
      res.append(i + " ");
    res.append("\n");
    for (int i=0; i<vertices; i++)
    {
      res.append("Adjascency List of vertex " +  i + " : ");
      int no = 0;
      for (Integer j: adjList.get(i))
      {
        no++;
        res.append(j);
        if (no != adjList.get(i).size())
          res.append(", ");
      }
      res.append("\n");
    }
    return res.toString();
  }
}
