package Controllers;

import Models.SimularSeñalesModel;
import Utils.Status;
import Views.SimularSeñalesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos Contreras
 */
public class SimularSeñalesController implements ActionListener {

    private SimularSeñalesModel simularSeñalesModel;
    private SimularSeñalesView simularSeñalesView;

    public SimularSeñalesController(SimularSeñalesModel simularSeñalesModel, SimularSeñalesView simularSeñalesView) {
        this.simularSeñalesModel = simularSeñalesModel;
        this.simularSeñalesView = simularSeñalesView;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == simularSeñalesView.getBtnIniciarSimulacion()) {
            String sensorID = simularSeñalesView.getTxtSensorID().getText();
            int numeroSimulaciones = Integer.parseInt(simularSeñalesView.getTxtNumeroSimulaciones().getText());
            ejecutarAccion(simularSeñalesModel.simularSeñales(sensorID, numeroSimulaciones));
            return;
        }
    }
    
    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_SIMULAR_SEÑALES.CODE) {
            simularSeñalesView.showOkMessage(s.MESSAGE, s.TITLE);
            simularSeñalesView.limpiarCampos();
            return;
        }
        if (s.CODE == Status.ERROR_SIMULAR_SEÑALES.CODE) {
            simularSeñalesView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }

}
