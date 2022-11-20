package gui;
import product.Item;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import product.Container;

public class CreateContainerDialog extends CreationDialog<Container> {
    protected CreateContainerDialog(MainWin parent){
        super(parent,"Container");

        this.names        = new JTextField(30);  
        this.descriptions = new JTextField(30);
        this.maxScoops    = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        creationDialog();
    }

    public Container getChoice(){
        return new Container(
                        names.getText(), 
                        descriptions.getText(), 
                        spinnerToInt(maxScoops)
        );
    }
    
    public void creationDialog(){
        JLabel name        = new JLabel("Name");
        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JLabel maxScoop       = new JLabel("<HTML><br/>Max Scoops</HTML>");

        
        Object[] objects = {
                        name, names, 
                        description, descriptions,
                        maxScoop, maxScoops   
        };

        int button = JOptionPane.showConfirmDialog(
            parent,
            objects,
            ("New " + type),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(getClass().getResource("resources/Container.png"))
        );

        if((button == JOptionPane.OK_OPTION)){
            Object[] choices = {"Name: " + names.getText(),
                                "Description: " + descriptions.getText(),
                                "Max Scoops: " + spinnerToInt(maxScoops)};
            super.confirmChoice(choices);
        }
    }

    protected int spinnerToInt(JSpinner spinner){ 
        return ((SpinnerNumberModel)spinner.getModel()).getNumber().intValue();
    }
    
    protected JTextField names;
    protected JTextField descriptions;
    protected JSpinner maxScoops;
}
