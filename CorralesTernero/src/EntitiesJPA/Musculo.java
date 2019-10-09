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
    @NamedQuery(name = "Musculo.findAll", query = "SELECT m FROM Musculo m")
    , @NamedQuery(name = "Musculo.findByMusculoID", query = "SELECT m FROM Musculo m WHERE m.musculoID = :musculoID")
    , @NamedQuery(name = "Musculo.findByColorMusculo", query = "SELECT m FROM Musculo m WHERE m.colorMusculo = :colorMusculo")})
public class Musculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer musculoID;
    @Basic(optional = false)
    private String colorMusculo;
    @OneToMany(mappedBy = "musculoID")
    private Collection<Crias> criasCollection;

    public Musculo() {
    }

    public Musculo(Integer musculoID) {
        this.musculoID = musculoID;
    }

    public Musculo(Integer musculoID, String colorMusculo) {
        this.musculoID = musculoID;
        this.colorMusculo = colorMusculo;
    }

    public Integer getMusculoID() {
        return musculoID;
    }

    public void setMusculoID(Integer musculoID) {
        this.musculoID = musculoID;
    }

    public String getColorMusculo() {
        return colorMusculo;
    }

    public void setColorMusculo(String colorMusculo) {
        this.colorMusculo = colorMusculo;
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
        hash += (musculoID != null ? musculoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musculo)) {
            return false;
        }
        Musculo other = (Musculo) object;
        if ((this.musculoID == null && other.musculoID != null) || (this.musculoID != null && !this.musculoID.equals(other.musculoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Musculo[ musculoID=" + musculoID + " ]";
    }
    
}
