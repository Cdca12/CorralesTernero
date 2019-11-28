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
            Crias cria = new Crias();
            cria.setCorralID(añadirCriaView.getTxtCorral().getText());
            cria.setPeso(añadirCriaView.getTxtPeso().getText());
            cria.setGrasa((añadirCriaView.getTxtGrasa().getText()));
            cria.setMusculoID(((Musculo) añadirCriaView.getCmbTipoMusculo().getSelectedItem()).getMusculoID());
            cria.setDietaID((añadirCriaView.getTxtDieta().getText()));
            ejecutarAccion(añadirCriaModel.añadirCria(cria));
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
