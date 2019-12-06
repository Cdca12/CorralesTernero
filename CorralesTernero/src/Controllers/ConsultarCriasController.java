package Controllers;

import DataAccesor.SQLConnectionHelper;
import Models.ConsultarCriasModel;
import Views.ConsultarCriasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCriasController implements ActionListener, KeyListener {

    private ConsultarCriasModel consultarCriasModel;
    private ConsultarCriasView consultarCriasView;

    public ConsultarCriasController(ConsultarCriasModel consultarCriasModel, ConsultarCriasView consultarCriasView) {
        this.consultarCriasView = consultarCriasView;
        this.consultarCriasModel = consultarCriasModel;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return consultarCriasModel.obtenerDatosTabla();
    }

    public Vector<Vector<String>> obtenerDatosTabla(String atributo, String valor) {
        return consultarCriasModel.obtenerDatosTabla();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == consultarCriasView.getCmbFiltro()) {
            consultarCriasView.getTxtConsultar().setText("");
            if (consultarCriasView.getCmbFiltro().getSelectedIndex() == 0) {
                consultarCriasView.getBtnConsultar().setEnabled(false);
                consultarCriasView.getTxtConsultar().setEditable(false);
                return;
            }
            consultarCriasView.getTxtConsultar().setEditable(true);
            consultarCriasView.getTxtConsultar().requestFocus();
        }
        if (evt.getSource() == consultarCriasView.getBtnConsultar()) {
            String atributo = consultarCriasView.getCmbFiltro().getSelectedItem().toString();
            String valor = consultarCriasView.getTxtConsultar().getText();
            Vector<Vector<String>> datosTablaCrias = consultarCriasModel.obtenerDatosTabla(atributo, valor);
            consultarCriasView.generarDatosTabla(datosTablaCrias);
            return;
        }
        if (evt.getSource() == consultarCriasView.getBtnConsultarTodas()) {
            Vector<Vector<String>> datosTablaCrias = consultarCriasModel.obtenerDatosTabla();
            consultarCriasView.generarDatosTabla(datosTablaCrias);
            consultarCriasView.getCmbFiltro().setSelectedIndex(0);
            return;
        }
        if (evt.getSource() == consultarCriasView.getBtnImprimir()) {
            consultarCriasView.imprimirTabla();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // Nada
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // Nada
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource() == consultarCriasView.getTxtConsultar()) {
            if (consultarCriasView.getTxtConsultar().getText().length() > 0) {
                consultarCriasView.getBtnConsultar().setEnabled(true);
                return;
            }
            consultarCriasView.getBtnConsultar().setEnabled(false);
            return;
        }
    }

}
