import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sudoku
{
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

    public static List<Integer> solves = new ArrayList<>();

    //-----------------------------------------------------------------------------------------------------------
    /*
    This function returns solutions for sudoku
    Input:  None
    Output: solves
    */
    public List<Integer> getSolves()
    {
        solves.clear();

        for (int i = 0; i < Globals.SUDOKU.length; i++)
        {
            board[i] = Arrays.copyOf(Globals.SUDOKU[i], Globals.SUDOKU[i].length);
        }

        solve();

        return solves;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function solves the Sudoku board
    Input:  None
    Output: if possible to solve
    */
    public boolean solve()
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
            if (isValidMove(board, row, col, num))
            {
                board[row][col] = num;
                solves.add(num);
                if (solve())
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
    This function checks if move is valid
    Input:  board, location and possible number
    Output: boolean answer
    */
    public boolean isValidMove(int[][] board, int row, int col, int num)
    {
        // Row has the unique (row-clash)
        for (int d = 0; d < board.length; d++)
        {
            if (board[row][d] == num)
            {
                return false;
            }
        }

        for (int[] ints : board)
        {
            if (ints[col] == num)
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
}
