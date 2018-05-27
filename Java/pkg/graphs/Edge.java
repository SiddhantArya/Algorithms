package pkg.graphs;

public class Edge
{
  int src, dest;

  Edge (int s, int d)
  {
    src = s;
    dest = d;
  }

  public int getSource()
  {
    return src;
  }

  public int getDestination()
  {
    return dest;
  }

  public String toString()
  {
    return ("(" + src + ", " + dest + ")");
  }
}
