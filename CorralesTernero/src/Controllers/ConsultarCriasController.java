package Controllers;

import DataAccesor.SQLConnectionHelper;
import Models.ConsultarCriasModel;
import Views.ConsultarCriasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCriasController implements ActionListener, ListSelectionListener {

    private ConsultarCriasModel consultarCriasModel;
    private ConsultarCriasView consultarCriasView;

    public ConsultarCriasController(ConsultarCriasModel consultarCriasModel, ConsultarCriasView consultarCriasView) {
        this.consultarCriasView = consultarCriasView;
        this.consultarCriasModel = consultarCriasModel;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return consultarCriasModel.obtenerDatosTabla();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        
    }

    
}
