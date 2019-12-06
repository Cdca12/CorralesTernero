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
    
    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_INSERT_DIETA.CODE) {
            añadirDietasView.showOkMessage(s.MESSAGE, s.TITLE);
            añadirDietasView.limpiarCampos();
            return;
        }
        if (s.CODE == Status.ERROR_INSERT_DIETA.CODE) {
            añadirDietasView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }
}
