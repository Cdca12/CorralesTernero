package Controllers;

import Models.AvanzarDiasModel;
import Utils.Status;
import Views.AvanzarDiasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos Contreras
 */
public class AvanzarDiasController implements ActionListener {
    
    private AvanzarDiasModel avanzarDiasModel;
    private AvanzarDiasView avanzarDiasView;

    public AvanzarDiasController(AvanzarDiasModel avanzarDiasModel, AvanzarDiasView avanzarDiasView) {
        this.avanzarDiasModel = avanzarDiasModel;
        this.avanzarDiasView = avanzarDiasView;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == avanzarDiasView.getBtnAvanzarDias()) {
            ejecutarAccion(avanzarDiasModel.avanzarDias(Integer.parseInt(avanzarDiasView.getTxtDias().getText())));
            return;
        }
    }
    
    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_AVANZAR_DIAS.CODE) {
            avanzarDiasView.showOkMessage(s.MESSAGE, s.TITLE);
            avanzarDiasView.limpiarCampos();
            return;
        }
        if (s.CODE == Status.ERROR_AVANZAR_DIAS.CODE) {
            avanzarDiasView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }
    
}
