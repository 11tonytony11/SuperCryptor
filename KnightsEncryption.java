import java.util.List;

public class KnightsEncryption extends Encryption
{

    public KnightsEncryption(List<Integer> newKeys, byte[] newBin)
    {
        super(newKeys, newBin);
    }

    @Override
    public byte[] encrypt()
    {
        int key = 0;
        for(int i = 0; i < this.bin.length; i++)
        {
            key = this.key.get(i % this.key.size());
            this.bin[i] = (byte) (this.bin[i] ^ key);
        }

        return this.bin;
    }

    @Override
    public byte[] decrypt()
    {
        return encrypt();
    }
}
