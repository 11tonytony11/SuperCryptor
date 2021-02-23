import java.util.List;

public class XorEncryption extends Encryption
{

    public XorEncryption(List<Integer> newKeys, byte[] newBin)
    {
        super(newKeys, newBin);
    }

    @Override
    public byte[] encrypt()
    {
        byte key = (this.key.get(0).byteValue());

        for(int i = 0; i < this.bin.length; i += 2)
        {
            this.bin[i] = (byte)(this.bin[i] ^ key);
        }

        return this.bin;
    }

    @Override
    public byte[] decrypt()
    {
        return encrypt();
    }
}
