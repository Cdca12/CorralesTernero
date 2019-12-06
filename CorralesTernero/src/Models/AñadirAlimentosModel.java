package Models;

import DataAccesor.SQLConnectionHelper;
import Entities.*;
import Utils.Status;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Carlos Contreras
 */
public class AñadirAlimentosModel {
    
    public AñadirAlimentosModel() {
        
    }
    
    public synchronized Status añadirAlimento(Alimentos alimento) {
        return Alimentos.añadirAlimento(alimento);
    }
}
