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
    @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e")
    , @NamedQuery(name = "Estados.findByEstadoID", query = "SELECT e FROM Estados e WHERE e.estadoID = :estadoID")
    , @NamedQuery(name = "Estados.findByNombre", query = "SELECT e FROM Estados e WHERE e.nombre = :nombre")})
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String estadoID;
    @Basic(optional = false)
    private String nombre;
    @OneToMany(mappedBy = "estadoID")
    private Collection<Corrales> corralesCollection;

    public Estados() {
    }

    public Estados(String estadoID) {
        this.estadoID = estadoID;
    }

    public Estados(String estadoID, String nombre) {
        this.estadoID = estadoID;
        this.nombre = nombre;
    }

    public String getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(String estadoID) {
        this.estadoID = estadoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (estadoID != null ? estadoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.estadoID == null && other.estadoID != null) || (this.estadoID != null && !this.estadoID.equals(other.estadoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Estados[ estadoID=" + estadoID + " ]";
    }
    
}
