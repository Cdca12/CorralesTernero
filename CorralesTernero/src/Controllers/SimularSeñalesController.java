package Controllers;

import Models.SimularSeñalesModel;
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
            simularSeñalesModel.simularSeñales(sensorID, numeroSimulaciones);
            return;
        }
    }

}
