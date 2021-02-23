import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MathUtil
{
    public static final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2 , 2 };
    public static final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };
    public static final int[][] board = new int[][] {
            { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
    };

    List<Integer> locations;
    List<Integer> solves;


    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with pascal triangle
    Input:  reference number
    Output: Key
    */
    public int pascal(int row, int num)
    {
        if ((row == num) || (num == 0))
            return 1;

        return pascal(row - 1, num) + pascal(row - 1, num - 1);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with fibonacci series
    Input:  reference number
    Output: Key series
    */
    public int fibonacci(int num)
    {
        double partial = (1 + Math.sqrt(5)) / 2;
        int output = (int) Math.round(Math.pow(partial, num) / Math.sqrt(5));

        return output;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with Hanoi Tower bin
    Input:  reference number
    Output: Key
    */
    public int hanoiTower(int num)
    {
        int output;

        output = (int)((Math.log10(num & -num)) / Math.log10(2)) + 1;

        return output;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function solves the Sudoku board in order to generate keys
    Input:  None
    Output: Solve for Sudoku
    */
    public boolean Sudoku()
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        for (int i = 0; i < Globals.SUDOKU_BOARD; i++)
        {
            for (int j = 0; j < Globals.SUDOKU_BOARD; j++)
            {
                if (board[i][j] == 0)
                {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }

            if (!isEmpty)
            {
                break;
            }
        }

        // No empty space left
        if (isEmpty)
        {
            return true;
        }

        // Else for each-row backtrack
        for (int num = 1; num <= Globals.SUDOKU_BOARD; num++)
        {
            if (isValid(board, row, col, num))
            {
                board[row][col] = num;
                solves.add(num);
                if (Sudoku())
                {
                    return true;
                }
                else
                {
                    // replace it
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function solves the Sudoku board in order to generate keys
    Input:  None
    Output: Solve for Sudoku
    */
    public boolean isValid(int[][] board, int row, int col, int num)
    {
        // Row has the unique (row-clash)
        for (int d = 0; d < board.length; d++)
        {
            if (board[row][d] == num)
            {
                return false;
            }
        }

        for (int r = 0; r < board.length; r++)
        {
            if (board[r][col] == num)
            {
                return false;
            }
        }

        // Corresponding square has unique number (box-clash)
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart; d < boxColStart + sqrt; d++)
            {
                if (board[r][d] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function solves the Knight's Tour challenge in order to generate keys
    Input:  None
    Output: Solve for Knight's Tour
    */
    public void KnightsTour(int visited[][], int x, int y, int pos)
    {
        visited[x][y] = pos;

        // if visited all possible squares
        if (pos >= Globals.CHESS_BOARD * Globals.CHESS_BOARD)
        {
            for (int[] r: visited)
            {
                locations.addAll(Arrays.stream(r).boxed().collect(Collectors.toList()));
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
                KnightsTour(visited, newX, newY, pos + 1);
            }
        }

        // backtrack from the current square and remove it from the current path
        visited[x][y] = 0;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function finds path between two points in a graph
    Input:  None
    Output: Possible paths
    */
    public List<Integer> PathCalc(int newDst)
    {
        Graph g = new Graph(8);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(2, 6);
        g.addEdge(3, 7);
        g.addEdge(4, 8);

        g.CalculatePaths(0, newDst);

        return g.getPaths();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function returns solutions for sudoku
    Input:  None
    Output: All solves
    */
    public List<Integer> getSolves()
    {
        return this.solves;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function returns solutions for knights tour
    Input:  None
    Output: All solves
    */
    public List<Integer> getLocations()
    {
        return this.locations;
    }
}
