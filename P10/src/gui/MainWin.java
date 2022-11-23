package gui;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.Box;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import product.Order;
import product.Container;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;


import emporium.Emporium;

public class MainWin extends JFrame{
    
    public MainWin(String titleBar) throws IOException {
        super(titleBar); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(720, 520);                             
        filename = new File("untitled.scp");
        emporium = new Emporium();

        //
        // ---------- M E N U   B A R ----------
        //
        JMenuBar menuBar = new JMenuBar();

        JMenu     file          = new JMenu    ("File"); 
        JMenuItem save          = new JMenuItem("Save");
        JMenuItem saveAs        = new JMenuItem("Save As...");
        JMenuItem open          = new JMenuItem("Open");
        JMenuItem quit          = new JMenuItem("Quit");
        save        .addActionListener(event -> onSaveClick());
        saveAs      .addActionListener(event -> onSaveAsClick());
        open        .addActionListener(event -> onOpenClick());
        quit        .addActionListener(event -> onQuitClick());
        file    .add(save);
        file    .add(saveAs);
        file    .add(open);
        file    .add(quit);

        JMenu     view          = new JMenu    ("View");
        JMenuItem icfView       = new JMenuItem("Ice Cream Flavors"); 
        JMenuItem mxfView       = new JMenuItem("Mix-In Flavors");
        JMenuItem contView      = new JMenuItem("Containers");
        JMenuItem orderView     = new JMenuItem("Orders");
        JMenuItem custView      = new JMenuItem("Customers");
        icfView     .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        mxfView     .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        //scpView     .addActionListener(event -> onScpViewClick());
        contView    .addActionListener(event -> view(Screen.CONTAINERS));
        orderView   .addActionListener(event -> view(Screen.ORDERS));
        custView    .addActionListener(event -> view(Screen.CUSTOMERS));
        view    .add(icfView);
        view    .add(mxfView);
        //view    .add(scpView);
        view    .add(contView);
        view    .add(orderView);
        view    .add(custView);

        JMenu     create        = new JMenu    ("Create");
        JMenuItem icfCreate     = new JMenuItem("Ice Cream Flavor");
        JMenuItem mxfCreate     = new JMenuItem("Mix-In Flavor");
        JMenuItem contCreate    = new JMenuItem("Container");
        JMenuItem orderCreate   = new JMenuItem("Order");
        JMenuItem custCreate    = new JMenuItem("Customer");
        icfCreate   .addActionListener(event -> onIcfCreateClick());
        mxfCreate   .addActionListener(event -> onMxfCreateClick());
        contCreate  .addActionListener(event -> onContCreateClick());
        orderCreate .addActionListener(event -> onOrderCreateClick());
        custCreate  .addActionListener(event -> onCustCreateClick());
        create  .add(icfCreate);
        create  .add(mxfCreate);
        create  .add(contCreate);
        create  .add(orderCreate);
        create  .add(custCreate);

        //scpCreate.setToolTipText("Create a flavor first!");
        //scpCreate.setEnabled(false);  //must have at least one ice cream flavor to use

        JMenu     help          = new JMenu    ("Help");
        JMenuItem about         = new JMenuItem("About");
        about   .addActionListener(event -> onAboutClick());
        help    .add(about);

        menuBar.add(file);
        menuBar.add(view);
        menuBar.add(create);
        menuBar.add(help);
        setJMenuBar(menuBar);
        //
        //---------- T O O L   B A R ----------
        //        
        JToolBar toolbar = new JToolBar("Options");

        JButton saveB       
            = new JButton(new ImageIcon(this.getClass().getResource("resources/save.png")));
        saveB      .setActionCommand("Save changes");
        saveB      .setToolTipText("Save changes");
        JButton saveAsB     
            = new JButton(new ImageIcon(this.getClass().getResource("resources/saveAs.png")));
        saveAsB    .setActionCommand("Save file as...");
        saveAsB    .setToolTipText("Save file as...");
        JButton openB 
            = new JButton(new ImageIcon(this.getClass().getResource("resources/load.png")));
        openB      .setActionCommand("Open");
        openB      .setToolTipText("Load previous save");
        saveB      .addActionListener(event -> onSaveClick());
        saveAsB    .addActionListener(event -> onSaveAsClick());
        openB      .addActionListener(event -> onOpenClick()); 
        toolbar.add(saveB);
        toolbar.add(saveAsB);
        toolbar.add(openB);
            
        toolbar.add(Box.createHorizontalStrut(20));   

        JButton icfViewB  
            = new JButton(new ImageIcon(this.getClass().getResource("resources/icfView.png")));
        icfViewB   .setActionCommand("View icf");
        icfViewB   .setToolTipText("View ice cream flavors");
        JButton mxfViewB  
            = new JButton(new ImageIcon(this.getClass().getResource("resources/mxfView.png")));
        mxfViewB   .setActionCommand("View mxf");
        mxfViewB   .setToolTipText("View mix-in flavors");
        JButton contViewB  
            = new JButton(new ImageIcon(this.getClass().getResource("resources/containerView.png")));
        contViewB  .setActionCommand("View cont");
        contViewB  .setToolTipText("View containers");
        JButton orderViewB  
            = new JButton(new ImageIcon(this.getClass().getResource("resources/orderView.png")));
        orderViewB   .setActionCommand("View order");
        orderViewB   .setToolTipText("View orders");
        JButton custViewB
            = new JButton(new ImageIcon(this.getClass().getResource("resources/customerView.png")));
        custViewB   .setActionCommand("View cust");
        custViewB   .setToolTipText("View customers");
        
        icfViewB   .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        mxfViewB   .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        contViewB  .addActionListener(event -> view(Screen.CONTAINERS));
        orderViewB .addActionListener(event -> view(Screen.ORDERS));
        custViewB  .addActionListener(event -> view(Screen.CUSTOMERS));
        toolbar.add(icfViewB);
        toolbar.add(mxfViewB);
        toolbar.add(contViewB);
        toolbar.add(orderViewB);
        toolbar.add(custViewB);
        
        toolbar.add(Box.createHorizontalStrut(10));

        JButton icfCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/icfCreate.png")));
        icfCreateB .setActionCommand("Create icf");
        icfCreateB .setToolTipText("Create new ice cream flavor");
        JButton mxfCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/mxfCreate.png")));
        mxfCreateB .setActionCommand("Create mxf");
        mxfCreateB .setToolTipText("Create new mix-in flavor");
        JButton contCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/containerCreate.png")));
        contCreateB .setActionCommand("Create cont");
        contCreateB .setToolTipText("Create new container");
        JButton orderCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/orderCreate.png")));
        orderCreateB .setActionCommand("Create order");
        orderCreateB .setToolTipText("Make an order");
        JButton custCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/customerCreate.png")));
        custCreateB  .setActionCommand("Create cust");
        custCreateB  .setToolTipText("Register new customer");
        //scpCreateB.setEnabled(false);
        icfCreateB   .addActionListener(event -> onIcfCreateClick());
        mxfCreateB   .addActionListener(event -> onMxfCreateClick());
        contCreateB  .addActionListener(event -> onContCreateClick());
        orderCreateB .addActionListener(event -> onOrderCreateClick());
        custCreateB  .addActionListener(event -> onCustCreateClick());
        toolbar.add(icfCreateB);
        toolbar.add(mxfCreateB);
        toolbar.add(contCreateB);
        toolbar.add(orderCreateB);
        toolbar.add(custCreateB);
        getContentPane().add(toolbar, BorderLayout.PAGE_START);

            // WELCOME SCREEN AND GUI FINAGLING

        JPanel mainWindow = new JPanel();

        JButton prevScreenB = new JButton("<");
        JButton nextScreenB = new JButton(">");
        prevScreenB.addActionListener(event -> ((CardLayout)display.getLayout()).previous(display));
        nextScreenB.addActionListener(event -> ((CardLayout)display.getLayout()).next(display));
        //prevScreenB.setEnabled(false);
        //nextScreenB.setEnabled(false);
        JPanel leftMargin = new JPanel(new GridBagLayout());
        JPanel rightMargin = new JPanel(new GridBagLayout());

        JPanel listScreen = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel welcome  = new JLabel("<HTML><font size=+5><b>Welcome to MICE!</center></b></font></HTML>",
                                    JLabel.CENTER);
        welcome.setVerticalAlignment(JLabel.TOP);
;
        c.gridy = 0;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        listScreen.add(welcome, c);

        c.weighty = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        listScreen.add(new JLabel("Register a customer to begin."), c);

        display = new JPanel(new CardLayout());
        display.add(listScreen, "0");
        
        leftMargin.add(prevScreenB);
        rightMargin.add(nextScreenB);
        leftMargin.setPreferredSize(new Dimension(60,display.getHeight()));
        rightMargin.setPreferredSize(new Dimension(60,display.getHeight()));
        getContentPane().add(leftMargin,BorderLayout.WEST);
        getContentPane().add(display, BorderLayout.CENTER);
        getContentPane().add(rightMargin, BorderLayout.EAST);


        setVisible(true);

        testing();
    }

    public void testing(){
        filename = new File("hasItems.scp");
            try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
                if(!(br.readLine()).equals(MAGIC_COOKIE)){
                    throw new RuntimeException("Not a MICE file.");
                }
                if(!(br.readLine()).equals(FILE_VER)){
                    throw new RuntimeException("Incompatible MICE file format.");
                }
                emporium = new Emporium(br);
                updateOnCreation(Screen.ICE_CREAM_FLAVORS);
            } catch (Exception e){
                JOptionPane.showMessageDialog(this,
                    "Could not open " + filename + ":\n" +
                    e, "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    //
    // ------------ L I S T E N E R S ------------
    // -------- FILE LISTENERS ---------
    protected void onSaveClick() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            bw.write(MAGIC_COOKIE + "\n");
            bw.write(FILE_VER + "\n");
            emporium.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not open " + filename + ":\n" +
                    e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onSaveAsClick(){
        try{
            final JFileChooser fc = initializeFileChooser();
            if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
                filename = fc.getSelectedFile();
                if(!filename.getAbsolutePath().endsWith(".scp")){
                    filename = new File(filename.getAbsolutePath() + ".scp");
                }
                onSaveClick();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not open " + filename + ":\n" +
                    e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onOpenClick(){
        final JFileChooser fc = initializeFileChooser();
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            filename = fc.getSelectedFile();
            try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
                if(!(br.readLine()).equals(MAGIC_COOKIE)){
                    throw new RuntimeException("Not a MICE file.");
                }
                if(!(br.readLine()).equals(FILE_VER)){
                    throw new RuntimeException("Incompatible MICE file format.");
                }
                emporium = new Emporium(br);
                updateOnCreation(Screen.ICE_CREAM_FLAVORS);
            } catch (Exception e){
                JOptionPane.showMessageDialog(this,
                    "Could not open " + filename + ":\n" +
                    e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        switch (display.getComponentCount()){
            default:
                display.remove(2);
            case 2:
                display.remove(1);
                break;
            case 1:
            case 0:
                break;
        }
    }

    private JFileChooser initializeFileChooser(){ //not a listener
        JFileChooser fc = new JFileChooser(filename);
        FileFilter scpFiles = new FileNameExtensionFilter("Scoop files", "scp");
        fc.addChoosableFileFilter(scpFiles);
        fc.setFileFilter(scpFiles);
        return fc;
    }
    
    protected void onQuitClick(){
        System.exit(0);
    }
    
    // -------CREATE LISTENERS------------
    protected void onIcfCreateClick(){
        CreateIceCreamDialog flavorDialog = new CreateIceCreamDialog(this);
        if(flavorDialog.success){
            emporium.addIcf(flavorDialog.getChoice());
            updateOnCreation(Screen.ICE_CREAM_FLAVORS);
        }
    }

    protected void onMxfCreateClick(){
        CreateMixInDialog flavorDialog = new CreateMixInDialog(this);
        if(flavorDialog.success){
            emporium.addMxf(flavorDialog.getChoice());
            updateOnCreation(Screen.MIX_IN_FLAVORS);
        }
    }

    protected void onContCreateClick() {
        CreateContainerDialog contDialog = new CreateContainerDialog(this);
        if(contDialog.success){
            emporium.addCont(contDialog.getChoice());
            updateOnCreation(Screen.CONTAINERS);
        }
    }

    protected void onOrderCreateClick() {
        if(emporium.cust().length == 0){ //make a customer if none exist
            if(!onCustCreateClick()) {
                return;
            }
        }
        if(display.getComponentCount() > 1){ //screen already open; return to existing screen
            ((CardLayout)display.getLayout()).show(display, "1");
            return;
        }
        CreateOrderScreen orderScreen = new CreateOrderScreen(this);
        ((CardLayout)display.getLayout()).next(display);
    }

    protected boolean onCustCreateClick() {
        CreateCustomerDialog custDialog = new CreateCustomerDialog(this);
        if(custDialog.success) {
            emporium.addCust(custDialog.getChoice());
            updateOnCreation(Screen.CUSTOMERS);
        }
        //System.out.println("" + custDialog.success);
        return custDialog.success;
    }

    // ------------HELP LISTENERS-------------
    protected void onAboutClick(){
        AboutDialog about = new AboutDialog(this);
    }
    //
    //  ----------  M A I N    P A N E L  -----------
    //
    private void view(Screen screen){
        currScreen = screen;
        
        StringBuilder screenTitle = new StringBuilder("<HTML><font size=+2><b>" 
                                                        + screen.getLabel().toUpperCase());
        StringBuilder screenDisplay = new StringBuilder("<HTML>");

        int i;
        switch(screen){
            case ICE_CREAM_FLAVORS:
            case MIX_IN_FLAVORS:
                Object[] arr = {};
                if(screen == screen.MIX_IN_FLAVORS) arr = emporium.mxf();
                else arr = emporium.icf();
                i = 1;
                for(Object flavor: arr){
                    screenDisplay.append("\t" + i + ".  $" + ((Item)flavor).price()+"\t "
                        +((Item)flavor).name()+" - "+((Item)flavor).description()+"<br>"
                    );
                    i++;
                } break;
            case CONTAINERS:
                i = 1;
                for(Object cont : emporium.cont()){
                    screenDisplay.append("\t"+ i +".  "+((Container)cont).name() 
                        + " - " + ((Container)cont).desc() + "<br>"
                    );
                    i++;
                } break;
            case ORDERS:
                i = 1;
                for(Object o: emporium.order()){
                    Order order = (Order)o;
                    screenDisplay.append("\tOrder " + i + " for "+ order.getCustomer().name() + " $" + order.price() + "<br>" + order.toString() + "<br>");
                    i++;
                } break;
            case CUSTOMERS:
                for(Object cust: emporium.cust()){
                    screenDisplay.append("\t" + cust.toString() + "<br>");
                } break;
            default:
                throw new IllegalArgumentException("Invalid screen.");
        }
        screenTitle.append("</b></font></HTML>");
        screenDisplay.append("</HTML>");

        GridBagConstraints c = new GridBagConstraints();
        // c.gridy = 0;
        // c.weighty = 0;
        // c.anchor = GridBagConstraints.NORTH;
        // c.fill = GridBagConstraints.NONE;
        ((JLabel)((JPanel)display.getComponent(0)).getComponent(0)).setText(screenTitle.toString());

        // c.weighty = 1;
        // c.gridy = 1;
        // c.weightx = 1;
        // c.anchor = GridBagConstraints.NORTHWEST;
        ((JLabel)((JPanel)display.getComponent(0)).getComponent(1)).setText(screenDisplay.toString());

        ((CardLayout)display.getLayout()).show(display, "0");
        ((JPanel)display.getComponent(0)).revalidate();
        ((JPanel)display.getComponent(0)).repaint();

        //getContentPane().revalidate();
        //getContentPane().repaint();
    }

        private void updateOnCreation(Screen screen){ //update mainwin on changes made                               
            // if(Screen.ICE_CREAM_FLAVORS == screen){
            //     enableScoopIfFlavor();
            // }
            //System.out.print(display.getComponentCount());

            switch (display.getComponentCount()) {
                default:
                case 3:
                    ((CreateServingScreen)display.getComponent(2)).updateScreen(screen);
                case 2:
                    ((CreateOrderScreen)display.getComponent(1)).updateScreen(screen);
                    break;
                case 1:
                    break;
            }
            //if(display.getComponentCount() > 1){
                //((CreateOrderScreen)display.getComponent(1)).emporium = this.emporium;
                
                
                // ArrayList<Serving> currentOrder = new ArrayList<>(emporium.order()[])
                // list.fireIntervalAdded(list.getModel(), 0, )
                // list.getModel().ensureIndexIsVisible(3);
                // ((CreateOrderScreen)display.getComponent(1)).revalidate();
                // ((CreateOrderScreen)display.getComponent(1)).repaint();
                // ((JPanel)display.getComponent(1)).revalidate();
                // ((JPanel)display.getComponent(1)).repaint();
                
            //}
            if(currScreen == screen){
                view(screen);
            }
    }

    public static final String FILE_VER = "1.4";
    public static final String MAGIC_COOKIE = "SCOOP🍦";
    Emporium emporium; //package private :)
    JPanel display;
    private File filename;
    private Screen currScreen;
}
