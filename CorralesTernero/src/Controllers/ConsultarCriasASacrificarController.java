package Controllers;

import DataAccesor.SQLConnectionHelper;
import Models.ConsultarCriasASacrificarModel;
import Utils.Status;
import Views.ConsultarCriasASacrificarView;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCriasASacrificarController implements ActionListener, ListSelectionListener {

    private ConsultarCriasASacrificarModel consultarCriasASacrificarModel;
    private ConsultarCriasASacrificarView consultarCriasASacrificarView;

    public ConsultarCriasASacrificarController(ConsultarCriasASacrificarModel consultarCriasASacrificarModel, ConsultarCriasASacrificarView consultarCriasASacrificarView) {
        this.consultarCriasASacrificarModel = consultarCriasASacrificarModel;
        this.consultarCriasASacrificarView = consultarCriasASacrificarView;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return consultarCriasASacrificarModel.obtenerDatosTabla();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == consultarCriasASacrificarView.getBtnSacrificar()) {
            int row = consultarCriasASacrificarView.getTablaCriasASacrificar().getSelectedRow();
            String criaID = consultarCriasASacrificarView.getTablaCriasASacrificar().getModel().getValueAt(row, 2).toString();
            ejecutarAccion(consultarCriasASacrificarModel.sacrificarCria(criaID));
            consultarCriasASacrificarView.getBtnSacrificar().setEnabled(false);
            return;
        }
        if (evt.getSource() == consultarCriasASacrificarView.getBtnSacrificarAll()) {
            ejecutarAccion(consultarCriasASacrificarModel.sacrificarCriaAll());
            return;
        }
        if (evt.getSource() == consultarCriasASacrificarView.getBtnImprimir()) {
            consultarCriasASacrificarView.imprimirTabla();
            return;
        }
    }

    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_SACRIFICAR.CODE) {
            consultarCriasASacrificarView.showOkMessage(s.MESSAGE, s.TITLE);
            consultarCriasASacrificarView.generarTablaResultados();
            consultarCriasASacrificarView.getTablaCriasASacrificar().updateUI();
            return;
        }
        if (s.CODE == Status.ERROR_SACRIFICAR.CODE) {
            consultarCriasASacrificarView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }

        if (s.CODE == Status.OK_SACRIFICAR_ALL.CODE) {
            consultarCriasASacrificarView.showOkMessage(s.MESSAGE, s.TITLE);
            consultarCriasASacrificarView.generarTablaResultados();
            consultarCriasASacrificarView.getTablaCriasASacrificar().updateUI();
            return;
        }
        if (s.CODE == Status.ERROR_SACRIFICAR_ALL.CODE) {
            consultarCriasASacrificarView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getSource() == consultarCriasASacrificarView.getTablaCriasASacrificar().getSelectionModel()) {
            consultarCriasASacrificarView.getBtnSacrificar().setEnabled(true);
            return;
        }
    }

}
