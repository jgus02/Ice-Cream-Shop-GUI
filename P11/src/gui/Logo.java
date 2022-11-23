package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Canvas;

public class Logo extends JPanel{
    
        private BufferedImage logo;

        public Logo(){
            super();
            //setSize(50,50);
            try{
                logo = ImageIO.read(getClass().getResource("resources/logo.png"));
            } catch (IOException e){
                System.err.print("Error: could not find logo.");
            }
            setVisible(true);
            setOpaque(true);
            setPreferredSize(new Dimension(400, 150));
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D gfx = (Graphics2D)g;
            gfx.setFont(new Font("SansSerif", Font.BOLD, 30));
            

            for(int i = 0; i<14 ;i++){
                gfx.setColor(Color.YELLOW);
                gfx.drawLine(10+i*30,0,10+i*30,95);
                gfx.setColor(Color.GREEN);
                gfx.drawLine(0,i*7,430,i*7);
                gfx.setColor(Color.PINK);
                gfx.drawLine(15+i*50,0,15+i*50,95);
            }
            gfx.drawImage(logo,10,5,null);
            gfx.drawString("mm ice ceam :)", 50, 100);
        }
}