import javax.swing.*;
import java.awt.*;

public class AutoRecommender extends Component
{

    int[] tree;
    int[] features;

    JComboBox<String> cmbUse = new JComboBox<>(Globals.USAGE);
    JComboBox<String> cmbPC = new JComboBox<>(Globals.PC);

    public AutoRecommender(int size)
    {
        this.tree = new int[]  {0,1,500,1,1,-1,-4,-5,0,-2,0,-2,-3,-2, 0};
        this.features = new int[] {0, size, 0};

        AddInputEvents();
    }

    public int showForm()
    {
        Object[] inputFields = {"Usage Type: ", cmbUse, "PC Strength: ", cmbPC};

        return JOptionPane.showConfirmDialog(this, inputFields, "Auto Recommender", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

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

    private void AddInputEvents()
    {
        cmbUse.addActionListener( e ->
                this.features[0] = cmbUse.getSelectedIndex());
        cmbPC.addActionListener( e ->
                this.features[2] = cmbPC.getSelectedIndex());
    }
}

/*
* usage - from user
* File Size - from code
* PC Power - from user
*
*
*
* Path - Enterprise - small file - slow pc
* Knights - -2
* Sudoku - Enterprise - small file - good pc
* Shuffle - Home - good pc
* Half Xor - Home - slow pc
{0,1,0,1,0,0,-5,-4,0,0,0,0,0,0};
* */
