package Controllers;

import Models.AnalizarSeñalesSensoresModel;
import Views.AnalizarSeñalesSensoresView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == analizarSeñalesSensoresView.getBtnAñadirCuarentena()) {
            // TODO: Añadir Cuarentena
            return;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
//        if (lse.getSource() == analizarSeñalesSensoresView.getTablaSeñalesSensores().getSelectionModel()) {
//            
//            analizarSeñalesSensoresModel.criasID.add("Añadir Cria");
//            return;
//        }
    }

}
