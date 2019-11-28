package Models;

import DataAccesor.SQLConnectionHelper;
import Entities.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Carlos Contreras
 */
public class AñadirAlimentosModel {
    
    public AñadirAlimentosModel() {
        
    }
    
    public synchronized int añadirAlimento(Alimentos alimento) {
        return Alimentos.añadirAlimento(alimento);
    }
}
