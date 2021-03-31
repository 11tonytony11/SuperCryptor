import javax.swing.*;

/*
* Todo:
** Implement Decision Tree
** Connect Decision Tree to GUI
** Final Fixes & Refactoring
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
