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
first dialog: Create new servList, or select and deselect from previous creations.
open serving dialog.
serving dialog: scoop dialog, create new scoops, select/deselect list. 
                new extra dialog after to add 'toppings'   
*/

public class CreateOrderScreen extends JPanel { 
    public CreateOrderScreen(MainWin main){
        setLayout(new GridBagLayout());
        this.main = main;
        this.emporium = main.emporium;
        //cl = (CardLayout)main.display.getLayout();
        main.display.add(this, "1");
        
        servList = new JList();
        servList.setModel(new DefaultListModel());
        JScrollPane servScrollPane = new JScrollPane(servList);
        servList.setLayoutOrientation(JList.VERTICAL);
        servList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JComboBox contSelect = new JComboBox(emporium.cont());

        //BUTTONS
        JButton createServB = new JButton("New Serving");
        createServB.setToolTipText("Select a container");
        createServB.addActionListener(event -> openServScreen((Container)contSelect.getSelectedItem()));
        if(emporium.cont().length == 0){
            createServB.setToolTipText("Create a container first!");
        }

        JButton placeOrderB = new JButton("Place Order");
        JButton cancelB = new JButton ("Cancel Order");
        placeOrderB.addActionListener(event -> onPlaceOrderClick());
        cancelB.addActionListener(event -> onCancelClick()); 


        GridBagConstraints gbc = new GridBagConstraints();
        JPanel servInterface = new JPanel(new GridBagLayout());

        gbc.insets = new Insets(50,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        servInterface.add(new JLabel("<HTML><font size =+2>Select Container</font></HTML>"), gbc);
        gbc.weighty = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,10,0);
        servInterface.add(contSelect, gbc);
        gbc.weighty = 0;
        gbc.gridy = 2;
        servInterface.add(createServB, gbc);
        gbc.gridy = 3;
        servInterface.add(new JLabel(""), gbc);
        gbc.gridy = 4;
        servInterface.add(cancelB, gbc);
    
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10,5,10); //top, left, bottom, right
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("<HTML><font size =+2>Your Servings</font></HTML>"),gbc);

        
        gbc.weighty = 10;
        gbc.weightx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(servScrollPane,gbc);

        gbc.insets = new Insets(5,10,5,5);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 1;
        add(servInterface, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,0,10);
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(placeOrderB, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.insets = new Insets(5,10,0,5);
        
        setVisible(true);
    }

    public void onPlaceOrderClick(){
        Order order = new Order();
        servList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        servList.setSelectionInterval(0, servList.getModel().getSize() - 1);
        for(Serving s : servList.getSelectedValuesList()){
            order.addServing(s);
        }
        emporium.addOrder(order);
        onCancelClick();
    }

    public void onCancelClick(){
        if(main.display.getComponentCount() > 2){
            main.display.remove(2);
        }
        main.display.remove(1);
    }

    public void updateScreen(Screen screen) {
        if(screen == Screen.CONTAINERS){
            ((JComboBox)((JPanel)getComponent(2)).getComponent(1)).addItem(emporium.cont()[emporium.cont().length - 1]);
        }
        revalidate();
        repaint();
    }

    public void openServScreen(Container cont){
        //getComponent(4).setEnabled(false);
        CreateServingScreen servScreen = new CreateServingScreen(this, cont);
        main.display.add(servScreen, "2");
        ((CardLayout)main.display.getLayout()).next(main.display);
    }

    public MainWin main;
    public JList<Serving> servList;
    public Emporium emporium;
}