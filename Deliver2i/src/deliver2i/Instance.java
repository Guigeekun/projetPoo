/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Date date;

    private int dureeMax; //en minute

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
    public Instance() {
        nom = null;
        date = null;
        dureeMax = 0;
        dureeMin = 0;
    }

    public Instance(String nom, Date fin, int max, int min) {
        nom = nom;
        date = fin;
        dureeMax = max;
        dureeMin = min;
    }

//===============Methodes==============================
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
        return "deliver2i.Instance[ id=" + id + " nom" + nom + " date " + date + " ]";
    }

}
