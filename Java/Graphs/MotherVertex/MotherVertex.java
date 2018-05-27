// Kosaraju's Strongly Connected Component Algorithm

// A mother vertex in a graph G = (V,E) is a vertex v such that
// all other vertices in G can be reached by a path from v.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// There can be more than one mother vertices in a graph.
// We need to output anyone of them.

// For the three types of Graphs, we have some use cases:
// Undirected Connected Graph : All the vertices are mother vertices as
//                              we can reach to all the other nodes in the graph
// Directed Connected Graph : We have to find a vertex v in the graph such that
//                            we can reach to all the other nodes in the graph
//                            through a DIRECTED path.
// Undirected/Directed DISCONNECTED Graph : There is no mother vertices as we
//                                          cannot reach all the other nodes.

// A Naive approach : A trivial approach is to perform a DFS/BFS on all the
//  (inneficient)     vertices and find whether we can reach all the vertices
//                    from that vertex. This approach takes O(V(E+V)) time.

// Instead, we follow the Kosaraju's Stronly Connected Algorithm according to
// which if there exist mother vertex (or vertices), then one of the mother
// vertices is the last finished vertex in DFS.
// i.e. Or a mother vertex has the maximum finish time in DFS traversal.

// Time Complexity : O(V+E)
// Space Complexity: O(V+E) for graphs

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import pkg.graphs.Graph;

public class MotherVertex
{
  Graph g;

  MotherVertex()
  {
    g = new Graph(7, true);
    constructGraph(g);
  }

  // Recursive DFS utility functions
  public void DFS_Recursive(int v, boolean[] visited)
  {
    visited[v] = true;
    LinkedList<Integer> children = g.getAdjList().get(v);
    for(Integer child : children)
    {
      if (visited[child] == false)
        DFS_Recursive(child, visited);
    }
  }

  public int findMother()
  {
    boolean[] visited = new boolean[g.getVertices()];
    Arrays.fill(visited, false);

    // Find last finished vertex on DFS
    int v=-1;
    for (int i=0; i<g.getVertices(); i++)
    {
      if (visited[i] == false)
      {
        DFS_Recursive(i, visited);
        v = i;
      }
    }

    // If DFS traversal yields every vertex as visited, it is a mother vertex
    Arrays.fill(visited, false);
    DFS_Recursive(v, visited);
    for (int i = 0; i<g.getVertices(); i++)
      if (visited[i]==false)
        return -1;
    return v;
  }

  public void constructGraph(Graph gr)
  {
    gr.addEdge(0, 1);
    gr.addEdge(0, 2);
    gr.addEdge(1, 3);
    gr.addEdge(4, 1);
    gr.addEdge(6, 4);
    gr.addEdge(5, 6);
    gr.addEdge(5, 2);
    gr.addEdge(6, 0);
  }

  // Driver Method
  public static void main(String[] args)
  {
    MotherVertex obj = new MotherVertex();
    System.out.println("Given Graph: " + obj.g);
    int result = obj.findMother();
    if (result == -1)
      System.out.println("Mother Vertex Not Found");
    else
      System.out.println("Mother Vertex: " + result);
  }
}
