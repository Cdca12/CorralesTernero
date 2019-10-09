/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesJPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Contreras
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrasladosCrias.findAll", query = "SELECT t FROM TrasladosCrias t")
    , @NamedQuery(name = "TrasladosCrias.findByTransaccion", query = "SELECT t FROM TrasladosCrias t WHERE t.transaccion = :transaccion")
    , @NamedQuery(name = "TrasladosCrias.findByFechaIngreso", query = "SELECT t FROM TrasladosCrias t WHERE t.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "TrasladosCrias.findByFechaEgreso", query = "SELECT t FROM TrasladosCrias t WHERE t.fechaEgreso = :fechaEgreso")
    , @NamedQuery(name = "TrasladosCrias.findByDiasEnCorral", query = "SELECT t FROM TrasladosCrias t WHERE t.diasEnCorral = :diasEnCorral")})
public class TrasladosCrias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer transaccion;
    @Basic(optional = false)
    private String fechaIngreso;
    private String fechaEgreso;
    @Basic(optional = false)
    private int diasEnCorral;
    @JoinColumn(name = "CorralID", referencedColumnName = "CorralID")
    @ManyToOne
    private Corrales corralID;
    @JoinColumn(name = "CriasID", referencedColumnName = "CriasID")
    @ManyToOne
    private Crias criasID;

    public TrasladosCrias() {
    }

    public TrasladosCrias(Integer transaccion) {
        this.transaccion = transaccion;
    }

    public TrasladosCrias(Integer transaccion, String fechaIngreso, int diasEnCorral) {
        this.transaccion = transaccion;
        this.fechaIngreso = fechaIngreso;
        this.diasEnCorral = diasEnCorral;
    }

    public Integer getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Integer transaccion) {
        this.transaccion = transaccion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public int getDiasEnCorral() {
        return diasEnCorral;
    }

    public void setDiasEnCorral(int diasEnCorral) {
        this.diasEnCorral = diasEnCorral;
    }

    public Corrales getCorralID() {
        return corralID;
    }

    public void setCorralID(Corrales corralID) {
        this.corralID = corralID;
    }

    public Crias getCriasID() {
        return criasID;
    }

    public void setCriasID(Crias criasID) {
        this.criasID = criasID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccion != null ? transaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrasladosCrias)) {
            return false;
        }
        TrasladosCrias other = (TrasladosCrias) object;
        if ((this.transaccion == null && other.transaccion != null) || (this.transaccion != null && !this.transaccion.equals(other.transaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.TrasladosCrias[ transaccion=" + transaccion + " ]";
    }
    
}
