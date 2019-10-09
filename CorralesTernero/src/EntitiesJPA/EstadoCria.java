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
    @NamedQuery(name = "EstadoCria.findAll", query = "SELECT e FROM EstadoCria e")
    , @NamedQuery(name = "EstadoCria.findByEstadoCriaID", query = "SELECT e FROM EstadoCria e WHERE e.estadoCriaID = :estadoCriaID")
    , @NamedQuery(name = "EstadoCria.findByDescripcion", query = "SELECT e FROM EstadoCria e WHERE e.descripcion = :descripcion")})
public class EstadoCria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer estadoCriaID;
    @Basic(optional = false)
    private String descripcion;
    @OneToMany(mappedBy = "estadoCriaID")
    private Collection<Crias> criasCollection;

    public EstadoCria() {
    }

    public EstadoCria(Integer estadoCriaID) {
        this.estadoCriaID = estadoCriaID;
    }

    public EstadoCria(Integer estadoCriaID, String descripcion) {
        this.estadoCriaID = estadoCriaID;
        this.descripcion = descripcion;
    }

    public Integer getEstadoCriaID() {
        return estadoCriaID;
    }

    public void setEstadoCriaID(Integer estadoCriaID) {
        this.estadoCriaID = estadoCriaID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (estadoCriaID != null ? estadoCriaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCria)) {
            return false;
        }
        EstadoCria other = (EstadoCria) object;
        if ((this.estadoCriaID == null && other.estadoCriaID != null) || (this.estadoCriaID != null && !this.estadoCriaID.equals(other.estadoCriaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.EstadoCria[ estadoCriaID=" + estadoCriaID + " ]";
    }
    
}
