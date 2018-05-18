package pkg.graphs;

import java.util.LinkedList;
import java.util.ArrayList;

public class Graph
{
  int vertices;
  ArrayList<LinkedList<Integer>> adjList;

  public Graph()
  {
    this(5);
  }

  public Graph(int n)
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
