import javax.swing.*;
import java.io.*;

public class FileHandler
{
    File file;
    String path;
    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the handler constructor
    Input:  None
    Output: None
    */
    public FileHandler(File newFile)
    {
        this.file = newFile;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function opens the file and converts it to binary
    Input:  None
    Output: file bytes
    */
    public byte[] loadFile(String newPath)
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

            this.path = newPath;

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
    Input:  None
    Output: New File
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
}
