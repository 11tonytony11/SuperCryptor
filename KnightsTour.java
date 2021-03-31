import java.util.ArrayList;
import java.util.List;

public class KnightsTour
{
    public static final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2 , 2 };
    public static final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };

    public static List<Integer> locations = new ArrayList<>();
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function returns solutions for knights tour
    Input:  None
    Output: solves
    */
    public List<Integer> CalculateKnightsMoves()
    {
        int[][] visited = new int[Globals.CHESS_BOARD][Globals.CHESS_BOARD];
        int pos = 1;

        locations.clear();
        KnightsTourSolve(visited, 1, 3, pos);

        return locations;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function solves the Knight's Tour challenge in order to generate keys
    Input:  visited array and position info
    Output: None
    */
    public void KnightsTourSolve(int[][] visited, int x, int y, int pos)
    {
        visited[x][y] = pos;

        // if visited all possible squares
        if (pos >= Globals.CHESS_BOARD * Globals.CHESS_BOARD)
        {
            for (int[] r: visited)
            {
                locations.add(r[0] % 10);
            }

            visited[x][y] = 0;
            return;
        }

        // Checking 8 possible movements and it's paths
        for (int k = 0; k < 8; k++)
        {
            int newX = x + row[k];
            int newY = y + col[k];

            // if new position is valid keep checking from it
            if (newX >= 0 && newX < Globals.CHESS_BOARD && newY >= 0 && newY < Globals.CHESS_BOARD && visited[newX][newY] == 0)
            {
                KnightsTourSolve(visited, newX, newY, pos + 1);
            }
        }

        // backtrack from the current square and remove it from the current path
        visited[x][y] = 0;
    }
}
