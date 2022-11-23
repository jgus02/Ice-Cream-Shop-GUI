package gui;
import product.Item;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import person.Customer;

public class CreateCustomerDialog extends CreationDialog<Customer>{
    protected CreateCustomerDialog(MainWin parent){
        super(parent,"Customer");

        this.names        = new JTextField(30);  
        this.phones       = new JTextField(12);
        creationDialog();
    }

    public Customer getChoice(){
        return new Customer(
                        names.getText(), 
                        phones.getText()
        );
    }
    
    public void creationDialog(){
        JLabel name        = new JLabel("Name");
        JLabel phone       = new JLabel("<HTML><br/>Phone # (XXX-XXX-XXXX)</HTML>");

        
        Object[] objects = {
                        name, names, 
                        phone, phones
        };

        int button = JOptionPane.showConfirmDialog(
            parent,
            objects,
            ("New " + type),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(getClass().getResource("resources/customer.png"))
        );

        if((button == JOptionPane.OK_OPTION)){
            Object[] choices = {"Name: " + names.getText(),
                                "Phone #: " + phones.getText()};
            super.confirmChoice(choices);
        }
    }

    protected JTextField names;
    protected JTextField phones;
}
