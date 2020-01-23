/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin;

import deliver2i.Instance;
import deliver2i.Shift;
import deliver2i.Solution;
import deliver2i.Tournee;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author PORTABLE
 */
public class ZoneGraphique extends JPanel {

    private LinkedList<Forme> collectionDesFormes;

    public ZoneGraphique() {
        this.setBackground(WHITE);
        this.setBounds(25, 25, 1460, 800);//1460=24*60+20 en minutes
        this.collectionDesFormes = new LinkedList<>();
        repaint();

    }

    /*
    ATTENTION tout le graph a une marge de 20 en x et en y
    cette marge est HARD CODé, si on trouve le temps : à fix
     */
    public void addAxe() {
        super.paintComponent(this.getGraphics());

        Point p1 = new Point(20, 20);
        Point p2 = new Point(20, this.getSize().height);
        Droite d1 = new Droite(Color.BLACK, p1, p2);

        Point p3 = new Point(20, 20);
        Point p4 = new Point(this.getSize().width, 20);
        Droite d2 = new Droite(Color.BLACK, p3, p4);

        System.out.println("longueur axe:" + this.getSize().width);
        // Graduation

        Point p7 = new Point((this.getSize().width / 4) + 20, 10);
        Point p8 = new Point((this.getSize().width / 4) + 20, 18);
        Droite d4 = new Droite(Color.ORANGE, p7, p8);// représente 6h

        Point p9 = new Point((this.getSize().width / 3) + 20, 10);
        Point p10 = new Point((this.getSize().width / 3) + 20, 18);
        Droite d5 = new Droite(Color.MAGENTA, p9, p10);//représente 8h

        Point p11 = new Point((int) ((this.getSize().width / 2.4) + 20), 10);
        Point p12 = new Point((int) ((this.getSize().width / 2.4) + 20), 18);
        Droite d6 = new Droite(Color.MAGENTA, p11, p12);//représente 10h

        Point p5 = new Point((this.getSize().width / 2) + 20, 10);
        Point p6 = new Point((this.getSize().width / 2) + 20, 18);
        Droite d3 = new Droite(Color.RED, p5, p6); // Représente 12h

        Point p13 = new Point((int) ((this.getSize().width / 1.71) + 20), 10);
        Point p14 = new Point((int) ((this.getSize().width / 1.71) + 20), 18);
        Droite d7 = new Droite(Color.BLACK, p13, p14); // Représente 14h
        
        
        Point p15 = new Point((int) ((this.getSize().width / 1.71) + 20), 10);
        Point p16 = new Point((int) ((this.getSize().width / 1.71) + 20), 18);
        Droite d8 = new Droite(Color.BLACK, p15, p16); // Représente 16h
        
        
        Point p17 = new Point((int) ((this.getSize().width / 1.5) + 20), 10);
        Point p18 = new Point((int) ((this.getSize().width / 1.5) + 20), 18);
        Droite d9 = new Droite(Color.BLACK, p17, p18); // Représente 17h
        
        
        Point p19 = new Point((int) ((this.getSize().width / 1.33) + 20), 10);
        Point p20 = new Point((int) ((this.getSize().width / 1.33) + 20), 18);
        Droite d10 = new Droite(Color.BLACK, p19, p20); // Représente 20h
        
        Point p21 = new Point((int) ((this.getSize().width / 1.71) + 20), 10);
        Point p22 = new Point((int) ((this.getSize().width / 1.71) + 20), 18);
        Droite d11 = new Droite(Color.BLACK, p21, p22); // Représente 22h
        
        
        collectionDesFormes.add(d2);
        collectionDesFormes.add(d3);
        collectionDesFormes.add(d4);
        collectionDesFormes.add(d5);
        collectionDesFormes.add(d6);
        collectionDesFormes.add(d7);
        collectionDesFormes.add(d8);
        collectionDesFormes.add(d9);
        collectionDesFormes.add(d10);
        collectionDesFormes.add(d11);

    }

    public void construireShift(List<Shift> lshift, Instance inst) {

        int size = lshift.size();
        for (int k = 0; k < size; k++) {
            int x1 = (int) ((lshift.get(k).getDateDebut().getTime() - inst.getDate().getTime()) / 60000) - 20; // différence en minute entre 00h00 et la date de debut du shift
            int y1 = 20 + k * this.getSize().height / size + 1; //hauteur du point supérieur gauche du rectangle
            //le cast de long vers int peut sembler problematique, mais on ne travail que sur des durée de 24h max, ca ne devrait pas poser de probléme
            Point p1 = new Point(x1, y1);

            int x2 = (int) (x1 + lshift.get(k).duree());
            int y2 = y1 + (this.getHeight() - 20) / size + 1;

            Point p2 = new Point(x2, y2);

            Rectangle rec = new Rectangle(Color.RED, p1, p2);
            collectionDesFormes.add(rec);

            //affichage des tournée VVV (work in progress)
            List<Tournee> tourn = lshift.get(k).getMesTournee();
            int sizeT = tourn.size();
            for (int u = 0; u < sizeT; u++) {
                int xt1 = (int) ((tourn.get(u).getDateDebut().getTime() - inst.getDate().getTime()) / 60000) - 20;
                int yt1 = 20 + k * this.getSize().height / size + 1;
                Point pt1 = new Point(xt1, yt1);

                int xt2 = (int) (xt1 + tourn.get(u).duree());
                int yt2 = yt1 + (this.getHeight() - 20) / size + 1;
                Point pt2 = new Point(xt2, yt2);

                Rectangle recT = new Rectangle(Color.BLUE, pt1, pt2);
                collectionDesFormes.add(recT);
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.collectionDesFormes.forEach((f) -> {
            f.seDessiner(g);
        });

    }
}
