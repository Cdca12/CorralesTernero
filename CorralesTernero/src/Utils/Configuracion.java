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

    private static Map<Integer, String> Menu;
    private static Map<Integer, String> MenuItem;

    public Configuracion(Token tk) {
        // Obtener configuración
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return;
        }
        String SQL = "EXECUTE spObtenerConfiguracion '" + tk.getUser() + "'";
        ResultSet resultQuery;
        Menu = new HashMap<Integer, String>();
        MenuItem = new HashMap<Integer, String>();
        try {
            // Obtenemos todos los JMenu
            SQL += ", 'Menu'";
            resultQuery = conexion.executeQuery(SQL);
            int MenuID;
            String MenuName;
            for (int i = 0; resultQuery.next(); i++) {
                MenuID = resultQuery.getInt(1);
                MenuName = resultQuery.getString(2);
                Menu.put(MenuID, MenuName);
            }

            // Obtenemos todos los JMenuItem
            SQL += ", 'MenuItem'";
            resultQuery = conexion.executeQuery(SQL);
            int MenuItemID;
            String MenuItemName;
            for (int i = 0; resultQuery.next(); i++) {
                MenuItemID = resultQuery.getInt(1);
                MenuItemName = resultQuery.getString(2);
                MenuItem.put(MenuItemID, MenuItemName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public static Map<Integer, String> getMenu() {
        return Menu;
    }

    public static void setMenu(Map<Integer, String> Menu) {
        Configuracion.Menu = Menu;
    }

    public static Map<Integer, String> getMenuItem() {
        return MenuItem;
    }

    public static void setMenuItem(Map<Integer, String> MenuItem) {
        Configuracion.MenuItem = MenuItem;
    }

    

}
