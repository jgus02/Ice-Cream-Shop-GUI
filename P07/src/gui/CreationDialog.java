package gui;

import javax.swing.JOptionPane;
import product.Item;

public abstract class CreationDialog<T>{ 

    public boolean success = true;
    protected String type;
    protected MainWin parent;

    protected CreationDialog(MainWin parent, String type){
        this.type = type;
        this.parent = parent;
    }

    protected void confirmChoice(Object[] choices){
        Object[] message = {
            "Is this " + type + " correct?",
            choices
            };
        int button = JOptionPane.showConfirmDialog(
                            parent,
                            message,
                            ("Confirm " + type),
                            JOptionPane.YES_NO_OPTION
                    );

        if(button == JOptionPane.NO_OPTION){
            creationDialog();
        }
        else{
            try { 
                getChoice();
            } catch(Item.IllegalIceCreamException e) { //why do you still throw exception. works tho
                int b;
                b = JOptionPane.showConfirmDialog(
                        parent,
                        new String("Error Creating " + type + ":\n" + e.getMessage() + "Try again?"),
                        "Creation Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE
                    );
                if(b == JOptionPane.YES_OPTION){
                    creationDialog();
                }
            }
        }
    }

    abstract void creationDialog();

    public abstract T getChoice();
    
}