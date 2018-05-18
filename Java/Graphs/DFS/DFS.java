// Depth First Search / Traversal

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// It starts at the root (selecting some arbitrary node as the root in the case
// of a graph) and explores as far as possible along each branch before
// backtracking.

// It uses a STACK (Last In First Out) instead of a QUEUE (First In First Out)

// It checks whether a vertex has been discovered by delaying this check until
// the vertex is dequeued from queue rather than before enqueueing the vertex

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

public class DFS
{
  Graph g;

  DFS()
  {
    this(6);
    constructGraph();
  }

  DFS(int v)
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

  public void traverse_iter()
  {
    boolean[] visited = new boolean[g.getVertices()];
    LinkedList<Integer> vStack = new LinkedList<Integer>();
    System.out.print("Iterative BFS: ");

    vStack.add(0);

    while(!vStack.isEmpty())
    {
      // Pop top of Stack
      int cur = vStack.pop();
      // If not visited then visit
      if(visited[cur] != true)
      {
        System.out.print(cur+1 + " ");
        visited[cur] = true;
      }
      // If children of popped element are not visited,
      // then visit them recursively
      LinkedList<Integer> children = g.getAdjList().get(cur);
      for (Integer child : children)
      {
        if (visited[child] != true)
          vStack.push(child);
      }
    }
    System.out.println();
  }

  public void traverse_rec_util(int cur, boolean[] visited)
  {

    System.out.print(cur+1 + " ");
    visited[cur] = true;

    LinkedList<Integer> children = g.getAdjList().get(cur);
    for (Integer child : children)
    {
      if (visited[child] != true)
        traverse_rec_util(child, visited);
    }
  }

  public void traverse_rec()
  {
    boolean[] visited = new boolean[g.getVertices()];
    System.out.print("Recursive BFS: ");

    traverse_rec_util(0, visited);
    System.out.println();
  }

  public static void main(String[] args)
  {
    DFS obj = new DFS();
    System.out.println("Given Gaph: ");
    System.out.println(obj.g);
    obj.traverse_iter();
    obj.traverse_rec();
  }

}
