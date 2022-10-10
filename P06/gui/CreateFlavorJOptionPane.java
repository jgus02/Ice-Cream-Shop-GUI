package gui;
import product.Item;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.Item;

public class CreateFlavorJOptionPane{ 
    public boolean success;                      

    protected CreateFlavorJOptionPane(Screen flavorType, MainWin parent){
        super();
        this.parent = parent;
        this.flavorType = flavorType;
        switch (flavorType){
            case ICE_CREAM_FLAVORS:
                identifier = "Ice Cream Flavor";
                break;
            case MIX_IN_FLAVORS:
                identifier = "Mix-In Flavor";
                break;
            default:
                throw new IllegalArgumentException("Flavor type not entered.");
        }

        creationDialog("", "");
    }

    public IceCreamFlavor returnIceCream(){
        return new IceCreamFlavor(names.getText(), descriptions.getText(), spinnerToInt(prices), spinnerToInt(costs));
    }

    public MixInFlavor returnMixIn(){
        return new MixInFlavor(names.getText(), descriptions.getText(), spinnerToInt(prices), spinnerToInt(costs));
    }

    private int spinnerToInt(JSpinner spinner){ //i hate java
        return ((SpinnerNumberModel)spinner.getModel()).getNumber().intValue();
    }


    private void creationDialog(String nameInput, String descInput){
        success = false;
        JLabel name        = new JLabel("Name");                                        
        names              = new JTextField(nameInput, 30);                             

        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        descriptions       = new JTextField(descInput, 30);

        JLabel price       = new JLabel("<HTML><br/>Price</HTML>");
        prices             = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));

        JLabel cost        = new JLabel("<HTML><br/>Cost</HTML>");
        costs              = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));

        Object[] objects = {name, names, description, descriptions, price, prices, cost, costs};

        int button = JOptionPane.showConfirmDialog(
            parent,
            objects,
            ("New " + identifier),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        
        if((button == JOptionPane.OK_OPTION)){
            System.out.print("why");
            confirmChoice();
        }
    }

    /*private boolean validData(){
        //if(prices.getValue() < costs.getValue()
        return 1;
    }*/ //stop validating data and do your homework

    private void confirmChoice(){
        Object[] choices = { //TODO: there is a new line. after the strings. i don't want there to be
            "Confirm Choices?",
            "\nFlavor: ", names.getText(),
            "\nDescription: ", descriptions.getText(),
            "\nPrice: ", prices.getValue(),
            "\nCost: ", costs.getValue()
            };
        int button = JOptionPane.showConfirmDialog(
            parent,
            choices,
            ("Confirm " + identifier + "?"),
            JOptionPane.YES_NO_OPTION
        );

        if(button == JOptionPane.NO_OPTION){
            creationDialog(
                        names.getText(),
                        descriptions.getText()
            );
        }
        else{
            success = true;
        }
    }

    private final static int WIDTH = 150;
    private final static int HEIGHT = 450;

    private String identifier;
    private Screen flavorType;
    private MainWin parent;
    
    private JTextField names;
    private JTextField descriptions;
    private JSpinner prices;
    private JSpinner costs;
}