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
        // Nada...
        if (evt.getSource() == consultarCriasASacrificarView.getBtnSacrificar()) {

            Statement conexion = SQLConnectionHelper.getConnection();
            if (conexion == null) {
                return;
            }
            try {
                conexion.execute("EXECUTE spSacrificarCrias");
            } catch (SQLException e) {
                return;
            }
        }
        if (evt.getSource() == consultarCriasASacrificarView.getBtnCancelar()) {
            consultarCriasASacrificarView.dispose();
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        // Nada...
    }

}
