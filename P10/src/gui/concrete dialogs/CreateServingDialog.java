package gui;
import javax.swing.JList;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

import product.Serving;
import product.Scoop;
import product.Container;
import product.MixInAmount;
import product.MixInFlavor;
import product.MixIn;
import product.IceCreamFlavor;
import emporium.Emporium;

/*
first dialog: Create new servings, or select and deselect from previous creations.
open serving dialog.
serving dialog: scoop dialog, create new scoops, select/deselect list. 
                new extra dialog after to add 'toppings'
                
*/
/*
    1st step: Pick a container, or create new
    2nd     : Create your scoop
    3rd     : Select your toppings
            Confirm?
                YES: return serving to order arraylist and clear fields
                        make another?
                                YES: step 1 of serving dialog
                                NO: close 
*/

public class CreateServingDialog extends CreationDialog<Serving>{ 
    public CreateServingDialog(MainWin parent, Emporium emporium){
        super(parent, "Serving");
        this.emporium = emporium;

        Container cont;
        scoopArr = new ArrayList<>();


        creationDialog();
    }

    public void creationDialog(){ //TODO: remake as horizontal orientation?
        // JLabel iceCreamFlavor = new JLabel("Ice Cream Flavor");
        // JLabel mixInFlavor = new JLabel("Mix-Ins");

        // int button = JOptionPane.showConfirmDialog(
        //     super.parent,
        //     objects,
        //     ("New Scoop"),
        //     JOptionPane.OK_CANCEL_OPTION,
        //     JOptionPane.QUESTION_MESSAGE,
        //     new ImageIcon(getClass().getResource("resources/Scoop.png"))
        // );


        // if((button == JOptionPane.OK_OPTION) && !mixInFlavors.isSelectionEmpty()){
        //     //occurs when no mix ins are selected OR no mix ins have been created
        //     getMixInAmounts();
        // }
        // else if(button == JOptionPane.OK_OPTION){
        //     Object[] choices = {"Flavor: " + iceCreamFlavors.getModel().getSelectedItem().toString()};
        //     super.confirmChoice(choices);
        // }
    }

    public Serving getChoice(){
        // IceCreamFlavor flavor = (IceCreamFlavor)(iceCreamFlavors.getModel().getSelectedItem());
        // Scoop scoop = new Scoop(flavor);

        // if(!mixInFlavors.isSelectionEmpty()){
        //     int i = 1;
        //     for(MixInFlavor currMixIn: new ArrayList<MixInFlavor>(mixInFlavors.getSelectedValuesList())){ 
        //         scoop.addMixin(new MixIn(currMixIn, 
        //             (MixInAmount)(((JSpinner)mixInAmountTracker.get(i)).getModel().getValue()))); //IT WORKS AGAIN
        //         i+=2;                                                                         //OLD CODE IN GIT/1325tmp/...
        //     }
        // }

        return null;
    }

    private JComboBox iceCreamFlavors;
    private JList scoops;
    private ArrayList<Scoop> scoopArr;
    private Emporium emporium;
}