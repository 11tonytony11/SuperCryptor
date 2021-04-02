import javax.swing.*;
import java.awt.*;

public class AutoRecommender extends Component
{

    int[] tree;
    int[] features;

    JComboBox<String> cmbUse = new JComboBox<>(Globals.USAGE);
    JComboBox<String> cmbPC = new JComboBox<>(Globals.PC);

    //-----------------------------------------------------------------------------------------------------------
    /*
    This is the auto recommender constructor
    Input:  file size
    Output: None
    */
    public AutoRecommender(int size)
    {
        this.tree = new int[]  {0,1,500,1,1,-1,-4,-5,0,-2,0,-2,-3,-2, 0};
        this.features = new int[] {0, size, 0};

        AddInputEvents();
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function shows popup form which gets relevant data from user in order to encrypt
    Input:  None
    Output: form exit code - OK / Cancel
    */
    public int showForm()
    {
        Object[] inputFields = {"Usage Type: ", cmbUse, "PC Strength: ", cmbPC};

        return JOptionPane.showConfirmDialog(this, inputFields, "Auto Recommender", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function iterates through the decision tree (heap)
    Input:  tree index and features index
    Output: index of the recommended encryption
    */
    public int recommend(int tindex, int findex)
    {
        if(features[0] == 1 && findex == 1)
        {
            findex = 2;
        }

        // If done
        if(tree[tindex] <= 0)
        {
            return tree[tindex] * -1;
        }

        if(features[findex] >= tree[tindex])
        {
            return recommend(2 * tindex + 1, findex + 1);
        }
        else
        {
            return recommend(2 * tindex, findex + 1);
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    /*
    This function appends events to the popup form
    Input:  None
    Output: None
    */
    private void AddInputEvents()
    {
        cmbUse.addActionListener( e ->
                this.features[0] = cmbUse.getSelectedIndex());
        cmbPC.addActionListener( e ->
                this.features[2] = cmbPC.getSelectedIndex());
    }
}

/*
* Enterprise - File size - PC power
* Home - PC Power
* */
