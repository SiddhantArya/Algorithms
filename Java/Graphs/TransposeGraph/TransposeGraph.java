// Transpose of a Graph

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// We traverse the adjacency list and as we find a vertex v in the adjacency
// list of vertex u which indicates an edge from u to v in main graph,
// we just add an edge from v to u in the transpose graph
// i.e. add u in the adjacency list of vertex v of the new graph.
// Thus traversing lists of all vertices of main graph we can get the transpose graph.

// Time Complexity : O(V+E)
import pkg.graphs.Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

public class TransposeGraph
{
  Graph g;

  TransposeGraph()
  {
    g = new Graph(5, true);
    constructGraph();
  }

  public void constructGraph()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 4);
    g.addEdge(0, 3);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    g.addEdge(4, 1);
    g.addEdge(4, 3);
  }

  public void transpose()
  {
    ArrayList<LinkedList<Integer>> tList = new ArrayList<LinkedList<Integer>>();
    ArrayList<ArrayList<Integer>> tInList = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> tOutList
                                  = new ArrayList<ArrayList<Integer>>();
    ArrayList<HashMap<Integer, Integer>> tWtList
                                  = new ArrayList<HashMap<Integer, Integer>>();

    for (int i =0; i<g.getVertices(); i++)
    {
      tList.add(new LinkedList<Integer>());
      tInList.add(new ArrayList<Integer>());
      tOutList.add(new ArrayList<Integer>());
      tWtList.add(new HashMap<Integer, Integer>());
    }

    for (int i=0; i<g.getVertices(); i++)
    {
      LinkedList<Integer> children = g.getAdjList().get(i);
      HashMap<Integer, Integer> wts = g.getWtList().get(i);
      for (Integer child : children)
      {
        tList.get(child).add(i);
        tOutList.get(child).add(i);
        tInList.get(i).add(child);
        tWtList.get(child).put(i, wts.get(child));
      }
    }
    g.setAdjList(tList);
    g.setInList(tInList);
    g.setOutList(tOutList);
    g.setWtList(tWtList);
  }

  public static void main(String[] args)
  {
    TransposeGraph obj = new TransposeGraph();
    System.out.println("Given Graph: " + obj.g);
    obj.transpose();
    System.out.println("Transposed Graph: " + obj.g);
  }

}
