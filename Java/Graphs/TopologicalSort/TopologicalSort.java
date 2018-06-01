// Topological Sort

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
// vertices such that for every directed edge u->v, vertex u comes before v in
// the ordering.

// Time Complexity: O(V+E) for the adjascency list representaion(similar to DFS)
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import pkg.graphs.Graph;

public class TopologicalSort
{
  Graph g;
  TopologicalSort()
  {
    g = new Graph(6, true);
    constructGraph();
  }

  // Similar to DFS, but differs in the way that instead of printing the vertex
  // before visiting the vertices, we call Topological Sort on the adjascent
  // vertices and then push the element on the STACK
  public void tSort(int v, boolean[] visited, Stack<Integer> stack)
  {
    visited[v] = true;

    LinkedList<Integer> children = g.getAdjList().get(v);
    for (Integer child : children)
    {
      if (!visited[child])
        tSort(child, visited, stack);
    }
    stack.push(v);
  }

  public void tSort()
  {
    boolean[] visited = new boolean[g.getVertices()];
    Arrays.fill(visited, false);

    Stack<Integer> stack = new Stack<Integer>();

    for (int i =0; i<g.getVertices(); i++)
    {
      if (visited[i] == false)
        tSort(i, visited, stack);
    }

    System.out.println("Topological Sort: ");
    while (!stack.isEmpty())
      System.out.print(stack.pop() + " ");
    System.out.print("\n");
  }

  public void constructGraph()
  {
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
  }

  public static void main(String[] args)
  {
    TopologicalSort obj = new TopologicalSort();
    System.out.println("Given Graph: " + obj.g);
    obj.tSort();

  }
}
