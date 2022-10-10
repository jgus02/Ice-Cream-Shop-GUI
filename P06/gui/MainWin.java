package gui;
//import java.awt.Frame; //what are you. who do you serve
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import emporium.Emporium;

/*import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;*/ //THIEVED CODE WRITE YOUR OWN

public class MainWin extends JFrame{
    Emporium emporium = new Emporium();
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
        iceCreamCreate.addActionListener(event -> onIceCreamCreateClick());
        mixInCreate   .addActionListener(event -> onMixInCreateClick());
        scoopCreate   .addActionListener(event -> onScoopCreateClick());
        create  .add(iceCreamCreate);
        create  .add(mixInCreate);
        create  .add(scoopCreate);

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
    }

    // -------- File  Listeners ---------
    protected void onQuitClick(){
        System.exit(0);
    }
    //
    // --------- View Listeners ----------
    protected void onIceCreamViewClick(){
        
    }

    protected void onMixInViewClick(){
        
    }

    protected void onScoopViewClick(){
        
    }
    //
    // ------- Create Listeners ------------
    protected void onIceCreamCreateClick(){
        CreateFlavorJOptionPane flavorDialog = new CreateFlavorJOptionPane(Screen.ICE_CREAM_FLAVORS, this);
        if(flavorDialog.success){
            emporium.addIceCreamFlavor(flavorDialog.returnIceCream());
        }
    }

    protected void onMixInCreateClick(){
        CreateFlavorJOptionPane flavorDialog = new CreateFlavorJOptionPane(Screen.MIX_IN_FLAVORS, this);
        if(flavorDialog.success){
            emporium.addMixInFlavor(flavorDialog.returnMixIn());
        }
    }

    protected void onScoopCreateClick(){
        
    }
    //
    // --------- Help Listeners -------------
    protected void onAboutClick(){
        
    }

}