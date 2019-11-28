package Controllers;

import Views.*;
import Models.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Carlos Contreras
 */
public class ConsultarAlimentosController implements ActionListener, ListSelectionListener {
    
    private ConsultarAlimentosModel alimentosModel;
    private ConsultarAlimentosView alimentosView;

    public ConsultarAlimentosController(ConsultarAlimentosModel alimentosModel, ConsultarAlimentosView alimentosView) {
        this.alimentosView = alimentosView;
        this.alimentosModel = alimentosModel;
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        return alimentosModel.obtenerDatosTabla();
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == alimentosView.getBtnSeleccionar()) {
            alimentosView.guardarId();
            return;
        }
        if(evt.getSource() == alimentosView.getBtnCancelar()) {
            alimentosView.dispose();
            return;
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getSource() == alimentosView.getTablaAlimentos().getSelectionModel()) {
            alimentosView.getBtnSeleccionar().setEnabled(true);
            alimentosView.getBtnSeleccionar().requestFocus();
            return;
        }
    }
    
}
