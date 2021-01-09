import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame
{
    final String[] Encryptions = { "Byte Shuffle Card", "Bit Shuffle Card", "Byte Rev", "Byte Xor", "Bit Flip"};
    final String[] Generators = { "Fibonacci", "Pascal", "Hanoi", "Pseudo-Random", "Random" };

    final private JPasswordField fieldPwd = new JPasswordField(20);
    final private JTextField pathField    = new JTextField(20);

    final private JComboBox<String> comboEnc = new JComboBox<>(Encryptions);
    final private JComboBox<String> comboGen = new JComboBox<>(Generators);

    public MainWindow()
    {
        super("SuperCryptor");

        // Create labels
        final JLabel labelKey  = new JLabel("Encryption key: ");
        final JLabel labelEnc  = new JLabel("Encryption Type: ");
        final JLabel labelPath = new JLabel("Choose a file: ");
        final JLabel labelGen  = new JLabel("Key Generator: ");

        // Create buttons
        final JButton btnBrowse  = new JButton("Browse");
        final JButton btnEncrypt = new JButton("Encrypt");
        final JButton btnDecrypt = new JButton("Decrypt");

        // Create password checkbox
        final JCheckBox checkPwd = new JCheckBox("Show Key");

        // set up layout and icon
        setLayout(new GridBagLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        // Add first row
        addComponent(labelKey, 0, 0, 30, 10, GridBagConstraints.WEST);
        addComponent(fieldPwd, 1, 0, 30, 10, GridBagConstraints.HORIZONTAL);
        addComponent(checkPwd, 2, 0, 30, 10, GridBagConstraints.HORIZONTAL);

        // Add second row
        addComponent(labelPath, 0, 1, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(pathField, 1, 1, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(btnBrowse, 2, 1, 10, 10, GridBagConstraints.HORIZONTAL);

        // Add third row
        addComponent(labelEnc, 0, 2, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(comboEnc, 1, 2, 10, 10, GridBagConstraints.HORIZONTAL);

        // Add fourth row
        addComponent(labelGen, 0, 3, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(comboGen, 1, 3, 10, 10, GridBagConstraints.HORIZONTAL);

        // Add fifth row
        addComponent(btnEncrypt, 0, 4, 15, 20, GridBagConstraints.HORIZONTAL);
        addComponent(btnDecrypt, 1, 4, 15, 20, GridBagConstraints.NONE);

        setFonts();
        pack();
    }

    private void addComponent(Component component, int gridx, int gridy, int paddingTop, int PaddingBottom, int fill)
    {
        // Init property class
        GridBagConstraints constraints = new GridBagConstraints();

        // Init style
        constraints.insets = new Insets(paddingTop, 10, PaddingBottom, 10);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill   = fill;

        // Init location
        constraints.gridx = gridx;
        constraints.gridy = gridy;

        // Add component to GUI
        add(component, constraints);
    }

    private void setFonts()
    {
        //To be implemented
    }
}
