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
 * @author cdca1
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
                    añadirCriaView.getTxtCorralID().getText(),
                    añadirCriaView.getTxtPesoID().getText(),
                    añadirCriaView.getTxtGrasaCoberturaID().getText(),
                    añadirCriaView.getTxtMusculoID().getText(),
                    añadirCriaView.getTxtEstadoCriaID().getText(),
                    añadirCriaView.getTxtDietaID().getText()));
            return;
        }
        if (evt.getSource() == añadirCriaView.getBtnLimpiar()) {
            añadirCriaView.limpiarCampos();
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
                break;
        }
    }

    public List<Peso> obtenerPesos() {
        return añadirCriaModel.obtenerPesos();
    }

    public List<GrasaCobertura> obtenerGrasasCobertura() {
        return añadirCriaModel.obtenerGrasasCobertura();
    }

    public List<Musculo> obtenerMusculos() {
        return añadirCriaModel.obtenerMusculos();
    }

    public List<EstadoCria> obtenerEstadosCria() {
        return añadirCriaModel.obtenerEstadosCria();
    }

    public List<Dietas> obtenerDietas() {
        return añadirCriaModel.obtenerDietas();
    }

}
