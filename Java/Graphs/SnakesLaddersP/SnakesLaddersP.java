// Snakes and Ladder problem

// @author Siddhant Arya
// @email  siddhant.arya18@gmail.com

// Given a snake and ladder board, find the minimum number of dice throws
// required to reach the destination or last cell from source or 1st cell.
// Basically, the player has total control over outcome of dice throw and wants
// to find out minimum number of throws required to reach last cell.

// If the player reaches a cell which is base of a ladder, the player has to
// climb up that ladder and if reaches a cell is mouth of the snake, has to go
// down to the tail of snake without a dice throw.

// The idea is to consider the given snake and ladder board as a directed graph
// with number of vertices equal to the number of cells in the board.
// The problem reduces to finding the shortest path in a graph.
// Every vertex of the graph has an edge to next six vertices if next 6 vertices
// do not have a snake or ladder.
// If any of the next six vertices has a snake or ladder, then the edge from
// current vertex goes to the top of the ladder or tail of the snake.
// Since all edges are of equal weight, we can efficiently find shortest path
// using Breadth First Search of the graph.

// Time Complexity: O(E+V) same as BFS as the Graph is represented using lists

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import pkg.graphs.Graph;

public class SnakesLaddersP
{

  Graph g;
  int[] target;
  SnakesLaddersP()
  {
    // Here weight implies the throw on the dice
    g = new Graph(30, true, true);
    target = new int[30];
    constructBoard();
    constructGraph();
  }

  public void constructBoard()
  {
    Arrays.fill(target, -1);

    // Ladders
    target[2] = 21;
    target[4] = 7;
    target[10] = 25;
    target[19] = 28;

    // Snakes
    target[26] = 0;
    target[20] = 8;
    target[16] = 3;
    target[18] = 6;
  }

  public void constructGraph()
  {
    for (int i=0; i<target.length ; i++)
    {
      for (int j=i+1; j<target.length && j<=i+6; j++)
      {
        int next = (target[j] == -1 ? j : target[j]);
        g.addEdge(i, next, j-i);
      }
    }
  }

  class ListNode
  {
    int index;
    int throwNo;

    ListNode(int i, int no)
    {
      index = i;
      throwNo = no;
    }

    int getIndex()
    {
      return index;
    }

    int getThrowNo()
    {
      return throwNo;
    }
  }

  public int findMinThrows()
  {
    LinkedList<ListNode> nodeList = new LinkedList<>();
    int N = target.length;
    boolean[] visited = new boolean[N];

    ListNode top = new ListNode(0, 0);
    visited[0] = true;
    nodeList.add(top);

    while (!nodeList.isEmpty())
    {
      top = nodeList.poll();

      if (top.getIndex() == N)
        break;

      LinkedList<Integer> children = g.getAdjList().get(top.getIndex());
      for (Integer child : children)
      {
        if (!visited[child])
        {
          visited[child] = true;
          nodeList.add(new ListNode(child, top.getThrowNo() + 1));
        }
      }
    }
    return top.getThrowNo();
  }

  public static void main(String[] args)
  {
    SnakesLaddersP obj = new SnakesLaddersP();
    System.out.println("Given Graph: " + obj.g);
    System.out.println("Min Throws required: " + obj.findMinThrows());
  }
}
