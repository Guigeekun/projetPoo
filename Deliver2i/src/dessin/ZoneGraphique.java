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
    
    
public void creerAxe(int x, int y)
{  
     
      
    
}
    @Override
    public void paintComponent(Graphics g) {
       
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        
        Point p1 = new Point(20, 20);
        Point p2 = new Point(20, 20);
        
        Point p3= new Point(20,20);
        Point p4= new Point(20,20);
        Droite d1 = new Droite(Color.BLACK, p1, p2);
        Droite d2= new Droite(Color.BLACK,p3,p4);
        d1.seDessiner(g);
        d2.seDessiner(g);
    }
    
    

   

    
}
