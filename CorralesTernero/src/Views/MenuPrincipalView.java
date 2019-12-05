package Views;

import javax.swing.*;
import Controllers.*;
import Models.*;
import java.awt.Image;
import Utils.Config;
import Utils.Configuracion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalView extends JFrame {

    private MenuPrincipalController menuPrincipalController;

    private JMenuBar menuBar;
    public JMenu[] arregloJMenu;
    public JMenuItem[] arregloJMenuItem;
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

        // Cargar ventanas Configuración
        arregloJMenu = new JMenu[14];
        arregloJMenuItem = new JMenuItem[14];

        cargarJMenu();
        cargarJMenuItem();
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
        for (int i = 0; i < arregloJMenuItem.length; i++) {
//            if (arregloJMenuItem[i] != null) {
                arregloJMenuItem[i].addActionListener(menuPrincipalController);
//            }
        }
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

    public void abrirConsultarDietas() {
        ConsultarDietasView dietasView = new ConsultarDietasView(Config.CONSULTA);
        ConsultarDietasModel dietasModel = new ConsultarDietasModel();
        ConsultarDietasController dietasController = new ConsultarDietasController(dietasModel, dietasView);

        dietasView.setController(dietasController);
        dietasView.launchView();
    }

    public void abrirConsultarCorrales() {
        ConsultarCorralesView corralesView = new ConsultarCorralesView(Config.CONSULTA);
        ConsultarCorralesModel corralesModel = new ConsultarCorralesModel();
        ConsultarCorralesController corralesController = new ConsultarCorralesController(corralesModel, corralesView);

        corralesView.setController(corralesController);
        corralesView.launchView();
    }

    public void abrirConsultarAlimentos() {
        ConsultarAlimentosView alimentosView = new ConsultarAlimentosView(Config.CONSULTA);
        ConsultarAlimentosModel alimentosModel = new ConsultarAlimentosModel();
        ConsultarAlimentosController alimentosController = new ConsultarAlimentosController(alimentosModel, alimentosView);

        alimentosView.setController(alimentosController);
        alimentosView.launchView();
    }

    public void abrirConsultarCriasASacrificar() {
        ConsultarCriasASacrificarView consultarAlimentosView = new ConsultarCriasASacrificarView();
        ConsultarCriasASacrificarModel consultarAlimentosModel = new ConsultarCriasASacrificarModel();
        ConsultarCriasASacrificarController consultarAlimentosController = new ConsultarCriasASacrificarController(consultarAlimentosModel, consultarAlimentosView);

        consultarAlimentosView.setController(consultarAlimentosController);
        consultarAlimentosView.launchView();
    }

    private void cargarJMenu() {
        ArrayList<Integer> MenuID = Configuracion.JMenu.getMenuID();
        ArrayList<String> MenuName = Configuracion.JMenu.getMenuName();

        // Inicializar JMenu
        for (int i = 0; i < MenuID.size(); i++) {
            arregloJMenu[MenuID.get(i) - 1] = new JMenu(MenuName.get(i));
            menuBar.add(arregloJMenu[i]);
        }
    }

    private void cargarJMenuItem() {
        ArrayList<Integer> MenuID = Configuracion.JMenuItem.getMenuID();
        ArrayList<String> MenuName = Configuracion.JMenuItem.getMenuName();
        ArrayList<Integer> MenuItemID = Configuracion.JMenuItem.getMenuItemID();
        ArrayList<String> MenuItemName = Configuracion.JMenuItem.getMenuItemName();

        // Inicializar JMenuItem
        for (int i = 0; i < MenuItemID.size(); i++) {
            arregloJMenuItem[MenuItemID.get(i) - 1] = new JMenuItem(MenuItemName.get(i));

            // Enlazar JMenuItem a su JMenu correspondiente
            arregloJMenu[MenuID.get(i) - 1].add(arregloJMenuItem[MenuItemID.get(i) - 1]);
        }
    }

}
