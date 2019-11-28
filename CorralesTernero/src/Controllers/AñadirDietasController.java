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
public class AñadirDietasController implements ActionListener {
    
    private AñadirDietasModel añadirDietasModel;
    private AñadirDietasView añadirDietasView;

    public AñadirDietasController(AñadirDietasModel añadirDietasModel, AñadirDietasView añadirDietasView) {
        this.añadirDietasView = añadirDietasView;
        this.añadirDietasModel = añadirDietasModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == añadirDietasView.getBtnAñadir()) {
            Dietas dieta = new Dietas();
            dieta.setDiasTratamiento(añadirDietasView.getTxtDiasTratamiento().getText());
            dieta.setAlimentoID(añadirDietasView.getTxtAlimentoID().getText());
            ejecutarAccion(añadirDietasModel.añadirDieta(dieta));
            return;
        }
        if (evt.getSource() == añadirDietasView.getBtnLimpiar()) {
            añadirDietasView.limpiarCampos();
            return;
        }
        if (evt.getSource() == añadirDietasView.getBtnSeleccionarAlimento()) {
            añadirDietasView.abrirSeleccionAlimentos();
            return;
        }
    }
    
    private void ejecutarAccion(int statusAccion) {
        if (statusAccion == Status.ERROR_INSERT.CODE) {
            añadirDietasView.showErrorMessage(Status.ERROR_INSERT.TITLE, Status.ERROR_INSERT.MESSAGE);
            return;
        }
        if (statusAccion == Status.OK_INSERT.CODE) {
            añadirDietasView.showOkMessage(Status.OK_INSERT.TITLE, Status.OK_INSERT.MESSAGE);
            añadirDietasView.limpiarCampos();
            return;
        }
    }
}
