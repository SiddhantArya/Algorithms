package pkg.graphs;

public class Edge
{
  int src, dest;
  int weight;

  public Edge (int s, int d)
  {
    this (s, d, 1);
  }

  public Edge (int s, int d, int wt)
  {
    src = s;
    dest = d;
    weight = wt;
  }

  public int getSource()
  {
    return src;
  }

  public int getDestination()
  {
    return dest;
  }

  public int getWeight()
  {
    return weight;
  }

  public String toString()
  {
    return ("(" + src + ", " + dest + ", " + weight +")");
  }
}
