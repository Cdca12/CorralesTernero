package Controllers;

import Entities.Sensores;
import Models.AñadirSensoresModel;
import Utils.Status;
import Views.AñadirSensoresView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirSensoresController implements ActionListener {
    
    private AñadirSensoresModel añadirSensoresModel;
    private AñadirSensoresView añadirSensoresView;

    public AñadirSensoresController(AñadirSensoresModel añadirSensoresModel, AñadirSensoresView añadirSensoresView) {
        this.añadirSensoresView = añadirSensoresView;
        this.añadirSensoresModel = añadirSensoresModel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == añadirSensoresView.getBtnComprar()) {
            Sensores sensor = new Sensores();
            String marca = añadirSensoresView.getTxtMarca().getText();
            int cantidad = Integer.parseInt(añadirSensoresView.getTxtCantidad().getText());
            sensor.setMarca(marca);
            ejecutarAccion(añadirSensoresModel.añadirSensor(sensor, cantidad));
            return;
        }
        if (evt.getSource() == añadirSensoresView.getBtnLimpiar()) {
            añadirSensoresView.limpiarCampos();
            return;
        }
    }
    
    private void ejecutarAccion(int statusAccion) {
        if (statusAccion == Status.ERROR_INSERT.CODE) {
            añadirSensoresView.showErrorMessage(Status.ERROR_INSERT.TITLE, Status.ERROR_INSERT.MESSAGE);
            return;
        }
        if (statusAccion == Status.OK_INSERT.CODE) {
            añadirSensoresView.showOkMessage(Status.OK_INSERT.TITLE, Status.OK_INSERT.MESSAGE);
            añadirSensoresView.limpiarCampos();
            return;
        }
    }
    
}
