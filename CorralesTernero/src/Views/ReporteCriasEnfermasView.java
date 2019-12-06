package Views;

import Controllers.ReporteCriasEnfermasController;
import Utils.Status;
import java.awt.print.PrinterException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
    private JButton btnImprimir;

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
        scrollPane.setBounds(30, 60, 1250, 470);
        add(scrollPane);
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(getWidth() - 125, 15, 100, 30);
        add(btnImprimir);
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
        btnImprimir.addActionListener(reporteCriasEnfermasController);
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
                "DiasEdad"
        ));
        tablaCriasEnfermas = new JTable(datosTablaCriasEnfermas, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCriasEnfermas);
    }

    public JTable getTablaCriasEnfermas() {
        return tablaCriasEnfermas;
    }
    
    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void imprimirTabla() {
        try {
            if (tablaCriasEnfermas.print()) {
                JOptionPane.showMessageDialog(null, Status.OK_PRINT.TITLE, Status.OK_PRINT.MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, Status.ERROR_PRINT.TITLE, Status.ERROR_PRINT.MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

}
