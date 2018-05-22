package pkg.graphs;

import java.util.LinkedList;
import java.util.ArrayList;

public class Graph
{
  int vertices;
  boolean directional;
  ArrayList<LinkedList<Integer>> adjList;

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
    directional = dir;
    adjList = new ArrayList<LinkedList<Integer>>(n);
    for (int i = 0; i<vertices; i++)
      adjList.add(new LinkedList<Integer>());
  }


  public void addEdge(int src, int dest)
  {
    if (directional == false)
    {
      adjList.get(src).add(dest);
      adjList.get(dest).add(src);
    }
    else
      adjList.get(src).add(dest);
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
