package Views;

import Controllers.CorralesController;
import Controllers.ReporteCriasEnfermasController;
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
public class ReporteCriasEnfermasView extends JDialog {

    private ReporteCriasEnfermasController reporteCriasEnfermasController;

    private JTable tablaCriasEnfermas;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;

    public ReporteCriasEnfermasView() {
        setTitle("Reporte Crias Enfermas");
        setSize(1300, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 1250, 500);
        add(scrollPane);
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ReporteCriasEnfermasController reporteCriasEnfermasController) {
        this.reporteCriasEnfermasController = reporteCriasEnfermasController;
        generarTablaResultados();
        addListeners();
    }

    private void addListeners() {
        tablaCriasEnfermas.getSelectionModel().addListSelectionListener(reporteCriasEnfermasController);
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaCriasEnfermas = reporteCriasEnfermasController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "CriasID",
                "CorralID",
                "Peso",
                "GrasaCobertura",
                "TipoMusculo",
                "EstadoCria",
                "DietaID",
                "SensorID",
                "VecesEnTratamiento"
        ));
        tablaCriasEnfermas = new JTable(datosTablaCriasEnfermas, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCriasEnfermas);
    }

    public JTable getTablaCriasEnfermas() {
        return tablaCriasEnfermas;
    }

}
