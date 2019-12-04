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
        Menu = new HashMap<>();
        MenuItem = new HashMap<>();
        try {

            // Obtenemos todos los JMenu
            resultQuery = conexion.executeQuery(SQL + ", 'Menu'");
            int MenuID;
            String MenuName;
            for (int i = 0; resultQuery.next(); i++) {
                MenuID = resultQuery.getInt(1);
                MenuName = resultQuery.getString(2);
                Menu.put(MenuID, MenuName);
            }

            // Obtenemos todos los JMenuItem
            resultQuery = conexion.executeQuery(SQL + ", 'MenuItem'");
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

    public static Map<Integer, String> getMenuConfiguration() {
        return Menu;
    }

    public static Map<Integer, String> getMenuItemConfiguration() {
        return MenuItem;
    }

}
