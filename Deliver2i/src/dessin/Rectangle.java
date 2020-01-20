/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin;

import deliver2i.Shift;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author PORTABLE
 */
public class Rectangle extends Forme {

       private Point p1;
    private Point p2;

    public Rectangle(Color macouleur,Point p1,Point p2){
        super(macouleur);
        this.p1=p1;
        this.p2=p2;
    }
    
    public Rectangle(Color macouleur, Shift leShift,int hauteur,int k)
    {
        super(macouleur);
        int y1=20+k*hauteur;
        
        
    }

    @Override
    public void seDessiner(Graphics g) {
       g.setColor(macouleur);
        //g.drawRect(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        g.drawRect(Math.min(p1.getX(), p2.getX()),Math.min(p1.getY(), p2.getY()) ,Math.abs(p1.getX()-p2.getX()),Math.abs(p1.getY()-p2.getY()));
        
    }

   
    
}
