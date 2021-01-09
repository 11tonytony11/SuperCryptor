import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Handler
{
    //Encryption encryption;

    //Recommender recommender;
    MathUtil math;
    byte[] binary;
    String path;
    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the handler constructor
    Input:  File path
    Output: None
    */
    public Handler(String newPath)
    {
        math = new MathUtil();
        this.path = newPath;
        loadFile();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function opens the file and converts it to binary
    Input:  None
    Output: None
    */
    public void loadFile()
    {
        try
        {
            this.binary = Files.readAllBytes(Paths.get(this.path));
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "SuperCryptor encountered critical error while trying to open the file", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function converts byte array to File and saves it
    Input:  None
    Output: New File
    */
    public void saveFile()
    {
        try
        {
            Path path = Paths.get(this.path);
            Files.write(path, this.binary);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "SuperCryptor encountered critical error while trying to save the file", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function calls the encryptor to encrypt/decrypt file
    Input:  None
    Output: None
    */
    public void callEncryption(int keyGen, int encryption)
    {
        JOptionPane.showMessageDialog(null, "Not implemented", "Error!", JOptionPane.ERROR_MESSAGE);
        saveFile();
    }
}