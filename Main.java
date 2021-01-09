import javax.swing.*;

/*
* Todo:
** Implement Handler
** Implement Encryption Algorithms (Threads?)
** Implement Decision Tree
** Finish GUI
** connect backend to GUI
** Bug Fixes
*/
public class Main
{
    public static void main(String[] args)
    {
        // Init JFrame
        MainWindow mainWindow = new MainWindow();

        // Basic settings
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);

        // Show GUI
        mainWindow.setVisible(true);
    }
}
