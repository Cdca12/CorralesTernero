package Controllers;

import Entities.*;
import Views.*;
import Models.*;
import Utils.Status;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaController implements ActionListener {

    private AñadirCriaModel añadirCriaModel;
    private AñadirCriaView añadirCriaView;

    public AñadirCriaController(AñadirCriaModel añadirCriaModel, AñadirCriaView añadirCriaView) {
        this.añadirCriaView = añadirCriaView;
        this.añadirCriaModel = añadirCriaModel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == añadirCriaView.getBtnAñadir()) {
            ejecutarAccion(añadirCriaModel.añadirCria(
                    añadirCriaView.getTxtCorral().getText(),
                    añadirCriaView.getTxtPeso().getText(),
                    ((GrasaCobertura) añadirCriaView.getCmbGrasaCobertura().getSelectedItem()).getGrasaCoberturaID(),
                    ((Musculo) añadirCriaView.getCmbTipoMusculo().getSelectedItem()).getMusculoID(),
                    añadirCriaView.getTxtDieta().getText()));
            return;
        }
        if (evt.getSource() == añadirCriaView.getBtnLimpiar()) {
            añadirCriaView.limpiarCampos();
            return;
        }
        if (evt.getSource() == añadirCriaView.getBtnSeleccionarCorral()) {
            añadirCriaView.abrirCorrales();
            return;
        }
        if (evt.getSource() == añadirCriaView.getBtnSeleccionarDieta()) {
            añadirCriaView.abrirDietas();
            return;
        }
        
    }

    private void ejecutarAccion(int statusAccion) {
        switch (statusAccion) {
            case Status.ERROR_INSERT:
                añadirCriaView.showErrorMessage(Status.ERROR_INSERT_TITLE, Status.ERROR_INSERT_MESSAGE);
                break;
            case Status.OK_INSERT:
                añadirCriaView.showOkMessage(Status.OK_INSERT_TITLE, Status.OK_INSERT_MESSAGE);
                añadirCriaView.limpiarCampos();
                break;
        }
    }
    
    public List<Corrales> obtenerCorrales() {
        return añadirCriaModel.obtenerCorrales();
    }

    public List<GrasaCobertura> obtenerGrasasCobertura() {
        return añadirCriaModel.obtenerGrasasCobertura();
    }

    public List<Musculo> obtenerTiposMusculo() {
        return añadirCriaModel.obtenerTiposMusculo();
    }

    public List<Dietas> obtenerDietas() {
        return añadirCriaModel.obtenerDietas();
    }

}
