/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author PORTABLE
 */

    public abstract class Forme  {
    Color macouleur;
    
    public Forme(Color macouleur)
    {
        this.macouleur=macouleur;
    }

    public Color getMacouleur() {
        return macouleur;
    }

    
    public abstract void seDessiner(Graphics g);
    
}  
    

