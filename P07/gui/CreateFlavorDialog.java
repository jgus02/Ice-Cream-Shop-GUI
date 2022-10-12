package gui;
import product.Item;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;

import product.Item;

public abstract class CreateFlavorDialog<T> implements CreationDialog<T>{ 
    public boolean success;                      

    protected CreateFlavorDialog(MainWin parent, String identifier){
        this.identifier = identifier;

        this.names        = new JTextField(30);  
        this.descriptions = new JTextField(30);
        this.prices       = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        this.costs        = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        creationDialog();
    }

    public abstract T getChoice();

    protected int spinnerToInt(JSpinner spinner){ //i hate java
        return ((SpinnerNumberModel)spinner.getModel()).getNumber().intValue();
    }

    public void creationDialog(){
        JLabel name        = new JLabel("Name");                                        
        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JLabel price       = new JLabel("<HTML><br/>Price</HTML>");
        JLabel cost        = new JLabel("<HTML><br/>Cost</HTML>");
        
        Object[] objects = {
                        name,        names, 
                        description, descriptions,
                        price,       prices, 
                        cost,        costs
        };

        int button = JOptionPane.showConfirmDialog(
            parent,
            objects,
            ("New " + identifier),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if((button == JOptionPane.OK_OPTION)){
            confirmChoice();
        }
    }

    /*private boolean validData(){
        //if(prices.getValue() < costs.getValue()
        return 1;
    }*/

    public void confirmChoice(){
        Object[] choices = {
            "Confirm " + identifier + "?",
            "Flavor: " + names.getText(),
            "Description: " + descriptions.getText(),
            "Price: " + spinnerToInt(prices),
            "Cost: " + spinnerToInt(costs)
            };
        int button = JOptionPane.showConfirmDialog(
            parent,
            choices,
            ("Confirm " + identifier + "?"),
            JOptionPane.YES_NO_OPTION
        );

        if(button == JOptionPane.NO_OPTION){
            creationDialog();
        }
        else{
            success = true;
        }
    }
    private String identifier;
    private MainWin parent;
    
    protected JTextField names;
    protected JTextField descriptions;
    protected JSpinner prices;
    protected JSpinner costs;
}