/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin;

import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author PORTABLE
 */
public class ZoneGraphique extends JPanel {

    public ZoneGraphique() {
        this.setBackground(WHITE);
        this.setBounds(25, 25, 600, 600);
       repaint();
    }
    
    

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
         Point p1= new Point(30, 50);
        Point p2= new Point(30,80);
        Droite d1= new Droite(Color.MAGENTA,p1,p2);
        d1.seDessiner(g);
    }
    
    

   

    
}
