package Controllers;

import Models.ReporteCriasEnfermasModel;
import Views.ReporteCriasEnfermasView;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carlos Contreras
 */
public class ReporteCriasEnfermasController implements ActionListener, ListSelectionListener {

    private ReporteCriasEnfermasModel reporteCriasEnfermasModel;
    private ReporteCriasEnfermasView reporteCriasEnfermasView;

    public ReporteCriasEnfermasController(ReporteCriasEnfermasModel reporteCriasEnfermasModel, ReporteCriasEnfermasView reporteCriasEnfermasView) {
        this.reporteCriasEnfermasModel = reporteCriasEnfermasModel;
        this.reporteCriasEnfermasView = reporteCriasEnfermasView;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return reporteCriasEnfermasModel.obtenerDatosTabla();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Nada...
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        // Nada...
    }

}
