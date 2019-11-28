package Controllers;

import Entities.*;
import Views.*;
import Models.*;
import Utils.OtroStatus;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirAlimentosController implements ActionListener {

    private AñadirAlimentosModel añadirAlimentosModel;
    private AñadirAlimentosView añadirAlimentosView;

    public AñadirAlimentosController(AñadirAlimentosModel añadirAlimentosModel, AñadirAlimentosView añadirAlimentosView) {
        this.añadirAlimentosView = añadirAlimentosView;
        this.añadirAlimentosModel = añadirAlimentosModel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == añadirAlimentosView.getBtnAñadir()) {
            Alimentos alimento = new Alimentos();
            alimento.setNombre(añadirAlimentosView.getTxtNombre().getText());
            alimento.setCantidad(añadirAlimentosView.getTxtCantidad().getText());
            ejecutarAccion(añadirAlimentosModel.añadirAlimento(alimento));
            return;
        }
        if (evt.getSource() == añadirAlimentosView.getBtnLimpiar()) {
            añadirAlimentosView.limpiarCampos();
            return;
        }
    }

    private void ejecutarAccion(int statusAccion) {
        if (statusAccion == OtroStatus.ERROR_INSERT.CODE) {
            añadirAlimentosView.showErrorMessage(OtroStatus.ERROR_INSERT.TITLE, OtroStatus.ERROR_INSERT.MESSAGE);
            return;
        }
        if (statusAccion == OtroStatus.OK_INSERT.CODE) {
            añadirAlimentosView.showOkMessage(OtroStatus.OK_INSERT.TITLE, OtroStatus.OK_INSERT.MESSAGE);
            añadirAlimentosView.limpiarCampos();
            return;
        }
    }

}
