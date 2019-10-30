package Models;

import DataAccesor.SQLConnectionHelper;
import Entities.*;
import Utils.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaModel {

    public AñadirCriaModel() {
        
    }

    public synchronized int añadirCria(Crias cria) {
        return Crias.añadirCria(cria);
    }

    public synchronized int añadirCria(String CorralID, String PesoID, String GrasaCoberturaID, String MusculoID, String DietaID) {
        Crias cria = new Crias();
        cria.setCorralID(CorralID);
        cria.setPesoID(PesoID);
        cria.setGrasaCoberturaID(GrasaCoberturaID);
        cria.setMusculoID(MusculoID);
        cria.setDietaID(DietaID);
        return Crias.añadirCria(cria);
    }
    
    public List<Corrales> obtenerCorrales() {
        List<Corrales> listaCorrales = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM Corrales;");
            Corrales corral;
            while (resultQuery.next()) {
                corral = new Corrales();
                corral.setCorralID(resultQuery.getString("CorralID"));
                corral.setEstadoID(resultQuery.getString("EstadoID"));
                corral.setTipoCorralID(resultQuery.getString("TipoCorralID"));
                listaCorrales.add(corral);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaCorrales;
    }


    public List<GrasaCobertura> obtenerGrasasCobertura() {
        List<GrasaCobertura> listaGrasasCobertura = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM GrasaCobertura;");
            GrasaCobertura grasaCobertura;
            while (resultQuery.next()) {
                grasaCobertura = new GrasaCobertura();
                grasaCobertura.setGrasaCoberturaID(resultQuery.getString("GrasaCoberturaID"));
                grasaCobertura.setTipo(resultQuery.getString("Tipo"));
                grasaCobertura.setDescripcion(resultQuery.getString("Descripcion"));
                listaGrasasCobertura.add(grasaCobertura);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaGrasasCobertura;
    }

    public List<Musculo> obtenerTiposMusculo() {
        List<Musculo> listaDietas = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM Musculo;");
            Musculo dieta;
            while (resultQuery.next()) {
                dieta = new Musculo();
                dieta.setMusculoID(resultQuery.getString("MusculoID"));
                dieta.setColorMusculo(resultQuery.getString("ColorMusculo"));
                listaDietas.add(dieta);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaDietas;
    }

    public List<EstadoCria> obtenerEstadosCria() {
        List<EstadoCria> listaEstadosCria = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM EstadoCria;");
            EstadoCria estadoCria;
            while (resultQuery.next()) {
                estadoCria = new EstadoCria();
                estadoCria.setEstadoCriaID(resultQuery.getString("EstadoCriaID"));
                estadoCria.setDescripcion(resultQuery.getString("Descripcion"));
                listaEstadosCria.add(estadoCria);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaEstadosCria;
    }

    public List<Dietas> obtenerDietas() {
        List<Dietas> listaDietas = new ArrayList();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM Dietas;");
            Dietas dieta;
            while (resultQuery.next()) {
                dieta = new Dietas();
                dieta.setDietaID(resultQuery.getString("DietaID"));
                dieta.setDiasTratamiento(resultQuery.getString("DiasTratamiento"));
                dieta.setAlimentoID(resultQuery.getString("AlimentoID"));
                listaDietas.add(dieta);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaDietas;
    }
    
}
