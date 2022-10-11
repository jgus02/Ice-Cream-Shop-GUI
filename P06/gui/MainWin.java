package gui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import emporium.Emporium;

public class MainWin extends JFrame{
    private Emporium emporium = new Emporium();
    private JLabel display = new JLabel("hello beloved TA. I hate java");
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
    protected void onIceCreamCreateClick(JMenuItem scoopCreate){
        CreateIceCreamDialog flavorDialog = new CreateIceCreamDialog(this);
        if(flavorDialog.success){
            scoopCreate.setEnabled(true);
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

        switch(screen){
            case ICE_CREAM_FLAVORS:
                displayBuilder.append("ICE CREAM FLAVORS</b><br><br>");
                int i = 1;
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
            case SCOOPS:
                displayBuilder.append("SCOOPS</b><br><br>");
                i = 1;
                for(Object scoop: emporium.scoops()){
                    displayBuilder.append(i + ".  " + scoop.toString() + "<br>");
                    i++;
                }
                break;
            default:
                throw new IllegalArgumentException("View must contain value from screen enum.");
        }
        displayBuilder.append("</HTML>");
        display.setText(displayBuilder.toString());
    }
}