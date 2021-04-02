import java.util.List;

abstract class Encryption
{
    protected List<Integer> key;
    protected byte[] bin;

    abstract public byte[] encrypt();
    abstract public byte[] decrypt();

    public Encryption(List<Integer> newKeys, byte[] newBin)
    {
        this.key = newKeys;
        this.bin = newBin;
    }
}
