package Utils;

import DataAccesor.SQLConnectionHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carlos Contreras
 */
public final class Configuracion {

    public static class JMenu {

        private static ArrayList<Integer> MenuID;
        private static ArrayList<String> MenuName;

        public static ArrayList<Integer> getMenuID() {
            return MenuID;
        }

        public static ArrayList<String> getMenuName() {
            return MenuName;
        }
        
    }
    
    public static class JMenuItem {

        private static ArrayList<Integer> MenuID;
        private static ArrayList<String> MenuName;
        private static ArrayList<Integer> MenuItemID;
        private static ArrayList<String> MenuItemName;

        public static ArrayList<Integer> getMenuID() {
            return MenuID;
        }

        public static ArrayList<String> getMenuName() {
            return MenuName;
        }

        public static ArrayList<Integer> getMenuItemID() {
            return MenuItemID;
        }

        public static ArrayList<String> getMenuItemName() {
            return MenuItemName;
        }

    }

    private static boolean configured = false;
    
    public Configuracion(Token tk) {
        // Ya se configuró, salir
        if (configured) {
            return;
        }
        String SQL;
        ResultSet resultQuery;

        // Obtener configuración
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return;
        }

        SQL = "SELECT MenuID, MenuName FROM ObtenerConfiguracionView "
                + "WHERE Username = '" + tk.getUser() + "' "
                + "GROUP BY MenuID, MenuName";
        JMenu.MenuID = new ArrayList<>();
        JMenu.MenuName = new ArrayList<>();

        try {
            // Obtenemos todos los JMenu
            resultQuery = conexion.executeQuery(SQL);
            while (resultQuery.next()) {
                JMenu.MenuID.add(resultQuery.getInt(1));
                JMenu.MenuName.add(resultQuery.getString(2));
            }

            SQL = "SELECT MenuID, MenuName, MenuItemID, MenuItemName FROM ObtenerConfiguracionView "
                    + "WHERE Username = '" + tk.getUser() + "'";
            JMenuItem.MenuID = new ArrayList<>();
            JMenuItem.MenuName = new ArrayList<>();
            JMenuItem.MenuItemID = new ArrayList<>();
            JMenuItem.MenuItemName = new ArrayList<>();

            // Obtenemos todos los JMenuItem
            resultQuery = conexion.executeQuery(SQL);
            while (resultQuery.next()) {
                JMenuItem.MenuID.add(resultQuery.getInt(1));
                JMenuItem.MenuName.add(resultQuery.getString(2));
                JMenuItem.MenuItemID.add(resultQuery.getInt(3));
                JMenuItem.MenuItemName.add(resultQuery.getString(4));
            }
            configured = true; // Configuración obtenida
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    
    
    
    
    
    

}
