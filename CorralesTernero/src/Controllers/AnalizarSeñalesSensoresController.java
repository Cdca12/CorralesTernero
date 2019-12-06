package Controllers;

import Models.AnalizarSeñalesSensoresModel;
import Utils.Status;
import Views.AnalizarSeñalesSensoresView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class AnalizarSeñalesSensoresController implements ActionListener, ListSelectionListener {

    private AnalizarSeñalesSensoresModel analizarSeñalesSensoresModel;
    private AnalizarSeñalesSensoresView analizarSeñalesSensoresView;

    public AnalizarSeñalesSensoresController(AnalizarSeñalesSensoresModel analizarSeñalesSensoresModel, AnalizarSeñalesSensoresView analizarSeñalesSensoresView) {
        this.analizarSeñalesSensoresModel = analizarSeñalesSensoresModel;
        this.analizarSeñalesSensoresView = analizarSeñalesSensoresView;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return analizarSeñalesSensoresModel.obtenerDatosTabla();
    }

    public Vector<Vector<String>> obtenerDatosTablaPropensosEnfermarse() {
        return analizarSeñalesSensoresModel.obtenerDatosTablaPropensosEnfermarse();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnCriasPropensasEnfermarse()) {
            analizarSeñalesSensoresView.actualizarTablaPropensosEnfermarse();
            analizarSeñalesSensoresView.getBtnAñadirCuarentena().setEnabled(false);
            return;
        }
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnAñadirCuarentena()) {
            int row = analizarSeñalesSensoresView.getTablaSeñalesSensores().getSelectedRow();
            String criaID = analizarSeñalesSensoresView.getTablaSeñalesSensores().getModel().getValueAt(row, 6).toString();
            ejecutarAccion(analizarSeñalesSensoresModel.añadirCuarentena(criaID));
            return;
        }
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnAñadirCuarentenaAll()) {
            ejecutarAccion(analizarSeñalesSensoresModel.añadirCuarentenaAll());
            return;
        }
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnImprimir()) {
            analizarSeñalesSensoresView.imprimirTabla();
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getSource() == analizarSeñalesSensoresView.getTablaSeñalesSensores().getSelectionModel()) {
            analizarSeñalesSensoresView.getBtnAñadirCuarentena().setEnabled(true);
            return;
        }
    }

    private void ejecutarAccion(Status s) {
        if (s.CODE == Status.OK_ADD_CUARENTENA.CODE) {
            analizarSeñalesSensoresView.showOkMessage(s.MESSAGE, s.TITLE);
            analizarSeñalesSensoresView.generarTablaPropensasEnfermarse();
            analizarSeñalesSensoresView.getTablaSeñalesSensores().updateUI();
            return;
        }
        if (s.CODE == Status.ERROR_ADD_CUARENTENA.CODE) {
            analizarSeñalesSensoresView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }

        if (s.CODE == Status.OK_ADD_CUARENTENA_ALL.CODE) {
            analizarSeñalesSensoresView.showOkMessage(s.MESSAGE, s.TITLE);
            analizarSeñalesSensoresView.generarTablaPropensasEnfermarse();
            analizarSeñalesSensoresView.getTablaSeñalesSensores().updateUI();
            return;
        }
        if (s.CODE == Status.ERROR_ADD_CUARENTENA_ALL.CODE) {
            analizarSeñalesSensoresView.showErrorMessage(s.MESSAGE, s.TITLE);
            return;
        }
    }

}
