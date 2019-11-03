package Models;

import DataAccesor.SQLConnectionHelper;
import Views.MenuPrincipalView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalModel {

    public MenuPrincipalModel() {

    }

    public void pruebaConexionBaseDeDatos() {
        // Test bd
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Compruebe"
                    + " su conexión a internet", "ERROR: Conexión NO realizada", JOptionPane.ERROR_MESSAGE);
            System.exit(0); // Cerrar programa Java
            return;
        }
//        JOptionPane.showMessageDialog(null, "Conexion SI realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
//        SQLConnectionHelper.closeConnection();

    }

    public void sacrificarCrias() {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return;
        }
        try {
            conexion.execute("EXECUTE spSacrificarCrias");
        } catch (SQLException e) {
            return;
        }
    }
}
