/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2i;

import java.io.Serializable;
import java.sql.SQLException;
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
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false)
    private double cout;

    @JoinColumn(nullable = false)
    private Instance monInstance;
    
//==========Getter================================
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCout() {
        return cout;
    }

    public Instance getMonInstance() {
        return monInstance;
    }
    
//==========Constructor================================

    public Solution() throws ClassNotFoundException, SQLException {
        this.cout = 0.0;
        this.monInstance = new Instance();
    }
    
    public Solution(double cout, Instance inst) throws ClassNotFoundException, SQLException {
        this.cout = cout;
        this.monInstance = inst;
    }

//==========Methode================================
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solution)) {
            return false;
        }
        Solution other = (Solution) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solution{" + "cout=" + cout + ", monInstance=" + monInstance + '}';
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Solution sol1 = new Solution();
        System.out.println(sol1);
    }
}
