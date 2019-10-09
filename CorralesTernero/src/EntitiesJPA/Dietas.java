/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesJPA;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Contreras
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dietas.findAll", query = "SELECT d FROM Dietas d")
    , @NamedQuery(name = "Dietas.findByDietaID", query = "SELECT d FROM Dietas d WHERE d.dietaID = :dietaID")
    , @NamedQuery(name = "Dietas.findByDiasTratamiento", query = "SELECT d FROM Dietas d WHERE d.diasTratamiento = :diasTratamiento")})
public class Dietas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer dietaID;
    @Basic(optional = false)
    private int diasTratamiento;
    @JoinColumn(name = "AlimentoID", referencedColumnName = "AlimentoID")
    @ManyToOne
    private Alimentos alimentoID;
    @OneToMany(mappedBy = "dietaID")
    private Collection<Crias> criasCollection;

    public Dietas() {
    }

    public Dietas(Integer dietaID) {
        this.dietaID = dietaID;
    }

    public Dietas(Integer dietaID, int diasTratamiento) {
        this.dietaID = dietaID;
        this.diasTratamiento = diasTratamiento;
    }

    public Integer getDietaID() {
        return dietaID;
    }

    public void setDietaID(Integer dietaID) {
        this.dietaID = dietaID;
    }

    public int getDiasTratamiento() {
        return diasTratamiento;
    }

    public void setDiasTratamiento(int diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    public Alimentos getAlimentoID() {
        return alimentoID;
    }

    public void setAlimentoID(Alimentos alimentoID) {
        this.alimentoID = alimentoID;
    }

    @XmlTransient
    public Collection<Crias> getCriasCollection() {
        return criasCollection;
    }

    public void setCriasCollection(Collection<Crias> criasCollection) {
        this.criasCollection = criasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dietaID != null ? dietaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dietas)) {
            return false;
        }
        Dietas other = (Dietas) object;
        if ((this.dietaID == null && other.dietaID != null) || (this.dietaID != null && !this.dietaID.equals(other.dietaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Dietas[ dietaID=" + dietaID + " ]";
    }
    
}
