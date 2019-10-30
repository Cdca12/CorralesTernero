package Controllers;

import Models.DietasModel;
import Views.DietasView;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class DietasController {

    private DietasModel dietasModel;
    private DietasView dietasView;

    public DietasController(DietasModel dietasModel, DietasView dietasView) {
        this.dietasView = dietasView;
        this.dietasModel = dietasModel;
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        return dietasModel.obtenerDatosTabla();
    }

}
