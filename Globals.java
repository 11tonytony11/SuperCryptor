public class Globals
{
    public static final String[] ENCRYPTIONS = { "Path Encryption", "Knight's Tour", "Sudoku", "Shuffle Card" , "Half XOR"  };
    public static final String[] GENERATORS = { "Fibonacci", "Pascal", "Hanoi" };

    public static final String[] USAGE = {"Enterprise", "Home"};
    public static final String[] PC = {"Slow", "Fast"};


    public static String REGEX_FILTER = "[^a-zA-Z0-9,]";
    public static String KEY_PARSER   = ",";
    public static char DELIMITER      = '#';

    public static int COMMA = 44;

    public static final int CHESS_BOARD  = 5;
    public static final int SUDOKU_BOARD = 9;

    public static int FIELD_LEN = 20;

    public static final int[][] SUDOKU = new int[][] {
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
}
