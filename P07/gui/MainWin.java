package gui;
import javax.swing.JFrame;
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

import javax.swing.JFileChooser;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import emporium.Emporium;

public class MainWin extends JFrame{
    private Emporium emporium = new Emporium();
    private JLabel display = new JLabel("");
    public MainWin(String titleBar){
        super(titleBar); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //"It is recommended you only use this in
        setSize(640, 360);                              //an application."  closes entire program? 
        //
        // ---------- M E N U   B A R ----------
        JMenuBar menuBar = new JMenuBar();

        JMenu     file           = new JMenu    ("File"); 
        JMenuItem quit           = new JMenuItem("Quit");
        quit          .addActionListener(event -> onQuitClick());
        file   .add(quit);

        JMenu     view           = new JMenu    ("View");
        JMenuItem iceCreamView   = new JMenuItem("Ice Cream Flavors"); 
        JMenuItem mixInView      = new JMenuItem("Mix-In Flavors");
        JMenuItem scoopView      = new JMenuItem("Scoops");
        iceCreamView  .addActionListener(event -> onIceCreamViewClick());
        mixInView     .addActionListener(event -> onMixInViewClick());
        scoopView     .addActionListener(event -> onScoopViewClick());
        view   .add(iceCreamView);
        view   .add(mixInView);
        view   .add(scoopView);

        JMenu     create         = new JMenu    ("Create");
        JMenuItem iceCreamCreate = new JMenuItem("Ice Cream Flavor");
        JMenuItem mixInCreate    = new JMenuItem("Mix-In Flavor");
        JMenuItem scoopCreate    = new JMenuItem("Scoop");
        iceCreamCreate.addActionListener(event -> onIceCreamCreateClick(scoopCreate));
        mixInCreate   .addActionListener(event -> onMixInCreateClick());
        scoopCreate   .addActionListener(event -> onScoopCreateClick());
        create  .add(iceCreamCreate);
        create  .add(mixInCreate);
        create  .add(scoopCreate);

        scoopCreate.setToolTipText("Create a flavor first!");
        scoopCreate.setEnabled(false);

        JMenu     help           = new JMenu    ("Help");
        JMenuItem about          = new JMenuItem("About");
        about         .addActionListener(event -> onAboutClick());
        help    .add(about);

        menuBar.add(file);
        menuBar.add(view);
        menuBar.add(create);
        menuBar.add(help);
        setJMenuBar(menuBar);
        //
        // ---------- T O O L   B A R ----------        //TODO
        /*JToolBar toolbar = new JToolBar("Options");

        JButton iceCreamCreateB = new JButton*/ 
        setVisible(true);
        
        this.add(display);
    }

    // -------- File  Listeners ---------
    protected void onQuitClick(){
        System.exit(0);
    }

    protected void onSaveClick(){
        //JFileChooser saveInfo = new JFileChooser();
    }
    //
    // --------- View Listeners ----------
    protected void onIceCreamViewClick(){
        view(Screen.ICE_CREAM_FLAVORS);
    }

    protected void onMixInViewClick(){
        view(Screen.MIX_IN_FLAVORS);
    }

    protected void onScoopViewClick(){
        view(Screen.SCOOPS);
    }
    //
    // ------- Create Listeners ------------
    protected void onIceCreamCreateClick(JMenuItem scoopCreate){ //TODO: INEFFICIENT SCOOPCREATE DISABLING METHOD?
        CreateIceCreamDialog flavorDialog = new CreateIceCreamDialog(this);
        if(flavorDialog.success){
            if(!scoopCreate.isEnabled()){
                scoopCreate.setToolTipText("");
                scoopCreate.setEnabled(true);
            }
            emporium.addIceCreamFlavor(flavorDialog.getChoice());
        }
    }

    protected void onMixInCreateClick(){
        CreateMixInDialog flavorDialog = new CreateMixInDialog(this);
        if(flavorDialog.success){
            emporium.addMixInFlavor(flavorDialog.getChoice());
        }
    }

    protected void onScoopCreateClick(){
        CreateScoopDialog scoopDialog = new CreateScoopDialog(this, this.emporium);
        if(scoopDialog.success){
            emporium.addScoop(scoopDialog.getChoice());
        }
    }
    //
    // --------- Help Listeners -------------
    protected void onAboutClick(){
    JOptionPane.showMessageDialog(null, "Ice Cream Emporium\n\nCopyright 2022 Jasper Gustafson - Licensed under Gnu GPL 3.0\n\n" + "Create ice cream and topping flavors, and then assemble them into scoops for an ice cream cone");
    }

    private void view(Screen screen){
        StringBuilder displayBuilder = new StringBuilder("<HTML><b>");
        int i = 1;
        Object[] flavors = {};
        /*switch(screen){
            case SCOOPS:
                displayBuilder.append("SCOOPS</b><br><br>");
                i = 1;
                for(Object scoop: emporium.scoops()){
                    displayBuilder.append(i + ".  " + scoop.toString() + "<br>");
                    i++;
                }
                displayBuilder.append("</HTML>");
                display.setText(displayBuilder.toString());
            break;
            case ICE_CREAM_FLAVORS:
                displayBuilder.append("ICE CREAM FLAVORS</b><br><br>");
                i = 1;
                for(Object flavor: emporium.iceCreamFlavors()){
                    displayBuilder.append(i + ".  $" + ((IceCreamFlavor)flavor).price() + " " + flavor.toString() + " - " + ((IceCreamFlavor)flavor).description() + "<br>");
                    i++;
                }
                break;
            case MIX_IN_FLAVORS:
                displayBuilder.append("MIX-IN FLAVORS</b><br><br>");
                i = 1;
                for(Object flavor: emporium.mixInFlavors()){
                    displayBuilder.append(i + ".  $" + ((MixInFlavor)flavor).price() + " " + flavor.toString() + " - " + ((MixInFlavor)flavor).description() + "<br>");
                    i++;
                }
                break;

            default:
                
        }*/

        switch(screen){
            case SCOOPS:    //Case SCOOPS leaves method before exiting switch statement.
                displayBuilder.append("SCOOPS</b><br><br>");
                for(Object scoop: emporium.scoops()){
                    displayBuilder.append(i + ".  " + scoop.toString() + "<br>");
                    i++;
                }
                displayBuilder.append("</HTML>");
                display.setText(displayBuilder.toString());
                return;     //exits here

            case ICE_CREAM_FLAVORS:
                displayBuilder.append("ICE CREAM FLAVORS</b><br><br>");
                flavors = emporium.iceCreamFlavors();
                break;
            case MIX_IN_FLAVORS:
                displayBuilder.append("MIX-IN FLAVORS</b><br><br>");
                flavors = emporium.mixInFlavors();  
                break;
            default:
                throw new IllegalArgumentException("Invalid screen passed to view.");
        }

        for(Object flavor: flavors){ //Cases ICE_CREAM and MIX_IN both use this display format.
            displayBuilder.append(i + ".  $" + ((Item)flavor).price() + " " + ((Item)flavor).name() + " - " + ((Item)flavor).description() + "<br>");
            i++;
        }
        displayBuilder.append("</HTML>");
        display.setText(displayBuilder.toString());
    }
}
