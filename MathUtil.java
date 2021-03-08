import java.util.List;

public class MathUtil
{
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with pascal triangle
    Input:  reference number
    Output: Key
    */
    public static int generatePascalKeys(int row, int num)
    {
        if ((row == num) || (num == 0))
            return 1;

        return Math.abs(generatePascalKeys(row - 1, num) + generatePascalKeys(row - 1, num - 1));
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with fibonacci series
    Input:  reference number
    Output: Key series
    */
    public static int generateFibonacciKeys(int num)
    {
        double partial = (1 + Math.sqrt(5)) / 2;
        return Math.abs((int)Math.round(Math.pow(partial, num) / Math.sqrt(5)));
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with Hanoi Tower bin
    Input:  reference number
    Output: Key
    */
    public static int generateHanoiTowerKeys(int num)
    {
        int output;

        output = (int)((Math.log10(num & -num)) / Math.log10(2)) + 1;

        return Math.abs(output);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption keys with Sudoku solves
    Input:  None
    Output: Keys
    */
    public static List<Integer> generateSudokuKeys()
    {
        Sudoku sudoku = new Sudoku();
        return sudoku.getSolves();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption keys with graph path calculations
    Input:  dst point in graph
    Output: Keys
    */
    public static List<Integer> generatePathKeys(int newDst)
    {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);

        g.CalculatePaths(0, newDst);
        return g.getPaths();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption keys with Knights tour solves
    Input:  None
    Output: Keys
    */
    public static List<Integer> generateKnightKeys()
    {
        KnightsTour knight = new KnightsTour();
        return knight.CalculateKnightsMoves();
    }
}
