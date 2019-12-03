/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author guigeek
 */
@Entity
public class Tournee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Date Date;

    @Column(nullable = false)
    private Date dureeMin;

    @Column(nullable = false)
    private Date dureeMax;

    @Column(nullable = false)
    private Instance monInstance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDureeMin() {
        return dureeMin;
    }

    public Date getDureeMax() {
        return dureeMax;
    }

    public Instance getMonInstance() {
        return monInstance;
    }

    public Tournee() throws ParseException {
        this.nom = "Ma tournée test";
        DateFormat format = new SimpleDateFormat("mm");
        DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        this.dureeMax = format.parse("30");
        this.dureeMin = format.parse("5");
        this.Date = format2.parse("03/12/2019");
    }

    public Tournee(String nom, Date Date, Date dureeMin, Date dureeMax) throws ParseException {
        this();
        this.nom = nom;
        this.Date = Date;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
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
        if (!(object instanceof Tournee)) {
            return false;
        }
        Tournee other = (Tournee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tournee{" + "nom=" + nom + ", Date=" + Date + ", dureeMin=" + dureeMin + ", dureeMax=" + dureeMax + ", monInstance=" + monInstance + '}';
    }

    public static void main(String[] args) throws ParseException {

        Tournee t1 = new Tournee();
        System.out.println(t1);
        DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat format = new SimpleDateFormat("mm");
        Tournee t2 = new Tournee("Ma deuxième tournée", format2.parse("04/12/2019 12:12:12"), format.parse("5"), format.parse("30"));
        System.out.println(t2);
    }

}
