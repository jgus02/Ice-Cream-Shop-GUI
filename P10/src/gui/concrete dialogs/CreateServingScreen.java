package gui;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import product.Order;
import product.Container;
import product.Serving;
import product.Scoop;
import product.MixInAmount;
import product.MixInFlavor;
import product.MixIn;
import product.IceCreamFlavor;
import emporium.Emporium;

/*
first dialog: Create new scoops, or select and deselect from previous creations.
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

public class CreateServingScreen extends JPanel { 
    public CreateServingScreen(CreateOrderScreen parent, Container cont){
        this.emporium = parent.emporium;
        scoopArr = new ArrayList<Scoop>();
        serving = new Serving(cont);
        setLayout(new GridBagLayout());

        scoops = new JList();
        scoops.setModel(new DefaultListModel());
        JScrollPane scoopScrollPane = new JScrollPane(scoops);
        scoops.setLayoutOrientation(JList.VERTICAL);

        topInterface = new JPanel(new GridBagLayout());
        JSpinner topAmount = new JSpinner(new SpinnerListModel(MixInAmount.values()));
        JComboBox topFlavor = new JComboBox(emporium.mxf());

        JList toppings = new JList();
        toppings.setModel(new DefaultListModel());
        JScrollPane toppingScrollPane = new JScrollPane(toppings);
        JButton addTopB = new JButton("Add To Container");
        addTopB.addActionListener(event -> onAddToppingClick((MixInFlavor)topFlavor.getSelectedItem(), (MixInAmount)topAmount.getValue(), toppings));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,0,5,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        topInterface.add(topFlavor, gbc);
        topInterface.add(new JLabel(""), gbc);
        gbc.gridy = 1;
        topInterface.add(topAmount, gbc);
        gbc.gridy = 2;
        topInterface.add(addTopB, gbc);
        gbc.gridy = 3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        topInterface.add(toppingScrollPane, gbc);



        JButton newScoopB = new JButton("New Scoop");
        newScoopB.addActionListener(event -> onNewScoopClick(parent));
        JButton addServingB = new JButton("Add to Order");
        addServingB.addActionListener(event -> onAddServingClick(parent));
        //JButton cancelB = new JButton("Cancel Serving");

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10,5,10); //top, left, bottom, right
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Container: "+cont.name()),gbc);
        gbc.gridy = 1;
        add(new JLabel("Capacity: "+cont.maxScoops()+" Scoops"),gbc);

        // JPanel buttonPanel = new JPanel();
        // buttonPanel.add(addServingB);
        // buttonPanel.add(cancelB);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(scoopScrollPane,gbc);
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.insets = new Insets(5,10,15,10);
        add(newScoopB, gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Select Toppings"),gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 2;
        add(topInterface, gbc);

        gbc.insets = new Insets(5,10,15,10);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 3;
        gbc.gridx = 1;
        // add(buttonPanel, gbc);
        add(addServingB, gbc);

        //LAYOUT ENDS HERE
        
        setVisible(true);

    }

    public void onAddToppingClick(MixInFlavor flav, MixInAmount amnt, JList toppings){
        MixIn mix = new MixIn(flav, amnt);
        serving.addTopping(mix);
        ((DefaultListModel)toppings.getModel()).addElement(mix);
       
    }

    public void onNewScoopClick(CreateOrderScreen parent) {
        CreateScoopDialog scpDialog = new CreateScoopDialog(parent.main);
        if(scpDialog.success){
            Scoop scp = scpDialog.getChoice();
            serving.addScoop(scp);
            scoopArr.add(scp);
            ((DefaultListModel)scoops.getModel()).addElement(scp);
        }
    }

    public void onAddServingClick(CreateOrderScreen parent) {
        ((DefaultListModel)parent.servList.getModel()).addElement(serving);
        ((CardLayout)parent.main.display.getLayout()).previous(parent.main.display);
        parent.main.display.remove(2);
    }

    public void onCancelClick(CreateOrderScreen parent) {
        parent.main.display.remove(2);
    }

    public void updateScreen(Screen screen) {
        if(screen == Screen.MIX_IN_FLAVORS){
             ((JComboBox)topInterface.getComponent(0)).addItem(emporium.mxf()[emporium.mxf().length - 1]);
        }
        revalidate();
        repaint();
    }
    
    private Serving serving;
    private JList<Scoop> scoops;
    private JPanel topInterface;
    private ArrayList<Scoop> scoopArr;
    private Emporium emporium;
}