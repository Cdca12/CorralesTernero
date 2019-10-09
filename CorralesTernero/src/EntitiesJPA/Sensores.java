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
    @NamedQuery(name = "Sensores.findAll", query = "SELECT s FROM Sensores s")
    , @NamedQuery(name = "Sensores.findBySe\u00f1al", query = "SELECT s FROM Sensores s WHERE s.se\u00f1al = :se\u00f1al")
    , @NamedQuery(name = "Sensores.findBySensorID", query = "SELECT s FROM Sensores s WHERE s.sensorID = :sensorID")
    , @NamedQuery(name = "Sensores.findByPresionArterial", query = "SELECT s FROM Sensores s WHERE s.presionArterial = :presionArterial")
    , @NamedQuery(name = "Sensores.findByRespiracion", query = "SELECT s FROM Sensores s WHERE s.respiracion = :respiracion")
    , @NamedQuery(name = "Sensores.findByPulso", query = "SELECT s FROM Sensores s WHERE s.pulso = :pulso")
    , @NamedQuery(name = "Sensores.findByTemperatura", query = "SELECT s FROM Sensores s WHERE s.temperatura = :temperatura")})
public class Sensores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer señal;
    @Basic(optional = false)
    private int sensorID;
    @Basic(optional = false)
    private int presionArterial;
    @Basic(optional = false)
    private int respiracion;
    @Basic(optional = false)
    private int pulso;
    @Basic(optional = false)
    private int temperatura;
    @OneToMany(mappedBy = "sensorID")
    private Collection<Crias> criasCollection;

    public Sensores() {
    }

    public Sensores(Integer señal) {
        this.señal = señal;
    }

    public Sensores(Integer señal, int sensorID, int presionArterial, int respiracion, int pulso, int temperatura) {
        this.señal = señal;
        this.sensorID = sensorID;
        this.presionArterial = presionArterial;
        this.respiracion = respiracion;
        this.pulso = pulso;
        this.temperatura = temperatura;
    }

    public Integer getSeñal() {
        return señal;
    }

    public void setSeñal(Integer señal) {
        this.señal = señal;
    }

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }

    public int getRespiracion() {
        return respiracion;
    }

    public void setRespiracion(int respiracion) {
        this.respiracion = respiracion;
    }

    public int getPulso() {
        return pulso;
    }

    public void setPulso(int pulso) {
        this.pulso = pulso;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
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
        hash += (señal != null ? señal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensores)) {
            return false;
        }
        Sensores other = (Sensores) object;
        if ((this.señal == null && other.señal != null) || (this.señal != null && !this.señal.equals(other.señal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Sensores[ se\u00f1al=" + señal + " ]";
    }
    
}
