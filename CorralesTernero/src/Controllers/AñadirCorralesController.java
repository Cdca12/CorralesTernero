package Controllers;

import Entities.*;
import Views.*;
import Models.*;
import Utils.Status;
import java.awt.event.*;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCorralesController implements ActionListener {

    private AñadirCorralesModel añadirCorralesModel;
    private AñadirCorralesView añadirCorralesView;

    public AñadirCorralesController(AñadirCorralesModel añadirCorralesModel, AñadirCorralesView añadirCorralesView) {
        this.añadirCorralesView = añadirCorralesView;
        this.añadirCorralesModel = añadirCorralesModel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == añadirCorralesView.getBtnAñadir()) {
            Corrales corral = new Corrales();
            corral.setTipoCorralID(((TipoCorral) añadirCorralesView.getCmbTipoCorral().getSelectedItem()).getTipoCorralID());
            corral.setEstadoID(((Estados) añadirCorralesView.getCmbEstados().getSelectedItem()).getEstadoID());
            ejecutarAccion(añadirCorralesModel.añadirCorral(corral));
            return;
        }
        if (evt.getSource() == añadirCorralesView.getBtnLimpiar()) {
            añadirCorralesView.limpiarCampos();
            return;
        }

    }

    private void ejecutarAccion(int statusAccion) {
        switch (statusAccion) {
            case Status.ERROR_INSERT:
                añadirCorralesView.showErrorMessage(Status.ERROR_INSERT_TITLE, Status.ERROR_INSERT_MESSAGE);
                break;
            case Status.OK_INSERT:
                añadirCorralesView.showOkMessage(Status.OK_INSERT_TITLE, Status.OK_INSERT_MESSAGE);
                añadirCorralesView.limpiarCampos();
                break;
        }
    }

    public List<TipoCorral> obtenerTipoCorral() {
        return añadirCorralesModel.obtenerTipoCorral();
    }
    
    public List<Estados> obtenerEstados() {
        return añadirCorralesModel.obtenerEstados();
    }

}
