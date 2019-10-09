/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesJPA;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Statement;
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
    @NamedQuery(name = "Crias.findAll", query = "SELECT c FROM Crias c")
    , @NamedQuery(name = "Crias.findByCriasID", query = "SELECT c FROM Crias c WHERE c.criasID = :criasID")
    , @NamedQuery(name = "Crias.findByVecesEnTratamiento", query = "SELECT c FROM Crias c WHERE c.vecesEnTratamiento = :vecesEnTratamiento")})
public class Crias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer criasID;
    @Basic(optional = false)
    private int vecesEnTratamiento;
    @JoinColumn(name = "CorralID", referencedColumnName = "CorralID")
    @ManyToOne
    private Corrales corralID;
    @JoinColumn(name = "DietaID", referencedColumnName = "DietaID")
    @ManyToOne
    private Dietas dietaID;
    @JoinColumn(name = "EstadoCriaID", referencedColumnName = "EstadoCriaID")
    @ManyToOne
    private EstadoCria estadoCriaID;
    @JoinColumn(name = "GrasaCoberturaID", referencedColumnName = "GrasaCoberturaID")
    @ManyToOne
    private GrasaCobertura grasaCoberturaID;
    @JoinColumn(name = "MusculoID", referencedColumnName = "MusculoID")
    @ManyToOne
    private Musculo musculoID;
    @JoinColumn(name = "PesoID", referencedColumnName = "PesoID")
    @ManyToOne
    private Peso pesoID;
    @JoinColumn(name = "SensorID", referencedColumnName = "Se\u00f1al")
    @ManyToOne
    private Sensores sensorID;
    @OneToMany(mappedBy = "criasID")
    private Collection<TrasladosCrias> trasladosCriasCollection;

    public Crias() {
    }

    public Crias(Integer criasID) {
        this.criasID = criasID;
    }

    public Crias(Integer criasID, int vecesEnTratamiento) {
        this.criasID = criasID;
        this.vecesEnTratamiento = vecesEnTratamiento;
    }

    public Integer getCriasID() {
        return criasID;
    }

    public void setCriasID(Integer criasID) {
        this.criasID = criasID;
    }

    public int getVecesEnTratamiento() {
        return vecesEnTratamiento;
    }

    public void setVecesEnTratamiento(int vecesEnTratamiento) {
        this.vecesEnTratamiento = vecesEnTratamiento;
    }

    public Corrales getCorralID() {
        return corralID;
    }

    public void setCorralID(Corrales corralID) {
        this.corralID = corralID;
    }

    public Dietas getDietaID() {
        return dietaID;
    }

    public void setDietaID(Dietas dietaID) {
        this.dietaID = dietaID;
    }

    public EstadoCria getEstadoCriaID() {
        return estadoCriaID;
    }

    public void setEstadoCriaID(EstadoCria estadoCriaID) {
        this.estadoCriaID = estadoCriaID;
    }

    public GrasaCobertura getGrasaCoberturaID() {
        return grasaCoberturaID;
    }

    public void setGrasaCoberturaID(GrasaCobertura grasaCoberturaID) {
        this.grasaCoberturaID = grasaCoberturaID;
    }

    public Musculo getMusculoID() {
        return musculoID;
    }

    public void setMusculoID(Musculo musculoID) {
        this.musculoID = musculoID;
    }

    public Peso getPesoID() {
        return pesoID;
    }

    public void setPesoID(Peso pesoID) {
        this.pesoID = pesoID;
    }

    public Sensores getSensorID() {
        return sensorID;
    }

    public void setSensorID(Sensores sensorID) {
        this.sensorID = sensorID;
    }

    @XmlTransient
    public Collection<TrasladosCrias> getTrasladosCriasCollection() {
        return trasladosCriasCollection;
    }

    public void setTrasladosCriasCollection(Collection<TrasladosCrias> trasladosCriasCollection) {
        this.trasladosCriasCollection = trasladosCriasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (criasID != null ? criasID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crias)) {
            return false;
        }
        Crias other = (Crias) object;
        if ((this.criasID == null && other.criasID != null) || (this.criasID != null && !this.criasID.equals(other.criasID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corralesternero.Crias[ criasID=" + criasID + " ]";
    }
    
    // Métodos CRUD
    
    public static int añadirCria(Crias cria) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("INSERT INTO Crias VALUES ("
                    + cria.getCorralID() + ", "
                    + cria.getPesoID() + ", "
                    + cria.getGrasaCoberturaID() + ", "
                    + cria.getMusculoID() + ", "
                    + cria.getEstadoCriaID() + ", "
                    + cria.getDietaID() + ", "
                    + cria.getSensorID() + ", "
                    + cria.getVecesEnTratamiento() + ");");
        } catch (SQLException e) {
            return Status.ERROR_INSERT;
        }
        return Status.OK_INSERT;
    }

}
