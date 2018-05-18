// Breadth First Search / Traversal

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// It starts at the tree root (or some arbitrary node of a graph (search key)
// explores the neighbor nodes first, before moving to the next level neighbours

// It uses a QUEUE (First In First Out) instead of a STACK (Last In First Out)

// It checks whether a vertex has been discovered before enqueueing the vertex
// rather than delaying this check until the vertex is dequeued from the queue.

// Time Complexity:
//     Matrix:      O(V^2) since every vertex and every edge will be explored
//
//     List:        O(V + E), since every vertex and every edge will be explored
//                  where V is the no of vertices
//                        E is the no of edges
//                  O(E) may vary between O(1) and O(V^2),
//                  depending on how sparse the input graph is.

// Space Complexity:
//     Matrix:       O(V^2) for adjascency matrix as it  keeps a value (0/1)
//                   for every pair of nodes, whether the edge exists or not.
//
//     List:         O(V*E) for adjascency list which is generally less than
//                   O(V*V) (Unless fully connected graph)
//                   as there are fewer edges than nodes generally


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import pkg.graphs.Graph;

public class BFS
{
  Graph g;

  BFS()
  {
    this(6);
    constructGraph();
  }

  BFS(int v)
  {
    this.g = new Graph(v);
  }

  public void constructGraph()
  {
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(2, 4);
    g.addEdge(3, 5);
    g.addEdge(4, 3);
    g.addEdge(4, 5);
  }

  public void traverse()
  {
    boolean[] visited = new boolean[g.getVertices()];
    LinkedList<Integer> vQueue = new LinkedList<Integer>();
    vQueue.add(0);
    visited[0] = true;

    System.out.print("BFS: ");
    while(!vQueue.isEmpty())
    {
      int cur = vQueue.poll();
      System.out.print(cur+1 + " ");
      LinkedList<Integer> children = g.getAdjList().get(cur);
      for (Integer child : children)
      {
        if (visited[child] != true)
        {
          visited[child] = true;
          vQueue.add(child);
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args)
  {
    BFS obj = new BFS();
    System.out.println("Given Gaph: ");
    System.out.println(obj.g);
    obj.traverse();
  }

}
