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
import javax.persistence.JoinColumn;

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

    @JoinColumn(nullable = false)
    private Date Date;

    @JoinColumn(nullable = false)
    private Date dateDebut;

    @JoinColumn(nullable = false)
    private Date dateFin;

    @JoinColumn(nullable = false)
    private Instance monInstance;

    public Long getId() {
        return id;
    }
//===========Setter=============================================================

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setMonInstance(Instance monInstance) {
        this.monInstance = monInstance;
    }

    public void setId(Long id) {
        this.id = id;
    }

//===========Getter=============================================================
    public Date getDate() {
        return Date;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Instance getMonInstance() {
        return monInstance;
    }

//===========Constructor========================================================
    public Tournee() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.Date = format.parse("03/12/2019 00:00");
        this.dateDebut = format.parse("03/12/2019 12:00");
        this.dateFin = format.parse("03/12/2019 20:00");
    }

    public Tournee(Date Date, Date dateDebut, Date dateFin, Instance inst) throws ParseException {
        this();
        this.Date = Date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.monInstance = inst;
    }

    public Tournee(Date Date, Date dateDebut, Date dateFin) throws ParseException {
        this();
        this.Date = Date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
//===========Methode============================================================

    public long duree() { //retourne la durée du shift
        long heureEnMs = 60 * 60 * 1000;

        System.out.println(this.dateFin.getTime());
        System.out.println(this.dateDebut.getTime());
        long a = (this.dateFin.getTime() - this.dateDebut.getTime()) / heureEnMs; //getTime convert date to Timestamp
        System.out.println("La tournée dure  " + a + "h");
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
        return "dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

    public static void main(String[] args) throws ParseException {

        Tournee t1 = new Tournee();
        System.out.println(t1);
        DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Tournee t2 = new Tournee(format2.parse("03/12/2019 00:00"), format2.parse("03/12/2019 08:00"), format2.parse("03/12/2019 12:00"));
        System.out.println(t2);
        System.out.println(t2.duree());
    }

}
