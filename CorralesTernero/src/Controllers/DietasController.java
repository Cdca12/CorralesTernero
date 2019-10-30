package Controllers;

import Models.DietasModel;
import Views.DietasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class DietasController implements ActionListener, ListSelectionListener {

    private DietasModel dietasModel;
    private DietasView dietasView;

    public DietasController(DietasModel dietasModel, DietasView dietasView) {
        this.dietasView = dietasView;
        this.dietasModel = dietasModel;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return dietasModel.obtenerDatosTabla();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == dietasView.getBtnSeleccionar()) {
            dietasView.guardarId();
            return;
        }
        if(evt.getSource() == dietasView.getBtnCancelar()) {
            dietasView.dispose();
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getSource() == dietasView.getTablaDietas().getSelectionModel()) {
            dietasView.getBtnSeleccionar().setEnabled(true);
            return;
        }
    }

}
