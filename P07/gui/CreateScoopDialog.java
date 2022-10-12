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

    @SuppressWarnings("unchecked") public CreateScoopDialog(MainWin parent, Emporium emporium){
        this.parent = parent;
        this.emporium = emporium;

        iceCreamFlavors = new JComboBox(emporium.iceCreamFlavors());
        mixInFlavors = new JList(emporium.mixInFlavors());

        creationDialog();
    }

    @SuppressWarnings("unchecked") public void creationDialog(){ //TODO: remake as horizontal orientation?
        showMixInChoices = (emporium.mixInFlavors().length == 0) ? false : true; 

        JLabel iceCreamFlavor = new JLabel("Ice Cream Flavor");
        JLabel mixInFlavor = new JLabel("Mix-Ins");
        
        Object[] objects = {iceCreamFlavor,
                            iceCreamFlavors
        };

        if(showMixInChoices){
            Object[] showMixIns = {
                iceCreamFlavor, iceCreamFlavors,
                mixInFlavor,    mixInFlavors
            };
            objects = showMixIns;
        }

        int button = JOptionPane.showConfirmDialog(
            parent,
            objects,
            ("New Scoop"),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );


        if((button == JOptionPane.OK_OPTION) && !(mixInFlavors.isSelectionEmpty() || !showMixInChoices)){
            //occurs when no mix ins are selected OR no mix ins have been created
            getMixInAmounts();
        }
        else if(button == JOptionPane.OK_OPTION){
            confirmChoice();
        }
    }

    @SuppressWarnings("unchecked") private void getMixInAmounts(){
        selectedMixIns = new ArrayList<>(mixInFlavors.getSelectedValuesList());
        mixInSpinnerTracker = new ArrayList<>();
        mixInJLabelTracker = new ArrayList<>();

        for(MixInFlavor currMixIn: selectedMixIns){
            for(int i = 0; i<(selectedMixIns.size()); i++){
                mixInSpinnerTracker.add(new JSpinner(new SpinnerListModel(MixInAmount.values())));
                mixInJLabelTracker.add(new JLabel(currMixIn.name()));
            }
        }

        int button = JOptionPane.showConfirmDialog(
            parent,
            mixInSpinnerTracker.toArray(),
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

    /*public void confirmChoice(Object[] mixInSpinnerTracker, ArrayList<MixInFlavor> selectedMixIns){
        ArrayList<Object> choices = new ArrayList<>({ 
            "Confirm Scoop?",
            "Flavor: " + iceCreamFlavors.getModel().getSelectedItem().toString(),
            "Mix-Ins" 
        };

        Object[] mixInChoiceData = new Object[(selectedMixIns.size())*2];

        for(MixInFlavor currMixIn: selectedMixIns){
            for(int i = 0; i<(selectedMixIns.size()*2); i+=2){
                mixInSpinnerTracker[i]   = new JLabel(currMixIn.name());
                mixInSpinnerTracker[i+1] = new JSpinner(new SpinnerListModel(MixInAmount.values()));
            }
        }

                for(MixInFlavor currMixIn: selectedMixIns){
            for(int i = 0; i<(selectedMixIns.size()*2); i+=2){
                mixInSpinnerTracker[i]   = new JLabel(currMixIn.name());
                mixInSpinnerTracker[i+1] = new JSpinner(new SpinnerListModel(MixInAmount.values()));
            }
        }

        choices.addAll(objects);

        int button = JOptionPane.showConfirmDialog(
            parent,
            choices.toArray(),
            ("Confirm " + identifier + "?"),
            JOptionPane.YES_NO_OPTION
        );

        if(button == JOptionPane.NO_OPTION){
            creationDialog();
        }
        else{
            success = true;
        }
    }*/
    
    public Scoop getChoice(){
        IceCreamFlavor flavor = (IceCreamFlavor)(iceCreamFlavors.getModel().getSelectedItem());
        Scoop scoop = new Scoop(flavor);
        if(showMixInChoices)
        {
            MixIn temp = null;
            for(int i = 0; i < (selectedMixIns.size()); i++){
                temp = new MixIn(selectedMixIns.get(i), (MixInAmount)(mixInSpinnerTracker.get(i).getValue()));
                scoop.addMixin(temp);
            }
        }
        return scoop;
    }

    private boolean showMixInChoices;

    private JComboBox iceCreamFlavors;
    private JList mixInFlavors;
    private JSpinner mixInAmounts;
    private ArrayList<JSpinner> mixInSpinnerTracker;
    private ArrayList<JLabel> mixInJLabelTracker;
    private ArrayList<MixInFlavor> selectedMixIns;

    private MainWin parent;
    private Emporium emporium;
}