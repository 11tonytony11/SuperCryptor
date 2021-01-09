import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame
{
    // Text Fields
    final private JPasswordField fieldKey  = new JPasswordField(20);
    final private JPasswordField fieldSec  = new JPasswordField(20);
    final private JTextField     fieldPath = new JTextField(20);

    // Menus
    final private JComboBox<String> comboEnc = new JComboBox<>(Globals.Encryptions);
    final private JComboBox<String> comboGen = new JComboBox<>(Globals.Generators);

    // Buttons
    final JButton btnEncrypt = new JButton("Encrypt");
    final JButton btnDecrypt = new JButton("Decrypt");
    final JButton btnBrowse  = new JButton("Browse");

    // Labels
    final JLabel labelEnc  = new JLabel("Encryption Type: ");
    final JLabel labelKey  = new JLabel("Encryption key: ");
    final JLabel labelSec  = new JLabel("Secondary key: ");
    final JLabel labelGen  = new JLabel("Key Generator: ");
    final JLabel labelPath = new JLabel("Choose a file: ");

    // Check Box
    final JCheckBox checkPwd = new JCheckBox("Show Key");

    //Encryption Handler
    Handler handler;

    public MainWindow()
    {
        super("SuperCryptor");

        // set layout and icon
        setLayout(new GridBagLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        // Add components
        addButtons();
        addLabels();
        addFields();
        addMenus();

        // Events
        addKeyToggleEvent();
        addBrowseEvent();
        addKeyEvent();

        pack();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds labels to GUI
    Input:  None
    Output: None
    */
    private void addLabels()
    {
        addComponent(labelPath, 0, 0, 30, 10, GridBagConstraints.HORIZONTAL);
        addComponent(labelEnc,  0, 1, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(labelGen,  0, 2, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(labelSec,  0, 4, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(labelKey,  0, 3, 10, 10, GridBagConstraints.WEST);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds buttons to GUI
    Input:  None
    Output: None
    */
    private void addButtons()
    {
        addComponent(btnEncrypt, 0, 5, 15, 20, GridBagConstraints.HORIZONTAL);
        addComponent(btnBrowse,  2, 0, 30, 10, GridBagConstraints.HORIZONTAL);
        addComponent(btnDecrypt, 1, 5, 15, 20, GridBagConstraints.NONE);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds fields to GUI
    Input:  None
    Output: None
    */
    private void addFields()
    {
        addComponent(fieldPath, 1, 0, 30, 10, GridBagConstraints.HORIZONTAL);
        addComponent(fieldKey,  1, 3, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(fieldSec,  1, 4, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(checkPwd,  2, 3, 10, 10, GridBagConstraints.HORIZONTAL);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds combo menus to GUI
    Input:  None
    Output: None
    */
    private void addMenus()
    {
        addComponent(comboEnc, 1, 1, 10, 10, GridBagConstraints.HORIZONTAL);
        addComponent(comboGen, 1, 2, 10, 10, GridBagConstraints.HORIZONTAL);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds components to GUI layout
    Input:  None
    Output: None
    */
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
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function binds secondary key event
    Input:  None
    Output: None
    */
    private void addKeyEvent()
    {
        comboEnc.addItemListener(arg0 ->
        {
            boolean flag = comboEnc.getSelectedIndex() < 2;
            labelSec.setVisible(flag);
            fieldSec.setVisible(flag);
            pack();
        });
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function binds file browse button
    Input:  None
    Output: None
    */
    private void addBrowseEvent()
    {
        btnBrowse.addActionListener(e ->
        {
            JFileChooser browser = new JFileChooser();
            int returnVal = browser.showOpenDialog(btnBrowse);

            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                fieldPath.setText(browser.getSelectedFile().getPath());
            }
        });
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function binds password show check box
    Input:  None
    Output: None
    */
    private void addKeyToggleEvent()
    {
        checkPwd.addActionListener(e ->
        {
            char echo = checkPwd.isSelected() ? (char)0 : '•';
            fieldKey.setEchoChar(echo);
            fieldSec.setEchoChar(echo);
        });
    }
}
