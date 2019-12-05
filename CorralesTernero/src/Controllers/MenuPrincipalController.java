package Controllers;

import Models.*;
import Views.*;
import java.awt.event.*;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalController implements ActionListener {

    private MenuPrincipalModel menuPrincipalModel;
    private MenuPrincipalView menuPrincipalView;

    public MenuPrincipalController(MenuPrincipalModel menuPrincipalModel, MenuPrincipalView menuPrincipalView) {
        this.menuPrincipalModel = menuPrincipalModel;
        this.menuPrincipalView = menuPrincipalView;
    }

    public void probarConexionBaseDeDatos() {
        menuPrincipalModel.pruebaConexionBaseDeDatos();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Añadir Crias
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[0]) {
            menuPrincipalView.abrirAñadirCria();
            return;
        }
        // Consultar Crias
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[1]) {
            menuPrincipalView.abrirConsultarCrias();
            return;
        }
        // Reporte Crias Enfermas
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[2]) {
            menuPrincipalView.abrirReporteCriasEnfermas();
            return;
        }
        // Procesar Salidas
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[3]) {
            menuPrincipalView.abrirProcesarSalidasCrias();
            return;
        }
        // Sacrificar Crias
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[4]) {
            menuPrincipalView.abrirConsultarCriasASacrificar();
            return;
        }
        // Añadir Corrales
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[5]) {
            menuPrincipalView.abrirAñadirCorrales();
            return;
        }
        // Consultar Corrales
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[6]) {
            menuPrincipalView.abrirConsultarCorrales();
            return;
        }
        // Añadir Alimentos
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[7]) {
            menuPrincipalView.abrirAñadirAlimentos();
            return;
        }
        // Consultar Alimentos
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[8]) {
            menuPrincipalView.abrirConsultarAlimentos();
            return;
        }
        // Añadir Dietas
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[9]) {
            menuPrincipalView.abrirAñadirDietas();
            return;
        }
        // Consultar Dietas
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[10]) {
            menuPrincipalView.abrirConsultarDietas();
            return;
        }
        // Añadir Sensores
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[11]) {
            menuPrincipalView.abrirAñadirSensores();
            return;
        }
        // Consultar Sensores
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[12]) {
            menuPrincipalView.abrirConsultarSensores();
            return;
        }
        // Analizar Sensores
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[13]) {
            menuPrincipalView.abrirAnalizarSeñalesSensores();
            return;
        }
        // Simular Señales
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[14]) {
            menuPrincipalView.abrirSimularSeñales();
            return;
        }
        // Avanzar Días
        if (evt.getSource() == menuPrincipalView.arregloJMenuItem[15
                ]) {
            menuPrincipalView.abrirAvanzarDias();
            return;
        }
    }

}
