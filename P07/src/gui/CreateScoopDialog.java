package gui;
import javax.swing.JList;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Arrays;

import product.Scoop;
import product.MixInAmount;
import product.MixInFlavor;
import product.MixIn;
import product.IceCreamFlavor;
import emporium.Emporium;

public class CreateScoopDialog implements CreationDialog<Scoop>{ 
    public boolean success;

    public CreateScoopDialog(MainWin parent, Emporium emporium){
        this.parent = parent;
        this.emporium = emporium;

        iceCreamFlavors = new JComboBox<Object>(emporium.iceCreamFlavors());
        mixInFlavors = new JList(emporium.mixInFlavors());

        creationDialog();
    }

    @SuppressWarnings("unchecked") public void creationDialog(){ //TODO: remake as horizontal orientation?
        JLabel iceCreamFlavor = new JLabel("Ice Cream Flavor");
        JLabel mixInFlavor = new JLabel("Mix-Ins");

        Object[] objects = null;
        if(emporium.mixInFlavors().length > 0){
            Object[] tmp = {iceCreamFlavor, iceCreamFlavors,
                            mixInFlavor,    mixInFlavors};
            objects = tmp;
        }
        else{
            Object[] tmp = {iceCreamFlavor, iceCreamFlavors};
            objects = tmp;
        }

        int button = JOptionPane.showConfirmDialog(
            parent,
            objects,
            ("New Scoop"),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );


        if((button == JOptionPane.OK_OPTION) && !mixInFlavors.isSelectionEmpty()){
            //occurs when no mix ins are selected OR no mix ins have been created
            getMixInAmounts();
        }
        else if(button == JOptionPane.OK_OPTION){
            confirmChoice();
        }
    }

    @SuppressWarnings("unchecked") private void getMixInAmounts(){
        mixInAmountTracker = new ArrayList<>();
        for(MixInFlavor currMixIn: new ArrayList<MixInFlavor>(mixInFlavors.getSelectedValuesList())){
            mixInAmountTracker.add(new JLabel(currMixIn.name()));
            mixInAmountTracker.add(new JSpinner(new SpinnerListModel(MixInAmount.values())));
        }

        int button = JOptionPane.showConfirmDialog(
            parent,
            mixInAmountTracker.toArray(),
            ("Select Amount"),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if(button == JOptionPane.OK_OPTION){
            success = true; //TODO: make confirmation dialog for MixInAmounts
        }
        else{
            creationDialog();
        }
    }

    public void confirmChoice(){
        Object[] choices = { 
            "Confirm Scoop?",
            "Flavor: " + iceCreamFlavors.getModel().getSelectedItem().toString(),
            };
        int button = JOptionPane.showConfirmDialog(
            parent,
            choices,
            ("Confirm"),
            JOptionPane.YES_NO_OPTION
        );

        if(button == JOptionPane.NO_OPTION){
            creationDialog();
        }
        else{
            success = true;
        }
    }

    public Scoop getChoice(){
        IceCreamFlavor flavor = (IceCreamFlavor)(iceCreamFlavors.getModel().getSelectedItem());
        Scoop scoop = new Scoop(flavor);
        if(selectedMixIns.size() > 0)
        {
            MixIn temp = null;
            for(int i = 0; i < (selectedMixIns.size()); i++){
                temp = new MixIn(selectedMixIns.get(i), (MixInAmount)(mixInSpinnerTracker.get(i*2+1).getValue()));
                scoop.addMixin(temp);
            }
        }
        return scoop;
    }

    private boolean showMixInChoices;

    private JComboBox iceCreamFlavors;
    private JList mixInFlavors;
    private JSpinner mixInAmounts;
    private ArrayList<Object> mixInAmountTracker;
    private ArrayList<JSpinner> mixInSpinnerTracker;
    private ArrayList<JLabel> mixInJLabelTracker;
    private ArrayList<MixInFlavor> selectedMixIns;

    private MainWin parent;
    private Emporium emporium;
}