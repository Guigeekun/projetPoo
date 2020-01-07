/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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

    public Date getDateFin() {
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
        nom = nom;
        date = fin;
        dureeMax = max;
        dureeMin = min;
    }

//===============Methodes==============================
    public Solution Resolution1(EntityManager em) { // cette methode NE PERMET PAS d'avoir des temps morts (enfin en theorie)
        List<Shift> lshift = null;

        em.getTransaction().begin();
        Query query = em.createQuery("select i from Tournee AS i WHERE i.monInstance = :inst", Instance.class);
        query.setParameter("inst", this);
        List<Tournee> maListeTournee = query.getResultList();

        int nbTour = maListeTournee.size();
        lshift.add(new Shift());
        int k=0;
        for (int i = 0; i < nbTour; i++) {
            if (lshift.get(k).getDateFin().compareTo(maListeTournee.get(i).getDateDebut()) < 0 && this.getDureeMax() < lshift.get(k).duree()) { //s.getDateFin() is after (i).getDateDebut() + check duree max du shift
                    maListeTournee.get(i).addMonShift(lshift.get(k)); // ajoute la tournee au shift
                } else {
                    k=k+1;
                    lshift.add(new Shift());
                    maListeTournee.get(i).addMonShift(lshift.get(k));
                }

        }
// s.setDateFin(maListeTournee.get(i).getDateFin())
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instance)) {
            return false;
        }
        Instance other = (Instance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Instance " + id + " / date " + date;
    }

}
