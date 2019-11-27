package Controllers;

import Models.AvanzarDiasModel;
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
            avanzarDiasModel.avanzarDias(Integer.parseInt(avanzarDiasView.getTxtDias().getText()));
            avanzarDiasView.limpiarCampos();
            return;
        }
    }
    
}
