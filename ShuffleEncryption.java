import java.util.List;

public class ShuffleEncryption extends Encryption
{

    public ShuffleEncryption(List<Integer> newKeys, byte[] newBin)
    {
        super(newKeys, newBin);
    }

    @Override
    public byte[] encrypt()
    {
        int key = this.key.get(0) % this.bin.length;

        while (key-- > 0)
        {
            byte tmp = this.bin[0];
            for (int i = 1; i < this.bin.length; i++)
                this.bin[i - 1] = this.bin[i];
            this.bin[this.bin.length - 1] = tmp;
        }

        return this.bin;
    }

    @Override
    public byte[] decrypt()
    {
        int key = this.key.get(0) % this.bin.length;
        key = this.bin.length - key;

        while (key-- > 0)
        {
            byte tmp = this.bin[0];
            for (int i = 1; i < this.bin.length; i++)
                this.bin[i - 1] = this.bin[i];
            this.bin[this.bin.length - 1] = tmp;
        }

        return this.bin;
    }
}
