package Controllers;

import Models.ProcesarSalidasCriasModel;
import Views.ProcesarSalidasCriasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Controller
 */
public class ProcesarSalidasCriasController implements ActionListener, ListSelectionListener {

    private ProcesarSalidasCriasModel procesarSalidasCriasModel;
    private ProcesarSalidasCriasView procesarSalidasCriasView;

    public ProcesarSalidasCriasController(ProcesarSalidasCriasModel procesarSalidasCriasModel, ProcesarSalidasCriasView procesarSalidasCriasView) {
        this.procesarSalidasCriasView = procesarSalidasCriasView;
        this.procesarSalidasCriasModel = procesarSalidasCriasModel;
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        return procesarSalidasCriasModel.obtenerDatosTabla();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == procesarSalidasCriasView.getBtnProcesar()) {
            int row = procesarSalidasCriasView.getTablaProcesarSalidasCrias().getSelectedRow();
            String criaID = procesarSalidasCriasView.getTablaProcesarSalidasCrias().getModel().getValueAt(row, 0).toString();
            procesarSalidasCriasModel.procesarSalidas(criaID);
            return;
        }
        if(evt.getSource() == procesarSalidasCriasView.getBtnProcesarAll()) {
            procesarSalidasCriasModel.procesarSalidasAll();
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getSource() == procesarSalidasCriasView.getTablaProcesarSalidasCrias().getSelectionModel()) {
            procesarSalidasCriasView.getBtnProcesar().setEnabled(true);
            return;
        }
    }
    
    
    
    
    
}
