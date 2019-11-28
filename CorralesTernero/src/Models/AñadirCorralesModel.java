package Models;

import DataAccesor.SQLConnectionHelper;
import Entities.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCorralesModel {

    public AñadirCorralesModel() {

    }

    public synchronized int añadirCorral(Corrales corral) {
        return Corrales.añadirCorral(corral);
    }

    public List<TipoCorral> obtenerTipoCorral() {
        List<TipoCorral> listaTipoCorral = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM TipoCorral;");
            TipoCorral tipoCorral;
            while (resultQuery.next()) {
                tipoCorral = new TipoCorral();
                tipoCorral.setTipoCorralID(resultQuery.getString("TipoCorralID"));
                tipoCorral.setDescripcion(resultQuery.getString("Descripcion"));
                listaTipoCorral.add(tipoCorral);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaTipoCorral;
    }
    
    public List<Estados> obtenerEstados() {
        List<Estados> listaEstados = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM Estados;");
            Estados estado;
            while (resultQuery.next()) {
                estado = new Estados();
                estado.setEstadoID(resultQuery.getString("EstadoID"));
                estado.setNombre(resultQuery.getString("Nombre"));
                listaEstados.add(estado);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaEstados;
    }
    
    
}
