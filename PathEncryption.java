import java.util.List;

public class PathEncryption extends Encryption
{
    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the encryption constructor
    Input:  Key series, file binary
    Output: None
    */
    public PathEncryption(List<Integer> newKeys, byte[] newBin)
    {
        super(newKeys, newBin);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function is the encryption function
    Input:  None
    Output: encrypted binary
    */
    public byte[] encrypt()
    {
        int intKey;

        if(this.key.size() == 0)
        {
            return null;
        }

        for(int i = 0; i < this.bin.length; i++)
        {
            intKey = this.key.get(i % this.key.size());

            if(i > 0 && intKey % i == 0)
            {
                this.bin[i] = (byte)~this.bin[i];
            }
        }

        return this.bin;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function is the decryption function
    Input:  None
    Output: decrypted binary
    */
    public byte[] decrypt()
    {
        return encrypt();
    }
}
