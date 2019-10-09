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
    @NamedQuery(name = "Alimentos.findAll", query = "SELECT a FROM Alimentos a")
    , @NamedQuery(name = "Alimentos.findByAlimentoID", query = "SELECT a FROM Alimentos a WHERE a.alimentoID = :alimentoID")
    , @NamedQuery(name = "Alimentos.findByNombre", query = "SELECT a FROM Alimentos a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Alimentos.findByCantidad", query = "SELECT a FROM Alimentos a WHERE a.cantidad = :cantidad")})
public class Alimentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer alimentoID;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    private int cantidad;
    @OneToMany(mappedBy = "alimentoID")
    private Collection<Dietas> dietasCollection;

    public Alimentos() {
    }

    public Alimentos(Integer alimentoID) {
        this.alimentoID = alimentoID;
    }

    public Alimentos(Integer alimentoID, String nombre, int cantidad) {
        this.alimentoID = alimentoID;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Integer getAlimentoID() {
        return alimentoID;
    }

    public void setAlimentoID(Integer alimentoID) {
        this.alimentoID = alimentoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public Collection<Dietas> getDietasCollection() {
        return dietasCollection;
    }

    public void setDietasCollection(Collection<Dietas> dietasCollection) {
        this.dietasCollection = dietasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alimentoID != null ? alimentoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimentos)) {
            return false;
        }
        Alimentos other = (Alimentos) object;
        if ((this.alimentoID == null && other.alimentoID != null) || (this.alimentoID != null && !this.alimentoID.equals(other.alimentoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Alimentos[ alimentoID=" + alimentoID + " ]";
    }
    
}
