import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler
{
    File file;
    String path;
    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the handler constructor
    Input:  File object
    Output: None
    */
    public FileHandler(File newFile)
    {
        this.file = newFile;
        this.path = newFile.getPath();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function opens the file and converts it to binary
    Input:  None
    Output: file bytes
    */
    public byte[] loadFile()
    {
        byte[] data = null;

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            data  = new byte[(int) file.length()];

            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(data,0,data.length);


            fileInputStream.close();
            bufferedInputStream.close();

        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "SuperCryptor encountered critical error while trying to open the file", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return data;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function converts byte array to File and saves it
    Input:  binary
    Output: None
    */
    public void saveFile(byte[] binary)
    {
        try
        {
            File outFile = new File(this.file.getParentFile(), "SuperCryptor" + this.file.getName());
            FileOutputStream out= new FileOutputStream(outFile);

            out.write(binary);
            out.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "SuperCryptor encountered critical error while trying to save the file", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function saves encrypted file with decryption info
    Input:  binary, key and encryption type
    Output: None
    */
    public void saveFile(byte[] binary, int encryptionType, List<Integer> keys)
    {
        List<Integer> key = new ArrayList<>();
        key.add(encryptionType);
        key.addAll(keys);

        String tmp = Globals.DELIMITER + key.toString().replaceAll(Globals.REGEX_FILTER, "") + Globals.DELIMITER;
        byte[] output = new byte[tmp.getBytes().length + binary.length];

        System.arraycopy(tmp.getBytes(), 0, output, 0, tmp.getBytes().length);
        System.arraycopy(binary, 0, output, tmp.getBytes().length, binary.length);

        saveFile(output);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function loads key and encryption num from file
    Input:  file binary
    Output: key series and encryption type (Head of list is encryption type)
    */
    public List<Integer> getKeyFromFile()
    {
        List<Integer> res = new ArrayList<>();
        byte[] binary = loadFile();

        if(binary[0] != Globals.DELIMITER)
        {
            return null;
        }

        for(int i = 1; i < binary.length && binary[i] != Globals.DELIMITER; i++)
        {
            if ((int)binary[i] != 44)
            {
                res.add((int)binary[i] - '0');
            }
        }

        return res;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function removes key from file
    Input:  binary data from file
    Output: Cleaned file
    */
    public byte[] removeKeyFromFile(byte[] binary)
    {
        int idx = 1;

        while(idx < binary.length && binary[idx] != Globals.DELIMITER)
        {
            idx++;
        }

        return Arrays.copyOfRange(binary, idx + 1, binary.length);

    }
}
