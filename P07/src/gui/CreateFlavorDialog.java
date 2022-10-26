package gui;
import product.Item;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import product.Item;

public abstract class CreateFlavorDialog<T> extends CreationDialog<T>{
    protected CreateFlavorDialog(MainWin parent, String type){
        super(parent,type);

        this.names        = new JTextField(30);  
        this.descriptions = new JTextField(30);
        this.prices       = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        this.costs        = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        creationDialog();
    }

    public abstract T getChoice();
    
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
            ("New " + type),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(getClass().getResource("resources/" + type + ".png"))
        );

        if((button == JOptionPane.OK_OPTION)){
            Object[] choices = {"Flavor: " + names.getText(),
                                "Description: " + descriptions.getText(),
                                "Price: " + spinnerToInt(prices),
                                "Cost: " + spinnerToInt(costs)};
            super.confirmChoice(choices);
        }
    }

    protected int spinnerToInt(JSpinner spinner){ //i hate java
        return ((SpinnerNumberModel)spinner.getModel()).getNumber().intValue();
    }
    
    protected JTextField names;
    protected JTextField descriptions;
    protected JSpinner prices;
    protected JSpinner costs;
}
