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
    
    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_INSERT_SENSOR.CODE) {
            añadirSensoresView.showOkMessage(s.MESSAGE, s.TITLE);
            añadirSensoresView.limpiarCampos();
            return;
        }
        if (s.CODE == Status.ERROR_INSERT_SENSOR.CODE) {
            añadirSensoresView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }
    
}
