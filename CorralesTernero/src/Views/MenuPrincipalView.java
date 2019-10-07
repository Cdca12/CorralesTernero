package Views;

import javax.swing.*;
import Controller.*;

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
    
    private void abrirAñadirCria() {
        AñadirCriaView añadirCriaView = new AñadirCriaView();
        AñadirCriaModel añadirCriaModel = new AñadirCriaModel();
        AñadirCriaController añadirCriaController = new AñadirCriaController(añadirCriaModel, añadirCriaView);

        añadirCriaView.setController(añadirCriaController);
        añadirCriaView.lanzarVista();
    }
    
    
}
