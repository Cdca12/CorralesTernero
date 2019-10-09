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

    public synchronized int añadirCria(String CorralID, String PesoID, String GrasaCoberturaID, String MusculoID, String EstadoCriaID, String DietaID) {
        Crias cria = new Crias();
        cria.setCorralID(CorralID);
        cria.setPesoID(PesoID);
        cria.setGrasaCoberturaID(GrasaCoberturaID);
        cria.setMusculoID(MusculoID);
        cria.setEstadoCriaID(EstadoCriaID);
        cria.setDietaID(DietaID);
        return Crias.añadirCria(cria);
    }

    public List<Peso> obtenerPesos() {
        List<Peso> listaPesos = new ArrayList<Peso>();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery("SELECT * FROM Peso;");
            Peso peso;
            while (resultQuery.next()) {
                peso = new Peso();
                peso.setPesoID(resultQuery.getString("PesoID"));
                peso.setCondicionCorporal(resultQuery.getString("CondicionCorporal"));
                listaPesos.add(peso);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaPesos;
    }

    public List<GrasaCobertura> obtenerGrasasCobertura() {
        List<GrasaCobertura> listaGrasasCobertura = new ArrayList<GrasaCobertura>();
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

    public List<Musculo> obtenerMusculos() {

        return null;
    }

    public List<EstadoCria> obtenerEstadosCria() {

        return null;
    }

    public List<Dieta> obtenerDietas() {

        return null;
    }

}
