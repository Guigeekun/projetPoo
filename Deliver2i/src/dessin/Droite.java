/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author PORTABLE
 */
public class Droite extends Forme {

    private Point p1;
    private Point p2;
     

    public Droite(Color macouleur,Point p1,Point p2){
        super(macouleur);
        this.p1=p1;
        this.p2=p2;
    }

  
 
    @Override
    public void seDessiner(Graphics g) {
        g.setColor(macouleur);
       g.drawLine(p1.getX(), p1.getY(),p2.getX(), p2.getY());
       
    }

    
       
}
