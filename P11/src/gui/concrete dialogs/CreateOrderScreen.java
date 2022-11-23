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

import person.Customer;
import person.Person;

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
        main.display.add(this, "1");

        //'create serving' jpanel -- rightmost -- servInterface
        JPanel servInterface = new JPanel(new GridBagLayout());
        JComboBox contSelect = new JComboBox(emporium.cont());

        JButton createServB = new JButton("New Serving");
        createServB.setToolTipText("Select a container");
        createServB.addActionListener(event -> openServScreen((Container)contSelect.getSelectedItem()));
        if(emporium.cont().length == 0){
            createServB.setToolTipText("Create a container first!");
        }
            //UI management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        servInterface.add(new JLabel("<HTML><font size =+2>Select Container</font></HTML>"), gbc);
        gbc.weighty = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,10,0);
        servInterface.add(contSelect, gbc);
        gbc.weighty = 1;
        gbc.gridy = 2;
        servInterface.add(createServB, gbc);
    
        JComboBox custSelect = new JComboBox(emporium.cust()); //belongs in main panel
        //'FAVORITE SCOOP' jpanel favInterface -- top left panel
        JPanel favInterface = new JPanel(new GridBagLayout());
        JComboBox favSelect;
        JButton addFavB = new JButton("Add Favorite");

        //wont let me initialize this before I initialize it! COMPILER PLEASE STOP
        this.customer = (Customer)custSelect.getSelectedItem();
        if(emporium.favoriteServings(customer) == null){
            favSelect = new JComboBox<Serving>();
            addFavB.setEnabled(false);
            addFavB.setToolTipText("You don't have any favorites yet!");
            favSelect.setEnabled(false);
        }
        else {
            favSelect = new JComboBox(emporium.favoriteServings(customer));
        }

        addFavB.setToolTipText("Select your favorite serving from the\ndropdown and add it to your order.");
        addFavB.addActionListener(event -> addServing((Serving)favSelect.getSelectedItem()));
        custSelect.addActionListener(event -> updateFavSelect(favSelect,addFavB,(Customer)custSelect.getSelectedItem()));
            //UI management
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,10,0,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        favInterface.add(favSelect, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        favInterface.add(addFavB, gbc);

        //main panel -- bottom left
        servList = new JList();
        servList.setModel(new DefaultListModel());
        JScrollPane servScrollPane = new JScrollPane(servList);
        servList.setLayoutOrientation(JList.VERTICAL);
        servList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton placeOrderB = new JButton("Place Order");
        JButton cancelB = new JButton ("Cancel Order");
        placeOrderB.addActionListener(event -> onPlaceOrderClick());
        cancelB.addActionListener(event -> onCancelClick()); 
            //UI management
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,10,0,10); //top, left, bottom, right
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("<HTML><font size =+2>Your Servings - $" + price + "</font></HTML>"),gbc);

        gbc.weighty = 4;
        gbc.weightx = 4;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        add(servScrollPane,gbc);

        gbc.insets = new Insets(0,0,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0;
        gbc.weighty = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(servInterface, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(placeOrderB, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,0,10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(new JLabel("<HTML><font size =+1>Select Customer</font></HTML>"), gbc);

        gbc.weightx = 0;
        gbc.insets = new Insets(5,5,0,5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cancelB, gbc);

        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(custSelect, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,0,0,5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(favInterface, gbc);
        
        setVisible(true);
    }

    public void updateFavSelect(JComboBox favSelect, JButton addFavB, Customer customer){
        if(this.customer == customer){
            return;
        }
        this.customer = customer;
        Object[] favServ = emporium.favoriteServings(customer);
        ((DefaultListModel)servList.getModel()).clear();
        updatePrice(-price);
        if(favServ == null){
            favSelect.removeAllItems();
            addFavB.setEnabled(false);
            addFavB.setToolTipText("You don't have any favorites yet!");
            favSelect.setEnabled(false);
        }
        else {
            favSelect.removeAllItems();
            for(int i = 0; i<favServ.length;++i){
                favSelect.addItem(favServ[i]);
            }
            favSelect.setEnabled(true);
            addFavB.setToolTipText("Add Favorite");
            addFavB.setEnabled(true);
        }
        favSelect.revalidate();
        favSelect.repaint();
    }

    public void updatePrice(int amt){
        price += amt;
        ((JLabel)getComponent(0)).setText("<HTML><font size =+2>Your Servings - $" + price + "</font></HTML>");
    }

    //buttons
    public void addServing(Serving serv){
        ((DefaultListModel)servList.getModel()).addElement(serv);
        updatePrice(serv.price());
    }

    public void onPlaceOrderClick(){
        Order order = new Order(customer);
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

    //screens
    public void updateScreen(Screen screen) {
        if(screen == Screen.CONTAINERS){
            ((JComboBox)((JPanel)getComponent(2)).getComponent(1)).addItem(emporium.cont()[emporium.cont().length - 1]);
        }
        if(screen == Screen.CUSTOMERS) {
            ((JComboBox)getComponent(6)).addItem(emporium.cust()[emporium.cust().length - 1]);
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

    //fields
    private int price;
    private Customer customer = new Customer("ARE YOU", "SERIOUS");
    MainWin main;
    JList<Serving> servList;
    Emporium emporium;
}