package Controllers;

import Models.ProcesarSalidasCriasModel;
import Utils.Status;
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
            ejecutarAccion(procesarSalidasCriasModel.procesarSalidas(criaID));
            return;
        }
        if(evt.getSource() == procesarSalidasCriasView.getBtnProcesarAll()) {
            ejecutarAccion(procesarSalidasCriasModel.procesarSalidasAll());
            return;
        }
        if(evt.getSource() == procesarSalidasCriasView.getBtnImprimir()) {
            procesarSalidasCriasView.imprimirTabla();
            return;
        }
    }
    
    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_PROCCESS.CODE) {
            procesarSalidasCriasView.showOkMessage(Status.OK_PROCCESS.MESSAGE, Status.OK_PROCCESS.TITLE);
            procesarSalidasCriasView.generarTablaResultados();
            procesarSalidasCriasView.getBtnProcesar().setEnabled(false);
            procesarSalidasCriasView.getTablaProcesarSalidasCrias().updateUI();
            return;
        }
        if (s.CODE == Status.ERROR_PROCCESS.CODE) {
            procesarSalidasCriasView.showErrorMessage(Status.ERROR_PROCCESS.MESSAGE, Status.ERROR_PROCCESS.TITLE);
            procesarSalidasCriasView.getBtnProcesar().setEnabled(false);
            return;
        }
        
        if (s.CODE == Status.OK_PROCCESS_ALL.CODE) {
            procesarSalidasCriasView.showOkMessage(Status.OK_PROCCESS_ALL.MESSAGE, Status.OK_PROCCESS_ALL.TITLE);
            procesarSalidasCriasView.generarTablaResultados();
            procesarSalidasCriasView.getBtnProcesar().setEnabled(false);
            procesarSalidasCriasView.getTablaProcesarSalidasCrias().updateUI();
            return;
        }
        if (s.CODE == Status.ERROR_PROCCESS_ALL.CODE) {
            procesarSalidasCriasView.showErrorMessage(Status.ERROR_PROCCESS_ALL.MESSAGE, Status.ERROR_PROCCESS_ALL.TITLE);
            procesarSalidasCriasView.getBtnProcesar().setEnabled(false);
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
