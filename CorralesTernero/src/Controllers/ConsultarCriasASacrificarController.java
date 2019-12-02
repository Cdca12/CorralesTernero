package Controllers;

import DataAccesor.SQLConnectionHelper;
import Models.ConsultarCriasASacrificarModel;
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
            consultarCriasASacrificarModel.sacrificarCria(criaID);
            return;
        }
        if (evt.getSource() == consultarCriasASacrificarView.getBtnCancelar()) {
            consultarCriasASacrificarView.dispose();
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
