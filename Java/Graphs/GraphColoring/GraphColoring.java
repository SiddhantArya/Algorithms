// Graph Coloring or m Coloring Problem.

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Graph coloring problem is to assign colors to certain elements of a graph
// subject to certain constraints.

// Vertex coloring is the most common graph coloring problem. The problem is,
// given m colors, find a way of coloring the vertices of a graph such that
// no two adjacent vertices are colored using same color.

// Chromatic Number: The smallest number of colors needed to color a graph G.

// The Greedy approach never uses more than d+1 colors where d is the maximum
// degree of a vertex in the given graph.

// The Graph Coloring decision problem is np-complete, i.e, asking for existence
//  of a coloring with less than 'q' colors, as given a coloring , it can be
// easily checked in polynomial time, whether or not it uses less than 'q' colors.

// On the other hand the Graph Coloring Optimisation problem, which aims to
// find the coloring with minimum colors is np-hard, because even if you are
// given a coloring, you will not be able to say that it's minimum or not.
// This is the basic difference b/w np-complete and np-hard, and is applicable
// to many other problems as well, such as the Travelling Salesman Problem.

// Time Complexity:
//    Greedy      : O(E+V)
//    Backtracking: O(V*d^V) where d is the chromatic number.
//                  Although this approach will find a solution eventually
//                  (if one exists), it isn't speedy.
//                  Backtracking over n variables, each of which can take on k
//                  possible values, is O(kn).
//                  For graph coloring, we will have one variable for each node
//                  in the graph. Each variable will take on any of the
//                  available colors.

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import pkg.graphs.Graph;

public class GraphColoring
{
  Graph g;

  GraphColoring(int i)
  {
    g = new Graph(5);
    constructGraph(i);
  }

  // An array can be used to keep a track of colors, but here an enum is used.
  enum Color
  {
    RED, GREEN, BLUE, WHITE, BLACK, YELLOW;
  }

  public void constructGraph(int x)
  {
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(1, 3+x);
    g.addEdge(2, 3+x);
    g.addEdge(3, 4);
  }

  // Resets available colors.
  public void initAvailColors(LinkedList<Color> list)
  {
    int i =0;
    for (Color c : Color.values())
    {
      if (i++<g.getVertices() && !list.contains(c))
        list.add(c.ordinal(), c);
    }
  }

  // Greedy Solution
  public void vertexColorGreedy()
  {
    int V =g.getVertices();
    Color[] result = new Color[V];

    // Instead of Linked List, an array of integers could have also been used
    LinkedList<Color> availColors = new LinkedList<Color>();

    // System.out.println(Arrays.toString(pq.toArray()));

    result[0] = Color.RED;

    for (int i=0; i<V; i++)
    {
      initAvailColors(availColors);
      LinkedList<Integer> children = g.getAdjList().get(i);
      for (Integer child : children)
      {
        if (result[child] != null && availColors.contains(result[child]))
          availColors.remove(result[child]);
      }
      // This is the greedy approach
      // It assigns a color to the node without cnosidering future nodes
      result[i] = availColors.poll();
    }
    System.out.println("Assigned Colors (Greedy): "  + Arrays.toString(result));
  }

  // Backtracking Solution Util
  public void vertexColorBacktracking(int i,
                                        Color[] result,
                                        ArrayList<Color[]> solutions)
  {
    if (i!=g.getVertices())
    {
      LinkedList<Color> availColors = new LinkedList<Color>();
      initAvailColors(availColors);

      if (result[i] != null)
      availColors.remove(result[i]);

      LinkedList<Integer> children = g.getAdjList().get(i);
      for (Integer child : children)
      {
        if (result[child] != null && availColors.contains(result[child]))
        availColors.remove(result[child]);
      }

      // Backtracking approach
      // Check if colorable by each remaining color
      while (!availColors.isEmpty())
      {
        result[i] = availColors.poll();

        // * To be used if only one solution is required *
        // if (vertexColorBacktracking(i+1, result))
        //   return true;

        vertexColorBacktracking(i+1, result, solutions);

        result[i] = null;
      }
    }
    else if (!Arrays.asList(result).contains(null))
    {
      solutions.add(result.clone());
      // * To be used if only one solution is required *
      // return true;
    }
  }

  // Backtracking Solution
  // If displayLimit = -1, then print all solutions found
  // Else, print
  public void vertexColorBacktracking(int displayLimit)
  {
    Color[] result = new Color[g.getVertices()];
    ArrayList<Color[]> solutions = new ArrayList<Color[]>();

    // vertexColor only works if the Graph is connected.
    // Hence, doing it for only one vertex
    vertexColorBacktracking(0, result, solutions);

    System.out.println("Printing " + displayLimit + " solutions (-1: all): ");

    int i = 0;
    for (Color[] solution : solutions)
    {
      if (i++ > displayLimit)
        break;
      System.out.println("Assigned Colors (Backtracking " + i + "): "
                      + Arrays.toString(solution));
    }
  }

  // Driver Method
  public static void main(String[] args)
  {
    for (int i=0; i<2; i++)
    {
      GraphColoring obj = new GraphColoring(i);
      System.out.println("Given Graph: " + obj.g);
      obj.vertexColorGreedy();
      obj.vertexColorBacktracking(5);
    }
  }
}
