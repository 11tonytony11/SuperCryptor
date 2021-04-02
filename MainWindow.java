import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame
{
    // Text Fields
    final private JPasswordField fieldKey  = new JPasswordField(Globals.FIELD_LEN);
    final private JTextField     fieldPath = new JTextField(Globals.FIELD_LEN);

    // Menus
    final private JComboBox<String> comboEnc = new JComboBox<>(Globals.ENCRYPTIONS);
    final private JComboBox<String> comboGen = new JComboBox<>(Globals.GENERATORS);

    // Buttons
    final JButton btnEncrypt = new JButton("Encrypt");
    final JButton btnDecrypt = new JButton("Decrypt");
    final JButton btnBrowse  = new JButton("Browse");

    // Labels
    final JLabel labelEnc  = new JLabel("Encryption Type: ");
    final JLabel labelKey  = new JLabel("Encryption key(s): ");
    final JLabel labelGen  = new JLabel("Key Generator: ");
    final JLabel labelPath = new JLabel("Choose a file: ");

    // Check Box
    final JCheckBox checkPwd = new JCheckBox("Show Key(s)");

    // File handler, encryption and recommender
    AutoRecommender recommender;
    FileHandler fileHandler;
    Encryption enc;

    private List<Integer> keys;

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
        addEncryptEvent();
        addDecryptEvent();
        addBrowseEvent();
        addComboEvent();

        // Hide generators
        comboGen.setVisible(false);
        labelGen.setVisible(false);

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
        addComponent(checkPwd,  2, 3, 10, 10, GridBagConstraints.HORIZONTAL);

        fieldKey.putClientProperty("JPasswordField.cutCopyAllowed",true);
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
    This function binds file browse button
    Input:  None
    Output: None
    */
    private void addBrowseEvent()
    {
        btnBrowse.addActionListener(e ->
        {
            JFileChooser browser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
            int returnVal = browser.showOpenDialog(btnBrowse);

            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                fieldPath.setText(browser.getSelectedFile().getPath());
                fileHandler = new FileHandler(browser.getSelectedFile());
                keys = fileHandler.getKeyFromFile();
                if(keys != null)
                {
                    comboEnc.setSelectedIndex(keys.get(0));
                    this.keys.remove(0);
                    fieldKey.setText(keys.toString().replaceAll(Globals.REGEX_FILTER, ""));
                    JOptionPane.showMessageDialog(null, "Encryption Detected - Auto Decryption is enabled", "Auto Decryption", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    this.recommender = new AutoRecommender((int) browser.getSelectedFile().length());

                    if(this.recommender.showForm() == JOptionPane.OK_OPTION)
                    {
                        comboEnc.setSelectedIndex(this.recommender.recommend(1, 0));
                    }
                }
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
        });
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function adds combo boxes events
    Input:  None
    Output: None
    */
    private void addComboEvent()
    {
        comboEnc.addActionListener(e ->
        {
            int idx = comboEnc.getSelectedIndex();
            boolean flag = idx > 2;

            comboGen.setVisible(flag);
            labelGen.setVisible(flag);

            pack();
        });
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function calls the encryption
    Input:  None
    Output: None
    */
    private void addEncryptEvent()
    {
        btnEncrypt.addActionListener(e ->
        {
            List<Integer> keys = generateKeys();

            if(keys == null)
                return;

            switch (comboEnc.getSelectedIndex())
            {
                case 0:
                    this.enc = new PathEncryption(keys, this.fileHandler.loadFile());
                    break;
                case 1:
                    this.enc = new KnightsEncryption(keys, this.fileHandler.loadFile());
                    break;
                case 2:
                    this.enc = new SudokuEncryption(keys, this.fileHandler.loadFile());
                    break;
                case 3:
                    this.enc = new ShuffleEncryption(keys, this.fileHandler.loadFile());
                    break;
                case 4:
                    this.enc = new XorEncryption(keys, this.fileHandler.loadFile());
                    break;
            }

            // Encrypt & Save
            fileHandler.saveFile(this.enc.encrypt(), comboEnc.getSelectedIndex(), keys);

            // Give key to user and notify him the encryption is done
            fieldKey.setText(keys.toString().replaceAll(Globals.REGEX_FILTER, ""));
            JOptionPane.showMessageDialog(null, "Done", "Done!", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function calls the decryption
    Input:  None
    Output: None
    */
    private void addDecryptEvent()
    {
        btnDecrypt.addActionListener(e ->
        {
            if(this.keys == null)
            {
                String keyInput = new String(fieldKey.getPassword());
                this.keys = getKeysFromField(keyInput);
            }

            byte[] binary = fileHandler.removeKeyFromFile(this.fileHandler.loadFile());

            if(this.keys == null)
            {
                return;
            }

            switch (comboEnc.getSelectedIndex())
            {
                case 0:
                    this.enc = new PathEncryption(keys, binary);
                    break;
                case 1:
                    this.enc = new KnightsEncryption(keys, binary);
                    break;
                case 2:
                    this.enc = new SudokuEncryption(keys, binary);
                    break;
                case 3:
                    this.enc = new ShuffleEncryption(keys, binary);
                    break;
                case 4:
                    this.enc = new XorEncryption(keys, binary);
                    break;
            }

            fileHandler.saveFile(this.enc.decrypt());
        });
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates key for last two encryptions in the menu
    Input:  None
    Output: None
    */
    public List<Integer> generateKeys()
    {
        int num;
        List<Integer> res = new ArrayList<>();

        switch (comboEnc.getSelectedIndex())
        {
            case 0:
                return MathUtil.generatePathKeys(3);
            case 1:
                return MathUtil.generateKnightKeys();
            case 2:
                return MathUtil.generateSudokuKeys();
            default:
                break;
        }

        num = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter your secret number: ", null));

        switch(comboGen.getSelectedIndex())
        {
            case 0:
                res.add(MathUtil.generateFibonacciKeys(num));
                break;
            case 1:
                res.add(MathUtil.generatePascalKeys(num, num));
                break;
            case 2:
                res.add(MathUtil.generateHanoiTowerKeys(num));
                break;
        }

        return res;
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function generates key from GUI field
    Input:  GUI input
    Output: Keys as list
    */
    public static List<Integer> getKeysFromField(String input)
    {
        List<Integer> keys;

        try
        {
            keys = Arrays.stream(input.split(Globals.KEY_PARSER)).map(Integer::parseInt).collect(Collectors.toList());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Invalid Key", "Error!", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return keys;
    }
}
