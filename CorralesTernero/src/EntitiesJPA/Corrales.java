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
    @NamedQuery(name = "Corrales.findAll", query = "SELECT c FROM Corrales c")
    , @NamedQuery(name = "Corrales.findByCorralID", query = "SELECT c FROM Corrales c WHERE c.corralID = :corralID")})
public class Corrales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer corralID;
    @OneToMany(mappedBy = "corralID")
    private Collection<Crias> criasCollection;
    @OneToMany(mappedBy = "corralID")
    private Collection<TrasladosCrias> trasladosCriasCollection;
    @JoinColumn(name = "EstadoID", referencedColumnName = "EstadoID")
    @ManyToOne
    private Estados estadoID;
    @JoinColumn(name = "TipoCorralID", referencedColumnName = "TipoCorralID")
    @ManyToOne
    private TipoCorral tipoCorralID;

    public Corrales() {
    }

    public Corrales(Integer corralID) {
        this.corralID = corralID;
    }

    public Integer getCorralID() {
        return corralID;
    }

    public void setCorralID(Integer corralID) {
        this.corralID = corralID;
    }

    @XmlTransient
    public Collection<Crias> getCriasCollection() {
        return criasCollection;
    }

    public void setCriasCollection(Collection<Crias> criasCollection) {
        this.criasCollection = criasCollection;
    }

    @XmlTransient
    public Collection<TrasladosCrias> getTrasladosCriasCollection() {
        return trasladosCriasCollection;
    }

    public void setTrasladosCriasCollection(Collection<TrasladosCrias> trasladosCriasCollection) {
        this.trasladosCriasCollection = trasladosCriasCollection;
    }

    public Estados getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(Estados estadoID) {
        this.estadoID = estadoID;
    }

    public TipoCorral getTipoCorralID() {
        return tipoCorralID;
    }

    public void setTipoCorralID(TipoCorral tipoCorralID) {
        this.tipoCorralID = tipoCorralID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (corralID != null ? corralID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corrales)) {
            return false;
        }
        Corrales other = (Corrales) object;
        if ((this.corralID == null && other.corralID != null) || (this.corralID != null && !this.corralID.equals(other.corralID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Corrales[ corralID=" + corralID + " ]";
    }
    
}
