package Controllers;

import Models.AnalizarSeñalesSensoresModel;
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
            analizarSeñalesSensoresView.isChanged = true;
            return;
        }
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnAñadirCuarentena()) {
            int row = analizarSeñalesSensoresView.getTablaSeñalesSensores().getSelectedRow();
            String criaID = analizarSeñalesSensoresView.getTablaSeñalesSensores().getModel().getValueAt(row, 6).toString();
            analizarSeñalesSensoresModel.añadirCuarentena(criaID);
            return;
        }
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnAñadirCuarentenaAll()) {
            analizarSeñalesSensoresModel.añadirCuarentenaAll();
            return;
        }
    }
    
 

@Override
    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getSource() == analizarSeñalesSensoresView.getTablaSeñalesSensores().getSelectionModel()
                && analizarSeñalesSensoresView.isChanged) {
            analizarSeñalesSensoresView.getBtnAñadirCuarentena().setEnabled(true);
            analizarSeñalesSensoresView.getBtnAñadirCuarentenaAll().setEnabled(true);
            return;
        }
    }

}
