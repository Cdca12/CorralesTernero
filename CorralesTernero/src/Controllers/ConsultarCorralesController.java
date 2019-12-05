package Controllers;

import Views.ConsultarCorralesView;
import Models.ConsultarCorralesModel;
import Utils.Tipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCorralesController implements ActionListener, ListSelectionListener {

    private ConsultarCorralesModel corralesModel;
    private ConsultarCorralesView corralesView;

    public ConsultarCorralesController(ConsultarCorralesModel corralesModel, ConsultarCorralesView corralesView) {
        this.corralesView = corralesView;
        this.corralesModel = corralesModel;
    }
    
    public Vector<Vector<String>> obtenerDatosTabla(Tipo config) {
        return corralesModel.obtenerDatosTabla(config);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == corralesView.getBtnSeleccionar()) {
            corralesView.guardarId();
            return;
        }
        if(evt.getSource() == corralesView.getBtnCancelar()) {
            corralesView.dispose();
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getSource() == corralesView.getTablaCorrales().getSelectionModel()) {
            corralesView.getBtnSeleccionar().setEnabled(true);
            corralesView.getBtnSeleccionar().requestFocus();
            return;
        }
    }
    
}
