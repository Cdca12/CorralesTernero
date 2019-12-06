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

    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_INSERT_ALIMENTO.CODE) {
            añadirAlimentosView.showOkMessage(s.MESSAGE, s.TITLE);
            añadirAlimentosView.limpiarCampos();
            return;
        }
        if (s.CODE == Status.ERROR_INSERT_ALIMENTO.CODE) {
            añadirAlimentosView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }

}
