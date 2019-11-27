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
    private JMenu criasJMenu, sensoresJMenu, simulacionesJMenu;
    private JMenuItem añadirCriaJMenuItem, consultarCriasJMenuItem, reporteCriasEnfermasJMenuItem, procesarSalidasCriasJMenuItem,
            analizarSeñalesSensoresJMenuItem, sacrificarCriasJMenuItem, simularSeñalesJMenuItem, avanzarDiasJMenuItem;
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
        sensoresJMenu = new JMenu("Sensores");
        simulacionesJMenu = new JMenu("Simulaciones");

        // MenuItem
        añadirCriaJMenuItem = new JMenuItem("Añadir Crías");
        consultarCriasJMenuItem = new JMenuItem("Consultar Crias");
        reporteCriasEnfermasJMenuItem = new JMenuItem("Reporte Crías Enfermas");
        procesarSalidasCriasJMenuItem = new JMenuItem("Procesar Salidas");
        sacrificarCriasJMenuItem = new JMenuItem("Sacrificar Crias");
        
        analizarSeñalesSensoresJMenuItem = new JMenuItem("Analizar Señales");
        
        simularSeñalesJMenuItem = new JMenuItem("Simular Señales");
        avanzarDiasJMenuItem = new JMenuItem("Avanzar Días");

        criasJMenu.add(añadirCriaJMenuItem);
        criasJMenu.add(consultarCriasJMenuItem);
        criasJMenu.add(reporteCriasEnfermasJMenuItem);
        criasJMenu.add(procesarSalidasCriasJMenuItem);
        criasJMenu.add(sacrificarCriasJMenuItem);
        
        sensoresJMenu.add(analizarSeñalesSensoresJMenuItem);
        
        simulacionesJMenu.add(simularSeñalesJMenuItem);
        simulacionesJMenu.add(avanzarDiasJMenuItem);
        
        menuBar.add(criasJMenu);
        menuBar.add(sensoresJMenu);
        menuBar.add(simulacionesJMenu);
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
        consultarCriasJMenuItem.addActionListener(menuPrincipalController);
        reporteCriasEnfermasJMenuItem.addActionListener(menuPrincipalController);
        procesarSalidasCriasJMenuItem.addActionListener(menuPrincipalController);
        analizarSeñalesSensoresJMenuItem.addActionListener(menuPrincipalController);
        sacrificarCriasJMenuItem.addActionListener(menuPrincipalController);
        simularSeñalesJMenuItem.addActionListener(menuPrincipalController);
        avanzarDiasJMenuItem.addActionListener(menuPrincipalController);
    }

    // Getters
    public JMenuItem getAñadirCriaJMenu() {
        return añadirCriaJMenuItem;
    }

    public JMenuItem getReporteCriasEnfermasJMenu() {
        return reporteCriasEnfermasJMenuItem;
    }

    public JMenuItem getProcesarSalidasCriasJMenu() {
        return procesarSalidasCriasJMenuItem;
    }

    public JMenuItem getSimularSeñalesJMenuItem() {
        return simularSeñalesJMenuItem;
    }

    public JMenuItem getAnalizarSeñalesSensoresJMenuItem() {
        return analizarSeñalesSensoresJMenuItem;
    }

    public JMenuItem getSacrificarCriasJMenuItem() {
        return sacrificarCriasJMenuItem;
    }

    public JMenuItem getConsultarCriasJMenuItem() {
        return consultarCriasJMenuItem;
    }
    
    public JMenuItem getAvanzarDiasJMenuItem() {
        return avanzarDiasJMenuItem;
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
    
    public void abrirSimularSeñales() {
        SimularSeñalesView simularSeñalesView = new SimularSeñalesView();
        SimularSeñalesModel simularSeñalesModel = new SimularSeñalesModel();
        SimularSeñalesController simularSeñalesController = new SimularSeñalesController(simularSeñalesModel, simularSeñalesView);
        
        simularSeñalesView.setController(simularSeñalesController);
        simularSeñalesView.launchView();
    }
    
    public void abrirAnalizarSeñalesSensores() {
        AnalizarSeñalesSensoresView analizarSeñalesSensoresView = new AnalizarSeñalesSensoresView();
        AnalizarSeñalesSensoresModel analizarSeñalesSensoresModel = new AnalizarSeñalesSensoresModel();
        AnalizarSeñalesSensoresController analizarSeñalesSensoresController = new AnalizarSeñalesSensoresController(analizarSeñalesSensoresModel, analizarSeñalesSensoresView);
        
        analizarSeñalesSensoresView.setController(analizarSeñalesSensoresController);
        analizarSeñalesSensoresView.launchView();
    }
    
    public void abrirConsultarCrias() {
        ConsultarCriasView consultarCriasView = new ConsultarCriasView();
        ConsultarCriasModel consultarCriasModel = new ConsultarCriasModel();
        ConsultarCriasController consultarCriasController = new ConsultarCriasController(consultarCriasModel, consultarCriasView);
        
        consultarCriasView.setController(consultarCriasController);
        consultarCriasView.launchView();
    }
    
    public void abrirAvanzarDias() {
        AvanzarDiasView avanzarDiasView = new AvanzarDiasView();
        AvanzarDiasModel avanzarDiasModel = new AvanzarDiasModel();
        AvanzarDiasController avanzarDiasController = new AvanzarDiasController(avanzarDiasModel, avanzarDiasView);
        
        avanzarDiasView.setController(avanzarDiasController);
        avanzarDiasView.launchView();
    }

    
    public void mostrarMensajeSacrificarCrias() {
        JOptionPane.showMessageDialog(null, "Crias Sacrificadas", "Las Crías se han sacrificado correctamente", JOptionPane.INFORMATION_MESSAGE);
    }
}
