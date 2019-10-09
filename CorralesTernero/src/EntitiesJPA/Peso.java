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
    @NamedQuery(name = "Peso.findAll", query = "SELECT p FROM Peso p")
    , @NamedQuery(name = "Peso.findByPesoID", query = "SELECT p FROM Peso p WHERE p.pesoID = :pesoID")
    , @NamedQuery(name = "Peso.findByCondicionCorporal", query = "SELECT p FROM Peso p WHERE p.condicionCorporal = :condicionCorporal")})
public class Peso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer pesoID;
    @Basic(optional = false)
    private String condicionCorporal;
    @OneToMany(mappedBy = "pesoID")
    private Collection<Crias> criasCollection;

    public Peso() {
    }

    public Peso(Integer pesoID) {
        this.pesoID = pesoID;
    }

    public Peso(Integer pesoID, String condicionCorporal) {
        this.pesoID = pesoID;
        this.condicionCorporal = condicionCorporal;
    }

    public Integer getPesoID() {
        return pesoID;
    }

    public void setPesoID(Integer pesoID) {
        this.pesoID = pesoID;
    }

    public String getCondicionCorporal() {
        return condicionCorporal;
    }

    public void setCondicionCorporal(String condicionCorporal) {
        this.condicionCorporal = condicionCorporal;
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
        hash += (pesoID != null ? pesoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peso)) {
            return false;
        }
        Peso other = (Peso) object;
        if ((this.pesoID == null && other.pesoID != null) || (this.pesoID != null && !this.pesoID.equals(other.pesoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Peso[ pesoID=" + pesoID + " ]";
    }
    
}
