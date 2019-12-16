/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.beans.Statement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.management.Query;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author guigeek
 */
@Entity
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateDebut;

    private Date dateFin;

    private Solution solution;

//=============Getters===================
    public Long getId() {
        return id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Solution getSolution() {
        return solution;
    }

//==============Setters=======================
    public void setId(Long id) {
        this.id = id;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
//============Constructors=======================

    public Shift() {
        dateDebut = null;
        dateFin = null;
        solution = null;
    }

    public Shift(Date debut, Date fin, Solution solu) {
        dateDebut = debut;
        dateFin = fin;
        solution = solu;
    }

//=============Methodes====================
    public long duree() { //retourne la durée du shift
        long a = this.dateFin.getTime() - this.dateDebut.getTime(); //getTime convert date to Timestamp
        return a / 60; //en minute
    }

    public int tempsMort(EntityManager em) {
        javax.persistence.Query q = em.createQuery("SELECT dateDebut,dateFin FROM Tournee WHERE shift = :id"); //syntaxe JPQL
        q.setParameter("id", id);
        Set<Tournee> list = (Set<Tournee>) q.getResultList();
        int sum = 0;
        for(Tournee test:list){
            sum+=test.duree();
        }
          int a = (int) (this.duree()-sum);
        return a;
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
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "deliver2i.Shift[ id=" + id + " dateDebut= " + dateDebut + " dateFin= " + dateFin + " ]";
    }

    
    public static void main(String[] args) {
        
    }
}
