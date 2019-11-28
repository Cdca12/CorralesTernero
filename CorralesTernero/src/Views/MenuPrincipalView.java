package Views;

import javax.swing.*;
import Controllers.*;
import Models.*;
import java.awt.Image;
import Utils.Config;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalView extends JFrame {

    private MenuPrincipalController menuPrincipalController;

    private JMenuBar menuBar;
    private JMenu criasJMenu, sensoresJMenu, simulacionesJMenu, alimentosJMenu, corralesJMenu, dietasJMenu;
    private JMenuItem añadirCriaJMenuItem, consultarCriasJMenuItem, reporteCriasEnfermasJMenuItem, procesarSalidasCriasJMenuItem,
            analizarSeñalesSensoresJMenuItem, sacrificarCriasJMenuItem, simularSeñalesJMenuItem, avanzarDiasJMenuItem,
            añadirAlimentosJMenuItem, consultarAlimentosJMenuItem,
            añadirCorralesJMenuItem, consultarCorralesJMenuItem,
            añadirDietasJMenuItem, consultarDietasJMenuItem;
    private JLabel imagotipo;

    public MenuPrincipalView() {
        super("Corrales Ternero");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon isotipo = new ImageIcon("./src/Resources/isotipo.png");
        setIconImage(isotipo.getImage());

        initComponents();
    }

    private void initComponents() {
        ImageIcon tmpIconAux = new ImageIcon("./src/Resources/imagotipo.png");
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(500,
                450, Image.SCALE_SMOOTH)); //SCALE_DEFAULT
        imagotipo = new JLabel(tmpIcon);
        imagotipo.setBounds(getWidth() / 2, getHeight() / 2, 200, 200);
        add(imagotipo);

        menuBar = new JMenuBar();
        menuBar.setSize(100, 100);
        setJMenuBar(menuBar);

        // Menu
        criasJMenu = new JMenu("Crías");
        sensoresJMenu = new JMenu("Sensores");
        simulacionesJMenu = new JMenu("Simulaciones");
        alimentosJMenu = new JMenu("Alimentos");
        corralesJMenu = new JMenu("Corrales");
        dietasJMenu = new JMenu("Dietas");

        // MenuItem
        añadirCriaJMenuItem = new JMenuItem("Añadir Crías");
        consultarCriasJMenuItem = new JMenuItem("Consultar Crias");
        reporteCriasEnfermasJMenuItem = new JMenuItem("Reporte Crías Enfermas");
        procesarSalidasCriasJMenuItem = new JMenuItem("Procesar Salidas");
        sacrificarCriasJMenuItem = new JMenuItem("Sacrificar Crias");

        añadirAlimentosJMenuItem = new JMenuItem("Añadir Alimentos");
        consultarAlimentosJMenuItem = new JMenuItem("Consultar Alimentos");

        añadirCorralesJMenuItem = new JMenuItem("Añadir Corrales");
        consultarCorralesJMenuItem = new JMenuItem("Consultar Corrales");

        añadirDietasJMenuItem = new JMenuItem("Añadir Dietas");
        consultarDietasJMenuItem = new JMenuItem("Consultar Dietas");

        analizarSeñalesSensoresJMenuItem = new JMenuItem("Analizar Señales");

        simularSeñalesJMenuItem = new JMenuItem("Simular Señales");
        avanzarDiasJMenuItem = new JMenuItem("Avanzar Días");

        criasJMenu.add(añadirCriaJMenuItem);
        criasJMenu.add(consultarCriasJMenuItem);
        criasJMenu.add(reporteCriasEnfermasJMenuItem);
        criasJMenu.add(procesarSalidasCriasJMenuItem);
        criasJMenu.add(sacrificarCriasJMenuItem);

        sensoresJMenu.add(analizarSeñalesSensoresJMenuItem);

        alimentosJMenu.add(añadirAlimentosJMenuItem);
        alimentosJMenu.add(consultarAlimentosJMenuItem);

        corralesJMenu.add(añadirCorralesJMenuItem);
        corralesJMenu.add(consultarCorralesJMenuItem);

        dietasJMenu.add(añadirDietasJMenuItem);
        dietasJMenu.add(consultarDietasJMenuItem);

        simulacionesJMenu.add(simularSeñalesJMenuItem);
        simulacionesJMenu.add(avanzarDiasJMenuItem);

        menuBar.add(criasJMenu);
        menuBar.add(corralesJMenu);
        menuBar.add(alimentosJMenu);
        menuBar.add(dietasJMenu);
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
        añadirCorralesJMenuItem.addActionListener(menuPrincipalController);
        añadirAlimentosJMenuItem.addActionListener(menuPrincipalController);
        añadirDietasJMenuItem.addActionListener(menuPrincipalController);
        consultarCorralesJMenuItem.addActionListener(menuPrincipalController);
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

    public JMenuItem getAñadirCorralesJMenuItem() {
        return añadirCorralesJMenuItem;
    }

    public JMenuItem getAñadirAlimentosJMenuItem() {
        return añadirAlimentosJMenuItem;
    }

    public JMenuItem getAñadirDietasJMenuItem() {
        return añadirDietasJMenuItem;
    }

    public JMenuItem getConsultarCorralesJMenuItem() {
        return consultarCorralesJMenuItem;
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

    public void abrirAñadirCorrales() {
        AñadirCorralesView añadirCorralesView = new AñadirCorralesView();
        AñadirCorralesModel añadirCorralesModel = new AñadirCorralesModel();
        AñadirCorralesController añadirCorralesController = new AñadirCorralesController(añadirCorralesModel, añadirCorralesView);

        añadirCorralesView.setController(añadirCorralesController);
        añadirCorralesView.launchView();
    }
    
    public void abrirAñadirAlimentos() {
        AñadirAlimentosView añadirAlimentosView = new AñadirAlimentosView();
        AñadirAlimentosModel añadirAlimentosModel = new AñadirAlimentosModel();
        AñadirAlimentosController añadirAlimentosController = new AñadirAlimentosController(añadirAlimentosModel, añadirAlimentosView);
        
        añadirAlimentosView.setController(añadirAlimentosController);
        añadirAlimentosView.launchView();
    }
    
    public void abrirAñadirDietas() {
        AñadirDietasView añadirDietasView = new AñadirDietasView();
        AñadirDietasModel añadirDietasModel = new AñadirDietasModel();
        AñadirDietasController añadirDietasController = new AñadirDietasController(añadirDietasModel, añadirDietasView);
        
        añadirDietasView.setController(añadirDietasController);
        añadirDietasView.launchView();
    }
    
    public void abrirConsultarCorrales() {
        CorralesView corralesView = new CorralesView(Config.CONSULTA);
        CorralesModel corralesModel = new CorralesModel();
        CorralesController corralesController = new CorralesController(corralesModel, corralesView);
        
        corralesView.setController(corralesController);
        corralesView.launchView();
    }

    public void mostrarMensajeSacrificarCrias() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas clasificar crías?", "Sacrificar Crías", JOptionPane.WARNING_MESSAGE);
        if (opcion == 1) {
            JOptionPane.showMessageDialog(null, "Las Crías se han sacrificado correctamente", "Crias Sacrificadas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

    }

}
