package Views;

import Controllers.ConsultarSensoresController;
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
public class ConsultarSensoresView extends JDialog {

    private ConsultarSensoresController consultarSensoresController;

    private JTable tablaSensores;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnImprimir;    

    public ConsultarSensoresView() {
        setTitle("Sensores");
        setSize(650, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }
    
    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 60, 590, 320);
        add(scrollPane);
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(getWidth() - 135, 15, 100, 30);
        add(btnImprimir);
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
        btnImprimir.addActionListener(consultarSensoresController);
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaSensores = consultarSensoresController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("SensorID", "Marca", "CriaID"));
        tablaSensores = new JTable(datosTablaSensores, vectorNombreColumnas);
        scrollPane.setViewportView(tablaSensores);
    }
    
    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void imprimirTabla() {
        try {
            if (tablaSensores.print()) {
                JOptionPane.showMessageDialog(null, Status.OK_PRINT.TITLE, Status.OK_PRINT.MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, Status.ERROR_PRINT.TITLE, Status.ERROR_PRINT.MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
