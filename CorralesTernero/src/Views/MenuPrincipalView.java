package Views;

import javax.swing.*;
import Controllers.*;
import Models.*;

import DataAccesor.*;
import java.sql.*;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalView extends JFrame {

    private MenuPrincipalController menuPrincipalController;

    private JMenuBar menuBar;
    private JMenu criasJMenu;
    private JMenuItem añadirCriaJMenuItem;
    private JLabel logoEmpresa;

    public MenuPrincipalView() {
        super("Corrales Ternero");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        // Test bd
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            JOptionPane.showMessageDialog(null, "Conexión NO realizada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Conexion SI realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        SQLConnectionHelper.closeConnection();

    }

    private void initComponents() {
        // TODO: Inicializar logo empresa con el logo correspondiente

        menuBar = new JMenuBar();
        menuBar.setSize(100, 100);
        setJMenuBar(menuBar);

        criasJMenu = new JMenu("Crías");

        añadirCriaJMenuItem = new JMenuItem("Añadir Crías");

        criasJMenu.add(añadirCriaJMenuItem);

        menuBar.add(criasJMenu);
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(MenuPrincipalController menuPrincipalController) {
        this.menuPrincipalController = menuPrincipalController;
        // TODO: Añadir escuchadores
        addListeners();
    }

    private void addListeners() {
        añadirCriaJMenuItem.addActionListener(menuPrincipalController);
    }

    // Getters
    public JMenuItem getAñadirCria() {
        return añadirCriaJMenuItem;
    }

    // Métodos para abrir los menús
    public void abrirAñadirCria() {
        AñadirCriaView añadirCriaView = new AñadirCriaView();
        AñadirCriaModel añadirCriaModel = new AñadirCriaModel();
        AñadirCriaController añadirCriaController = new AñadirCriaController(añadirCriaModel, añadirCriaView);

        añadirCriaView.setController(añadirCriaController);
        añadirCriaView.launchView();
    }

}
