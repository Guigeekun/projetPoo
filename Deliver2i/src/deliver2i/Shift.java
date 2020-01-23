/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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

    // @JoinTable(
    //        name = "Tournee_Shift", 
    // joinColumns = @JoinColumn(name = "id"), 
    //inverseJoinColumns = @JoinColumn(name = "id"
    //         )
    //  )
    private List<Tournee> mesTournee;

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

    public List<Tournee> getMesTournee() {
        return mesTournee;
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

    public void addTournee(Tournee tourn) {
        this.mesTournee.add(tourn); 
        if (tourn.getDateDebut().compareTo(this.dateDebut) < 0) {
            this.dateDebut = tourn.getDateDebut();
        }
        if (tourn.getDateFin().compareTo(this.dateFin) > 0) {
            this.dateFin = tourn.getDateFin();
        }
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

    public Shift(Solution solu) {

        Date dt = solu.getMonInstance().getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        dateDebut = dt;
        dateFin = solu.getMonInstance().getDate(); //les valeurs sont arbitrairement inateniable
        solution = solu;
        mesTournee = new LinkedList<>();
    }

//=============Methodes====================
    public long duree() { //retourne la durée du shift
        long a = this.dateFin.getTime() - this.dateDebut.getTime(); //getTime convert date to Timestamp
        return a / 60000; //en minute
    }

    public void update() { //permet de mettre à jour les date de debut et de fin à partir des tournée qui le compose
        Date debut = this.mesTournee.get(0).getDateDebut();
        Date fin = this.mesTournee.get(0).getDateFin();
        for (int i = 1; i < mesTournee.size(); i++) {
            if (this.mesTournee.get(i).getDateDebut().compareTo(this.dateDebut) < 0) {
                debut = this.mesTournee.get(i).getDateDebut();
            }
            if (this.mesTournee.get(i).getDateFin().compareTo(this.dateFin) > 0) {
                fin = this.mesTournee.get(i).getDateFin();
            }
        }
        this.dateDebut = debut;
        this.dateFin = fin;
    }

    public int tempsMort() {

        int sum = 0;
        for (Tournee test : this.mesTournee) {
            sum += test.duree();
        }
        int a = (int) (this.duree() - sum);
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

    public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
        DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Shift s1 = new Shift(format2.parse("03/12/2019 08:00"), format2.parse("03/12/2019 12:00"), new Solution());
        Shift s2 = new Shift(format2.parse("02/12/2019 08:00"), format2.parse("02/12/2019 12:00"), new Solution());

    }
}
