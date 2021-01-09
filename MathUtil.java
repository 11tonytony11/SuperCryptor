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
    public int pseudoRandom(int num)
    {
        int output = 0;

        return output;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates encryption key with pascal triangle
    Input:  reference number
    Output: Key
    */
    public int pascal(int num)
    {
        int output = 0;

        return output;
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

        return output;
    }
}
