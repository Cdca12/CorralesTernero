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
    @NamedQuery(name = "GrasaCobertura.findAll", query = "SELECT g FROM GrasaCobertura g")
    , @NamedQuery(name = "GrasaCobertura.findByGrasaCoberturaID", query = "SELECT g FROM GrasaCobertura g WHERE g.grasaCoberturaID = :grasaCoberturaID")
    , @NamedQuery(name = "GrasaCobertura.findByTipo", query = "SELECT g FROM GrasaCobertura g WHERE g.tipo = :tipo")
    , @NamedQuery(name = "GrasaCobertura.findByDescripcion", query = "SELECT g FROM GrasaCobertura g WHERE g.descripcion = :descripcion")})
public class GrasaCobertura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer grasaCoberturaID;
    @Basic(optional = false)
    private String tipo;
    @Basic(optional = false)
    private String descripcion;
    @OneToMany(mappedBy = "grasaCoberturaID")
    private Collection<Crias> criasCollection;

    public GrasaCobertura() {
    }

    public GrasaCobertura(Integer grasaCoberturaID) {
        this.grasaCoberturaID = grasaCoberturaID;
    }

    public GrasaCobertura(Integer grasaCoberturaID, String tipo, String descripcion) {
        this.grasaCoberturaID = grasaCoberturaID;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Integer getGrasaCoberturaID() {
        return grasaCoberturaID;
    }

    public void setGrasaCoberturaID(Integer grasaCoberturaID) {
        this.grasaCoberturaID = grasaCoberturaID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (grasaCoberturaID != null ? grasaCoberturaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrasaCobertura)) {
            return false;
        }
        GrasaCobertura other = (GrasaCobertura) object;
        if ((this.grasaCoberturaID == null && other.grasaCoberturaID != null) || (this.grasaCoberturaID != null && !this.grasaCoberturaID.equals(other.grasaCoberturaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.GrasaCobertura[ grasaCoberturaID=" + grasaCoberturaID + " ]";
    }
    
}
