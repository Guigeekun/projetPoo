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
    private Date dateDebut;

    @Column(nullable = false)
    private Date dateFin;

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

   

    public Instance getMonInstance() {
        return monInstance;
    }

    public Tournee() throws ParseException {
        this.nom = "Ma tournée test";
        DateFormat format =new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.Date = format.parse("03/12/2019 00:00");
        this.dateDebut=format.parse("03/12/2019 12:00");
        this.dateFin=format.parse("03/12/2019 20:00");
    }

    public Tournee(String nom, Date Date, Date dateDebut, Date dateFin) throws ParseException {
        this();
        this.nom = nom;
        this.Date = Date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    public long duree() { //retourne la durée du shift
        long a = this.dateFin.getTime() - this.dateDebut.getTime(); //getTime convert date to Timestamp
        return a / 60; //en minute
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
        return "Tournee{" + "id=" + id + ", nom=" + nom + ", Date=" + Date + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", monInstance=" + monInstance + '}';
    }


    public static void main(String[] args) throws ParseException {

        Tournee t1 = new Tournee();
        System.out.println(t1);
        DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Tournee t2= new Tournee("Ma deuxième tournée", format2.parse("03/12/2019 00:00"), format2.parse("03/12/2019 08:00"), format2.parse("03/12/2019 12:00"));
        System.out.println(t2);
    }

   

}
