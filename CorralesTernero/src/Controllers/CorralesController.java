package Controllers;

import Views.CorralesView;
import Models.CorralesModel;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class CorralesController {

    private CorralesModel corralesModel;
    private CorralesView corralesView;

    public CorralesController(CorralesModel corralesModel, CorralesView corralesView) {
        this.corralesView = corralesView;
        this.corralesModel = corralesModel;
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        return corralesModel.obtenerDatosTabla();
    }
}
