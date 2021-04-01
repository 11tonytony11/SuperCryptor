import java.util.ArrayList;
import java.util.List;

class Graph
{
    // adjacency list
    private final ArrayList<Integer>[] connections;
    private final List<Integer> paths;
    private final int vertics;

    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the Graph constructor
    Input:  number of vertices
    Output: None
    */
    public Graph(int newVertices)
    {
        this.vertics = newVertices;
        this.paths = new ArrayList<>();
        this.connections = new ArrayList[vertics];

        for (int i = 0; i < vertics; i++)
        {
            connections[i] = new ArrayList<>();
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds edge to the graph
    Input:  src and new edge
    Output: None
    */
    public void addEdge(int u, int v)
    {
        connections[u].add(v);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function handles path calculation
    Input:  src and dst
    Output: None
    */
    public void CalculatePaths(int src, int dst)
    {
        boolean[] isVisited = new boolean[vertics];
        ArrayList<Integer> pathList = new ArrayList<>();

        pathList.add(src);

        CalculateAllPaths(src, dst, isVisited, pathList);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function finds path between two points in a graph
    Input:  None
    Output: Possible paths
    */
    private void CalculateAllPaths(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList)
    {
        if (u.equals(d))
        {
            paths.addAll(localPathList);
            return;
        }

        // set as visited
        isVisited[u] = true;

        // Calculate all paths from given point
        for (Integer i : connections[u])
        {
            if (!isVisited[i])
            {
                localPathList.add(i);
                CalculateAllPaths(i, d, isVisited, localPathList);
                localPathList.remove(i);
            }
        }

        // set as not visited for different routes
        isVisited[u] = false;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function returns all paths from src to dst
    Input:  None
    Output: Possible paths
    */
    public List<Integer> getPaths()
    {
        return this.paths;
    }
}
