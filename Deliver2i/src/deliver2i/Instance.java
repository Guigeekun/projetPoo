/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

/**
 *
 * @author guigeek
 */
@Entity
public class Instance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int dureeMax; //en minute
    @Column(nullable = false)
    private int dureeMin; //en minute

//=====================Getters==============================
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    public int getDureeMax() {
        return dureeMax;
    }

    public int getDureeMin() {
        return dureeMin;
    }

//================Setters==================================
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateFin(Date dateFin) {
        this.date = dateFin;
    }

    public void setDureeMax(int dureeMax) {
        this.dureeMax = dureeMax;
    }

    public void setDureeMin(int dureeMin) {
        this.dureeMin = dureeMin;
    }

//===============Constructors==========================    
    public Instance() throws ClassNotFoundException, SQLException {
        nom = null;
        date = null;
        dureeMax = 0;
        dureeMin = 0;
        // this.connect();
    }

    public Instance(String nom, Date fin, int max, int min) {
        this.nom = nom;
        date = fin;
        dureeMax = max;
        dureeMin = min;
    }

//===============Methodes==============================
    public void Resolution1(EntityManager em) throws ClassNotFoundException, SQLException {
        List<Shift> lshift = new LinkedList<>();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Query query = em.createQuery("select i from Tournee AS i WHERE i.monInstance = :inst", Tournee.class);
            query.setParameter("inst", this);
            List<Tournee> maListeTournee = query.getResultList();
            Solution sol = new Solution(0.0, this); //le cout est (pour l'instant) fixé à 0

            int nbTour = maListeTournee.size();
            lshift.add(new Shift(sol));
            int k = 0; //k désigne l'index "actif" des shifts
            //
            for (int i = 0; i < nbTour; i++) {

                //ici on verifie si on peut mettre la tournée à la suite du shift
                if (lshift.get(k).getDateFin().compareTo(maListeTournee.get(i).getDateDebut()) < 0
                        && this.getDureeMax() >= (maListeTournee.get(i).getDateFin().getTime() - lshift.get(k).getDateDebut().getTime()) / 60000) { //s.getDateFin() est aprés (i).getDateDebut()
                    //durée du shift si on ajoute la tournée//  check duree max du shift
                    lshift.get(k).addTournee(maListeTournee.get(i));
                } else {
                    k = k + 1;
                    lshift.add(new Shift(sol));
                    lshift.get(k).addTournee(maListeTournee.get(i));
                }
            }
            for (int u = 0; u < nbTour; u++) {
                em.persist(maListeTournee.get(u));
            }
            for (int n = 0; n < lshift.size(); n++) {
                em.persist(lshift.get(n));
            }
            sol.calculCout(lshift);
            em.persist(sol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public void Resolution2(EntityManager em) throws ClassNotFoundException, SQLException { // 
        List<Shift> lshift = new LinkedList<>();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Query query = em.createQuery("select i from Tournee AS i WHERE i.monInstance = :inst", Tournee.class);
            query.setParameter("inst", this);
            List<Tournee> maListeTournee = query.getResultList();
            Solution sol = new Solution(0.0, this); //le cout est (pour l'instant) fixé à 0

            int nbTour = maListeTournee.size();
            lshift.add(new Shift(sol));
            int k = 0; //k désigne l'index "actif" des shifts
            for (int i = 0; i < nbTour; i++) {

                //ici on verifie si on peut mettre la tournée à la suite du shift
                if (lshift.get(k).getDateFin().compareTo(maListeTournee.get(i).getDateDebut()) < 0
                        && this.getDureeMax() >= (maListeTournee.get(i).getDateFin().getTime() - lshift.get(k).getDateDebut().getTime()) / 60000) { //s.getDateFin() est aprés (i).getDateDebut()
                    //durée du shift si on ajoute la tournée//  check duree max du shift
                    lshift.get(k).addTournee(maListeTournee.get(i));
                } else {
                    //ici on verifie si on peut mettre la tournée au debut d'un shift précédemment créé
                    if ((lshift.get(k).getDateDebut().compareTo(maListeTournee.get(i).getDateFin()) > 0
                            && this.getDureeMax() >= (lshift.get(k).getDateDebut().getTime() - maListeTournee.get(i).getDateFin().getTime()) / 60000)) {
                        lshift.get(k).addTournee(maListeTournee.get(i));
                    } else {
                        k = k + 1;
                        lshift.add(new Shift(sol));
                        lshift.get(k).addTournee(maListeTournee.get(i));
                    }
                }

            }
            for (int u = 0; u < nbTour; u++) {
                em.persist(maListeTournee.get(u));
            }
            for (int n = 0; n < lshift.size(); n++) {
                em.persist(lshift.get(n));
            }
            sol.calculCout(lshift);
            em.persist(sol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public void Resolution3(EntityManager em) throws ClassNotFoundException, SQLException { // 
        List<Shift> lshift = new LinkedList<>();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Query query = em.createQuery("select i from Tournee AS i WHERE i.monInstance = :inst ORDER BY i.dateDebut", Tournee.class);
            query.setParameter("inst", this);
            List<Tournee> maListeTournee = query.getResultList();
            Solution sol = new Solution(0.0, this); //le cout est (pour l'instant) fixé à 0

            int nbTour = maListeTournee.size();
            lshift.add(new Shift(sol));
            lshift.get(0).addTournee(maListeTournee.get(0));
            maListeTournee.remove(0);

            lshift = ResoRecu(lshift, maListeTournee, 0, sol);

            for (int u = 0; u < nbTour; u++) {
                em.persist(maListeTournee.get(u));
            }
            for (int n = 0; n < lshift.size(); n++) {
                em.persist(lshift.get(n));
            }
            sol.calculCout(lshift);
            em.persist(sol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private List<Shift> ResoRecu(List<Shift> lshift, List<Tournee> ltournee, int i, Solution sol) {
        //i défini le shift "actif
        Tournee foundTour=null;
        int index;
        if (ltournee.isEmpty()) {
            return (lshift);
        } else {
            //cherche la plus proche tourné  (datefin to datedebut)
            for (int k = 0; k < ltournee.size(); k++) {
                if (ltournee.get(k).getDateDebut().getTime() - lshift.get(i).getDateFin().getTime() >= 0) {
                        foundTour = ltournee.get(k);
                        ltournee.remove(k);
                        break;
                    }
            }
            if(foundTour==null){
                lshift.add(new Shift(sol));
                i++;
                lshift.get(i).addTournee(ltournee.get(0));
                ltournee.remove(0);
                return (ResoRecu(lshift, ltournee, i, sol));
            }
            if (this.getDureeMax() >= (foundTour.getDateFin().getTime() - lshift.get(i).getDateDebut().getTime()) / 60000) {
                        //add au shift
                        //remove la tournee du tab
                        lshift.get(i).addTournee(foundTour);         
                        
                    } else {//SINON crée un new shift
                        lshift.add(new Shift(sol));
                        i++;
                        lshift.get(i).addTournee(foundTour);

                    return (ResoRecu(lshift, ltournee, i, sol));
                }
            

        }return (ResoRecu(lshift, ltournee, i, sol));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object
    ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instance)) {
            return false;
        }
        Instance other = (Instance) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Instance " + id + " / date " + date;
    }

}
