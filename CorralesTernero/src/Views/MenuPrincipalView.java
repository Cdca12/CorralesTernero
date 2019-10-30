package Views;

import javax.swing.*;
import Controllers.*;
import Models.*;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalView extends JFrame {

    private MenuPrincipalController menuPrincipalController;

    private JMenuBar menuBar;
    private JMenu criasJMenu;
    private JMenuItem añadirCriaJMenuItem, reporteCriasEnfermasJMenuItem, procesarSalidasCriasJMenuItem;
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

        // Menu
        criasJMenu = new JMenu("Crías");

        // MenuItem
        añadirCriaJMenuItem = new JMenuItem("Añadir Crías");
        reporteCriasEnfermasJMenuItem = new JMenuItem("Reporte Crías Enfermas");
        procesarSalidasCriasJMenuItem = new JMenuItem("Procesar Salidas");

        criasJMenu.add(añadirCriaJMenuItem);
        criasJMenu.add(reporteCriasEnfermasJMenuItem);
        criasJMenu.add(procesarSalidasCriasJMenuItem);

        
        menuBar.add(criasJMenu);
    }

    public void launchView() {
        setVisible(true);
        menuPrincipalController.probarConexionBaseDeDatos();
    }

    public void setController(MenuPrincipalController menuPrincipalController) {
        this.menuPrincipalController = menuPrincipalController;
        addListeners();
    }

    private void addListeners() {
        añadirCriaJMenuItem.addActionListener(menuPrincipalController);
        reporteCriasEnfermasJMenuItem.addActionListener(menuPrincipalController);
        procesarSalidasCriasJMenuItem.addActionListener(menuPrincipalController);
    }

    // Getters
    public JMenuItem getAñadirCria() {
        return añadirCriaJMenuItem;
    }

    public JMenuItem getReporteCriasEnfermas() {
        return reporteCriasEnfermasJMenuItem;
    }

    public JMenuItem getProcesarSalidasCrias() {
        return procesarSalidasCriasJMenuItem;
    }
    

    // Métodos para abrir los menús
    public void abrirAñadirCria() {
        AñadirCriaView añadirCriaView = new AñadirCriaView();
        AñadirCriaModel añadirCriaModel = new AñadirCriaModel();
        AñadirCriaController añadirCriaController = new AñadirCriaController(añadirCriaModel, añadirCriaView);

        añadirCriaView.setController(añadirCriaController);
        añadirCriaView.launchView();
    }
    
    public void abrirReporteCriasEnfermas() {
        ReporteCriasEnfermasView reporteCriasEnfermasView = new ReporteCriasEnfermasView();
        ReporteCriasEnfermasModel reporteCriasEnfermasModel = new ReporteCriasEnfermasModel();
        ReporteCriasEnfermasController reporteCriasEnfermasController = new ReporteCriasEnfermasController(reporteCriasEnfermasModel, reporteCriasEnfermasView);
        
        reporteCriasEnfermasView.setController(reporteCriasEnfermasController);
        reporteCriasEnfermasView.launchView();
    }
    
    public void abrirProcesarSalidasCrias() {
        ProcesarSalidasCriasView procesarSalidasCriasView = new ProcesarSalidasCriasView();
        ProcesarSalidasCriasModel procesarSalidasCriasModel = new ProcesarSalidasCriasModel();
        ProcesarSalidasCriasController procesarSalidasCriasController = new ProcesarSalidasCriasController(procesarSalidasCriasModel, procesarSalidasCriasView);
        
        procesarSalidasCriasView.setController(procesarSalidasCriasController);
        procesarSalidasCriasView.launchView();
    }
    
    

}
