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
    @NamedQuery(name = "TipoCorral.findAll", query = "SELECT t FROM TipoCorral t")
    , @NamedQuery(name = "TipoCorral.findByTipoCorralID", query = "SELECT t FROM TipoCorral t WHERE t.tipoCorralID = :tipoCorralID")
    , @NamedQuery(name = "TipoCorral.findByDescripcion", query = "SELECT t FROM TipoCorral t WHERE t.descripcion = :descripcion")})
public class TipoCorral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer tipoCorralID;
    @Basic(optional = false)
    private String descripcion;
    @OneToMany(mappedBy = "tipoCorralID")
    private Collection<Corrales> corralesCollection;

    public TipoCorral() {
    }

    public TipoCorral(Integer tipoCorralID) {
        this.tipoCorralID = tipoCorralID;
    }

    public TipoCorral(Integer tipoCorralID, String descripcion) {
        this.tipoCorralID = tipoCorralID;
        this.descripcion = descripcion;
    }

    public Integer getTipoCorralID() {
        return tipoCorralID;
    }

    public void setTipoCorralID(Integer tipoCorralID) {
        this.tipoCorralID = tipoCorralID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Corrales> getCorralesCollection() {
        return corralesCollection;
    }

    public void setCorralesCollection(Collection<Corrales> corralesCollection) {
        this.corralesCollection = corralesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCorralID != null ? tipoCorralID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCorral)) {
            return false;
        }
        TipoCorral other = (TipoCorral) object;
        if ((this.tipoCorralID == null && other.tipoCorralID != null) || (this.tipoCorralID != null && !this.tipoCorralID.equals(other.tipoCorralID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.TipoCorral[ tipoCorralID=" + tipoCorralID + " ]";
    }
    
}
