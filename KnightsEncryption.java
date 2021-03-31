import java.util.List;

public class KnightsEncryption extends Encryption
{
    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the encryption constructor
    Input:  Key series, file binary
    Output: None
    */
    public KnightsEncryption(List<Integer> newKeys, byte[] newBin)
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
        int Skey;

        for(int i = 0; i < this.bin.length; i++)
        {
            Skey = this.key.get(i % this.key.size());
            this.bin[i] = (byte) (this.bin[i] ^ Skey);
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
