import java.util.Random;

public class MathUtil
{
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates random number
    Input:  flag - if true generate number between 1-8
    Output: random number
    */
    public int randomNumber(Boolean flag)
    {
        int output = 0;
        Random rand = new Random();

        output = flag ? rand.nextInt(8) : rand.nextInt();

        return output;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates pseudo random number
    Input:  reference number
    Output: Key
    */
    public int pseudoRandom(long num)
    {
        int output = 0;
        Random rand = new Random(num);

        output = rand.nextInt() * rand.nextInt(4) - rand.nextInt(150);

        return output;
    }
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
    Output: Key
    */
    public int fibonacci(int num)
    {
        int output = 0;
        double partial = 0;

        partial = (1 + Math.sqrt(5)) / 2;
        output = (int) Math.round(Math.pow(partial, num) / Math.sqrt(5));

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
        int output = 0;

        output = (int)((Math.log10(num & -num)) / Math.log10(2)) + 1;

        return output;
    }
}
