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
        if (evt.getSource() == menuPrincipalView.getAñadirCria()) {
            menuPrincipalView.abrirAñadirCria();
            return;
        }
        if (evt.getSource() == menuPrincipalView.getReporteCriasEnfermas()) {
            menuPrincipalView.abrirReporteCriasEnfermas();
            return;
        }
        if (evt.getSource() == menuPrincipalView.getProcesarSalidasCrias()) {
            menuPrincipalView.abrirProcesarSalidasCrias();
            return;
        }
        if (evt.getSource() == menuPrincipalView.getSimularSeñalesJMenuItem()) {
            menuPrincipalView.abrirSimularSeñales();
            return;
        }
    }
    
}
