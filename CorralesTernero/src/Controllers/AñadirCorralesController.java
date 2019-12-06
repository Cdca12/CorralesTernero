package Controllers;

import Entities.*;
import Views.*;
import Models.*;
import Utils.Status;
import java.awt.event.*;
import java.util.List;

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

    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_INSERT_CORRAL.CODE) {
            añadirCorralesView.showOkMessage(s.MESSAGE, s.TITLE);
            añadirCorralesView.limpiarCampos();
            return;
        }
        if (s.CODE == Status.ERROR_INSERT_CORRAL.CODE) {
            añadirCorralesView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }

    public List<TipoCorral> obtenerTipoCorral() {
        return añadirCorralesModel.obtenerTipoCorral();
    }

    public List<Estados> obtenerEstados() {
        return añadirCorralesModel.obtenerEstados();
    }

}
