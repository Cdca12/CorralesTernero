package Entities;

import DataAccesor.SQLConnectionHelper;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase que modela la entidad de Crias
 *
 * @author Carlos Contreras
 */
public class Crias {

    private String CriasID;
    private String CorralID;
    private String PesoID;
    private String GrasaCoberturaID;
    private String MusculoID;
    private String EstadoCriaID;
    private String DietaID;
    private String SensorID;
    private String VecesEnTratamiento;

    public Crias(String CriasID, String CorralID, String PesoID, String GrasaCoberturaID, String MusculoID, String EstadoCriaID, String DietaID, String SensorID, String VecesEnTratamiento) {
        this.CriasID = CriasID;
        this.CorralID = CorralID;
        this.PesoID = PesoID;
        this.GrasaCoberturaID = GrasaCoberturaID;
        this.MusculoID = MusculoID;
        this.EstadoCriaID = EstadoCriaID;
        this.DietaID = DietaID;
        this.SensorID = SensorID;
        this.VecesEnTratamiento = VecesEnTratamiento;
    }
    

    public static synchronized void añadirCria(Crias cria) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Compruebe"
                    + " su conexión a internet", "ERROR: Conexión NO realizada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            conexion.execute("INSERT INTO Crias VALUES ("
                    + cria.CorralID + ", "
                    + cria.PesoID + ", "
                    + cria.GrasaCoberturaID + ", "
                    + cria.MusculoID + ", "
                    + cria.EstadoCriaID + ", "
                    + cria.DietaID + ", "
                    + cria.SensorID + ", "
                    + cria.VecesEnTratamiento + ");");
        } catch (SQLException ex) {
            Logger.getLogger(Crias.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Errorsazo");
        }
    }

}
