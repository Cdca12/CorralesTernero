package Controllers;

import Models.ConsultarSensoresModel;
import Views.ConsultarSensoresView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarSensoresController implements ActionListener {
    
    private ConsultarSensoresModel consultarSensoresModel;
    private ConsultarSensoresView consultarSensoresView;

    public ConsultarSensoresController(ConsultarSensoresModel consultarSensoresModel, ConsultarSensoresView consultarSensoresView) {
        this.consultarSensoresView = consultarSensoresView;
        this.consultarSensoresModel = consultarSensoresModel;
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        return consultarSensoresModel.obtenerDatosTabla();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Nada aun
    }
    
}
