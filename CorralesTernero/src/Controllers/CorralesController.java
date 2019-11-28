package Controllers;

import Views.CorralesView;
import Models.CorralesModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class CorralesController implements ActionListener, ListSelectionListener {

    private CorralesModel corralesModel;
    private CorralesView corralesView;

    public CorralesController(CorralesModel corralesModel, CorralesView corralesView) {
        this.corralesView = corralesView;
        this.corralesModel = corralesModel;
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        return corralesModel.obtenerDatosTabla();
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
