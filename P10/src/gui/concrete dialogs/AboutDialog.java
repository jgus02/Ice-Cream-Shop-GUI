package gui;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout; 
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.Canvas;

public class AboutDialog extends JDialog{
    
        public AboutDialog(MainWin parent){
            super(parent, "About MICE");
            
            Logo logo = new Logo();

            JLabel title = new JLabel("<HTML><font size=+2><b>MAV\'S ICE CREAM EMPORIUM</b></HTML>");

            JLabel artists = new JLabel("<html>"
            + "<p>Version " + parent.FILE_VER + "</p>"
            + "<p>Copyright 2022 Jasper Gustafson, licensed under GNU GPL 3.0</p>"
            + "<p>Logo and save/load/view icons by me, licensed under CC BY-NC 4.0</p>"
            + "<p>Toolbar create and view icons by Andrew Nguyen and modified by</p>"
            + "<p>me, licensed under CC BY-NC4.0 </html>");
            
            JButton ok = new JButton("<HTML><b>OK</b></HTML>");
            ok.addActionListener(event -> setVisible(false));

            setLayout(new GridLayout(4,0,0,0)); //AWFUL alignment. functional.
            JPanel jpan = new JPanel();
            jpan.add(ok);
            logo.repaint();
            add(logo);
            add(title);
            add(artists);
            add(jpan);
            pack();
            setVisible(true);
            toFront();
        }
}