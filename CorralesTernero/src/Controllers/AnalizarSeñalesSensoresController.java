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
public class AnalizarSeñalesSensoresController implements ActionListener {

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
            return;
        }
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnAñadirCuarentena()) {
            
            return;
        }
    }
    
 

//    @Override
//    public void valueChanged(ListSelectionEvent lse) {
//        if (!lse.getValueIsAdjusting()) {
//            JTable tablaSensores = analizarSeñalesSensoresView.getTablaSeñalesSensores();
//            int selectedRow = tablaSensores.getSelectedRow();
//            String sensorID = tablaSensores.getValueAt(selectedRow, 1).toString();
//            analizarSeñalesSensoresModel.sensoresID.add(sensorID);
//        }
//    }

}
