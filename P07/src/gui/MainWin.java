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
import javax.swing.JToolBar;
import java.awt.BorderLayout;

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
        setSize(640, 360);                             
        filename = new File("untitled.scp");
        emporium = new Emporium();
        display  = new JLabel("<HTML><font size=+5><b>Welcome to MICE!</center></b></font></HTML>");
        display.setVerticalAlignment(JLabel.TOP);
        display.setHorizontalAlignment(JLabel.CENTER);

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
        JMenuItem scpView       = new JMenuItem("Scoops");
        icfView     .addActionListener(event -> onIcfViewClick());
        mxfView     .addActionListener(event -> onMxfViewClick());
        scpView     .addActionListener(event -> onScpViewClick());
        view    .add(icfView);
        view    .add(mxfView);
        view    .add(scpView);

        JMenu     create        = new JMenu    ("Create");
        JMenuItem icfCreate     = new JMenuItem("Ice Cream Flavor");
        JMenuItem mxfCreate     = new JMenuItem("Mix-In Flavor");
        JMenuItem scpCreate   = new JMenuItem("Scoop");
        icfCreate   .addActionListener(event -> onIcfCreateClick());
        mxfCreate   .addActionListener(event -> onMxfCreateClick());
        scpCreate   .addActionListener(event -> onScpCreateClick());
        create  .add(icfCreate);
        create  .add(mxfCreate);
        create  .add(scpCreate);

        scpCreate.setToolTipText("Create a flavor first!");
        scpCreate.setEnabled(false);  //must have at least one ice cream flavor to use

        JMenu     help          = new JMenu    ("Help");
        JMenuItem about         = new JMenuItem("About");
        about       .addActionListener(event -> onAboutClick());
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
        icfViewB   .setActionCommand("View ice cream flavors");
        icfViewB   .setToolTipText("View ice cream flavors");
        JButton mxfViewB  
            = new JButton(new ImageIcon(this.getClass().getResource("resources/mxfView.png")));
        mxfViewB   .setActionCommand("View mix-in flavors");
        mxfViewB   .setToolTipText("View mix-in flavors");
        JButton scpViewB  
            = new JButton(new ImageIcon(this.getClass().getResource("resources/scpView.png")));
        scpViewB   .setActionCommand("View scoops");
        scpViewB   .setToolTipText("View scoops");
        icfViewB   .addActionListener(event -> onIcfViewClick());
        mxfViewB   .addActionListener(event -> onMxfViewClick());
        scpViewB   .addActionListener(event -> onScpViewClick());
        toolbar.add(icfViewB);
        toolbar.add(mxfViewB);
        toolbar.add(scpViewB);
        
        toolbar.add(Box.createHorizontalStrut(10));

        JButton icfCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/icfCreate.png")));
        icfCreateB .setActionCommand("Create new ice cream flavor");
        icfCreateB .setToolTipText("Create new ice cream flavor");
        JButton mxfCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/mxfCreate.png")));
        mxfCreateB .setActionCommand("Create new mix-in flavor");
        mxfCreateB .setToolTipText("Create new mix-in flavor");
        JButton scpCreateB  
            = new JButton(new ImageIcon(getClass().getResource("resources/scpCreate.png")));
        scpCreateB .setActionCommand("Create new scoop");
        scpCreateB .setToolTipText("Create a flavor first!");
        scpCreateB.setEnabled(false);
        icfCreateB .addActionListener(event -> onIcfCreateClick());
        mxfCreateB .addActionListener(event -> onMxfCreateClick());
        scpCreateB .addActionListener(event -> onScpCreateClick());
        toolbar.add(icfCreateB);
        toolbar.add(mxfCreateB);
        toolbar.add(scpCreateB);
        
        JComponent[] tmp = {scpCreate, scpCreateB};
        scpButtons = tmp;

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        setVisible(true);
        
        this.add(display);
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

    
    // ---------VIEW LISTENSTERS----------
    protected void onIcfViewClick(){
        view(Screen.ICE_CREAM_FLAVORS);
    }

    protected void onMxfViewClick(){
        view(Screen.MIX_IN_FLAVORS);
    }

    protected void onScpViewClick(){
        view(Screen.SCOOPS);
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

    protected void onScpCreateClick(){
        CreateScoopDialog scoopDialog = new CreateScoopDialog(this, this.emporium);
        if(scoopDialog.success){
            emporium.addScp(scoopDialog.getChoice());
            updateOnCreation(Screen.SCOOPS);
        }
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

        Object[] arr = {};
        StringBuilder displayBuilder = new StringBuilder("<HTML><font size=+2><b>");
        display.setHorizontalAlignment(JLabel.LEFT);

        switch(screen){
            case ICE_CREAM_FLAVORS:
                displayBuilder.append("ICE CREAM FLAVORS");
                arr = emporium.icf();
                break;
            case MIX_IN_FLAVORS:
                displayBuilder.append("MIX-IN FLAVORS");
                arr = emporium.mxf();
                break;
            case SCOOPS:
                displayBuilder.append("SCOOPS");
                arr = emporium.scp();
                break;
            default:
                throw new IllegalArgumentException("Invalid screen.");
        }

        displayBuilder.append("</b></font><br><br>");

        int i = 1;
        if(screen == Screen.SCOOPS){
            for(Object scoop: arr){
                displayBuilder.append("\t" + i + ".  " + scoop.toString() + "<br>");
                i++;
            }
        } else {
            for(Object flavor: arr){
                displayBuilder.append("\t" +
                            i + ".  $" + ((Item)flavor).price() +
                            " " + ((Item)flavor).name() + " - " +
                            ((Item)flavor).description() + "<br>"
                );
                i++;
            }
        }
        displayBuilder.append("</HTML>");
        display.setText(displayBuilder.toString());
    }

    // --------------MISC ASSISTANTS---------------- 
    private void enableScoopIfFlavor(){
        boolean scoopEnabled = false;
        String toolTipMsg = "Create a flavor first!";
        if (emporium.icf().length != 0){
            scoopEnabled = true;
            toolTipMsg = "Create new scoop";
        }
        for(int i = 0;i<2;i++){
            scpButtons[i].setEnabled(scoopEnabled);
            scpButtons[i].setToolTipText(toolTipMsg);
        }
    }

        private void updateOnCreation(Screen screen){ //update mainwin on changes made                               
            if(Screen.ICE_CREAM_FLAVORS == screen){
                enableScoopIfFlavor();
            }
            if(currScreen == screen){
                view(screen);
            }
    }

    public static final String FILE_VER = "1.3";
    protected static final String MAGIC_COOKIE = "SCOOP🍦";
    private File filename;
    private JComponent[] scpButtons = null;
    protected Emporium emporium;
    private JLabel display;
    private Screen currScreen;
}
