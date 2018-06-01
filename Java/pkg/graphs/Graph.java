package pkg.graphs;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph
{
  int vertices, edges;
  boolean isDirected, isWeighted;
  // Adjeascency List for complete Graph
  ArrayList<LinkedList<Integer>> adjList;
  // Indegree and Outdegree for all vertices of Graph
  ArrayList<ArrayList<Integer>> inList, outList;
  // Weights for all edges (MAP) of all vertices(LIST) of Graph
  ArrayList<HashMap<Integer, Integer>> wtList;

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
    this(n, dir, false);
  }

  public Graph(int n, boolean dir, boolean wt)
  {
    vertices = n;
    isDirected = dir;
    isWeighted = wt;
    edges = 0;
    adjList = new ArrayList<LinkedList<Integer>>(n);
    wtList = new ArrayList<HashMap<Integer, Integer>>();
    // In a Directed Graph, the max number of edges can be either
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
      wtList.add(new HashMap<Integer, Integer>());
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
    addEdge(src, dest, 1);
  }

  public void addEdge(int src, int dest, int wt)
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
      // For Weights
      wtList.get(src).put(dest, wt);
      wtList.get(dest).put(src, wt);
    }
    else
    {
      // For Adjascency List
      adjList.get(src).add(dest);
      // For Edges
      outList.get(src).add(dest);
      inList.get(dest).add(src);
      // For Weights
      wtList.get(src).put(dest, wt);
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

  public ArrayList<HashMap<Integer, Integer>> getWtList()
  {
    return wtList;
  }

  public ArrayList<Edge> getEdgeList()
  {
    ArrayList<Edge> edgesList = new ArrayList<Edge>(edges);
    for (int i = 0; i < vertices; i++)
    {
      for (Integer j : adjList.get(i))
      {
        // We are assuming weight of each edge is 1 if the edges are unweighted
        int wt = 1;
        if (isWeighted)
          wt = wtList.get(i).get(j);

        if (isDirected)
          edgesList.add(new Edge(i, j, wt));
        else
        {
          if (i < j)
            edgesList.add(new Edge(i, j, wt));
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
    res.append("\nisDirected: " + isDirected);
    res.append("\nisWeigted: " + isWeighted);
    res.append("\nEdge Weights given in parentheses(wt:1 for undirected)\n");
    for (int i=0; i<vertices; i++)
    {
      res.append("Adjascency List of vertex " +  i + " : ");
      int no = 0;
      for (Integer j: adjList.get(i))
      {
        no++;
        res.append(j + "(" + wtList.get(i).get(j) + ")");
        if (no != adjList.get(i).size())
          res.append(", ");
      }
      res.append("\n");
    }
    return res.toString();
  }
}
