import java.util.List;

public class ShuffleEncryption extends Encryption
{
    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the encryption constructor
    Input:  Key series, file binary
    Output: None
    */
    public ShuffleEncryption(List<Integer> newKeys, byte[] newBin)
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
        int key = this.key.get(0) % Byte.SIZE;

        if(key == 4)
            key = 3;

        for(int i = 0; i < this.bin.length; i++)
        {
            this.bin[i] = (byte) (this.bin[i] ^ 1 << key);
            this.bin[i] = (byte) (this.bin[i] ^ 1 << (Byte.SIZE - key));
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
