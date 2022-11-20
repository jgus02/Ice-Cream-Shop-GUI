package gui;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

import product.Order;
import product.Serving;
import product.Scoop;
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

public class CreateOrderScreen extends JPanel { 
    public CreateOrderScreen(Emporium emporium){
        setLayout(new GridBagLayout());
        this.emporium = emporium;
        servList = new ArrayList<>();
        

        creationDialog();
    }

    public void creationDialog(){ //TODO: remake as horizontal orientation?
        JLabel orderLabel = new JLabel("<HTML><font size=+3><b>Your Order</b></font></HTML>");
        JLabel servLabel = new JLabel("Servings");

        //CreateServingDialog servDialog = new CreateServingDialog();
        //while(servDialog.getChoice() != null){

    }

    @SuppressWarnings("unchecked") private void getMixInAmounts(){
        // mixInAmountTracker = new ArrayList<>();
        // for(MixInFlavor currMixIn: new ArrayList<MixInFlavor>(mixInFlavors.getSelectedValuesList())){
        //     mixInAmountTracker.add(new JLabel(currMixIn.name()));
        //     mixInAmountTracker.add(new JSpinner(new SpinnerListModel(MixInAmount.values())));
        // }

        // int button = JOptionPane.showConfirmDialog(
        //     super.parent,
        //     mixInAmountTracker.toArray(),
        //     ("Select Amount"),
        //     JOptionPane.OK_CANCEL_OPTION,
        //     JOptionPane.QUESTION_MESSAGE,
        //     new ImageIcon(getClass().getResource("resources/Mix-In Flavor.png"))
        // );

        // if(button == JOptionPane.OK_OPTION){
        //     super.success = true; //TODO: make confirmation dialog for MixInAmounts
        // }
        // else{
        //     creationDialog();
        // }
    }

    public Order getChoice(){
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

    private JList mixInFlavors;
    private ArrayList<Serving> servList;
    private JList<Serving> servSelect;
    private Emporium emporium;
}