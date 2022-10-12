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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/*import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;*/

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import emporium.Emporium;

public class MainWin extends JFrame{
    
    public MainWin(String titleBar){
        super(titleBar); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //"It is recommended you only use this in
        setSize(640, 360);                              //an application."  closes entire program? 
        filename = new File("untitled.scp");
        emporium = new Emporium();
        display  = new JLabel("");
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

        scoopCreate.setToolTipText("Create a flavor first!");
        scoopCreate.setEnabled(false);  //must have at least one ice cream flavor to use

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
    protected void onSaveAsClick(){
        final JFileChooser fc = openSaveClickFcHelper();
        if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            filename = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith(".scp")){
                filename = new File(filename.getAbsolutePath() + ".scp");
            }
            onSaveClick();
        }
    }

    protected void onSaveClick(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VER + '\n');
            emporium.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Could not open " + filename + '\n' + 
            e, "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onOpenClick(){
        final JFileChooser fc = openSaveClickFcHelper();
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            filename = fc.getSelectedFile();

            try(BufferedReader br = new BufferedReader(new FileReader(filename))){
                if(!(br.readLine()).equals(MAGIC_COOKIE)){
                    throw new RuntimeException("Not a MICE file.");
                }
                if(!(br.readLine()).equals(FILE_VER)){
                    throw new RuntimeException("Incompatible MICE file format.");
                }

                emporium = new Emporium(br);
                if (emporium.iceCreamFlavors().size != 0){
                    this.getJMenuBar().getMenu(2).getItem(2).setEnabled(true);

                }
            }
        }
    }

    private JFileChooser openSaveClickFcHelper(){ //not a listener
        FileChooser fc = new JFileChooser(filename);
        FileFilter scpFiles = new FileNameExtensionFilter("Scoop files", "scp");
        fc.addChooseableFileFilter(scpFiles);
        fc.setFileFilter(scpFiles);
        return fc;
    }
    
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
    protected void onIceCreamCreateClick(){ //TODO: INEFFICIENT SCOOPCREATE DISABLING METHOD?
        CreateIceCreamDialog flavorDialog = new CreateIceCreamDialog(this);
        if(flavorDialog.success){
            JMenuItem tempScoopCreate = this.getJMenuBar().getMenu(2).getItem(2);
            if(!tempScoopCreate.isEnabled()){
                tempScoopCreate.setToolTipText(null);
                tempScoopCreate.setEnabled(true);
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
    JOptionPane.showMessageDialog(null, 
                    "Ice Cream Emporium\n\nCopyright 2022 Jasper Gustafson" + 
                    " - Licensed under Gnu GPL 3.0\n\n" + 
                    "Create ice cream and topping flavors and order them as servings of ice cream"
    );
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

    private String FILE_VER = "1.0";
    private String MAGIC_COOKIE = "SCOOP🍦";
    private File filename;

    private String VERSION = "1.3";

    private Emporium emporium;
    private JLabel display;
}
