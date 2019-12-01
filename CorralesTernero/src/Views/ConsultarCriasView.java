package Views;

import Controllers.ConsultarCriasController;
import Controllers.ConsultarCorralesController;
import Controllers.ConsultarDietasController;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCriasView extends JDialog {
    
private ConsultarCriasController consultarCriasController;

    private JTable tablaCrias;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;

    public ConsultarCriasView() {
        setTitle("Crias");
        setSize(1400, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();

    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 1350, 500);
        add(scrollPane);
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ConsultarCriasController consultarCriasController) {
        this.consultarCriasController = consultarCriasController;
        generarTablaResultados();
        addListeners();

    }

    private void addListeners() {
            
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaCrias = consultarCriasController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "CriaID",
                "CorralID",
                "Peso",
                "Grasa",
                "GrasaCobertura",
                "TipoMusculo",
                "SensorID",
                "DietaID",
                "EstadoCria",
                "DiasEdad"
        ));
        tablaCrias = new JTable(datosTablaCrias, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCrias);
    }

}
