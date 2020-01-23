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

            for (int i = 0; i < nbTour; i++) {
                long dur = (maListeTournee.get(i).getDateDebut().getTime() - lshift.get(k).getDateFin().getTime()) / 60000;//durée du shift si on ajoute la tournée
                if ((lshift.get(k).getDateFin().compareTo(maListeTournee.get(i).getDateDebut()) <= 0) && (this.getDureeMax() >= dur)) { //s.getDateFin() is after (i).getDateDebut() + check duree max du shift
                    // ajoute la tournee au shift
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
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
    }

    public void Resolution2(EntityManager em) throws ClassNotFoundException, SQLException { // cette methode NE PERMET PAS d'avoir des temps morts (enfin en theorie)
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
            int validate = 0; //permet de savoir si la tournée active à été traité
            //
            for (int i = 0; i < nbTour; i++) {
                
                //ici on verifie si on peut mettre la tournée à la suite du shift
                if ((lshift.get(k).getDateFin().compareTo(maListeTournee.get(i).getDateDebut()) <= 0)) { //s.getDateFin() est aprés (i).getDateDebut()
                    lshift.get(k).addTournee(maListeTournee.get(i));
                    //durée du shift si on ajoute la tournée
                    if (this.getDureeMax() >= lshift.get(k).duree()) { //  check duree max du shift
                        validate = 1; //la tournée a été traité
                    } else {
                        lshift.get(k).getMesTournee().remove(lshift.get(k).getMesTournee().size() - 1);//retire le dernier element
                        lshift.get(k).update();//met à jour les date de debut et de fin
                    }
                } if (validate == 0) {
                    //ici on verifie si on peut mettre la tournée au debut d'un shift précédemment créé
                    for (int u = 0; u < k; u++) {
                        if ((lshift.get(u).getDateFin().compareTo(maListeTournee.get(i).getDateDebut()) <= 0)) {
                            lshift.get(u).addTournee(maListeTournee.get(i));
                            if (this.getDureeMax() >= lshift.get(u).duree()) {
                                validate = 1;
                                break;
                            } else {
                                lshift.get(u).getMesTournee().remove(lshift.get(u).getMesTournee().size() - 1);//retire le dernier element
                                lshift.get(u).update();//met à jour les date de debut et de fin
                            }
                        }

                    }}
                    if (validate == 0) {
                        k = k + 1;
                        lshift.add(new Shift(sol));
                        lshift.get(k).addTournee(maListeTournee.get(i));
                    }

               
                validate = 0;

            }
            for (int u = 0; u < nbTour; u++) {
                em.persist(maListeTournee.get(u));
            }
            for (int n = 0; n < lshift.size(); n++) {
                lshift.get(n).update();
                em.persist(lshift.get(n));
            }
            sol.calculCout(lshift);
            em.persist(sol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
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
