package Views;

import Controllers.ConsultarSensoresController;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarSensoresView extends JDialog {

    private ConsultarSensoresController consultarSensoresController;

    private JTable tablaSensores;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;    

    public ConsultarSensoresView() {
        setTitle("Sensores");
        setSize(650, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }
    
    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 590, 250);
        add(scrollPane);
    }
    
    public void launchView() {
        setVisible(true);
    }

    public void setController(ConsultarSensoresController consultarSensoresController) {
        this.consultarSensoresController = consultarSensoresController;
        generarTablaResultados();
        addListeners();
    }
    
    private void addListeners() {
        // Nada aun
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaSensores = consultarSensoresController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("SensorID", "Marca", "CriaID"));
        tablaSensores = new JTable(datosTablaSensores, vectorNombreColumnas);
        scrollPane.setViewportView(tablaSensores);
    }
    
}
